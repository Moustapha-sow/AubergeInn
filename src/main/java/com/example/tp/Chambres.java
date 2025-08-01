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
            System.out.println("Servlet Chambres : POST dispatch vers Chambres.jsp");
            try{
                HttpSession session = request.getSession();
                String idChambre = request.getParameter("idChambre");
                request.setAttribute("idChambre", idChambre);

                if(champVide(idChambre)){
                    throw new AubergeInnException("ID chambre est vide");
                }

                GestionChambre gestionChambre = AubergeHelper.gestionAubergInnInterro(session).getGestionChambre();

                // Ajouter les informations du Chambre et les commodités à la requête
                request.setAttribute("chambre", gestionChambre.getChambre(toInt(idChambre)));
                request.setAttribute("commoditesLieAChambre", gestionChambre.getListeCommoditesChambre(toInt(idChambre)));

            } catch (Exception e) {
                List<String> listeMessageErreur = new LinkedList<>();
                listeMessageErreur.add(e.getMessage());
                request.setAttribute("listeMessageErreur", listeMessageErreur);
            }

            Dispatch(AubergeConstantes.CHAMBRES, request, response);
        }

    }
}
