package Servlets;

import JavaFiles.Reservatie;
import JavaFiles.Voorstelling;
import Repositories.VoorstellingenRepository;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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

@WebServlet(urlPatterns = {"/overzicht"})

public class OverzichtServlet extends HttpServlet implements Serializable {

    private static final long serialVersionUID = 1L;
    private final transient VoorstellingenRepository voorstellingenRepository = new VoorstellingenRepository();

    @Resource(name = VoorstellingenRepository.JNDI_NAME)
    void setDataSource(DataSource dataSource) {
        voorstellingenRepository.setDataSource(dataSource);
    }
 
    private static final String VIEW = "SubmitServlet";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(VIEW).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        @SuppressWarnings("unchecked")
        TreeMap<String, Reservatie> mandje = (TreeMap) session.getAttribute("mandje");
        Integer personalId = (Integer) session.getAttribute("personalid");

        List<Reservatie> gelukt = new ArrayList<>();
        List<Reservatie> mislukt = new ArrayList<>();

        for (Map.Entry<String, Reservatie> entry : mandje.entrySet()) {

            if (voorstellingenRepository.AddReservatie(entry.getKey(), entry.getValue().getAantalTickets(), personalId)) {
                gelukt.add(entry.getValue());

            } else {
                Voorstelling v = voorstellingenRepository.findOne(entry.getKey());
                int a = entry.getValue().getAantalTickets();
                Reservatie r = new Reservatie(v, a);
                mislukt.add(r);

            }

        }

        session.removeAttribute("mandje");
        session.setAttribute("gelukt", gelukt);
        session.setAttribute("mislukt", mislukt);
        response.sendRedirect(VIEW);
    }

}
