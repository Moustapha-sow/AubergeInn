package com.example.tp;

import main.AubergeInnException;
import tp.gestion.GestionCommodite;
import static com.example.tp.AubergeHelper.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
//servlet class CommoditeAdd

public class CommoditeAdd extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet CommoditeAdd: GET");

        if(estConnecter(request, response)){

            Dispatch(AubergeConstantes.COMMODITES, request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet CommoditeAdd: POST");

        try {
            HttpSession session = request.getSession();

            String idCommodite = request.getParameter("idCommodite");

            String description = request.getParameter("description");
            String surplusPrix = request.getParameter("surplusPrix");

            request.setAttribute("idCommodite", idCommodite);

            request.setAttribute("description", description);
            request.setAttribute("surplusPrix", surplusPrix);


            if (idCommodite == null || idCommodite.isEmpty() ||
                    description == null || description.isEmpty() ||
                    surplusPrix == null || surplusPrix.isEmpty()) {
                throw new AubergeInnException("Tous les champs sont obligatoires.");
            }


            GestionCommodite gestionCommodite = AubergeHelper.gestionAubergInnInterro(session).getGestionCommodite();
            gestionCommodite.ajouterCommodite(toInt(idCommodite), description, Double.parseDouble(surplusPrix));

            List<String> listeMessageSuccess = new LinkedList<>();

            listeMessageSuccess.add("Commodite " + idCommodite + " ajouté avec succès");

            request.setAttribute("listeMessageSuccess", listeMessageSuccess);

            Dispatch(AubergeConstantes.COMMODITES, request, response);

        } catch (Exception e) {
            List<String> listeMessageErreur = new LinkedList<>();

            listeMessageErreur.add(e.getMessage());

            request.setAttribute("listeMessageErreur", listeMessageErreur);

            doGet(request, response);
        }
    }
}
