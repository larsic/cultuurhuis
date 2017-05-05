/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import JavaFiles.NieuweKlant;
import JavaFiles.Reservatie;
import Repositories.VoorstellingenRepository;
import com.mysql.jdbc.StringUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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

public class OverzichtServlet extends HttpServlet implements Serializable{

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

        
        request.getRequestDispatcher(VIEW).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            HttpSession session = request.getSession(false);
            @SuppressWarnings("unchecked")
            TreeMap<String, Reservatie> mandje = (TreeMap) session.getAttribute("mandje");
            Integer personalId = (Integer) session.getAttribute("personalid");
            
            
            List<Reservatie> gelukt = new ArrayList<Reservatie>();
            List<Reservatie> mislukt = new ArrayList<Reservatie>();
            
            
            for(Map.Entry<String, Reservatie> entry : mandje.entrySet()){
                
                if(voorstellingenRepository.AddReservatie(entry.getKey(), entry.getValue().getAantalTickets(), personalId))
                {
                   gelukt.add(entry.getValue());
                   mandje.remove(entry.getKey());
               } else {
                   mislukt.add(entry.getValue());
                   mandje.remove(entry.getKey());
               }
                
            }
   
            request.setAttribute("gelukt", gelukt);
            request.setAttribute("mislukt", mislukt);
            request.getRequestDispatcher(VIEW).forward(request, response);
            
        

        
    }

}
