<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Auberg-Inn Hotel Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa;
        }
        header {
            background-color: #007bff;
            color: white;
            padding: 50px 0;
            margin-bottom: 30px;
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
            fill: #007bff;
        }
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }
        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Auberg-Inn</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">About</a>
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
        <p class="lead">Accédez facilement aux différentes sections de gestion de l'auberge.</p>
    </header>

    <!-- Main Menu -->
    <div class="row text-center">
        <div class="col-md-4 mb-4">
            <div class="card h-100">
                <div class="card-body">
                    <div class="icon mb-3">
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
        <div class="col-md-4 mb-4">
            <div class="card h-100">
                <div class="card-body">
                    <div class="icon mb-3">
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

        <div class="col-md-4 mb-4">
            <div class="card h-100">
                <div class="card-body">
                    <div class="icon mb-3">
                        <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-gear" viewBox="0 0 16 16">
                            <path d="M8 1a2 2 0 0 0-2 2v1.1a5.904 5.904 0 0 0-2.3 1.4L2 4.2A2 2 0 1 0 .8 5.8l1.3 1.7A5.904 5.904 0 0 0 1.1 10H0a2 2 0 1 0 0 4h1.1a5.904 5.904 0 0 0 1.4 2.3L1.8 14A2 2 0 1 0 4.2 15l1.7 1.3A5.904 5.904 0 0 0 10 14.9V16a2 2 0 1 0 4 0v-1.1a5.904 5.904 0 0 0 2.3-1.4L14 11.8A2 2 0 1 0 15 10l1.3-1.7A5.904 5.904 0 0 0 16 5.1V4a2 2 0 1 0-4 0v1.1a5.904 5.904 0 0 0-1.4 2.3L11.8 2a2 2 0 1 0-1.4-3.6L8 1.1a5.904 5.904 0 0 0-2.3-1.4L4 0a2 2 0 1 0-1.1.4L1.8 1.7A5.904 5.904 0 0 0 .9 3H0a2 2 0 1 0 0 4h1.1A5.904 5.904 0 0 0 1.4 9.3L.7 7.8A2 2 0 1 0 2.2 6.2l1.7 1.3a5.904 5.904 0 0 0 2.3-1.4V2a2 2 0 1 0-4 0v1.1zM8 6a2 2 0 1 1-2 2 2 2 0 0 1 2-2z"/>
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
