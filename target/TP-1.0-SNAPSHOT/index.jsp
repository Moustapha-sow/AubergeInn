<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--<%--%>
<%--  if(getServletContext().getAttribute("serveur") != null)--%>
<%--  {%>--%>
<%--<jsp:forward page="/WEB-INF/login.jsp" />--%>
<%--<%}--%>
<%--%>--%>
<!DOCTYPE html>
<html>
<head>




  <title>IFT287 - Système de gestion de aubergeInn</title>

  <meta name="description" content="Page d'accueil du projet AubergeInn">

  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

  <style>
    body {
    <h2 style="color: red;">TEST DE COULEUR</h2>

    background-color: #f5f5dc !important; /* beige clair */
    }

    h1 {
      color: #5c4033 !important; /* brun foncé */
      margin-top: 30px !important;
    }

    .form-control {
      background-color: #fffaf0 !important; /* blanc cassé */
      border: 1px solid #ccc !important;
    }

    .btn-primary {
      background-color: #228B22 !important; /* vert forêt */
      border-color: #228B22 !important;
    }

    .btn-primary:hover {
      background-color: #1e7e1e !important;
      border-color: #1c751c !important;
    }

    .container {
      padding-top: 40px !important;
      padding-bottom: 40px !important;
      border-radius: 10px !important;
    }
  </style>
</head>
<body>
<div class="container">
  <h1 class="text-center">Système de gestion d' AUBERGEINN</h1>
  <div class="col-md-4 offset-md-4">
    <form action="Login" method="POST">
      <div class="form-group">
        <label for="userIdBD">Nom d'utilisateur de la base de donnée</label>
        <input class="form-control" type="TEXT" name="userIdBD" value="<%= (request.getAttribute("userIdBD") != null ? (String)request.getAttribute("userIdBD") : "") %>" placeholder="ift287_XX">
      </div>
      <div class="form-group">
        <label for="motDePasseBD">Mot de passe</label>
        <input class="form-control" type="PASSWORD" name="motDePasseBD" value="<%= (request.getAttribute("motDePasseBD") != null ? (String)request.getAttribute("motDePasseBD") : "") %>">
      </div>
      <div class="form-group">
        <label for="serveur">Serveur</label>
        <select class="custom-select" name="serveur">
          <option value="local" <%= (request.getAttribute("serveur") != null ? (((String)request.getAttribute("serveur")).equals("local") ? "selected" : "") : "") %>>local
          <option value="dinf" <%= (request.getAttribute("serveur") != null ? (((String)request.getAttribute("serveur")).equals("dinf") ? "selected" : "") : "selected") %>>dinf
        </select>
      </div>
      <div class="form-group">
        <label for="bd">Nom de la base de donnée</label>
        <input class="form-control" type="TEXT" name="bd" value="<%= (request.getAttribute("bd") != null ? (String)request.getAttribute("bd") : "") %>" placeholder="ift287_XXdb">
      </div>
      <input class="btn btn-primary" type="SUBMIT" name="inscrire" value="connexion BD">
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
