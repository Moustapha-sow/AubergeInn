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


// classe GestionReservation
public class GestionReservation
{
    private final Reservations reservations;
    private final Clients clients;
    private final Chambres chambres;
    private final Connexion cx;

    /**
     * Constructeur de la classe GestionReservation.
     *
     */
    public GestionReservation(Reservations reservations, Clients clients, Chambres chambres)
            throws AubergeInnException {


        if (clients.getConnexion() != chambres.getConnexion() || reservations.getConnexion() != chambres.getConnexion())
            throw new AubergeInnException(
                    "Les instances de client, de chambre et de reservation n'utilisent pas la même connexion au serveur");


        // Utilise la connexion de l'objet Clients pour l'instance GestionReservation
        this.cx = clients.getConnexion();

        this.clients = clients;
        this.chambres = chambres;
        this.reservations = reservations;
    }



    /**
     * Effectue une réservation d'un client pour chambre
     *
     */
    public void reserver(int idClient, int idChambre, Date date_debut, Date date_fin) throws Exception {
        try {

            Client client = this.clients.getClient(idClient);
            if (client == null)

                throw new AubergeInnException("Client inexistant: " + idClient);


            // Vérifier que la chambre existe
            Chambre chambre = this.chambres.getChambre(idChambre);
            if (chambre == null)
                throw new AubergeInnException("Chambre inexistante: " + idChambre);

            // Vérifier que la chambre n'est pas déjà réservée dans la période donnée
            List<Reservation> listReservationChambre = reservations.getReservationChambre(idChambre);

            if (listReservationChambre.size() > 0) {
                for (Reservation res : listReservationChambre) {

                    if (date_debut.before(res.getDate_fin()) && res.getDate_debut().before(date_fin))
                        throw new AubergeInnException("la Chambre " + idChambre + "a déjà réservée pour cette période.");
                }
            }


            // Vérifier que le client n'a pas déjà une réservation dans la période donnée
            List<Reservation> listReservationClient = reservations.getReservationClient(idClient);

            if (listReservationClient.size() > 0) {
                for (Reservation res : listReservationClient) {
                    if (date_debut.before(res.getDate_fin()) && res.getDate_debut().before(date_fin))

                        throw new AubergeInnException("Le client " + idClient + " a déjà une réservation pour cette période.");
                }
            }


            // Vérifier que la date de réservation n'est pas dans le passé
            if (date_debut.before(new Date()))
                throw new AubergeInnException("La date de réservation ne peut pas être dans le passé.");


            double prix_total = chambre.getPrixTotal();



            reservations.reserver(idClient, idChambre, prix_total, date_debut, date_fin);


        } catch (Exception e) {
            throw e;
        }
    }

}

