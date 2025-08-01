<%--
  Created by IntelliJ IDEA.
  User: zouba
  Date: 2024-08-01
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- messageErreur.jsp --%>
<%
    List<String> listeMessageErreur = (List<String>) request.getAttribute("listeMessageErreur");
    List<String> listeMessageSuccess = (List<String>) request.getAttribute("listeMessageSuccess");

    if (listeMessageErreur != null && !listeMessageErreur.isEmpty()) {
%>
<div class="alert alert-danger">
    <ul>
        <% for (String messageErreur : listeMessageErreur) { %>
        <li><%= messageErreur %></li>
        <% } %>
    </ul>
</div>
<%
    }

    if (listeMessageSuccess != null && !listeMessageSuccess.isEmpty()) {
%>
<div class="alert alert-success">
    <ul>
        <% for (String messageSuccess : listeMessageSuccess) { %>
        <li><%= messageSuccess %></li>
        <% } %>
    </ul>
</div>
<%
    }
%>
