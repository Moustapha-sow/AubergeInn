package tp.gestion;

import main.AubergeInnException;
import tp.bdd.Connexion;
import tp.collections.Chambres;
import tp.collections.Commodites;
import tp.collections.Reservations;
import tp.objets.Chambre;
import tp.objets.Commodite;
import tp.objets.Reservation;

//import java.util.Calendar;
//import java.util.Date;
import java.util.*;


// Classe GestionChambre
public class GestionChambre {

    private final Chambres chambres;
    private final Reservations reservations;
    private final Commodites commodites;
    private final Connexion cx;

    /**
     * Constructeur de la classe GestionChambre.
     *

     */
    public GestionChambre(Chambres chambres, Reservations reservations, Commodites commodites) throws AubergeInnException {

        this.cx = chambres.getConnexion();

        // Vérifie que les instances de chambre et de reservation utilisent la même connexion au serveur
        if (chambres.getConnexion() != reservations.getConnexion())
            throw new AubergeInnException ("Les instances de chambre et de reservation n'utilisent pas la même connexion au serveur");

        this.chambres = chambres;
        this.reservations = reservations;
        this.commodites = commodites;
    }


    /**
     * Ajoute une chambre avec les informations fournies a la BD.
     *

     */
    public void ajouterChambre(int idChambre, String nom_chambre, String type_lit, double prix_base) throws AubergeInnException {
        try {

            if (chambres.existe(idChambre))
                throw new AubergeInnException("La chambre : " + idChambre + " existe déjà.");


            chambres.ajouterChambre(idChambre, nom_chambre, type_lit, prix_base);


        } catch (Exception e) {
            // En cas d'erreur, rollback la transaction et propage l'exception
            throw e;
        }
    }

    /**
     * Supprime une chambre avec l'identifiant spécifié de la BD
     *

     */
    public void supprimerChambre(int idChambre) throws AubergeInnException {
        try {

            // Vérifie que la chambre existe
            Chambre chambre = chambres.getChambre(idChambre);
            if (chambre == null)
                throw new AubergeInnException("Chambre inexistante: " + idChambre);

            // Vérifie si la chambre a une réservation présente ou future
            List<Reservation> reservation = this.reservations.getReservationChambre(idChambre);

            if (reservation.size() > 0) {

                Date date = new Date(Calendar.getInstance().getTime().getTime());
                for (Reservation res : reservation) {
                    // Vérifie si la date actuelle est avant la date de fin de la réservation
                    if (date.before(res.getDate_fin()))
                        throw new AubergeInnException("Chambre " + idChambre + " est deja réservée.");
                }
            }

            if (!chambres.supprimerChambre(idChambre))
                throw new AubergeInnException("Chambre " + idChambre + " n existe pas ");
        } catch (Exception e) {
            // En cas d'erreur, propage l'exception
            throw e;
        }
    }


    /**
     * Affiche toutes les chambres qui sont actuellement libres.
     */
    public void afficherChambresLibres() {
        List<Chambre> listeChambres;
        try {
             listeChambres= chambres.getAllChambres();
        } catch (AubergeInnException e) {
            System.err.println("Impossible de récupérer les chambres : " + e.getMessage());
            return;
        }
        // Parcourt la liste des chambres
        for (Chambre ch : listeChambres) {

            List<Reservation> reservation = this.reservations.getReservationChambre(ch.getIdChambre());

            // Associe les réservations à la chambre
            ch.setReservation(reservation);


            if (ch.estLibre())
                System.out.println("Chambre : " + ch.getIdChambre() + ", " + ch.getPrixTotal() + "$");
        }
    }

    /**
     * Affiche les informations d'une chambre spécifiée par son identifiant.
     *

     */
    public void afficherChambre(int idChambre) throws AubergeInnException {

        // Vérifie que la chambre existe
        Chambre chambre = chambres.getChambre(idChambre);
        if (chambre == null)
            throw new AubergeInnException("Chambre inexistante: " + idChambre);


        else {
            // Récupère les commodités associées à la chambre
            List<Commodite> commodite = commodites.getCommoditeChambre(idChambre);

            // Associe les commodités à la chambre
            chambre.setCommodites(commodite);


            chambre.printInfo();
        }
    }

}

