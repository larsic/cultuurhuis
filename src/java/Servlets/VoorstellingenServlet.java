/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;


import Repositories.VoorstellingenRepository;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet(urlPatterns = {"/voorstellingen"})


public class VoorstellingenServlet extends HttpServlet {
    
    

    private static final long serialVersionUID = 1L;
    private final transient VoorstellingenRepository voorstellingenRepository = new VoorstellingenRepository();
    
    @Resource(name = VoorstellingenRepository.JNDI_NAME)
    void setDataSource(DataSource dataSource) {
        voorstellingenRepository.setDataSource(dataSource);
    }    
    private static final String VIEW = "WEB-INF/jsp/voorstellingen.jsp";
    
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        String id = request.getParameter("id");
    
        request.setAttribute("id", id);
        request.setAttribute("lijstje", voorstellingenRepository.findAll(id));
        
        request.getRequestDispatcher(VIEW).forward(request, response);

    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}