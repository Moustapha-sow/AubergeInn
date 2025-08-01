<%--
  Created by IntelliJ IDEA.
  User: zouba
  Date: 2024-08-01
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.*,java.text.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>IFT287 - Système de gestion de aubergeInn</title>
    <meta name="author" content="Vincent Ducharme">
    <meta name="description" content="Page d'accueil du système de gestion de la bilbiothèque.">

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

</head>
<body>
<div class="container">
    <h1 class="text-center">Système de gestion de la aubergeInn</h1>
    <div class="col-md-4 offset-md-4">
        <form action="Accueil" method="POST">
            <div class="form-group">
                <label for="userIdBD">Nom d'utilisateur</label>
                <input id="userIdBD" class="form-control" type="TEXT" name="userId" value="<%= (request.getAttribute("userId") != null ? (String)request.getAttribute("userId") : "admin") %>">
            </div>
            <div class="form-group">
                <label for="motDePasseBD">Mot de passe</label>
                <input id="motDePasseBD" class="form-control" type="PASSWORD" name="motDePasse" value="<%= (request.getAttribute("motDePasse") != null ? (String)request.getAttribute("motDePasse") : "admin") %>">
            </div>
            <div class="row">
                <div class="col-md-6">
                    <input class="btn btn-primary" type="SUBMIT" name="connecter" value="Se connecter">
                </div>
            </div>
        </form>
    </div>
</div>
<br>
<%-- inclusion d'une autre page pour l'affichage des messages d'erreur--%>
<jsp:include page="/WEB-INF/messageErreur.jsp" />
<br>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>

