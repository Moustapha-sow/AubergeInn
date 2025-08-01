<%--
  Created by IntelliJ IDEA.
  User: zouba
  Date: 2024-08-01
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp"/>
<body>
<jsp:include page="navigation.jsp"/>

<h1>Ajouter une commodité</h1>

<form action="CommoditeAdd" method="POST">
    <div>
        <label for="idCommodite">ID Commodité:</label>
        <input type="text" id="idCommodite" name="idCommodite" required>
    </div>
    <div>
        <label for="description">Description:</label>
        <input type="text" id="description" name="description" required>
    </div>
    <div>
        <label for="surplusPrix">Prix Surplus:</label>
        <input type="number" step="0.01" id="surplusPrix" name="surplusPrix" required>
    </div>
    <div>
        <button type="submit" name="ajouter">Ajouter Commodité</button>
    </div>
</form>

<%-- Affichage des messages d'erreur et de succès --%>
<% List<String> listeMessageErreur = (List<String>) request.getAttribute("listeMessageErreur");
    List<String> listeMessageSuccess = (List<String>) request.getAttribute("listeMessageSuccess");
%>

<% if (listeMessageErreur != null && !listeMessageErreur.isEmpty()) { %>
<div style="color: red;">
    <ul>
        <% for (String message : listeMessageErreur) { %>
        <li><%= message %></li>
        <% } %>
    </ul>
</div>
<% } %>

<% if (listeMessageSuccess != null && !listeMessageSuccess.isEmpty()) { %>
<div style="color: green;">
    <ul>
        <% for (String message : listeMessageSuccess) { %>
        <li><%= message %></li>
        <% } %>
    </ul>
</div>
<% } %>

<a href="Commodites">Retour à la liste des commodités</a>
</body>
</html>
