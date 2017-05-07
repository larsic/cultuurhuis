<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang='nl'>
    <head>
        <c:import url='/WEB-INF/jsp/head.jsp'>
            <c:param name='title' value='Het CultuurHuis: overzicht'/> 
            <c:param name='image' value='images/bevestig.png'/> 
        </c:import>
    </head>
    <body>
        <c:import url='/WEB-INF/jsp/menu2.jsp'/>
        <h2>Gelukte Reserveringen</h2>
        <table>
            <tr><th>Datum </th><th>Titel</th><th>Uitvoerders</th><th>Prijs</th><th>Plaatsen</th></tr>
            <c:forEach var='res' items='${gelukt}'>
                <fmt:formatDate value="${res.voorstelling.date}" var="formattedDate" 
                                pattern="dd-MM-yyyy HH:mm" />
            <tr><td>${formattedDate}</td>
                <td>${res.voorstelling.titel}</td>
                <td>${res.voorstelling.uitvoerders}</td>
                <td>${res.voorstelling.prijs}</td>
                <td align="right">${res.aantalTickets}</td>
            </tr>
                </c:forEach> 
        </table>
        <h2>Mislukte Reserveringen</h2>
        <table>
            <tr><th>Datum </th><th>Titel</th><th>Uitvoerders</th><th>Prijs</th><th>Plaatsen</th><th>Vrije Plaatsen</th></tr>
            <c:forEach var='res2' items='${mislukt}'>
                <fmt:formatDate value="${res2.voorstelling.date}" var="formattedDate" 
                                pattern="dd-MM-yyyy HH:mm" />
            <tr><td>${formattedDate}</td>
                <td>${res2.voorstelling.titel}</td>
                <td>${res2.voorstelling.uitvoerders}</td>
                <td>${res2.voorstelling.prijs}</td>
                <td align="right">${res2.aantalTickets}</td>
                <td align="right">${res2.voorstelling.vrijePlaatsen}</td>
            </tr>
                </c:forEach> 
        </table>
    </body>
</html>