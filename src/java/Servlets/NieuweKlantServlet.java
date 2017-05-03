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

@WebServlet(urlPatterns = {"/nieuweklant"})

public class NieuweKlantServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final transient VoorstellingenRepository voorstellingenRepository = new VoorstellingenRepository();

    @Resource(name = VoorstellingenRepository.JNDI_NAME)
    void setDataSource(DataSource dataSource) {
        voorstellingenRepository.setDataSource(dataSource);
    }
    private static final String VIEW = "WEB-INF/jsp/nieuweklant.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        request.getRequestDispatcher(VIEW).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> fouten = new HashMap<>();
        String voornaam = request.getParameter("voornaam");
        if (voornaam.isEmpty()) {
        fouten.put("voornaam", "voornaam niet ingevuld");
        }
        String familienaam = request.getParameter("familienaam");
        if (familienaam.isEmpty()) {
        fouten.put("familienaam", "familienaam niet ingevuld");
        }
        String straat = request.getParameter("straat");
        if (straat.isEmpty()) {
        fouten.put("straat", "straat niet ingevuld");
        }
        String huisnr = request.getParameter("huisnr");
        if (huisnr.isEmpty()) {
        fouten.put("huisnr", "huisnr niet ingevuld");
        }
        String postcode = request.getParameter("postcode");
        if (postcode.isEmpty()) {
        fouten.put("postcode", "postcode niet ingevuld");
        }
        String gemeente = request.getParameter("gemeente");
        if (gemeente.isEmpty()) {
        fouten.put("gemeente", "gemeente niet ingevuld");
        }
        String user = request.getParameter("user");
        if (user.isEmpty()) {
        fouten.put("user", "gebruikersnaam niet ingevuld");
        }
        String pwd = request.getParameter("pwd");
        if (pwd.isEmpty()) {
        fouten.put("pwd", "paswoord niet ingevuld");
        }
        String checkpwd = request.getParameter("checkpwd");
        if (checkpwd.isEmpty()) {
        fouten.put("checkpwd", "herhaal paswoord niet ingevuld");
        }
        if(!pwd.equals(checkpwd)){
            fouten.put("passwords", "paswoorden zijn verschillend");
        }
        if(!fouten.isEmpty()){
           request.setAttribute("fouten", fouten); 
        }

        request.getRequestDispatcher(VIEW).forward(request, response);
    }

}
