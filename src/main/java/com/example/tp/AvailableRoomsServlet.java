package com.example.tp;

import tp.objets.Chambre;
import tp.gestion.GestionReservation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.bson.Document;
import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import com.google.gson.Gson;
//servlet class AvailableRoomsServlet
@WebServlet("/AvailableRoomsServlet")
public class AvailableRoomsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        GestionReservation gestionReservation = AubergeHelper.gestionAubergInnInterro(session).getGestionReservation();

        String dateDebutStr = request.getParameter("dateDebut");

        String dateFinStr = request.getParameter("dateFin");

        if (dateDebutStr != null && dateFinStr != null) {
            try {
                Date dateDebut = Date.valueOf(dateDebutStr);


                Date dateFin = Date.valueOf(dateFinStr);

                List<Chambre> chambresLibres = gestionReservation.getListeChambresLibres(dateDebut, dateFin);

                // Convertir la liste des chambres libres en JSON
                List<Document> chambresJson = new LinkedList<Document>();
                for (Chambre chambre : chambresLibres) {

                    Document doc = new Document("idChambre", chambre.getIdChambre())
                            .append("nomChambre", chambre.getNomChambre());

                    chambresJson.add(doc);
                }


                // Utiliser Gson pour convertir la liste des Documents en JSON
                Gson gson = new Gson();
                String json = gson.toJson(chambresJson);


                // Envoyer la réponse JSON
                response.setContentType("application/json");
                response.getWriter().write(json);


            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

                response.getWriter().write("Erreur: " + e.getMessage());
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }
    }
}

