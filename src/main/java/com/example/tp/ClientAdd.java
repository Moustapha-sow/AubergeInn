package com.example.tp;

import main.AubergeInnException;
import tp.gestion.GestionClient;

import static com.example.tp.AubergeHelper.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ClientAdd extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet Client Add : GET");
        if (estConnecter(request, response)) {
            Dispatch(AubergeConstantes.CLIENTS, request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet Client Add : POST");
        try {
            HttpSession session = request.getSession();


                // lecture des paramètres du formulaire d'ajout d'un client
                String idClient = request.getParameter("idClient");
                String prenom = request.getParameter("prenom");
                String nom = request.getParameter("nom");
                String age = request.getParameter("age");

                request.setAttribute("idClient", idClient);
                request.setAttribute("prenom", prenom);
                request.setAttribute("nom", nom);
                request.setAttribute("age", age);

                if (champVide(idClient)) {
                    throw new AubergeInnException("Vous devez entrer un idClient");
                }

                if (!estEntier(idClient)) {
                    throw new AubergeInnException("L'id Client doit être un entier.");
                }
                if (champVide(nom)) {
                    throw new AubergeInnException("Vous devez entrer un nom");
                }
                if (champVide(prenom)) {
                    throw new AubergeInnException("Vous devez entrer un prénom");
                }

                if (champVide(age)) {
                    throw new AubergeInnException("Vous devez entrer un âge");
                }

                GestionClient gestionClient = AubergeHelper.gestionAubergInnInterro(session).getGestionClient();
                gestionClient.ajouterClient(toInt(idClient), prenom, nom, toInt(age));

                List<String> listeMessageSuccess = new LinkedList<>();
                listeMessageSuccess.add("Client " + idClient + " ajouté");
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
