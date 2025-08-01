package com.example.tp;

import main.AubergeInnException;
import tp.gestion.GestionChambre;
import static com.example.tp.AubergeHelper.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class ChambreSupp extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet Chambre Supprimer: GET");
        if(estConnecter(request, response)){
            Dispatch(AubergeConstantes.CHAMBRES, request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet ChambreSupp : POST");
        if(estConnecter(request, response)) {
            try {
                HttpSession session = request.getSession();
                String idChambre = request.getParameter("idChambre");
                if (champVide(idChambre)) {
                    throw new AubergeInnException("ID chambre est vide");
                }
                request.setAttribute("idChambre", idChambre);

                GestionChambre gestionChambre = AubergeHelper.gestionAubergInnInterro(session).getGestionChambre();
                if (gestionChambre != null) {
                    gestionChambre.supprimerChambre(toInt(idChambre));
                }
                else{
                    throw new AubergeInnException("Session invalide. Veuillez vous reconnecter");
                }

                List<String> listeMessageSuccess = new LinkedList<>();
                listeMessageSuccess.add("La chambre " + idChambre + " a été supprimée avec succès");
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
