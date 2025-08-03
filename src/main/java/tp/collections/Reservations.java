package tp.collections;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import main.AubergeInnException;
import tp.bdd.Connexion;
import tp.objets.Chambre;
import tp.objets.Reservation;
import org.bson.Document;

import java.util.*;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Filters.lte;
import static com.mongodb.client.model.Indexes.ascending;

public class Reservations {
    private final Connexion cx;
    private final MongoCollection<Document> reservationsCollection;

    /**
     * Constructeur de la classe Reservations.
     */
    public Reservations(Connexion cx) {
        this.cx = cx;
        this.reservationsCollection = cx.getDatabase().getCollection("Reservation");
    }

    /**
     * Retourne la connexion associée.
     */
    public Connexion getConnexion() {
        return cx;
    }

    /**
     * Récupère la liste des réservations d'un client.
     */
    public List<Reservation> getReservationClient(int idClient) {
        List<Reservation> reservations = new LinkedList<>();
        MongoCursor<Document> cursor = reservationsCollection
                .find(eq("idClient", idClient))
                .sort(ascending("dateReservation"))
                .iterator();
        try {
            while (cursor.hasNext()) {
                reservations.add(new Reservation(cursor.next()));
            }
        } finally {
            cursor.close();
        }
        return reservations;
    }

    /**
     * Récupère la liste des réservations d'une chambre.
     */
    public List<Reservation> getReservationChambre(int idChambre) {
        List<Reservation> reservations = new LinkedList<>();
        MongoCursor<Document> cursor = reservationsCollection
                .find(eq("idChambre", idChambre))
                .sort(ascending("dateReservation"))
                .iterator();
        try {
            while (cursor.hasNext()) {
                reservations.add(new Reservation(cursor.next()));
            }
        } finally {
            cursor.close();
        }
        return reservations;
    }

    /**
     * Enregistre une nouvelle réservation.
     */
    public Reservation reserver(int idClient,int idReservation, int idChambre, Double prixTotal, Date dateDebut, Date dateFin)
            throws AubergeInnException {
        Reservation reservation = new Reservation(idClient, idReservation, idChambre, prixTotal, dateDebut, dateFin);
        try {
            reservationsCollection.insertOne(reservation.toDocument());
        } catch (MongoException e) {
            throw new AubergeInnException("Erreur lors de l'insertion de la réservation : " + e.getMessage());
        }
        return reservation;
    }

    /**
     * Modifie une réservation existante.
     */
    public boolean modifierReservation(int idClient, int idChambre, Date nouvelleDateDebut, Date nouvelleDateFin, Double nouveauPrix) {
        Document updateFields = new Document()
                .append("dateDebut", nouvelleDateDebut)
                .append("dateFin", nouvelleDateFin)
                .append("prixTotal", nouveauPrix);

        Document update = new Document("$set", updateFields);

        return reservationsCollection.updateOne(
                and(eq("idClient", idClient), eq("idChambre", idChambre)),
                update
        ).getModifiedCount() > 0;
    }

    /**
     * Supprime une réservation pour un client et une chambre spécifiques.
     */
    public boolean supprimerReservation(int idClient, int idChambre) {
        return reservationsCollection
                .deleteOne(and(eq("idClient", idClient), eq("idChambre", idChambre)))
                .getDeletedCount() > 0;
    }

    /**
     * Alias propre pour getReservationClient (évite redondance).
     */
    public List<Reservation> getListeReservationsClient(int idClient) {
        return getReservationClient(idClient);
    }

    /**
     * Lecture de la liste des chambres libres
     */
//

    public List<Chambre> getListeChambresLibres(Date dateDebut, Date dateFin, List<Chambre> chambres) {
        List<Reservation> reservations = new LinkedList<>();

        MongoCursor<Document> cursor = reservationsCollection.find(
                and(
                        lt("dateFin", dateFin),
                        gt("dateDebut", dateDebut)
                )
        ).iterator();
        System.out.println("Chambres totales : " + chambres.size());
        System.out.println("Réservations conflictuelles : " + reservations.size());
        //System.out.println("Chambres réservées : " + reservedChambreIds);


        try {
            while (cursor.hasNext()) {
                reservations.add(new Reservation(cursor.next()));
            }
        } finally {
            cursor.close();
        }

        // Récupère les id des chambres réservées
        Set<Integer> reservedChambreIds = reservations.stream()
                .map(Reservation::getIdChambre)
                .collect(Collectors.toSet());

        // Filtre les chambres disponibles
        return chambres.stream()
                .filter(chambre -> !reservedChambreIds.contains(chambre.getIdChambre()))
                .collect(Collectors.toList());
    }



}
