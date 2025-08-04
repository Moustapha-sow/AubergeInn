package com.example.tp;


import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


import tp.bdd.Connexion;
import main.AubergeInnException;

import static com.example.tp.AubergeHelper.*;


//servlet class Login

public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Servlet Login : GET");


        if (infoBDValide(getServletContext())) {

            DispatchToLogin(request, response);
        } else {

            DispatchToBDConnect(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)

            throws ServletException, IOException {

        System.out.println("Servlet Login : POST");

        // Vérifier si les informations de connexion sont déjà valides
        if (AubergeHelper.infoBDValide(getServletContext())) {

            AubergeHelper.DispatchToLogin(request, response);
        }


        String userId = request.getParameter("userIdBD");

        String motDePasse = request.getParameter("motDePasseBD");
        String serveur = request.getParameter("serveur");

        String bd = request.getParameter("bd");

        // Sauvegarder les informations dans la session
        request.setAttribute("userIdBD", userId);

        request.setAttribute("motDePasseBD", motDePasse);
        request.setAttribute("serveur", serveur);

        request.setAttribute("bd", bd);

        try {

            if (userId == null || userId.equals("")) {
                throw new AubergeInnException("Vous devez entrer un nom d'utilisateur.");

            }
            if (motDePasse == null || motDePasse.equals("")) {

                throw new AubergeInnException("Vous devez entrer un mot de passe.");
            }

            if (bd == null || bd.equals("")) {

                throw new AubergeInnException("Vous devez entrer un nom de base de donnée");
            }

            if (serveur == null || serveur.equals("")) {
                throw new AubergeInnException("Vous devez chosir un serveur");

            }

            try {

                Connexion cx = new Connexion(serveur, bd, userId, motDePasse);
                cx.fermer();


                // Sauvegarder les informations de connexion dans le contexte pour les réutiliser
                getServletContext().setAttribute("serveur", serveur);

                getServletContext().setAttribute("bd", bd);
                getServletContext().setAttribute("user", userId);

                getServletContext().setAttribute("pass", motDePasse);

                // Rediriger vers la page de connexion principale
                RequestDispatcher dispatcher = request.getRequestDispatcher(AubergeConstantes.LOGIN);

                dispatcher.forward(request, response);

            } catch (AubergeInnException e) {
                List<String> listMessageError = new LinkedList<String>();

                listMessageError.add("Erreur de connection à la Base de données:");
                listMessageError.add(e.getMessage());

                request.setAttribute("Liste de message d'Erreur ", listMessageError);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {

            List<String> listMessageError = new LinkedList<String>();

            listMessageError.add(e.getMessage());

            request.setAttribute("messageErreur", listMessageError);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        }

    }

}
