package Servlets;

import JavaFiles.Reservatie;
import Repositories.VoorstellingenRepository;
import com.mysql.jdbc.StringUtils;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet(urlPatterns = {"/reserveren"})

public class ReserverenServlet extends HttpServlet implements Serializable {

    private static final long serialVersionUID = 1L;
    private final transient VoorstellingenRepository voorstellingenRepository = new VoorstellingenRepository();

    @Resource(name = VoorstellingenRepository.JNDI_NAME)
    void setDataSource(DataSource dataSource) {
        voorstellingenRepository.setDataSource(dataSource);
    }
    private static final String VIEW = "WEB-INF/jsp/reserveren.jsp";
    private static final String SUCCESS_VIEW = "WEB-INF/jsp/boeken.jsp";
    private static final String MANDJE = "mandje";

    private int max;
    private String id2;
    double totalPrice = 0;
    private int numberOfTickets;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        id2 = request.getParameter("id");

        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        TreeMap mandje2 = (TreeMap) session.getAttribute(MANDJE);

        if (mandje2 != null) {

            Reservatie res = (Reservatie) mandje2.get(id2);
            if (res != null) {
                numberOfTickets = res.getAantalTickets();
            } else {
                numberOfTickets = 0;
            }

        } else {
            numberOfTickets = 0;
        }

        max = voorstellingenRepository.findOne(id2).getVrijePlaatsen();

        request.setAttribute("tickets", numberOfTickets);
        request.setAttribute("id2", id2);
        request.setAttribute("onevrstl", voorstellingenRepository.findOne(id2));

        request.getRequestDispatcher(VIEW).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, String> fouten = new HashMap<>();
        int aantalInt = 0;
        String aantalString = request.getParameter("aantal");
        if (aantalString.isEmpty()) {
            fouten.put("leeg", "tik een getal");
        } else {
            aantalInt = Integer.parseInt(aantalString);

            BigDecimal aantal = null;
            if (StringUtils.isStrictlyNumeric(aantalString)) {
                aantal = new BigDecimal(aantalString);
                if (aantalInt <= 0 || aantalInt > max) {
                    int maxy = max + 1;
                    fouten.put("aantal", " kies een aantal tickets tussen 0 en " + maxy);
                }
            }
        }

        if (fouten.isEmpty()) {

            HttpSession session = request.getSession();
            @SuppressWarnings("unchecked")
            TreeMap mandje = (TreeMap) session.getAttribute(MANDJE);

            if (mandje == null) {
                mandje = new TreeMap();
                totalPrice = 0;
            } else {
                totalPrice = (double) session.getAttribute("totalPrice");
            }

            Reservatie reservatie = new Reservatie(voorstellingenRepository.findOne(id2), aantalInt);
            mandje.put(id2, reservatie);
            totalPrice += (Double.parseDouble(reservatie.getVoorstelling().getPrijs()) * reservatie.getAantalTickets());
            session.setAttribute(MANDJE, mandje);
            session.setAttribute("totalPrice", totalPrice);
            request.getRequestDispatcher(SUCCESS_VIEW).forward(request, response);

        } else {
            request.setAttribute("fouten", fouten);
            request.setAttribute("onevrstl", voorstellingenRepository.findOne(id2));
            request.getRequestDispatcher(VIEW).forward(request, response);

        }
    }
}
