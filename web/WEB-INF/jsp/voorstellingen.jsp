<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang='nl'>
<head>

    <c:import url='/WEB-INF/jsp/head.jsp'>
        <c:param name='title' value='Het CultuurHuis: voorstellingen'/> 
        <c:param name='image' value='images/voorstellingen.png'/> 

    </c:import>
</head>
<body>
    <c:import url='/WEB-INF/jsp/menu.jsp'/>
    <h2>
        <h2>${id}</h2>
        <table>
            <tr><th>Datum </th><th>Titel</th><th>Uitvoerders</th><th>Prijs</th><th>Vrije Plaatsen</th><th> Reserveren </th></tr>
            <c:forEach var='vrstl' items='${lijstje}'>
                <fmt:formatDate value="${vrstl.date}" var="formattedDate" 
                                pattern="dd-MM-yyyy HH:mm" />
            <tr><td>${formattedDate}</td>
                <td>${vrstl.titel}</td>
                <td>${vrstl.uitvoerders}</td>
                <td>${vrstl.prijs}</td>
                <td align="right">${vrstl.vrijePlaatsen}</td>
                <td><c:if test="${vrstl.vrijePlaatsen>0}"><a href="">reserveren</a></c:if>
                </td>
            </tr>
                </c:forEach> 
        </table>
    </ul></body>
</html>
