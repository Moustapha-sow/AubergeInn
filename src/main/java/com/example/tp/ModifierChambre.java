package com.example.tp;

import main.AubergeInnException;
import tp.gestion.GestionChambre;
import tp.objets.Commodite;
import tp.gestion.GestionCommodite;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;

import static com.example.tp.AubergeHelper.Dispatch;


//servlet class ModifierChambre

public class ModifierChambre extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (AubergeHelper.estConnecter(request, response)) {

            int idChambre = Integer.parseInt(request.getParameter("idChambre"));
            request.setAttribute("idChambre", idChambre);


            HttpSession session = request.getSession();
            GestionChambre gestionChambre = AubergeHelper.gestionAubergInnInterro(session).getGestionChambre();

            GestionCommodite gestionCommodite = AubergeHelper.gestionAubergInnInterro(session).getGestionCommodite();
            List<Commodite> commodites = null;

            try {
                commodites = gestionChambre.getListeCommoditesChambre(idChambre);

            } catch (AubergeInnException e) {

                throw new RuntimeException(e);
            }
            request.setAttribute("commodites", commodites);


            List<Commodite> toutesCommodites = gestionCommodite.getTouteLesCommodites();

            request.setAttribute("toutesCommodites", toutesCommodites);


            Dispatch(AubergeConstantes.CHAMBRES, request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (AubergeHelper.estConnecter(request, response)) {

            try {
                HttpSession session = request.getSession();

                GestionChambre gestionChambre = AubergeHelper.gestionAubergInnInterro(session).getGestionChambre();


                int idChambre = Integer.parseInt(request.getParameter("idChambre"));

                String[] selectedCommodites = request.getParameterValues("commodites");

                List<Commodite> commoditesActuelles = gestionChambre.getListeCommoditesChambre(idChambre);
                for (Commodite commodite : commoditesActuelles) {

                    gestionChambre.supprimerCommodite(idChambre, commodite.getIdCommodite());
                }

                if (selectedCommodites != null) {
                    for (String idCommodite : selectedCommodites) {

                        gestionChambre.inclureCommodite(idChambre, Integer.parseInt(idCommodite));
                    }
                }

                List<String> listeMessageSuccess = new LinkedList<>();

                listeMessageSuccess.add("Les commodités ont été mises à jour avec succès pour la chambre " + idChambre);
                request.setAttribute("listeMessageSuccess", listeMessageSuccess);


                Dispatch(AubergeConstantes.CHAMBRES, request, response);

            } catch (Exception e) {
                // Gestion des erreurs et retour au formulaire de modification dans le modal

                List<String> listeMessageErreur = new LinkedList<>();
                listeMessageErreur.add(e.getMessage());

                request.setAttribute("listeMessageErreur", listeMessageErreur);

                doGet(request, response);
            }
        }
    }
}
