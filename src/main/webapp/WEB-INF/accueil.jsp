<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Auberg-Inn Hotel Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f5f5dc; /* beige doux */
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }


        header {
            background-color: #228B22; /* vert forêt */
            color: white;
            padding: 50px 0;
            margin-bottom: 30px;
            border-radius: 0 0 20px 20px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.2);

        }

        .navbar {
            background-color: #ffffff;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1);

            border-radius: 0 0 10px 10px;
        }

        .navbar-brand {
            font-weight: bold;

            color: #228B22;
        }

        .nav-link {
            color: #555;
        }


        .nav-link.active {
            color: #228B22;
            font-weight: bold;
        }

        .card {
            background-color: #ffffff;
            border-radius: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            transition: transform 0.2s ease-in-out, box-shadow 0.2s ease-in-out;
        }


        .card:hover {
            transform: translateY(-10px);
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.4);
        }

        .icon svg {
            fill: #228B22;
        }

        .btn-primary {
            background-color: #228B22;
            border-color: #228B22;
        }


        .btn-primary:hover {
            background-color: #1e7e1e;
            border-color: #1c751c;
        }
    </style>
</head>

<body>
<div class="container">
    <!-- Barre de Navigation -->
    <nav class="navbar navbar-expand-lg">
        <a class="navbar-brand" href="#">Auberg-Inn</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" href="#">Accueil</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">À propos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Contact</a>
                </li>
            </ul>
        </div>
    </nav>

    <!-- Main Header -->
    <header class="text-center">
        <h1 class="display-4">Page Principale de Gestion de l'Auberge</h1>
        <p class="lead">Accédez aux différentes sections ci-dessous.</p>
    </header>

    <!-- Main Menu -->
    <div class="row text-center">
        <!-- Clients -->
        <div class="col-md-4 mb-4">
            <div class="card h-100">
                <div class="card-body">
                    <div class="icon mb-3">
                        <!-- SVG icon -->
                        <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                            <path d="M11 8a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                            <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 1 0 0 14A7 7 0 0 0 8 1z"/>
                        </svg>
                    </div>

                    <h5 class="card-title">Gestion des Clients</h5>
                    <p class="card-text">Consultez et gérez les informations des clients.</p>
                    <a href="Clients" class="btn btn-primary">Accéder</a>

                </div>
            </div>
        </div>

        <!-- Chambres -->
        <div class="col-md-4 mb-4">
            <div class="card h-100">
                <div class="card-body">
                    <div class="icon mb-3">
                        <!-- SVG icon -->
                        <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-bed" viewBox="0 0 16 16">
                            <path d="M12 5a3 3 0 0 1 3 3v3h1v2h-1v1H1v-1H0v-2h1V8a3 3 0 0 1 3-3h8zM2 12h12v-4a2 2 0 0 0-2-2H4a2 2 0 0 0-2 2v4zm3-3.5a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0zm8 0a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0z"/>
                        </svg>
                    </div>
                    <h5 class="card-title">Gestion des Chambres</h5>
                    <p class="card-text">Accédez à la gestion des chambres de l'auberge.</p>
                    <a href="Chambres" class="btn btn-primary">Accéder</a>
                </div>
            </div>
        </div>

        <!-- Commodités -->
        <div class="col-md-4 mb-4">
            <div class="card h-100">
                <div class="card-body">
                    <div class="icon mb-3">
                        <!-- SVG icon -->
                        <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-gear" viewBox="0 0 16 16">
                            <path d="M8 6a2 2 0 1 1-2 2 2 2 0 0 1 2-2z"/>
                            <path d="M9.243 1.757a1 1 0 0 1 1.414 0l.707.707a1 1 0 0 1 0 1.414L10.657 4.5a6.978 6.978 0 0 1 1.414 1.414l1.622-.707a1 1 0 0 1 1.414 0l.707.707a1 1 0 0 1 0 1.414l-.707.707a6.978 6.978 0 0 1 0 2.828l.707.707a1 1 0 0 1 0 1.414l-.707.707a1 1 0 0 1-1.414 0l-1.622-.707a6.978 6.978 0 0 1-1.414 1.414l.707 1.622a1 1 0 0 1 0 1.414l-.707.707a1 1 0 0 1-1.414 0l-.707-.707a6.978 6.978 0 0 1-2.828 0l-.707.707a1 1 0 0 1-1.414 0l-.707-.707a1 1 0 0 1 0-1.414l.707-1.622a6.978 6.978 0 0 1-1.414-1.414l-1.622.707a1 1 0 0 1-1.414 0l-.707-.707a1 1 0 0 1 0-1.414l.707-.707a6.978 6.978 0 0 1 0-2.828l-.707-.707a1 1 0 0 1 0-1.414l.707-.707a1 1 0 0 1 1.414 0l1.622.707a6.978 6.978 0 0 1 1.414-1.414L5.343 4.5a1 1 0 0 1 0-1.414l.707-.707a1 1 0 0 1 1.414 0z"/>
                        </svg>
                    </div>
                    <h5 class="card-title">Gestion des Commodités</h5>
                    <p class="card-text">Ajoutez ou modifiez les commodités disponibles.</p>
                    <a href="Commodites" class="btn btn-primary">Accéder</a>
                </div>
            </div>
        </div>

    </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
