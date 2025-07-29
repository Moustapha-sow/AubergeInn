package tp.collections;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import main.AubergeInnException;
import tp.bdd.Connexion;
import tp.objets.Reservation;
import org.bson.Document;


import java.util.*;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Indexes.ascending;


// classe Reservations
public class Reservations
{
    ;
    private final Connexion cx;
    private final MongoCollection<Document> reservationsCollection;


    /**
     * Constructeur de la classe Reservations.
     *

     */
    public Reservations(Connexion cx)  {
        this.cx = cx;
        reservationsCollection = cx.getDatabase().getCollection("Reservation");
    }

    /**
     * Retourner la connexion associée.
     *
     * @return La connexion associée à la classe Reservations.
     */
    public Connexion getConnexion() {
        return cx;
    }


    /**
     * Récupère la liste des réservations associées à un client, triées par date de réservation.

     */
    public List<Reservation> getReservationClient(int idClient)
    {
        List<Reservation> reservations = new LinkedList<>();
        MongoCursor<Document> doc =  reservationsCollection.find(eq("idClient", idClient)).sort(ascending("dateReservation")).iterator();
        try

        {
            while (doc.hasNext())
            {
                reservations.add(new Reservation(doc.next()));
            }

        }
        finally
        {
            doc.close();
        }


        return  reservations;
    }

    // list getReservationChambre
    public List<Reservation> getReservationChambre(int idChambre)
    {

        List<Reservation> reservations = new LinkedList<>();
        MongoCursor<Document> doc =  reservationsCollection.find(eq("idChambre", idChambre)).sort(ascending("dateReservation")).iterator();
        try
        {
            while (doc.hasNext())
            {
                reservations.add(new Reservation(doc.next()));
            }
        }

        finally
        {
            doc.close();
        }

        return  reservations;

    }

    /**
     * Effectue une réservation pour un client dans une chambre donné

     */
    public Reservation reserver(int idClient, int idChambre, double prix_total, Date date_debut, Date date_fin)
            throws AubergeInnException
    {

        Reservation reservation = new Reservation(idClient,idChambre, prix_total, date_debut, date_fin);

        reservationsCollection.insertOne(reservation.toDocument());

        return reservation;
    }



    // fonction pour modifier la reservation
    public boolean modifierReservation(int idClient, int idChambre, Date nouvelleDateDebut, Date nouvelleDateFin, double nouveauPrix) {
        Document updateFields = new Document()
                .append("date_debut", nouvelleDateDebut)
                .append("date_fin", nouvelleDateFin)
                .append("prix_total", nouveauPrix);

        Document update = new Document("$set", updateFields);

        return reservationsCollection.updateOne(
                eq("idClient", idClient),
                update
        ).getModifiedCount() > 0;
    }




    public boolean supprimerReservation(int idClient, int idChambre) {
        return reservationsCollection
                .deleteOne(eq("idClient", idClient))
                .getDeletedCount() > 0;
    }






}
