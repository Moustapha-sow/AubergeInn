package com.example.tp;

import main.AubergeInnException;
import tp.gestion.GestionCommodite;
import static com.example.tp.AubergeHelper.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

//servlet class Commodites

public class Commodites extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet Commodites : GET");

        if(estConnecter(request, response)){

            System.out.println("Servlet Commodites : GET dispatch vers Commodites.jsp");
            Dispatch(AubergeConstantes.COMMODITES, request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet Commodites : POST");

        if(estConnecter(request, response)){
            System.out.println("Servlet Commodites : POST dispatch vers Commodites.jsp");

            try{
                HttpSession session = request.getSession();
                String idCommodite = request.getParameter("idCommodite");

                request.setAttribute("idCommodite", idCommodite);


                if(champVide(idCommodite)){
                    throw new AubergeInnException("id commodite est vide");
                }


                GestionCommodite gestionCommodite = AubergeHelper.gestionAubergInnInterro(session).getGestionCommodite();

                request.setAttribute("commodite", gestionCommodite.getCommodite(toInt(idCommodite)));

            } catch (Exception e) {

                List<String> listeMessageErreur = new LinkedList<>();

                listeMessageErreur.add(e.getMessage());
                request.setAttribute("listeMessageErreur", listeMessageErreur);

            }

            // Rediriger vers la page d'affichage des clients
            Dispatch(AubergeConstantes.COMMODITES, request, response);
        }
    }
}

