package tp.gestion;

import main.AubergeInnException;
import tp.bdd.Connexion;
import tp.collections.Clients;
import tp.collections.Reservations;
import tp.objets.Client;
import tp.objets.Reservation;

import java.util.Date;
import java.util.List;

// classe GestionClient
public class GestionClient
{
    private final Clients clients;
    private final Reservations reservations;
    private final Connexion cx;

    /**
     * Constructeur de la classe GestionClient.
     *

     */
    public GestionClient(Clients clients, Reservations reservations) throws AubergeInnException {

        this.cx = clients.getConnexion();

        if (clients.getConnexion() != reservations.getConnexion())
            throw new AubergeInnException("Les instances de client et de reservation n'utilisent pas la même connexion au serveur");

        // Initialise les attributs de la classe
        this.clients = clients;
        this.reservations = reservations;
    }

    /**
     * Ajoute un client avec les informations fournies.
     */

    public void ajouterClient(int idClient, String nom, String prenom, int age)
            throws AubergeInnException {
        try {

            // Vérifie si le client existe déjà
            if (clients.existe(idClient))
                throw new AubergeInnException("Le client : " + idClient + " existe dans la BD.");


            clients.ajouterClient(idClient, nom, prenom, age);

        } catch (Exception e) {

            throw e;
        }
    }

    // faculatatif
    public boolean modifierClient(int idClient, String nom, String prenom, int age) {
        try {
            return clients.modifierClient(idClient, nom, prenom, age);
        } catch (AubergeInnException e) {
            System.err.println("Erreur lors de la modification du client : " + e.getMessage());
            return false;
        }
    }


    /**
     * Supprime un client avec l'identifiant spécifié de la BD
     *

     */
    public void supprimerClient(int idClient) throws Exception {
        try {


            // Vérifie si le client existe
            Client client = this.clients.getClient(idClient);
            if (client == null)
                throw new AubergeInnException("Client inexistant: " + idClient);

            // Vérifie si le client a une réservation présente ou future
            List<Reservation> reservationList = reservations.getReservationClient(idClient);


            if (reservationList.size() > 0) {
                Date date = new Date();

                for (Reservation res : reservationList) {

                    if (date.before(res.getDate_fin()))
                        throw new AubergeInnException("Client " + idClient + " a une réservation en cours.");
                }
            }


            if (!clients.supprimerClient(idClient))
                throw new AubergeInnException("Client " + idClient + " inexistant.");



        } catch (Exception e) {

            throw e;
        }
    }

    /**
     * Affiche les informations d'un client et de ses réservations.
     *
     */
    public void afficherClient(int idClient) throws Exception {
        try {


            // Vérifie si le client existe
            Client client = clients.getClient(idClient);

            if (client == null)
                throw new AubergeInnException("Client inexistant: " + idClient);
            else

                client.printInfo();



            List<Reservation> listeRes = reservations.getReservationClient(idClient);

            if (!(listeRes.size() > 0))


                System.out.println("Aucune réservation.");
            else {

                System.out.println("Réservations ci-dessous :");

                for (Reservation res : listeRes) {
                    res.printInfo();
                }
            }
        } catch (Exception e) {
            // En cas d'erreur, propage l'exception
            throw e;
        }
    }

}

