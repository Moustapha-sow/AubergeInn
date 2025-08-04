<%--
  Created by IntelliJ IDEA.
  User: zouba
  Date: 2025-07-25
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html>
<head>
    <title>Gérer les Commodités</title>
</head>
<body>
<jsp:include page="navigation.jsp"/>


<h1>Gérer les Commodités</h1>

<h2>Inclure une Commodité</h2>
<form action="GererCommodite" method="post">
    <input type="hidden" name="action" value="inclure">
    <div>
        <label for="idChambre">ID Chambre:</label>
        <input type="text" id="idChambre" name="idChambre" required>
    </div>
    <div>
        <label for="idCommodite">ID Commodité:</label>

        <input type="text" id="idCommodite" name="idCommodite" required>
    </div>

    <button type="submit">Inclure Commodité</button>
</form>


<h2>Enlever une Commodité</h2>
<form action="GererCommodite" method="post">
    <input type="hidden" name="action" value="enlever">

    <div>
        <label for="idChambre">ID Chambre:</label>

        <input type="text" id="idChambre" name="idChambre" required>
    </div>
    <div>

        <label for="idCommodite">ID Commodité:</label>
        <input type="text" id="idCommodite" name="idCommodite" required>
    </div>

    <button type="submit">Enlever Commodité</button>
</form>


<jsp:include page="messageErreur.jsp"/>
</body>
</html>

