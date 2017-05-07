<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<header>
<nav>
<a href="<c:url value='/index.htm'/>">Voorstellingen</a>
<c:if test="${not empty sessionScope.mandje}">
<a href="<c:url value='/boeken'/>">Reservatiemandje</a>
<a href="<c:url value='/bevestiging'/>">Bevestiging reservatie</a>    
</c:if>
<br><br>
</nav>
</header>