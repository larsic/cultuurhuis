package Servlets;

import Repositories.VoorstellingenRepository;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet(urlPatterns = {"/bevestiging"})

public class BevestigingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final transient VoorstellingenRepository voorstellingenRepository = new VoorstellingenRepository();

    @Resource(name = VoorstellingenRepository.JNDI_NAME)
    void setDataSource(DataSource dataSource) {
        voorstellingenRepository.setDataSource(dataSource);
    }
    private static final String VIEW = "WEB-INF/jsp/bevestiging.jsp";
    private static final String REDIRECT_URL = "/index.htm";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setAttribute("hidebevestigen", "disabled");
        request.getRequestDispatcher(VIEW).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String user = (String) request.getParameter("gebruikersnaam");
        String pwd = (String) request.getParameter("paswoord");
    
        String message;

        if (voorstellingenRepository.checkUserAndPassword(user, pwd)) {

            message = voorstellingenRepository.naamEnAdres(user);
            request.setAttribute("hidegebruikersnaam", "disabled");
            request.setAttribute("hidepaswoord", "disabled");
            request.setAttribute("hidezoekmeop", "disabled");
            request.setAttribute("hidenieuweklant", "disabled");
            
            HttpSession session = request.getSession();
            @SuppressWarnings("unchecked")
            Integer personalid = voorstellingenRepository.findPID(user);
            session.setAttribute("personalid", personalid);
            request.setAttribute("test", personalid);
        } else {
            message = "Verkeerde gebruikersnaam of paswoord.";
            request.setAttribute("hidebevestigen", "disabled");
        }
        request.setAttribute("message", message);
        request.getRequestDispatcher(VIEW).forward(request, response);
    }

}
