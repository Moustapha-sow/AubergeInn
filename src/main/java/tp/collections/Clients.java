package tp.collections;

import com.mongodb.client.MongoCollection;
import main.AubergeInnException;
import tp.bdd.Connexion;
import tp.objets.Client;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;

// classe Clients
public class Clients
{
    private final Connexion cx;
    private final MongoCollection<Document> clientCollection;


    // constructeur de la classe
    public Clients(Connexion cx)  {
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
        try {
        return clientCollection.find(eq("idClient", idClient)).first() != null;
    } catch (Exception e) {
    return false; // ou log l’erreur
    }
    }


    // fonction pour ajouter des info du client
    public Client ajouterClient(int idClient, String nom, String prenom, int age) throws AubergeInnException {
        try {
        Client client = new Client(idClient, nom, prenom, age);
        clientCollection.insertOne(client.toDocument());
        return client;
    } catch (Exception e) {
    throw new AubergeInnException("Erreur lors de l'ajout du client : " + e.getMessage());
    }
    }

    /**
     *la suppression d un client
     * @param idClient L'identifiant du client à supprimer.
     * @return true si le client si supprimé
     */
    public boolean supprimerClient(int idClient) throws AubergeInnException {
        try {
        return clientCollection.deleteOne(eq("idClient", idClient)).getDeletedCount() > 0;
    } catch (Exception e) {
    throw new AubergeInnException("Erreur lors de la suppression du client : " + e.getMessage());
    }
    }


    // ffonction pour modifier les info du client
    public boolean modifierClient(int idClient, String nouveauNom, String nouveauPrenom, int nouvelAge) throws AubergeInnException {
        try {
        Document update = new Document("$set", new Document("nom", nouveauNom)
                .append("prenom", nouveauPrenom)
                .append("age", nouvelAge));
        return clientCollection.updateOne(eq("idClient", idClient), update).getModifiedCount() > 0;
    } catch (Exception e) {
    throw new AubergeInnException("Erreur lors de la modification du client : " + e.getMessage());
}
    }



    public Client getClient(int idClient) throws AubergeInnException {
        try {

        Document doc = clientCollection.find(eq("idClient", idClient)).first();
        if (doc != null) {

            return new Client(doc);
        }
        return null;
        } catch (Exception e) {
            throw new AubergeInnException("Erreur lors de la récupération du client : " + e.getMessage());
        }
    }
}
