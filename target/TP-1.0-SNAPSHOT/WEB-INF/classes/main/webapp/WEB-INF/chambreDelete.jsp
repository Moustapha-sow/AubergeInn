<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 30/07/2025
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- Modal : Supprimer une chambre -->
<div class="modal fade" id="deleteChambreModal${chambre.idChambre}" tabindex="-1" aria-labelledby="deleteChambreLabel${chambre.idChambre}" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- En-tête -->
            <div class="modal-header">
                <h5 class="modal-title" id="deleteChambreLabel${chambre.idChambre}">Suppression de la chambre</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Fermer"></button>
            </div>

            <!-- Corps -->
            <div class="modal-body">
                <p>Confirmez-vous la suppression de la chambre <strong>${chambre.nomChambre}</strong> (ID : ${chambre.idChambre}) ?</p>
            </div>

            <!-- Pied de page -->
            <div class="modal-footer">
                <form action="ChambreDelete" method="post">
                    <input type="hidden" name="id_chambre" value="${chambre.idChambre}">
                    <button type="submit" class="btn btn-danger">Supprimer</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                </form>
            </div>

        </div>
    </div>
</div>

