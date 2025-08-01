package tp.collections;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import main.AubergeInnException;
import tp.bdd.Connexion;
import tp.objets.Chambre;
import org.bson.Document;
//import javax.swing.text.Document;
import java.util.LinkedList;
import java.util.List;
import static com.mongodb.client.model.Filters.eq;

//import static javax.management.Query.eq;

// classe Chambres
public class Chambres {

    private Connexion cx;
    private MongoCollection<Document> chambresCollection;

    // constructeur de la classe
    public Chambres(Connexion cx){
        this.cx = cx;
        chambresCollection = cx.getDatabase().getCollection("Chambre");
    }



    public Connexion getConnexion() {
        return cx;
    }


    // verifie si une chambre existe en cherchant l id
    public boolean existe(int idChambre) {
        try {
        return chambresCollection.find(eq("idChambre", idChambre)).first() != null;
        } catch (Exception e) {
            return false;
        }
    }


   // l ajout d une nouvelle chambre
    public Chambre ajouterChambre(int idChambre, String nom_chambre, String type_lit, double prix_base) throws AubergeInnException {
        try {

        Chambre chambre = new Chambre(idChambre, nom_chambre, type_lit, prix_base);


        chambresCollection.insertOne(chambre.toDocument());
        return chambre;
        } catch (Exception e) {
            throw new AubergeInnException("Erreur lors de l'ajout de la chambre : " + e.getMessage());
        }
    }

    /**
     * Supprime une chambre de la base de données.
     */
    public boolean supprimerChambre(int idChambre) throws AubergeInnException {
        try {

        return chambresCollection.deleteOne(eq("idChambre", idChambre)).getDeletedCount() > 0;
    } catch (Exception e) {
        throw new AubergeInnException("Erreur lors de la suppression de la chambre : " + e.getMessage());
    }
    }

// fonction modifierChambre
    public boolean modifierChambre(int idChambre, String nouveauNom, String nouveauTypeLit, double nouveauPrix) throws AubergeInnException {
        try {
        Document updateFields = new Document()
                .append("nom_chambre", nouveauNom)
                .append("type_lit", nouveauTypeLit)
                .append("prix_base", nouveauPrix);

        Document update = new Document("$set", updateFields);
        return chambresCollection.updateOne(eq("idChambre", idChambre), update).getModifiedCount() > 0;
    } catch (Exception e) {
        throw new AubergeInnException("Erreur lors de la modification de la chambre : " + e.getMessage());
    }
    }




    public Chambre getChambre(int idChambre) throws AubergeInnException {
        try {
        Document doc = chambresCollection.find(eq("idChambre", idChambre)).first();

        if (doc != null) {
            return new Chambre(doc);
        }

        return null;
        } catch (Exception e) {
            throw new AubergeInnException("Erreur lors de la récupération de la chambre : " + e.getMessage());
        }
    }


    public List<Chambre> getAllChambres() throws AubergeInnException {
        List<Chambre> chambres = new LinkedList<>();


        try (MongoCursor<Document> doc = chambresCollection.find().iterator()) {
            while (doc.hasNext()) {

                Document document = doc.next();

                // Crée un objet Chambre à partir du document et l'ajoute à la liste
                chambres.add(new Chambre(document));
            }
        } catch (Exception e) {
            throw new AubergeInnException("Erreur lors de la récupération des chambres : " + e.getMessage());
        }


        return chambres;
    }
}
