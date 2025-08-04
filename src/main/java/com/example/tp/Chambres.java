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

//servlet class Add

public class Chambres extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet Chambres : GET");

        if (estConnecter(request, response)) {
            System.out.println("Servlet Chambres : GET dispatch vers Chambres.jsp");

            Dispatch(AubergeConstantes.CHAMBRES, request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet Chambres : POST");


        if (estConnecter(request, response)) {
            HttpSession session = request.getSession();

            List<String> listeMessageErreur = new LinkedList<>();

            try {

                String idChambreStr = request.getParameter("idChambre");

                String nomChambre = request.getParameter("nomChambre");
                String typeLit = request.getParameter("typeLit");

                String prixBaseStr = request.getParameter("prixBase");

                // Vérification des champs vides
                if (champVide(idChambreStr) || champVide(nomChambre) || champVide(typeLit) || champVide(prixBaseStr)) {
                    throw new AubergeInnException("Tous les champs doivent être remplis.");
                }

                int idChambre = toInt(idChambreStr);

                double prixBase = Double.parseDouble(prixBaseStr);


                GestionChambre gestionChambre = AubergeHelper.gestionAubergInnInterro(session).getGestionChambre();


                gestionChambre.ajouterChambre(idChambre, nomChambre, typeLit, prixBase);

                request.setAttribute("messageSucces", "Chambre ajoutée avec succès.");

                // Charger la chambre et ses commodités pour affichage
                request.setAttribute("chambre", gestionChambre.getChambre(idChambre));

                request.setAttribute("commoditesLieAChambre", gestionChambre.getListeCommoditesChambre(idChambre));

            } catch (Exception e) {
                listeMessageErreur.add(e.getMessage());

                request.setAttribute("listeMessageErreur", listeMessageErreur);
            }


            Dispatch(AubergeConstantes.CHAMBRES, request, response);
        }
    }

}
