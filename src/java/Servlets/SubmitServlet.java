package Servlets;

import Repositories.VoorstellingenRepository;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet(urlPatterns = {"/SubmitServlet"})

public class SubmitServlet extends HttpServlet implements Serializable {

    private static final long serialVersionUID = 1L;
    private final transient VoorstellingenRepository voorstellingenRepository = new VoorstellingenRepository();

    @Resource(name = VoorstellingenRepository.JNDI_NAME)
    void setDataSource(DataSource dataSource) {
        voorstellingenRepository.setDataSource(dataSource);
    }
    private static final String VIEW = "WEB-INF/jsp/overzicht.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        @SuppressWarnings("unchecked")
        ArrayList gelukt = (ArrayList) session.getAttribute("gelukt");
        ArrayList mislukt = (ArrayList) session.getAttribute("mislukt");

        request.setAttribute("gelukt", gelukt);
        request.setAttribute("mislukt", mislukt);

        request.getRequestDispatcher(VIEW).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
