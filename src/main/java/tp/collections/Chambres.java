package tp.collections;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import tp.bdd.Connexion;
import tp.objets.Chambre;
import org.bson.Document;
//import javax.swing.text.Document;
import java.util.LinkedList;
import java.util.List;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.addToSet;
import static com.mongodb.client.model.Updates.pull;

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
        return chambresCollection.find(eq("idChambre", idChambre)).first() != null;

    }

    /**
     * Ajout d'une nouvelle commodité à la chambre.
     */
    public void inclureCommodite(int idChambre, int idCommodite) {
        chambresCollection.updateOne(eq("idChambre", idChambre), addToSet("idCommodites", idCommodite));
    }


   // l ajout d une nouvelle chambre
    public Chambre ajouterChambre(int idChambre, String nomChambre, String typeLit, Double prixBase) {

        Chambre chambre = new Chambre(idChambre, nomChambre, typeLit, prixBase);


        chambresCollection.insertOne(chambre.toDocument());
        return chambre;
    }

    /**
     * Supprime une chambre de la base de données.
     */
    public boolean supprimerChambre(int idChambre) {

        return chambresCollection.deleteOne(eq("idChambre", idChambre)).getDeletedCount() > 0;
    }

// fonction modifierChambre
    public boolean modifierChambre(int idChambre, String nouveauNom, String nouveauTypeLit, Double nouveauPrix) {
        Document updateFields = new Document()
                .append("nomChambre", nouveauNom)
                .append("typeLit", nouveauTypeLit)
                .append("prixBase", nouveauPrix);

        Document update = new Document("$set", updateFields);
        return chambresCollection.updateOne(eq("idChambre", idChambre), update).getModifiedCount() > 0;
    }




    public Chambre getChambre(int idChambre) {
        Document doc = chambresCollection.find(eq("idChambre", idChambre)).first();

        if (doc != null) {
            return new Chambre(doc);
        }

        return null;
    }


//    public List<Chambre> getAllChambres() {
//        List<Chambre> chambres = new LinkedList<>();
//        try (MongoCursor<Document> doc = chambresCollection.find().iterator()) {
//            while (doc.hasNext()) {
//
//                Document document = doc.next();
//
//                // Crée un objet Chambre à partir du document et l'ajoute à la liste
//                chambres.add(new Chambre(document));
//            }
//        }
//
//        return chambres;
//    }


    /**
     * Lecture de toutes les chambres
     */
    public List<Chambre> getAllChambres(){
        List<Chambre> chambres = new LinkedList<>();
        MongoCursor<Document> cursor = chambresCollection.find().iterator();
        try {
            while(cursor.hasNext()){
                chambres.add(new Chambre(cursor.next()));
            }
        } finally {
            cursor.close();
        }
        return chambres;
    }

    /**
     * Lecture de la liste des commodites d'une chambre
     */
    public List<Integer> getListeCommoditesChambre(Chambre chambre)
    {
        if (chambre != null) {
            return chambre.getCommodites();
        }
        return new LinkedList<>();
    }

    /**
     *  Enlever une commodité lié à une chambre
     */
    public void supprimerCommodite(int idChambre, int idCommodite) {
        chambresCollection.updateOne(eq("idChambre", idChambre), pull("idCommodites", idCommodite));
    }

    /**
     *  Valider si une commodité est lié à une chambre
     */
    public boolean validerCommoditeChambre(int idChambre, int idCommodite) {
        Chambre chambre = getChambre(idChambre);
        return chambre != null && chambre.getCommodites().contains(idCommodite);
    }
}
