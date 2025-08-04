
<%@ page import="java.util.List" %>
<%@ page import="tp.objets.Client" %>
<%@ page import="tp.objets.Chambre" %>
<%@ page import="tp.objets.Reservation" %>
<%@ page import="com.example.tp.AubergeHelper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <title>Réservations - AubergeInn</title>
    <meta charset="UTF-8">

</head>
<body>

<div class="container">

</div>
<jsp:include page="navigation.jsp"/>

<h1>Réservations</h1>


<h2>Liste des réservations</h2>
<%

    List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservationsAll");

    if (reservations != null && !reservations.isEmpty()) {
%>
<table>
    <tr>

        <th>Client</th>
        <th>Chambre</th>
        <th>Prix total</th>
        <th>Date de début</th>
        <th>Date de fin</th>
    </tr>


    <% for (Reservation reservation : reservations) { %>
    <tr>
        <td><%= reservation.getClient().getPrenom() %> <%= reservation.getClient().getNom() %></td>
        <td><%= reservation.getChambre().getNomChambre() %></td>
        <td><%= reservation.getPrixTotal() %></td>
        <td><%= reservation.getDateDebut() %></td>
        <td><%= reservation.getDateFin() %></td>
    </tr>
    <% } %>

</table>
<% } else { %>

<p>Il n'y a aucune réservation d'enregistrée.</p>

<% } %>

<h2>Chambres libres</h2>
<%
    List<Chambre> chambresLibres = (List<Chambre>) request.getAttribute("chambresLibres");

    if (chambresLibres != null && !chambresLibres.isEmpty()) {
%>
<table>
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Type de lit</th>
        <th>Prix</th>
    </tr>

    <% for (Chambre chambre : chambresLibres) { %>
    <tr>
        <td><%= chambre.getIdChambre() %></td>
        <td><%= chambre.getNomChambre() %></td>
        <td><%= chambre.getTypeLit() %></td>
        <td><%= chambre.getprixBase() %></td>
    </tr>
    <% } %>

</table>
<% } else { %>

<p>Il n'y a aucune chambre de disponible en ce moment.</p>

<% } %>

<h2>Réserver</h2>
<%
    List<Client> clients = (List<Client>) request.getAttribute("clientsAll");
    List<Chambre> chambres = (List<Chambre>) request.getAttribute("chambresAll");

    if (clients != null && chambres != null && !clients.isEmpty() && !chambres.isEmpty()) {
%>
<form action="Reservations" method="POST">

    <div>
        <label for="idClient">Client</label>
        <select name="idClient" id="idClient">
            <% for (Client client : clients) { %>
            <option value="<%= client.getIdClient() %>"><%= client.getPrenom() %> <%= client.getNom() %></option>
            <% } %>
        </select>
    </div>

    <div>
        <label for="idChambre">Chambre</label>
        <select name="idChambre" id="idChambre">
            <% for (Chambre chambre : chambres) { %>
            <option value="<%= chambre.getIdChambre() %>"><%= chambre.getNomChambre() %></option>
            <% } %>
        </select>
    </div>

    <div>
        <label for="dateDebut">Date de début</label>
        <input type="date" name="dateDebut" required>
    </div>

    <div>
        <label for="dateFin">Date de fin</label>
        <input type="date" name="dateFin" required>
    </div>

    <div>
        <input type="submit" name="reserver" value="Réserver">
    </div>

</form>

<% } else { %>

<p>Il est impossible de réserver pour le moment, veuillez ajouter des clients ou des chambres avant de poursuivre.</p>

<% } %>

<jsp:include page="messageErreur.jsp"/>
</body>
</html>
