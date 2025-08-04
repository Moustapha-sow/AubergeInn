package com.example.tp;

import main.AubergeInnException;
import tp.gestion.GestionChambre;

import static com.example.tp.AubergeHelper.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

////servlet class EnleverCommodite
public class EnleverCommodite extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet EnleverCommodite");

        if(estConnecter(request,response)){
            int idChambre = Integer.parseInt(request.getParameter("idChambre"));

            request.setAttribute("idChambre",idChambre);

            Dispatch(AubergeConstantes.CHAMBRES,request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (estConnecter(request, response)) {

            System.out.println("Servlet EnleverCommodite");
            try {

                HttpSession session = request.getSession();
                String idChambre = request.getParameter("idChambre");

                String idCommodite = request.getParameter("idCommodite");

                request.setAttribute("idChambre",idChambre);

                request.setAttribute("idCommodite",idCommodite);

                if (idChambre == null || idChambre.isEmpty() || idCommodite == null || idCommodite.isEmpty()) {
                    throw new AubergeInnException("L'ID de la chambre et de la commodité sont obligatoires.");
                }

                GestionChambre gestionChambre = AubergeHelper.gestionAubergInnInterro(session).getGestionChambre();
                gestionChambre.supprimerCommodite(toInt(idChambre), toInt(idCommodite));


                List<String> listeMessageSuccess = new LinkedList<>();

                listeMessageSuccess.add("Commodite " + idCommodite + " est supprimer de la chambre "+ idChambre + " Avec succès");
                request.setAttribute("listeMessageSuccess", listeMessageSuccess);


                Dispatch(AubergeConstantes.CHAMBRES, request, response);

            } catch (Exception e) {

                List<String> listeMessageErreur = new LinkedList<>();

                listeMessageErreur.add(e.getMessage());

                request.setAttribute("listeMessageErreur", listeMessageErreur);

                doGet(request, response);
            }
        }
    }
}

