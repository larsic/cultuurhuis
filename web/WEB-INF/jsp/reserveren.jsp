<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang='nl'>
<head>

    <c:import url='/WEB-INF/jsp/head.jsp'>
        <c:param name='title' value='Het CultuurHuis: reserveren'/> 
        <c:param name='image' value='images/reserveren.png'/> 

    </c:import>
</head>
<body>
    
    <h2>
        <h2>${id}</h2>
        <a href="<c:url value='/index.htm'/>">Voorstellingen</a>
        <h3>Voorstelling:</h3><br>
        <h3><b>${onevrstl.titel}</b></h3><br><br>
        <h3>Uitvoerders:</h3><br>
        <h3><b>${onevrstl.uitvoerders}</b></h3><br><br>
        <h3>Datum:</h3><br>
        <fmt:formatDate value="${onevrstl.date}" var="formattedDate" 
                                pattern="dd-MM-yyyy HH:mm" />
        <h3><b>${formattedDate}</b></h3><br><br>
        <h3>Prijs:</h3><br>
        <h3><b>${onevrstl.prijs}</b></h3><br><br>
        <h3>Vrije Plaatsen:</h3><br>
        <h3><b>${onevrstl.vrijePlaatsen}</b></h3><br><br>
        
    </body>
</html>
