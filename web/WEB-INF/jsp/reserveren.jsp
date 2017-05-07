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
        <c:import url='/WEB-INF/jsp/menu2.jsp'/>
  
  <form method="post">
  Voorstelling:<br>
  <b>${onevrstl.titel}</b><br><br>
  Uitvoerders:<br>
  <b>${onevrstl.uitvoerders}</b><br><br>
  Datum::<br>
  <fmt:formatDate value="${onevrstl.date}" var="formattedDate" 
                                pattern="dd-MM-yyyy HH:mm" />
  <b>${formattedDate}</b><br><br>
  Prijs:<br>
  <b>${onevrstl.prijs}</b><br><br>
  Vrije Plaatsen:<br>
  <b>${onevrstl.vrijePlaatsen}</b><br><br>
  Plaatsen:<br>
  <input type="text" name="aantal" value="${tickets}" size="3" type=number"><br><br>
  <input type="submit" value="Reserveren">
  </form> <br>
<c:forEach var="type" items="${fouten}">
   ${type.value}
</c:forEach>
    </body>
</html>
