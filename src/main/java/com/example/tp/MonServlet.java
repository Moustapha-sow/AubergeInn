package com.example.tp;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class MonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Définir le type de réponse
        resp.setContentType("text/html");

        // Écrire une réponse simple en HTML
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>Bonjour depuis MonServlet !</h1>");
        out.println("<p>Ceci est une servlet Java déployée avec Tomcat.</p>");
        out.println("</body></html>");
    }
}
