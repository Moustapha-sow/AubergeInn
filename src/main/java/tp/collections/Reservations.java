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
    public Reservation reserver(int idClient, int idChambre, double prixTotal, Date dateDebut, Date dateFin)
            throws AubergeInnException {
        Reservation reservation = new Reservation(idClient, idChambre, prixTotal, dateDebut, dateFin);
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
    public boolean modifierReservation(int idClient, int idChambre, Date nouvelleDateDebut, Date nouvelleDateFin, double nouveauPrix) {
        Document updateFields = new Document()
                .append("date_debut", nouvelleDateDebut)
                .append("date_fin", nouvelleDateFin)
                .append("prix_total", nouveauPrix);

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
    public List<Chambre> getListeChambresLibres(Date date_debut, Date date_fin, List<Chambre> chambres) {
        List<Reservation> reservations = new LinkedList<>();
        MongoCursor<Document> cursor = reservationsCollection.find(or(
                and(lte("date_debut", date_debut), gt("date_fin", date_debut)),
                and(lt("date_debut", date_fin), gte("date_fin", date_fin)),
                and(gt("date_debut", date_debut), lt("date_fin", date_fin))
        )).iterator();

        try {
            while (cursor.hasNext()) {
                reservations.add(new Reservation(cursor.next()));
            }
        } finally {
            cursor.close();
        }

        // On récupère les id des chambres réservées
        Set<Integer> reservedChambreIds = new HashSet<>();
        for (Reservation reservation : reservations) {
            int idChambre = reservation.getIdChambre();
            reservedChambreIds.add(Math.toIntExact(idChambre));
        }

        // On filtre les chambres qui ne sont pas réservées et les retourne
        return chambres.stream()
                .filter(chambre -> !reservedChambreIds.contains(chambre.getIdChambre()))
                .collect(Collectors.toList());
    }



}
