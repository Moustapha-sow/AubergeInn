
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
