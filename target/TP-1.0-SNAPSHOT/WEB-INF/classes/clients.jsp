<%--
  Created by IntelliJ IDEA.
  User: zouba
  Date: 2024-08-01
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="tp.objets.Client" %>
<%@ page import="tp.objets.Reservation" %>
<%@ page import="com.example.tp.AubergeHelper" %>
<%@ page import="tp.gestion.GestionChambre" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" />
<body>
<div class="container">
    <jsp:include page="navigation.jsp"/>
    <h1>Clients</h1>
    <!-- Bouton pour ouvrir le modal d'ajout de client -->
    <button type="button" class="btn btn-success mb-3" data-bs-toggle="modal" data-bs-target="#clientAddModal">
        Ajouter Client
    </button>

    <h2>Liste des Clients</h2>
    <%
        List<Client> clients = AubergeHelper.gestionAubergInnInterro(session).getGestionClient().afficherTousClient();
        GestionChambre gestionChambre = AubergeHelper.gestionAubergInnInterro(session).getGestionChambre();
        if (clients != null && !clients.isEmpty()) {
    %>
    <div class="table-responsive">
        <table class="table table-bordered table-striped table-hover">
            <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Prénom</th>
                <th>Nom</th>
                <th>Âge</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (Client client : clients) {
            %>
            <tr>
                <td><%= client.getIdClient() %></td>
                <td><%= client.getPrenom() %></td>
                <td><%= client.getNom() %></td>
                <td><%= client.getAge() %></td>
                <td>
                    <!-- Bouton pour voir les détails du client -->
                    <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#showClientModal<%= client.getIdClient() %>">
                        Info
                    </button>
                    <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#reserverModal<%= client.getIdClient() %>">
                        Ajouter Réservation
                    </button>
                    <!-- Bouton pour ouvrir le modal de suppression -->
                    <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteClientModal<%= client.getIdClient() %>">
                        Supprimer
                    </button>
                </td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
    <%
    } else {
    %>
    <p>Il n'y a aucun client d'enregistré.</p>
    <%
        }
    %>


    <!-- Inclure le modal d'ajout de client -->
    <jsp:include page="clientAdd.jsp"/>



<%
    for (Client client : clients) {
%>
    <!-- Modals pour afficher les details d'un client -->
    <div class="modal fade" id="showClientModal<%= client.getIdClient() %>" tabindex="-1" aria-labelledby="showClientModalLabel<%= client.getIdClient() %>" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="showClientModalLabel<%= client.getIdClient() %>">Détails du client</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="modal-body">
                    <table class="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nom</th>
                            <th>Prénom</th>
                            <th>Âge</th>
                        </tr>
                        </thead>
                        <tbody>
                        <!-- Première ligne avec les informations du client -->
                        <tr>
                            <td><%= client.getIdClient() %></td>
                            <td><%= client.getNom() %></td>
                            <td><%= client.getPrenom() %></td>
                            <td><%= client.getAge() %></td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <h3>Réservations: </h3>
                                <!-- Sous-table pour les réservations du client -->
                                <table class="table table-striped mb-0">
                                    <thead>
                                    <tr>
                                        <th>Nom de la Chambre</th>
                                        <th>Date Début</th>
                                        <th>Date Fin</th>
                                        <th>Prix Total ($)</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <%
                                        List<Reservation> reservations = AubergeHelper.gestionAubergInnInterro(session).getGestionReservation().getReservationsClient(client.getIdClient());
                                        if (reservations != null && !reservations.isEmpty()) {
                                            for (Reservation reservation : reservations) {
                                    %>
                                    <tr>
                                        <td><%= gestionChambre.getChambre(reservation.getIdChambre()).getNomChambre() %></td>
                                        <td><%= reservation.getDateDebut() %></td>
                                        <td><%= reservation.getDateFin() %></td>
                                        <td><%= reservation.getPrixTotal() %> $</td>
                                    </tr>
                                    <%
                                        }
                                    } else {
                                    %>
                                    <tr>
                                        <td colspan="4" class="text-center">Aucune réservation.</td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>


                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fermer</button>
                </div>
            </div>
        </div>
    </div>


    <!-- Modal Bootstrap pour la confirmation de suppression -->
    <div class="modal fade" id="deleteClientModal<%= client.getIdClient() %>" tabindex="-1" aria-labelledby="deleteClientModalLabel<%= client.getIdClient() %>" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteClientModalLabel<%= client.getIdClient() %>">Confirmation de suppression</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    Voudriez-vous supprimer le client ID: <%= client.getIdClient() %>?
                </div>
                <div class="modal-footer">

                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>


                    <form action="ClientSupp" method="post" style="display:inline;">
                        <input type="hidden" name="idClient" value="<%= client.getIdClient() %>">
                        <button type="submit" class="btn btn-danger">Supprimer</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <%--Modals pour faire la reservation pour un client--%>
    <div class="modal fade" id="reserverModal<%= client.getIdClient() %>" tabindex="-1" aria-labelledby="reserverModalLabel<%= client.getIdClient() %>" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="reserverModalLabel<%= client.getIdClient() %>">Réserver pour <%= client.getPrenom() %> <%= client.getNom() %></h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="FaireUneReservation" method="post">
                    <div class="modal-body">
                        <input type="hidden" name="idClient" value="<%= client.getIdClient() %>">

                        <div class="mb-3">
                            <label for="dateDebut<%= client.getIdClient() %>" class="form-label">Date de début</label>
                            <input type="date" name="dateDebut" id="dateDebut<%= client.getIdClient() %>" class="form-control" required
                                   onchange="updateMinDate('dateDebut<%= client.getIdClient() %>', 'dateFin<%= client.getIdClient() %>')">
                        </div>

                        <div class="mb-3">
                            <label for="dateFin<%= client.getIdClient() %>" class="form-label">Date de fin</label>
                            <input type="date" name="dateFin" id="dateFin<%= client.getIdClient() %>" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label for="idChambre<%= client.getIdClient() %>" class="form-label">Chambre</label>
                            <select name="idChambre" id="idChambre<%= client.getIdClient() %>" class="form-select" required>
                                <!-- Options seront ajoutées dynamiquement ici -->
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                        <button type="submit" class="btn btn-primary">Réserver</button>
                    </div>
                </form>
            </div>
        </div>
    </div>



</div>

<script>

    function updateMinDate(startDateId, endDateId) {
        var startDate = document.getElementById(startDateId).value;
        var endDateInput = document.getElementById(endDateId);
        endDateInput.min = startDate;
    }

        function updateAvailableRooms(clientId) {
        const dateDebut = document.getElementById('dateDebut' + clientId).value;
        const dateFin = document.getElementById('dateFin' + clientId).value;

        if (dateDebut && dateFin) {
        const xhr = new XMLHttpRequest();
        xhr.open("GET", "AvailableRoomsServlet?dateDebut="+dateDebut+"&dateFin="+dateFin, true);
        xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
        const chambres = JSON.parse(xhr.responseText);
        const roomSelect = document.getElementById('idChambre' + clientId);
        roomSelect.innerHTML = "";

        chambres.forEach(function(chambre) {
        const option = document.createElement("option");
        option.value = chambre.idChambre;
        option.textContent = chambre.nomChambre;
        roomSelect.appendChild(option);

    });
    } else if (xhr.readyState === 4 && xhr.status !== 200) {
        alert("Erreur lors de la récupération des chambres disponibles.");
    }
    };
        xhr.send();
    }
    }

        document.getElementById("dateDebut<%= client.getIdClient() %>").addEventListener('change', function() {
        updateAvailableRooms('<%= client.getIdClient() %>');
    });

        document.getElementById("dateFin<%= client.getIdClient() %>").addEventListener('change', function() {
        updateAvailableRooms('<%= client.getIdClient() %>');
    });


</script>
<% } %>
<jsp:include page="footer.jsp" />
<jsp:include page="alertMessages.jsp" />
</body>
</html>

