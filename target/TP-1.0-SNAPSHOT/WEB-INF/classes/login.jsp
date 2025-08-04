<%@ page import="java.util.*,java.text.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>IFT287 - Système de gestion de aubergeInn</title>
    <meta name="author" content="Vincent Ducharme">
    <meta name="description" content="Page d'accueil du système de gestion de l'aubergeInn.">

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

    <!-- Style personnalisé -->
    <style>
        body {
            background-color: #f5f5dc; /* beige doux */
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        h1 {
            margin-top: 40px;
            margin-bottom: 30px;
            color: #2f4f4f;
        }

        .form-container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        .form-group label {
            font-weight: bold;
            color: #333;
        }

        .btn-primary {
            background-color: #228B22 !important; /* vert forêt */
            border-color: #228B22 !important;
        }

        .btn-primary:hover {
            background-color: #1e7e1e !important;
            border-color: #1c751c !important;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="text-center">Système de gestion de la aubergeInn</h1>
    <div class="row justify-content-center">
        <div class="col-md-6 form-container">
            <form action="Accueil" method="POST">
                <div class="form-group">
                    <label for="userIdBD">Nom d'utilisateur</label>
                    <input id="userIdBD" class="form-control" type="text" name="userId"
                           value="<%= (request.getAttribute("userId") != null ? (String)request.getAttribute("userId") : "admin") %>">
                </div>
                <div class="form-group">
                    <label for="motDePasseBD">Mot de passe</label>
                    <input id="motDePasseBD" class="form-control" type="password" name="motDePasse"
                           value="<%= (request.getAttribute("motDePasse") != null ? (String)request.getAttribute("motDePasse") : "admin") %>">
                </div>
                <div class="text-center">
                    <input class="btn btn-primary" type="submit" name="connecter" value="Se connecter">
                </div>
            </form>
        </div>
    </div>
</div>

<br>
<jsp:include page="/WEB-INF/messageErreur.jsp" />
<br>

<!-- Scripts Bootstrap -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>
