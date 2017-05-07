package Servlets;

import JavaFiles.Reservatie;
import java.io.IOException;
import java.io.Serializable;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/boeken"})
public class BoekenServlet extends HttpServlet implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String VIEW = "WEB-INF/jsp/boeken.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        TreeMap mandje = (TreeMap) session.getAttribute("mandje");

        session.setAttribute("mandje", mandje);
        request.getRequestDispatcher(VIEW).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        TreeMap mandje = (TreeMap) session.getAttribute("mandje");
        double totalPrice = (double) session.getAttribute("totalPrice");

        String type = request.getParameter("id");
        Reservatie reservatie = (Reservatie) mandje.get(type);

        totalPrice -= (Double.parseDouble(reservatie.getVoorstelling().getPrijs()) * reservatie.getAantalTickets());
        mandje.remove(type);

        session.setAttribute("totalPrice", totalPrice);
        session.setAttribute("mandje", mandje);
        if (mandje.isEmpty()) {
            request.setAttribute("hideverwijderen", "disabled");
        }

        request.getRequestDispatcher(VIEW).forward(request, response);
    }
}
