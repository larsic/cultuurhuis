<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>

<!doctype html>
<html lang='nl'>
    <head>
        <c:import url='/WEB-INF/jsp/head.jsp'>
            <c:param name='title' value='Het CultuurHuis: bevestiging reservaties'/> 
            <c:param name='image' value='images/bevestig.png'/> 
        </c:import>
    </head>
    <body>
        <c:import url='/WEB-INF/jsp/menu2.jsp'/>
        <form method="post">
            <h2>Stap 1: Wie ben je ?</h2>
            Gebruikernaam<br>
            <input type="text" name="gebruikersnaam" ${hidegebruikersnaam}><br><br>
            Paswoord<br>
            <input type="text" name="paswoord" ${hidepaswoord}><br><br>
            <input type="submit" value="Zoek me op" action="<c:url value='/bevestiging'/>" ${hidezoekmeop}><br><br>
            <input type="submit" value="Ik ben nieuw"  formmethod="get" formaction="<c:url value='/nieuweklant'/>" ${hidenieuweklant}><br><br>
            ${message}
        </form>
        <form method="post" action="<c:url value='/overzicht'/>">
            <h2>Stap 2: Bevestigen</h2>
            <input type="submit" value="Bevestigen"  ${hidebevestigen}>
        </form>
    </body>
</html>

