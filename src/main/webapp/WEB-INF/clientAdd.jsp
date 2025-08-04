<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal fade" id="clientAddModal" tabindex="-1" aria-labelledby="clientAddModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="clientAddModalLabel">Ajouter un client</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body">
                <form action="ClientAdd" method="POST">
                    <div class="mb-3">

                        <label for="idClient" class="form-label">ID Client:</label>
                        <input type="text" class="form-control" id="idClient" name="idClient" required>
                    </div>


                    <div class="mb-3">
                        <label for="prenom" class="form-label">Prénom:</label>
                        <input type="text" class="form-control" id="prenom" name="prenom" required>
                    </div>
                    <div class="mb-3">
                        <label for="nom" class="form-label">Nom:</label>
                        <input type="text" class="form-control" id="nom" name="nom" required>
                    </div>
                    <div class="mb-3">

                        <label for="age" class="form-label">Âge:</label>
                        <input type="number" class="form-control" id="age" name="age" required>
                    </div>

                    <button type="submit" class="btn btn-primary">Ajouter Client</button>
                </form>
            </div>
        </div>
    </div>
</div>
