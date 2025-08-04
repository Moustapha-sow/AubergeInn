package com.example.tp;

import main.AubergeInnException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.example.tp.AubergeHelper.Dispatch;
import static com.example.tp.AubergeHelper.estConnecter;

// servlet class Accueil
public class Accueil extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Accueil() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet Accueil : GET");

        if (estConnecter(request, response)) {
            System.out.println("Servlet Accueil : GET dispatch vers Accueil.jsp");
            Dispatch(AubergeConstantes.ACCUEIL, request, response);
        }
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet Accueil : POST");

        try {
            if (!AubergeHelper.peutProcederLogin(this.getServletContext(), request, response)) {
                System.out.println("Servlet Accueil : POST ne peut pas procéder.");
                return;
            }

            HttpSession session = request.getSession();
            if (!AubergeHelper.gestionnairesCrees(session)) {
                System.out.println("Servlet Accueil : POST Création des gestionnaires");
                AubergeHelper.creerGestionnaire(this.getServletContext(), session);
            }

            RequestDispatcher dispatcher;
            Exception e;
            String userId;
            LinkedList listeMessageErreur;
            String motDePasse;
            if (request.getParameter("connecter") != null) {
                System.out.println("Servlet Accueil : POST - Connecter");

                try {
                    userId = request.getParameter("userId");
                    motDePasse = request.getParameter("motDePasse");

                    request.setAttribute("userId", userId);

                    request.setAttribute("motDePasse", motDePasse);
                    if (userId != null && userId.equals("admin")) {
                        if (motDePasse != null && motDePasse.equals("admin")) {

                            session.setAttribute("etat", new Integer(1));
                            session.setAttribute("connexion", new Integer(1));

                            System.out.println("Servlet Accueil : POST dispatch vers accueil.jsp");
                            dispatcher = request.getRequestDispatcher(AubergeConstantes.ACCUEIL);
                            dispatcher.forward(request, response);
                            return;
                        }

                        throw new AubergeInnException("Le mot de passe est incorrect!");
                    }

                    throw new AubergeInnException("Le nom d'utilisateur est incorrect!");
                } catch (Exception var17) {
                    e = var17;
                    listeMessageErreur = new LinkedList();

                    listeMessageErreur.add(e.getMessage());
                    request.setAttribute("listeMessageErreur", listeMessageErreur);

                    dispatcher = request.getRequestDispatcher(AubergeConstantes.LOGIN);
                    dispatcher.forward(request, response);
                    e.printStackTrace();
                }
            }
        } catch (Exception var19) {

            Exception e = var19;
            List<String> listeMessageErreur = new LinkedList();

            listeMessageErreur.add(e.getMessage());
            request.setAttribute("listeMessageErreur", listeMessageErreur);

            RequestDispatcher dispatcher = request.getRequestDispatcher(AubergeConstantes.LOGIN);
            dispatcher.forward(request, response);

            e.printStackTrace();
        }
    }

}
