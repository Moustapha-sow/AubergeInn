// Ce fichier sert a tester les fonctionnnalités de mongoDB ,
// L ajout, suppression, modiification, et affichage des entités
// pour tester ceux ci , vous pouvez decommenter chaque partie .



package tp;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Sorts;

import tp.bdd.Connexion;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.util.Arrays;
import java.util.Date;

import static com.mongodb.client.model.Filters.eq;

public class TestMongo {
    public static void main(String[] args) {
        try {
            // Connexion à MongoDB local ou distant
           // Connexion cx = new Connexion("local", "aubergeInn", "", "");
            // ou :
             Connexion cx = new Connexion("dinf", "ift287_69db", "ift287_69", "Eithavangae7");

            MongoDatabase db = cx.getDatabase();

            // test pour client
            MongoCollection<Document> clients = db.getCollection("Client");

            // test pour chambre
            MongoCollection<Document> chambres = db.getCollection("Chambre");

            // test pour collection
            MongoCollection<Document> reservations = db.getCollection("Reservation");

            Document client1 = new Document("nom", "SSS")
                    .append("prenom", "SSS")
                    .append("age", 25);

            Document client2 = new Document("nom", "ZZZZ")
                    .append("prenom", "NNN")
                    .append("age", 30);

            clients.insertMany(Arrays.asList(client1, client2));
            System.out.println("Clients insérés !");


            /*
            for (Document doc : clients.find()) {
                System.out.println(doc.toJson());
            }

            Document client = clients.find(eq("nom", "Fall")).first();
            if (client != null) {
                System.out.println("Client trouvé : " + client.toJson());
            } else {
                System.out.println("Client non trouvé");
            }

            clients.updateOne(eq("nom", "Fall"), new Document("$set", new Document("age", 26)));
            System.out.println("Client mis à jour");

            for (Document doc : clients.find()) {
                System.out.println(doc.toJson());
            }


            clients.deleteOne(eq("nom", "Ba"));
            System.out.println("Client supprimé");






            Document ch1 = new Document("noChambre", 101)
                    .append("type", "simple")
                    .append("prix", 50);

            Document ch2 = new Document("noChambre", 102)
                    .append("type", "double")
                    .append("prix", 80);

            chambres.insertMany(Arrays.asList(ch1, ch2));
            System.out.println("Chambres insérées !");


            for (Document chambre : chambres.find().sort(Sorts.ascending("prix"))) {
                System.out.println(chambre.toJson());
            }


            Document res = new Document("idReservation", 1)
                    .append("idClient", 1)
                    .append("noChambre", 101)
                    .append("dateDebut", new Date())
                    .append("dateFin", new Date(System.currentTimeMillis() + 86400000)); // +1 jour

            reservations.insertOne(res);
            System.out.println("Réservation insérée !");




            db.getCollection("Client").deleteMany(new Document());
            System.out.println("Tous les clients ont été supprimés !");



            for(Document cl: clients.find()){
                System.out.println(cl.toJson());
            }
            db.getCollection("Communaute").deleteMany(new Document());
            System.out.println("toutes les communautés ont ete supprimées");


/*
             */
            cx.fermer();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
