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
//servlet class Add
@WebServlet(name = "ChambreAdd", value = "/ChambreAdd")
public class ChambreAdd extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet Chambre Add : GET");

        if(estConnecter(request, response)){

        Dispatch(AubergeConstantes.CHAMBREADD, request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet Chambre Add : POST");

        try {
            HttpSession session = request.getSession();

            String idChambre = request.getParameter("idChambre");

            String nomChambre = request.getParameter("nomChambre");
            String typeLit = request.getParameter("typeLit");

            String prixBase = request.getParameter("prixBase");

            request.setAttribute("idChambre", idChambre);

            request.setAttribute("nomChambre", nomChambre);
            request.setAttribute("typeLit", typeLit);

            request.setAttribute("prixBase", prixBase);

            if (champVide(idChambre) || champVide(nomChambre) || champVide(typeLit) || champVide(prixBase)) {

                throw new AubergeInnException("Tous les champs doivent être remplis");
            }

            GestionChambre gestionChambre = AubergeHelper.gestionAubergInnInterro(session).getGestionChambre();

            gestionChambre.ajouterChambre(toInt(idChambre),nomChambre, typeLit, Double.parseDouble(prixBase));

            List<String> listeMessageSuccess = new LinkedList<>();
            listeMessageSuccess.add("Chambre ajoutée avec succès");

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
