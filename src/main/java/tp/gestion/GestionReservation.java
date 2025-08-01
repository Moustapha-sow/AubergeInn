package tp.gestion;

import main.AubergeInnException;
import tp.bdd.Connexion;
import tp.collections.Chambres;
import tp.collections.Clients;
import tp.collections.Reservations;
import tp.objets.Chambre;
import tp.objets.Client;
import tp.objets.Reservation;

import java.util.Date;
import java.util.List;

public class GestionReservation {
    private final Reservations reservations;
    private final Clients clients;
    private final Chambres chambres;
    private final Connexion cx;

    public GestionReservation(Reservations reservations, Clients clients, Chambres chambres)
            throws AubergeInnException {

        if (clients.getConnexion() != chambres.getConnexion() ||
                reservations.getConnexion() != chambres.getConnexion()) {
            throw new AubergeInnException("Les instances n'utilisent pas la même connexion.");
        }

        this.cx = clients.getConnexion();
        this.clients = clients;
        this.chambres = chambres;
        this.reservations = reservations;
    }

    public void reserver(int idClient, int idChambre, Date date_debut, Date date_fin) throws Exception {
        Client client = clients.getClient(idClient);
        if (client == null)
            throw new AubergeInnException("Client inexistant: " + idClient);

        Chambre chambre = chambres.getChambre(idChambre);
        if (chambre == null)
            throw new AubergeInnException("Chambre inexistante: " + idChambre);

        for (Reservation res : reservations.getReservationChambre(idChambre)) {
            if (date_debut.before(res.getDate_fin()) && res.getDate_debut().before(date_fin)) {
                throw new AubergeInnException("La chambre " + idChambre + " est déjà réservée pour cette période.");
            }
        }

        for (Reservation res : reservations.getReservationClient(idClient)) {
            if (date_debut.before(res.getDate_fin()) && res.getDate_debut().before(date_fin)) {
                throw new AubergeInnException("Le client " + idClient + " a déjà une réservation pour cette période.");
            }
        }

        if (date_debut.before(new Date())) {
            throw new AubergeInnException("La date de réservation ne peut pas être dans le passé.");
        }

        double prix_total = chambre.getPrixTotal();
        reservations.reserver(idClient, idChambre, prix_total, date_debut, date_fin);
    }

    public List<Reservation> getReservationsClient(int idClient) {
        try {
            Client client = clients.getClient(idClient);
            if (client == null) {
                throw new AubergeInnException("Client inexistant: " + idClient);
            }

            return reservations.getListeReservationsClient(idClient); // même si vide
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * retourner la liste des chambres Libres
     */
    public List<Chambre> getListeChambresLibres(java.sql.Date dateDebut, java.sql.Date dateFin) throws AubergeInnException {
        // Valider dateDebut < dateFin
        if (dateDebut.after(dateFin) || dateDebut.equals(dateFin)) {
            throw new AubergeInnException("La date de début doit être plus tôt que la date de fin" + "\n");
        }

        List<Chambre> listeChambres = chambres.getAllChambres();
        if (listeChambres.isEmpty()) {
            throw new AubergeInnException("Il n'y a pas de chambre dans la base de données" + "\n");
        }

        // Récupérer les chambres libres
        listeChambres = reservations.getListeChambresLibres(dateDebut, dateFin, listeChambres);
        if(listeChambres.isEmpty())
        {
            System.out.println("Il n'y a pas de chambre libre pour la période du " + dateDebut + " au " + dateFin);
        }

        return listeChambres;

    }

}
