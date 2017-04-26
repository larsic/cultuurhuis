/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;


import JavaFiles.Reservatie;
import Repositories.VoorstellingenRepository;
import com.mysql.jdbc.StringUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet(urlPatterns = {"/reserveren"})


public class ReserverenServlet extends HttpServlet {
    
    

    private static final long serialVersionUID = 1L;
    private final transient VoorstellingenRepository voorstellingenRepository = new VoorstellingenRepository();
    
    @Resource(name = VoorstellingenRepository.JNDI_NAME)
    void setDataSource(DataSource dataSource) {
        voorstellingenRepository.setDataSource(dataSource);
    }    
    private static final String VIEW = "WEB-INF/jsp/reserveren.jsp";
    
    private static final String SUCCESS_VIEW ="WEB-INF/jsp/boeken.jsp";
    
    private int max;
    private String id2;
    
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        id2 = request.getParameter("id");
        
    
        request.setAttribute("id2", id2);
        request.setAttribute("onevrstl", voorstellingenRepository.findOne(id2));
        
        max = voorstellingenRepository.findOne(id2).getVrijePlaatsen();
        
        request.getRequestDispatcher(VIEW).forward(request, response);

    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Map<String, String> fouten = new HashMap<>();
        
        String aantalString = request.getParameter("aantal");
        int aantalInt = Integer.parseInt(aantalString);
        
        BigDecimal aantal = null;
        if (StringUtils.isStrictlyNumeric(aantalString)) {
            aantal = new BigDecimal(aantalString);
            if (aantalInt<=0 || aantalInt > max) {
                int maxy = max+1;
                fouten.put("aantal", " kies een aantal tickets tussen 0 en " + maxy);
            }
        } else {
            fouten.put("aantal", "tik een getal");
        }
        if (fouten.isEmpty()) {
            
            
            Reservatie reservatie = new Reservatie(voorstellingenRepository.findOne(id2), max);
            request.setAttribute("reservatie", reservatie);
            request.getRequestDispatcher(SUCCESS_VIEW).forward(request, response);
            
        } else {
            request.setAttribute("fouten", fouten);
            request.setAttribute("onevrstl", voorstellingenRepository.findOne(id2));
            request.getRequestDispatcher(VIEW).forward(request, response);
            
        }
    }
}
