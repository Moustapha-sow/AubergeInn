<%--
  Created by IntelliJ IDEA.
  User: zouba
  Date: 2024-08-01
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- Modal Ajouter Chambre -->
<div class="modal fade" id="addChambreModal" tabindex="-1" aria-labelledby="addChambreLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addChambreLabel">Ajouter une chambre</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="ChambreAdd" method="POST">
                <div class="modal-body">
                    <div class="mb-3">
                        <label for="idChambre" class="form-label">ID Chambre:</label>
                        <input type="text" class="form-control" id="idChambre" name="idChambre" required>
                    </div>
                    <div class="mb-3">
                        <label for="nomChambre" class="form-label">Nom Chambre:</label>
                        <input type="text" class="form-control" id="nomChambre" name="nomChambre" required>
                    </div>
                    <div class="mb-3">
                        <label for="typeLit" class="form-label">Type de Lit:</label>
                        <input type="text" class="form-control" id="typeLit" name="typeLit" required>
                    </div>
                    <div class="mb-3">
                        <label for="prixBase" class="form-label">Prix de Base:</label>
                        <input type="number" step="0.01" class="form-control" id="prixBase" name="prixBase" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Ajouter Chambre</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                </div>
            </form>
        </div>
    </div>
</div>

