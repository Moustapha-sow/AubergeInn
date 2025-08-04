package com.example.tp;

import main.AubergeInnException;
import tp.gestion.GestionChambre;
import tp.gestion.GestionClient;
import tp.gestion.GestionReservation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.LinkedList;

import java.util.List;

import static com.example.tp.AubergeHelper.*;


//servlet class FaireUneReservationPourUnClient

public class FaireUneReservationPourUnClient extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(estConnecter(request, response)){

            System.out.println("Servlet Reservation : GET");
            HttpSession session = request.getSession();

            int idClient = Integer.parseInt(request.getParameter("idClient"));
            request.setAttribute("idClient", idClient);


            Dispatch(AubergeConstantes.CLIENTS, request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet Reservation : POST");

        if(estConnecter(request, response)){

            System.out.println("Servlet Reservation : POST dispatch  vers Reservations.jsp");
            try {

                HttpSession session = request.getSession();
                String idClient = request.getParameter("idClient");

                String idChambre = request.getParameter("idChambre");
                String dateDebutStr = request.getParameter("dateDebut");

                String dateFinStr = request.getParameter("dateFin");

                request.setAttribute("idClient", idClient);

                request.setAttribute("idChambre", idChambre);

                request.setAttribute("dateDebutStr", dateDebutStr);
                request.setAttribute("dateFinStr", dateFinStr);


                if (champVide(idClient))

                    throw new AubergeInnException("Le champ client est vide.");

                if (champVide(idChambre))

                    throw new AubergeInnException("Le champ chambre est vide.");


                if (champVide(dateDebutStr))

                    throw new AubergeInnException("Le champ date de début est vide.");


                if (champVide(dateFinStr))
                    throw new AubergeInnException("Le champ date de fin est vide.");


                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date dateDebut = dateFormat.parse(dateDebutStr);

                Date dateFin = dateFormat.parse(dateFinStr);


                GestionReservation gestionReservation = AubergeHelper.gestionAubergInnInterro(session).getGestionReservation();
                gestionReservation.reserver(toInt(idClient), toInt(idChambre), new java.sql.Date(dateDebut.getTime()), new java.sql.Date(dateFin.getTime()));


                List<String> listeMessageSuccess = new LinkedList<>();

                listeMessageSuccess.add("success de reservation du Client " + idClient + " pour la chambre " + idChambre + "du " + dateDebut + " au " + dateDebut);
                request.setAttribute("listeMessageSuccess : ", listeMessageSuccess);


            } catch (Exception e) {
                List<String> listeMessageErreur = new LinkedList<>();

                listeMessageErreur.add(e.getMessage());

                request.setAttribute("listeMessageErreur", listeMessageErreur);
            }

            Dispatch(AubergeConstantes.CLIENTS, request, response);
        }
    }
}

