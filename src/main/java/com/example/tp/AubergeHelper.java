package com.example.tp;

import AubergeInn.GestionAubergInn;
import main.AubergeInnException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AubergeHelper {

    public static GestionAubergInn gestionAubergInnInterro(HttpSession session) {
        return (GestionAubergInn) session.getAttribute("AubergInterrogation");
    }

    public static GestionAubergInn gestionAubergInnUpdate(HttpSession session) {
        return (GestionAubergInn) session.getAttribute("AubergUpdate");
    }

    public static void creerGestionnaire(ServletContext c, HttpSession s) throws Exception {
        String serveur = (String)c.getAttribute("serveur");
        String bd = (String)c.getAttribute("bd");
        String userIdBD = (String)c.getAttribute("user");
        String pass = (String)c.getAttribute("pass");
        GestionAubergInn AubergInterrogation = new GestionAubergInn(serveur, bd, userIdBD, pass);
        s.setAttribute("AubergInterrogation", AubergInterrogation);
        GestionAubergInn aubergUpdate = new GestionAubergInn(serveur, bd, userIdBD, pass);
        s.setAttribute("AubergUpdate", aubergUpdate);
    }

    public static boolean gestionnairesCrees(HttpSession session) {
        if (session == null) {
            return false;
        } else {
            return session.getAttribute("AubergInterrogation") != null;
        }
    }

    public static boolean peutProcederLogin(ServletContext c, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (infoBDValide(c)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }

            return true;
        } else {
            DispatchToBDConnect(request, response);
            return false;
        }
    }


    public static boolean infoBDValide(ServletContext servletContext) {
        String serveur = (String) servletContext.getAttribute("serveur");
        String bd = (String) servletContext.getAttribute("bd");
        String user = (String) servletContext.getAttribute("user");
        String pass = (String) servletContext.getAttribute("pass");

        // Vérifier si toutes les informations de connexion sont présentes
        return serveur != null && bd != null && user != null && pass != null;
    }

    public static boolean estConnecter(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session != null) {
            return true;
        }
        redirect("/login.jsp", request, response);

        return false;
    }

    public static boolean estConnecte(HttpSession session) {
        if (session == null) {
            return false;
        } else {
            return session.getAttribute("etat") != null;
        }
    }

    public static void Dispatch(String path, HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void redirect(String path, HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect(request.getContextPath() + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void DispatchToLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Invalider la session actuelle si elle existe
        HttpSession session = request.getSession(false);
        if (AubergeHelper.estConnecte(session)) {
            session.invalidate();
        }

        // Afficher le menu de connexion principal de l'application
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Login.jsp");
        dispatcher.forward(request, response);
    }

    public static void DispatchToBDConnect(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (AubergeHelper.estConnecte(session)) {
            session.invalidate();
        }

        // Rediriger vers la page d'accueil ou une autre page appropriée
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");
        dispatcher.forward(request, response);
    }


    public static boolean champVide(String value) {
        return value == null || value.equals("");
    }

    public static int toInt(String s) throws AubergeInnException {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            throw new AubergeInnException(s + " n'est pas un nombre.");
        }
    }

    public static double toDouble(String s) throws AubergeInnException {
        try {
            return Double.parseDouble(s);
        } catch (Exception e) {
            throw new AubergeInnException(s + " n'est pas un nombre.");
        }
    }

    public static boolean estEntier(String str) {
        if (str == null) {
            return false;
        }
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }


}
