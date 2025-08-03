package tp.gestion;

import main.AubergeInnException;
import tp.bdd.Connexion;
import tp.collections.Chambres;
import tp.collections.Commodites;
import tp.collections.Reservations;
import tp.objets.Chambre;
import tp.objets.Commodite;
import tp.objets.Reservation;

import java.util.*;

public class GestionChambre {

    private final Chambres chambres;
    private final Reservations reservations;
    private final Commodites commodites;
    private final Connexion cx;

    /**
     * Constructeur de la classe GestionChambre.
     */
    public GestionChambre(Chambres chambres, Reservations reservations, Commodites commodites) throws AubergeInnException {
        this.cx = chambres.getConnexion();

        // Vérifie que les instances de chambre et de reservation utilisent la même connexion
        if (chambres.getConnexion() != reservations.getConnexion())
            throw new AubergeInnException("Les instances de chambre et de reservation n'utilisent pas la même connexion au serveur");

        this.chambres = chambres;
        this.reservations = reservations;
        this.commodites = commodites;
    }

    /**
     * Ajoute une chambre avec les informations fournies à la BD.
     */
    public void ajouterChambre(int idChambre, String nomChambre, String typeLit, Double prixBase) throws AubergeInnException {
        if (chambres.existe(idChambre)) {
            throw new AubergeInnException("La chambre : " + idChambre + " existe déjà.");
        }
        chambres.ajouterChambre(idChambre, nomChambre, typeLit, prixBase);
    }

    /**
     * Supprime une chambre avec l'identifiant spécifié de la BD.
     */
    public void supprimerChambre(int idChambre) throws AubergeInnException {
        // Vérifie que la chambre existe
        Chambre chambre = chambres.getChambre(idChambre);
        if (chambre == null) {
            throw new AubergeInnException("Chambre inexistante: " + idChambre);
        }

        // Vérifie si la chambre a une réservation présente ou future
        List<Reservation> reservation = reservations.getReservationChambre(idChambre);
        if (!reservation.isEmpty()) {
            Date today = new Date();
            for (Reservation res : reservation) {
                if (today.before(res.getDateFin())) {
                    throw new AubergeInnException("Chambre " + idChambre + " est déjà réservée.");
                }
            }
        }

        if (!chambres.supprimerChambre(idChambre)) {
            throw new AubergeInnException("Chambre " + idChambre + " n'existe pas");
        }
    }

    /**
     * Affiche toutes les chambres actuellement libres.
     */
    public void afficherChambresLibres() {
        List<Chambre> listeChambres = chambres.getAllChambres();
        for (Chambre ch : listeChambres) {
            List<Reservation> reservation = reservations.getReservationChambre(ch.getIdChambre());
            ch.setReservation(reservation);

            if (ch.estLibre()) {
                System.out.println("Chambre : " + ch.getIdChambre() + ", " + ch.getPrixTotal() + "$");
            }
        }
    }

    /**
     * Affiche les informations d'une chambre spécifiée par son identifiant.
     */
    public void afficherChambre(int idChambre) throws AubergeInnException {
        Chambre chambre = chambres.getChambre(idChambre);
        if (chambre == null) {
            throw new AubergeInnException("Chambre inexistante: " + idChambre);
        }

        List<Commodite> commoditeList = commodites.getCommoditeChambre(idChambre);
        chambre.setCommodites(commoditeList);

        chambre.printInfo();
    }

    /**
     * Retourne une chambre selon son ID.
     */
    public Chambre getChambre(int idChambre) throws AubergeInnException {
        Chambre chambre = chambres.getChambre(idChambre);
        if (chambre == null) {
            throw new AubergeInnException("Chambre " + idChambre + " non existante");
        }
        return chambre;
    }

    /**
     * Retourne la liste des commodités d'une chambre.
     */
    public List<Commodite> getListeCommoditesChambre(int idChambre) throws AubergeInnException {
        Chambre chambre = chambres.getChambre(idChambre);
        if (chambre == null) {
            throw new AubergeInnException("ID Chambre " + idChambre + " inexistant");
        }

        return commodites.getCommoditeChambre(idChambre);
    }

    /**
     * Supprime une commodité d'une chambre.
     */
    public void supprimerCommodite(int idChambre, int idCommodite) throws AubergeInnException {
        Chambre chambre = chambres.getChambre(idChambre);
        if (chambre == null) {
            throw new AubergeInnException("ID Chambre " + idChambre + " inexistant");
        }

        Commodite c = commodites.getCommodite(idCommodite);
        if (c == null) {
            throw new AubergeInnException("ID Commodite " + idCommodite + " inexistant");
        }

        if (!chambres.validerCommoditeChambre(idChambre, idCommodite)) {
            throw new AubergeInnException("La commodité " + idCommodite + " n'est pas incluse dans la chambre " + idChambre);
        }

        chambres.supprimerCommodite(idChambre, idCommodite);

        System.out.println("\nSuppression de la commodite: " + idCommodite + " de la chambre " + idChambre + "\n");
    }

    public void inclureCommodite(int idChambre, int idCommodite) throws AubergeInnException {
        Chambre chambre = chambres.getChambre(idChambre);
        if (chambre == null) {
            throw new AubergeInnException("ID Chambre " + idChambre + " inexistant");
        }

        Commodite commodite = commodites.getCommodite(idCommodite);
        if (commodite == null) {
            throw new AubergeInnException("ID Commodite " + idCommodite + " inexistant");
        }

        if (commodites.existeChambreCommodite(idCommodite, idChambre)) {
            throw new AubergeInnException("La commodité " + idCommodite + " est déjà incluse dans la chambre " + idChambre);
        }

        commodites.inclureCommodite(idCommodite, idChambre);

        System.out.println("Ajout de la commodite: " + idCommodite + " à la chambre " + idChambre);
    }

    public List<Chambre> getAllChambres() throws Exception {
        return chambres.getAllChambres();
    }


}
