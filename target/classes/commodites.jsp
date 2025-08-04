
<%@ page import="java.util.List" %>
<%@ page import="tp.objets.Commodite" %>
<%@ page import="com.example.tp.AubergeHelper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html>
<jsp:include page="head.jsp" />

<body>
<div class="container">

    <jsp:include page="navigation.jsp"/>

    <div class="container mt-4">

        <h1>Commodités</h1>

        <button type="button" class="btn btn-success mb-3" data-bs-toggle="modal" data-bs-target="#addCommoditeModal">
            Ajouter une commodité
        </button>


        <h2>Liste des Commodités</h2>
        <%
            List<Commodite> commodites = AubergeHelper.gestionAubergInnInterro(session).getGestionCommodite().getTouteLesCommodites();
            if (commodites != null && !commodites.isEmpty()) {
        %>

        <div class="table-responsive">

            <table class="table table-striped table-hover">
                <thead class="table-dark">

                <tr>
                    <th>ID</th>
                    <th>Description</th>
                    <th>Prix Surplus</th>
                    <th>Actions</th>
                </tr>

                </thead>
                <tbody>
                <%
                    for (Commodite commodite : commodites) {
                %>
                <tr>
                    <td><%= commodite.getIdCommodite() %></td>
                    <td><%= commodite.getDescription() %></td>
                    <td><%= commodite.getSurplusPrix() %> $</td>

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
        <p>Aucune commodité enregistrée.</p>
        <%
            }
        %>

        <!-- Modal to add a new commodite -->
        <div class="modal fade" id="addCommoditeModal" tabindex="-1" aria-labelledby="addCommoditeModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addCommoditeModalLabel">Ajouter une nouvelle commodité</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="CommoditeAdd" method="post">
                            <div>
                                <label for="idCommodite" class="form-label">ID Commodité:</label>
                                <input type="text" class="form-control" id="idCommodite" name="idCommodite" required>
                            </div>
                            <div class="mb-3">
                                <label for="description" class="form-label">Description</label>
                                <input type="text" class="form-control" id="description" name="description" required>
                            </div>
                            <div class="mb-3">
                                <label for="surplusPrix" class="form-label">Prix Surplus</label>
                                <input type="number" class="form-control" id="surplusPrix" name="surplusPrix" step="0.01" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Ajouter</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="footer.jsp" />
    <jsp:include page="alertMessages.jsp" />
</div>

</body>
</html>
