// ce fichier est une reference(reformulation) basée sur le fichier zip de bibliotheque disponible sur moodle
// neccesaire au cas ou il y aura un besoin de l'ouvrir via turning
// merci pour la comprehension

package AubergeInn;

import main.AubergeInnException;
import tp.bdd.Connexion;
import tp.collections.Chambres;
import tp.collections.Clients;
import tp.collections.Commodites;
import tp.collections.Reservations;
import tp.gestion.GestionChambre;
import tp.gestion.GestionClient;
import tp.gestion.GestionCommodite;
import tp.gestion.GestionReservation;

import java.sql.SQLException;


// classe GestionAubergeInn
public class GestionAubergInn
{
    private final Connexion cx;
    private final Clients clients;
    private final Chambres chambres;
    private final Reservations reservations;
    private final Commodites commodites;
    private GestionClient gestionClient;
    private GestionChambre gestionChambre;
    private GestionReservation gestionReservation;
    private GestionCommodite gestionCommodite;


    public GestionAubergInn(String serveur, String bd, String user, String password)
            throws AubergeInnException, SQLException
    {
        cx = new Connexion(serveur, bd, user, password);

        clients = new Clients(cx);

        chambres = new Chambres(cx);
        reservations = new Reservations(cx);

        commodites = new Commodites(cx);
        setGestionClient(new GestionClient(clients, reservations));
        setGestionChambre(new GestionChambre(chambres, reservations,commodites));
        setGestionCommodite(new GestionCommodite(commodites, chambres));

        setGestionReservation(new GestionReservation(reservations, clients, chambres));

    }

    public void fermer()
    {

    }
    public GestionClient getGestionClient() {
        return gestionClient;
    }


    public void setGestionClient(GestionClient gestionClient) {
        this.gestionClient = gestionClient;
    }

    public GestionChambre getGestionChambre() {
        return gestionChambre;
    }

    public void setGestionChambre(GestionChambre gestionChambre) {
        this.gestionChambre = gestionChambre;

    }

    public GestionReservation getGestionReservation() {
        return gestionReservation;
    }


    public void setGestionReservation(GestionReservation gestionReservation) {
        this.gestionReservation = gestionReservation;
    }

    public GestionCommodite getGestionCommodite() {
        return gestionCommodite;
    }

    public void setGestionCommodite(GestionCommodite gestionCommodite) {
        this.gestionCommodite = gestionCommodite;
    }

    public Connexion getConnexion() {
        return cx;
    }
}

