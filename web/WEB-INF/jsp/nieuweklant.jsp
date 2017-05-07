<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
    <head>
        <c:import url='/WEB-INF/jsp/head.jsp'>
            <c:param name='title' value='Het CultuurHuis: nieuwe klant'/> 
            <c:param name='image' value='images/nieuweklant.png'/> 
        </c:import>
    </head>
    <body>
        <c:import url='/WEB-INF/jsp/menu2.jsp'/>
            <form method="post">
                Voornaam:<br>
                <input type="text" name="voornaam"><br><br>
                Familienaam:<br>
                <input type="text" name="familienaam"><br><br>
                Straat:<br>
                <input type="text" name="straat"><br><br>
                HuisNr:<br>
                <input type="text" name="huisnr"><br><br>
                Postcode:<br>
                <input type="text" name="postcode"><br><br>
                Gemeente:<br>
                <input type="text" name="gemeente"><br><br>
                Gebruikersnaam:<br>
                <input type="text" name="user"><br><br>
                Paswoord:<br>
                <input type="text" name="pwd"><br><br>
                Herhaal Paswoord:<br>
                <input type="text" name="checkpwd"><br><br>                           
                <input type="submit" value="OK" action="<c:url value='/nieuweklant'/>"><br><br>
            </form>
            <ul>
        <c:forEach items="${fouten}" var="item">
            <li>${item.value}</li>
        </c:forEach> 
            </ul>
    </body>
</html>