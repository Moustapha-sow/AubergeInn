package com.example.tp;

import main.AubergeInnException;
import tp.gestion.GestionChambre;

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

////servlet class GererCommodite
@WebServlet(name = "GererCommodite", value = "/GererCommodite")
public class GererCommodite extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet GererCommodite : GET");

        if (estConnecter(request, response)) {

            Dispatch(AubergeConstantes.GERERCOMMODITE, request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (estConnecter(request, response)) {

            System.out.println("Servlet GererCommodite : POST");
            try {
                HttpSession session = request.getSession();
                String action = request.getParameter("action");

                String idChambre = request.getParameter("idChambre");

                String idCommodite = request.getParameter("idCommodite");

                request.setAttribute("idChambre", idChambre);

                request.setAttribute("idCommodite", idCommodite);

                if (idChambre == null || idChambre.isEmpty() || idCommodite == null || idCommodite.isEmpty()) {

                    throw new AubergeInnException("L'ID de la chambre et de la commodité sont obligatoires.");
                }

                GestionChambre gestionChambre = AubergeHelper.gestionAubergInnInterro(session).getGestionChambre();

                List<String> listeMessageSuccess = new LinkedList<>();

                if ("inclure".equals(action)) {

                    gestionChambre.inclureCommodite(toInt(idChambre), toInt(idCommodite));

                    listeMessageSuccess.add("Commodité " + idCommodite + " ajoutée à la chambre " + idChambre + " avec succès.");
                } else if ("enlever".equals(action)) {

                    gestionChambre.supprimerCommodite(toInt(idChambre), toInt(idCommodite));

                    listeMessageSuccess.add("Commodité " + idCommodite + " enlevée de la chambre " + idChambre + " avec succès.");
                } else {

                    throw new AubergeInnException("Action inconnue : " + action);
                }

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
