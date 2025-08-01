<%--
  Created by IntelliJ IDEA.
  User: zouba
  Date: 2024-08-01
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="AubergeInn.objets.Chambre" %>
<%@ page import="AubergeInn.objets.Commodite" %>
<%@ page import="com.example.tp.AubergeHelper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" />
<body>
<div class="container">
    <jsp:include page="navigation.jsp"/>

    <h1>Chambres</h1>

    <button type="button" class="btn btn-success mb-3" data-bs-toggle="modal" data-bs-target="#addChambreModal">
        Ajouter une chambre
    </button>

    <h2>Liste des Chambres</h2>
    <%
        List<Chambre> chambres = AubergeHelper.gestionAubergInnInterro(session).getGestionChambre().getAllChambres();
        if (chambres != null && !chambres.isEmpty()) {
    %>
    <div class="table-responsive">
        <table class="table table-bordered table-striped table-hover">
            <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Type de lit</th>
                <th>Prix</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (Chambre chambre : chambres) {
            %>
            <tr>
                <td><%= chambre.getIdChambre() %></td>
                <td><%= chambre.getNomChambre() %></td>
                <td><%= chambre.getTypeLit() %></td>
                <td><%= chambre.getPrixBase() %> $</td>
                <td>
                    <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#viewChambreModal<%= chambre.getIdChambre() %>">
                        Voir
                    </button>
                    <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#editChambreModal<%= chambre.getIdChambre() %>">
                        Modifier
                    </button>
                    <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteChambreModal<%= chambre.getIdChambre() %>">
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
    <p>Il n'y a aucune chambre d'enregistrée.</p>
    <%
        }
    %>

    <!-- Modals pour chaque chambre -->
    <%
        for (Chambre chambre : chambres) {
    %>
    <!-- Modal Supprimer Chambre -->
    <div class="modal fade" id="deleteChambreModal<%= chambre.getIdChambre() %>" tabindex="-1" aria-labelledby="deleteChambreModalLabel<%= chambre.getIdChambre() %>" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteChambreModalLabel<%= chambre.getIdChambre() %>">Confirmer la suppression</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    Êtes-vous sûr de vouloir supprimer la chambre <strong><%= chambre.getNomChambre() %></strong> ?
                </div>
                <div class="modal-footer">
                    <form action="ChambreSupp" method="post" style="display:inline;">
                        <input type="hidden" name="idChambre" value="<%= chambre.getIdChambre() %>">
                        <button type="submit" class="btn btn-danger">Supprimer</button>
                    </form>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal Voir Chambre -->
    <div class="modal fade" id="viewChambreModal<%= chambre.getIdChambre() %>" tabindex="-1" aria-labelledby="viewChambreLabel<%= chambre.getIdChambre() %>" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="viewChambreLabel<%= chambre.getIdChambre() %>">Détails de la Chambre</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p><strong>ID:</strong> <%= chambre.getIdChambre() %></p>
                    <p><strong>Nom:</strong> <%= chambre.getNomChambre() %></p>
                    <p><strong>Type de lit:</strong> <%= chambre.getTypeLit() %></p>
                    <p><strong>Prix:</strong> <%= chambre.getPrixBase() %> $</p>
                    <h5>Commodités</h5>
                    <ul>
                        <%
                            List<Commodite> commodites = AubergeHelper.gestionAubergInnInterro(session).getGestionChambre().getListeCommoditesChambre(chambre.getIdChambre());
                            if (commodites != null && !commodites.isEmpty()) {
                                for (Commodite commodite : commodites) {
                        %>
                        <li><%= commodite.getDescription() %> (Surplus: <%= commodite.getSurplusPrix() %> $)</li>
                        <%
                            }
                        } else {
                        %>
                        <li>Aucune commodité.</li>
                        <%
                            }
                        %>
                    </ul>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fermer</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal Modifier Chambre -->
    <div class="modal fade" id="editChambreModal<%= chambre.getIdChambre() %>" tabindex="-1" aria-labelledby="editChambreModalLabel<%= chambre.getIdChambre() %>" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editChambreModalLabel<%= chambre.getIdChambre() %>">Modifier les commodités de la Chambre</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="ChambreModif" method="post">
                    <div class="modal-body">
                        <input type="hidden" name="idChambre" value="<%= chambre.getIdChambre() %>">
                        <h5>Commodités disponibles</h5>
                        <ul>
                            <%
                                List<Commodite> toutesCommodites = AubergeHelper.gestionAubergInnInterro(session).getGestionCommodite().getTouteLesCommodites();
                                List<Commodite> commoditesChambre = AubergeHelper.gestionAubergInnInterro(session).getGestionChambre().getListeCommoditesChambre(chambre.getIdChambre());
                                for (Commodite commodite : toutesCommodites) {
                                    boolean isChecked = commoditesChambre.stream().anyMatch(c -> c.getIdCommodite() == commodite.getIdCommodite());
                            %>
                            <li>
                                <label>
                                    <input type="checkbox" name="commodites" value="<%= commodite.getIdCommodite() %>" <%= isChecked ? "checked" : "" %>>
                                    <%= commodite.getDescription() %> (Surplus: <%= commodite.getSurplusPrix() %> $)
                                </label>
                            </li>
                            <%
                                }
                            %>
                        </ul>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-warning">Enregistrer</button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <% } %>

    <!-- Modal pour ajouter une chambre -->
    <jsp:include page="chambreAdd.jsp"/>

    <jsp:include page="footer.jsp" />
    <jsp:include page="alertMessages.jsp" />
</div>
</body>
</html>
