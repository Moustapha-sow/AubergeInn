package tp.collections;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import tp.bdd.Connexion;
import tp.objets.Client;
import org.bson.Document;

import java.util.LinkedList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

// classe Clients
public class Clients {
    private final Connexion cx;
    private final MongoCollection<Document> clientCollection;


    // constructeur de la classe
    public Clients(Connexion cx) {
        this.cx = cx;
        clientCollection = cx.getDatabase().getCollection("Client");
    }

    /**
     * Retourne la connexion associée à la gestion des clients.
     */
    public Connexion getConnexion() {
        return cx;
    }


    public boolean existe(int idClient) {
        return clientCollection.find(eq("idClient", idClient)).first() != null;
    }


    // fonction pour ajouter des info du client
    public Client ajouterClient(int idClient, String nom, String prenom, int age) {
        Client client = new Client(idClient, nom, prenom, age);
        clientCollection.insertOne(client.toDocument());
        return client;
    }

    /**
     * la suppression d un client
     *
     * @param idClient L'identifiant du client à supprimer.
     * @return true si le client si supprimé
     */
    public boolean supprimerClient(int idClient) {
        return clientCollection.deleteOne(eq("idClient", idClient)).getDeletedCount() > 0;
    }

    // ffonction pour modifier les info du client
    public boolean modifierClient(int idClient, String nouveauNom, String nouveauPrenom, int nouvelAge) {
        Document update = new Document("$set", new Document("nom", nouveauNom)
                .append("prenom", nouveauPrenom)
                .append("age", nouvelAge));
        return clientCollection.updateOne(eq("idClient", idClient), update).getModifiedCount() > 0;
    }


    public Client getClient(int idClient) {

        Document doc = clientCollection.find(eq("idClient", idClient)).first();
        if (doc != null) {

            return new Client(doc);
        }
        return null;
    }
    /*
    public Client getClient(int idClient) {
        Document c =clients.find(eq("idClient", idClient)).first();
        if(c != null){
            return new Client(c);
        }
        return null;

    }

     */

    /**
     * Obtenir tous les clients dans la BD
     */
    public List<Client> getTousLesClients() {
        List<Client> clients = new LinkedList<>();

        try (MongoCursor<Document> cursor = clientCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                clients.add(new Client(doc));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return clients;
    }
}
