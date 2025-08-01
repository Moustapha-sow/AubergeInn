<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 30/07/2025
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<jsp:include page="/header.jsp"/>
<body>
<jsp:include page="/navigation.jsp"/>

<h1>Suppression d’un client</h1>

<form action="ClientDelete" method="post">
    <div>
        <label for="idClient">Identifiant du client :</label>
        <input type="number" id="idClient" name="idClient" min="1" required>
    </div>

    <div>
        <button type="submit" name="supprimerClient">Supprimer le client</button>
    </div>
</form>

<jsp:include page="/messageErreur.jsp"/>
</body>
</html>

