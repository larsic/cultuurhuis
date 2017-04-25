<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<header>
    <h2>Genres</h2><br>
<nav>
<a href="<c:url value='/voorstellingen?id=Circustheater'/>">CircusTheater</a>
<a href="<c:url value='/voorstellingen?id=Dans'/>">Dans</a>
<a href="<c:url value='/voorstellingen?id=Familievoorstelling'/>">FamilieVoorstelling</a>
<a href="<c:url value='/voorstellingen?id=Humor'/>">Humor</a>
<a href="<c:url value='/voorstellingen?id=Modern klassiek'/>">Modern Klassiek</a>
<a href="<c:url value='/voorstellingen?id=Multimedia'/>">MultiMedia</a>
<a href="<c:url value='/voorstellingen?id=Muziek'/>">Muziek</a>
<a href="<c:url value='/voorstellingen?id=Muziektheater'/>">MuziekTheater</a>
<a href="<c:url value='/voorstellingen?id=Theater'/>">Theater</a>
<a href="<c:url value='/voorstellingen?id=Wereldmuziek'/>">WereldMuziek</a>
</nav>
</header>
