<%@ page import="java.util.List" %><%--


--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Affichage des messages d'alerte -->
<%
    List<String> listeMessageSuccess = (List<String>) request.getAttribute("listeMessageSuccess");
    List<String> listeMessageErreur = (List<String>) request.getAttribute("listeMessageErreur");

    if (listeMessageSuccess != null) {

        for (String message : listeMessageSuccess) {
%>
<div class="alert alert-success alert-dismissible fade show" role="alert">

    <%= message %>
    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
<%
        }

    }

    if (listeMessageErreur != null) {
        for (String message : listeMessageErreur) {
%>


<div class="alert alert-danger alert-dismissible fade show" role="alert">
    <%= message %>

    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>

<%
        }
    }
%>
