<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang='nl'>
<head>

    <c:import url='/WEB-INF/jsp/head.jsp'>
        <c:param name='title' value='Het CultuurHuis: mandje'/> 
        <c:param name='image' value='images/mandje.png'/> 

    </c:import>
</head>
<body>
    
    <h2>
        <h2>${id}</h2>
        <a href="<c:url value='/index.htm'/>">Voorstellingen</a>
        <c:if test="${not empty sessionScope.mandje}">

<a href="<c:url value='/voorstellingen?id=Familievoorstelling'/>">Bevestiging reservatie</a>    
        </c:if><br><br>
        <table>
            <tr><th>Datum </th><th>Titel</th><th>Uitvoerders</th><th>Prijs</th><th>Plaatsen</th><th><input type="submit" value="Verwijderen"> </th></tr>
        
            <c:forEach var='a' items='${mandje}'>
        
                <fmt:formatDate value="${a.value.voorstelling.date}" var="formattedDate" 
                                pattern="dd-MM-yyyy HH:mm" />
        
                
        
            
            <tr><td>${formattedDate}</td>
                <td>${a.value.voorstelling.titel}</td>
                <td>${a.value.voorstelling.uitvoerders}</td>
                <td>${a.value.voorstelling.prijs}</td>
                <td align="right">${a.value.aantalTickets}</td>
                <td align="center"><input type="radio">
                </td>
            </tr>
            
                </c:forEach> 
        </table>
        Te betalen: ${totalPrice}
        
    </body>
</html>
