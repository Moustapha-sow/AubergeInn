package com.example.tp;

import main.AubergeInnException;
import tp.gestion.GestionClient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.example.tp.AubergeHelper.*;

//servlet class ClientSupp

public class ClientSupp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet Client Supprimer : GET");

        if (estConnecter(request, response)) {

            Dispatch(AubergeConstantes.CLIENTS, request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet Client Supprimer : POST");

        if (estConnecter(request, response)) {
            try {

                HttpSession session = request.getSession();

                String idClient = request.getParameter("idClient");

                request.setAttribute("idClient", idClient);

                if (champVide(idClient)) {

                    throw new AubergeInnException("Vous devez entrer un idClient");
                }


                if (!estEntier(idClient)) {
                    throw new AubergeInnException("L'id Client doit être un entier.");
                }


                GestionClient gestionClient = AubergeHelper.gestionAubergInnInterro(session).getGestionClient();
                if (gestionClient == null) {
                    throw new AubergeInnException("Session invalide. Veuillez vous reconnecter.");
                }


                gestionClient.supprimerClient(toInt(idClient));

                // Ajouter un message de succès
                List<String> listeMessageSuccess = new LinkedList<>();

                listeMessageSuccess.add("Le client " + idClient + " a été supprimé avec succès");

                request.setAttribute("listeMessageSuccess", listeMessageSuccess);

                Dispatch(AubergeConstantes.CLIENTS, request, response);

            } catch (Exception e) {

                List<String> listeMessageErreur = new LinkedList<>();
                listeMessageErreur.add(e.getMessage());

                request.setAttribute("listeMessageErreur", listeMessageErreur);

                doGet(request, response);
            }
        }
    }
}
