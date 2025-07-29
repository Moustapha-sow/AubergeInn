package tp.bdd;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import main.AubergeInnException;

// classe connexion
public class Connexion {

    private MongoClient client;
    private MongoDatabase database;

    // Ouverture de la connexion

    public Connexion( String serveur, String bd, String user, String pass) throws AubergeInnException
    {
        // pour le serveur local
        if (serveur.equals("local")) {
            client = MongoClients.create();

        }
        // pour le serveur distant
        else if (serveur.equals("dinf")) {

           client = MongoClients.create("mongodb://"+user+":"+pass+"@bd-info2.dinf.usherbrooke.ca:27017/"+bd+"?ssl=true");

        }
        else {
            // aucun serveur trouvé
            throw new AubergeInnException("serveur inconnu");
        }


        database = client.getDatabase(bd);

        System.out.println("Ouverture de la connexion :");
        System.out.println("Connecté à la base MongoDB '" + bd + "' avec l'utilisateur '" + user + "'");

    }

    public void fermer()
    {
        client.close();

        System.out.println("Connexion fermée avec succes");
    }


    public MongoClient getConnexion() {
        return client;
    }

    public MongoDatabase getDatabase()
    {
        return database;
    }

}
