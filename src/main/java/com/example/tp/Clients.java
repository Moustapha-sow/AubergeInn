package com.example.tp;

import main.AubergeInnException;
import tp.gestion.GestionClient;
import tp.gestion.GestionReservation;

import static com.example.tp.AubergeHelper.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class Clients extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet Client : GET");
        if (estConnecter(request, response)) {
            System.out.println("Servlet Client : GET dispatch vers Clients.jsp");
            Dispatch(AubergeConstantes.CLIENTS, request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet Client : POST");
        if (estConnecter(request, response)) {
            System.out.println("Servlet Client : POST dispatch vers Clients.jsp");
            try {
                HttpSession session = request.getSession();
                String idClient = request.getParameter("idClient");
                request.setAttribute("idClient", idClient);

                if (champVide(idClient)) {
                    throw new AubergeInnException("ID client est vide");
                }

                GestionClient gestionClient = AubergeHelper.gestionAubergInnInterro(session).getGestionClient();
                GestionReservation gestionReservation  =  AubergeHelper.gestionAubergInnInterro(session).getGestionReservation();

                // Ajouter les informations du client et les réservations à la requête
                request.setAttribute("client", gestionClient.getClient(toInt(idClient)));
                request.setAttribute("reservation", gestionReservation.getReservationsClient(toInt(idClient)));
            } catch (Exception e) {
                List<String> listeMessageErreur = new LinkedList<>();
                listeMessageErreur.add(e.getMessage());
                request.setAttribute("listeMessageErreur", listeMessageErreur);
            }

            // Rediriger vers la page d'affichage des clients
            Dispatch(AubergeConstantes.CLIENTS, request, response);
        }

    }
}
