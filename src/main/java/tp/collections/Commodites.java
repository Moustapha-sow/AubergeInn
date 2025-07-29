package tp.collections;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.types.ObjectId;
import tp.bdd.Connexion;
import tp.objets.Commodite;
import org.bson.Document;

//import java.util.LinkedList;
import java.util.*;

import static com.mongodb.client.model.Filters.eq;


// classe Commodites
public class Commodites
{
    private final Connexion cx;
    private final MongoCollection<Document> commoditesCollection;
    private final MongoCollection<Document> commoditesChambres_Collection;


  // constructeur de la classe
    public Commodites(Connexion cx) {
        this.cx = cx;
        commoditesCollection = cx.getDatabase().getCollection("Commodite");


        commoditesChambres_Collection = cx.getDatabase().getCollection("Chambre_commodite");
    }



    public Connexion getConnexion() {
        return cx;
    }


    public boolean existe(int idCommodite) {
        return commoditesCollection.find(eq("idCommodite", idCommodite)).first() != null;
    }

    /**
     * Ajoute une nouvelle commodité dans la base de données.
     */

    public Commodite ajouterCommodite(int idCommodite, String description, float surplus_Prix) {
        Commodite comm = new Commodite(idCommodite, description, surplus_Prix);
        commoditesCollection.insertOne(comm.toDocument());
        return comm;
    }

    /**
     * Effectue une liaison une commodité à une chambre dans la base de données.
     */
    public Commodite inclureCommodite(int idCommodite, int idChambre) {
        Commodite comm = new Commodite(idCommodite, idChambre);

        commoditesChambres_Collection.insertOne(comm.toDocInclure());
        return comm;
    }



    /**
     * Supprime la liaison entre une commodité et une chambre.
     *
     */
    public void enleverCommodite(int idCommodite, int idChambre) {
        ObjectId _id = null;


        MongoCursor<Document> doc = commoditesChambres_Collection.find().iterator();
        try {
            while (doc.hasNext()) {
                Document document = doc.next();
                if (document.getInteger("idCommodite") == idCommodite && document.getInteger("idChambre") == idChambre) {
                    _id = document.getObjectId("_id");
                }
            }
        } finally {
            doc.close();
        }



        commoditesChambres_Collection.deleteOne(eq("_id", _id)).getDeletedCount();
    }


    // cette fonction est facultative
    public boolean modifierCommodite(int idCommodite, String nouvelleDescription, float nouveauSurplus) {
        Document updateFields = new Document()
                .append("description", nouvelleDescription)
                .append("surplus_Prix", nouveauSurplus);
        Document update = new Document("$set", updateFields);
        return commoditesCollection.updateOne(eq("idCommodite", idCommodite), update).getModifiedCount() > 0;
    }


    /**
     * Vérifie si il existe une liaison entre une commodité et une chambre
     *

     */
    public boolean existeChambreCommodite(int idCommodite, int idChambre) {
        // Parcours des documents dans la collection de liaisons
        MongoCursor<Document> doc = commoditesChambres_Collection.find().iterator();
        try {
            while (doc.hasNext()) {
                Document document = doc.next();

                if (document.getInteger("idCommodite") == idCommodite && document.getInteger("idChambre") == idChambre) {
                    return true;
                }
            }
        } finally {
            doc.close();
        }

        return false;
    }



    public Commodite getCommodite(int idCommodite) {

        Document d = commoditesCollection.find(eq("idCommodite", idCommodite)).first();
        if (d != null) {

            return new Commodite(d);
        }
        return null;
    }

    /**
     * Récupère la liste des commodités associées à une chambre.
     *

     */
    public List<Commodite> getCommoditeChambre(int idChambre) {
        List<Commodite> commoditeList = new LinkedList<>();

        try (MongoCursor<Document> doc = commoditesChambres_Collection.find(eq("idChambre", idChambre)).iterator()) {
            while (doc.hasNext()) {
                Document document = doc.next();
                // Ajout de chaque commodité associée à la chambre dans la liste
                commoditeList.add(getCommodite(document.getInteger("idCommodite")));
            }
        }
        return commoditeList;
    }



}

