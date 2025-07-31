<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 30/07/2025
  Time: 22:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="tp.objets.Chambre" %>
<%@ page import="com.example.tp.AubergeHelper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<jsp:include page="/includes/head.jsp" />
<body>
<div class="container">
    <jsp:include page="/includes/navigation.jsp" />

    <h1>Liste des Chambres</h1>

    <button class="btn btn-success mb-3" data-bs-toggle="modal" data-bs-target="#addChambreModal">
        Ajouter une chambre
    </button>

    <%
        List<Chambre> chambres = AubergeHelper.gestionAubergInnInterro(session).getGestionChambre().getAllChambres();
        if (chambres != null && !chambres.isEmpty()) {
    %>

    <div class="table-responsive">
        <table class="table table-bordered table-hover">
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
                <td><%= chambre.getNom_chambre() %></td>
                <td><%= chambre.getType_lit() %></td>
                -- a verifier
                <td><%= chambre.getPrixTotal() %> $</td>
                <td>
                    <jsp:include page="chambreView.jsp">
                        <jsp:param name="idChambre" value="<%= chambre.getIdChambre() %>" />
                    </jsp:include>
                    <jsp:include page="chambreEdit.jsp">
                        <jsp:param name="idChambre" value="<%= chambre.getIdChambre() %>" />
                    </jsp:include>
                    <jsp:include page="chambreDelete.jsp">
                        <jsp:param name="idChambre" value="<%= chambre.getIdChambre() %>" />
                    </jsp:include>
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
    <p>Aucune chambre trouvée.</p>
    <%
        }
    %>

    <jsp:include page="chambreAdd.jsp" />
    <jsp:include page="/includes/footer.jsp" />
    <jsp:include page="/includes/alertMessages.jsp" />
</div>
</body>
</html>

