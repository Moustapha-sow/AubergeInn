// ce fichier est une reference(reformulation) basée sur le fichier zip de bibliotheque disponible sur moodle
// neccesaire au cas ou il y aura un besoin de l'ouvrir via turning
// merci pour la comprehension
package AubergeInn;

import main.AubergeInnException;

import java.io.*;
import java.util.Date;
import java.util.StringTokenizer;

///  classe AubergeInn
public class AubergeInn
{
    private static GestionAubergInn gestionInn;
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception
    {
        if (args.length < 4)
        {
            System.out.println("Usage: java AubergeInn.AubergeInn <serveur> <bd> <user> <password> [<fichier-transactions>]");
            return;
        }

        AubergeInn instanceInn = null;

        try
        {

            instanceInn = new AubergeInn(args[0], args[1], args[2], args[3]);
            BufferedReader reader = ouvrirFichier(args);

            String transaction = lireTransaction(reader);

            while (!finTransaction(transaction))
            {
                executerTransaction(transaction);
                transaction = lireTransaction(reader);
            }
        }

        finally
        {
            if (instanceInn != null)
                instanceInn.fermer();
        }

    }
    public AubergeInn(String serveur, String bd, String user, String pass)
            throws Exception
    {
        gestionInn = new GestionAubergInn(serveur, bd, user, pass);
    }
    // fermeture de la connexion
    public void fermer() throws Exception
    {
        gestionInn.fermer();
    }

    /**
     * Decodage et traitement d'une transaction
     */
    static void executerTransaction(String transaction)
            throws Exception, AubergeInnException
    {
        try
        {
            System.out.print(transaction);

            // Decoupage de la transaction en mots
            StringTokenizer tokenizer = new StringTokenizer(transaction, " ");
            if (tokenizer.hasMoreTokens())
            {
                String command = tokenizer.nextToken();

                if (command.equals("ajouterClient"))
                {

                    int idClient = readInt(tokenizer);
                    String nom = readString(tokenizer);

                    String prenom = readString(tokenizer);
                    int age = readInt(tokenizer);

                    gestionInn.getGestionClient().ajouterClient(idClient, nom, prenom,age);
                }


                else if (command.equals("supprimerClient"))
                {
                    // Lecture des parametres
                    int idClient = readInt(tokenizer);

                    gestionInn.getGestionClient().supprimerClient(idClient);
                }


                else if (command.equals("ajouterChambre"))
                {

                    int idChambre = readInt(tokenizer);
                    String nomChambre = readString(tokenizer);

                    String typeLit = readString(tokenizer);
                    float prix = readInt(tokenizer);
                    gestionInn.getGestionChambre().ajouterChambre(idChambre, nomChambre, typeLit, prix);
                }


                else if (command.equals("supprimerChambre"))
                {

                    int idChambre = readInt(tokenizer);
                    gestionInn.getGestionChambre().supprimerChambre(idChambre);
                }


                else if (command.equals("ajouterCommodite"))
                {
                    int idCommodite = readInt(tokenizer);
                    String description = readString(tokenizer);

                    float surplusPrix = readInt(tokenizer);
                    gestionInn.getGestionCommodite().ajouterCommodite(idCommodite, description, surplusPrix);
                }


                else if (command.equals("inclureCommodite"))
                {
                    int idChambre = readInt(tokenizer);
                    int idCommodite = readInt(tokenizer);

                    gestionInn.getGestionCommodite().inclureCommodite(idChambre, idCommodite);
                }


                else if (command.equals("enleverCommodite"))
                {
                    int idChambre = readInt(tokenizer);
                    int idCommodite = readInt(tokenizer);

                    gestionInn.getGestionCommodite().enleverCommodite(idChambre, idCommodite);
                }

                else if (command.equals("afficherChambresLibres"))
                {
                    gestionInn.getGestionChambre().afficherChambresLibres();
                }


                else if (command.equals("afficherClient"))
                {
                    int idClient = readInt(tokenizer);

                    gestionInn.getGestionClient().afficherClient(idClient);
                }


                else if (command.equals("afficherChambre"))
                {
                    int idChambre = readInt(tokenizer);
                    gestionInn.getGestionChambre().afficherChambre(idChambre);
                }


                else if (command.equals("reserver"))
                {
                    int idClient = readInt(tokenizer);

                    int idChambre = readInt(tokenizer);
                    Date dateDebut = readDate(tokenizer);

                    Date dateFin = readDate(tokenizer);
                    gestionInn.getGestionReservation().reserver(idClient, idChambre, dateDebut, dateFin);
                }


                else
                {
                    System.out.println(" : Transaction non reconnue");
                }
                System.out.print('\n');
            }
        }

        catch (Exception e)
        {
            System.out.println("** " + e);
        }
    }


    // ****************************************************************
    // *  pas de modif
    // ****************************************************************
    /**
     * Ouvre le fichier de transaction, ou lit à partir de System.in
     */
    public static BufferedReader ouvrirFichier(String[] args) throws FileNotFoundException
    {
        if (args.length < 5)
            // Lecture au clavier
            return new BufferedReader(new InputStreamReader(System.in));
        else
            // Lecture dans le fichier passe en parametre
            return new BufferedReader(new InputStreamReader(new FileInputStream(args[4])));
    }

    /**
     * Lecture d'une transaction
     */
    static String lireTransaction(BufferedReader reader) throws IOException
    {
        return reader.readLine();
    }

    /**
     * Verifie si la fin du traitement des transactions est atteinte.
     */
    static boolean finTransaction(String transaction)
    {
        // fin de fichier atteinte
        return (transaction == null || transaction.equals("quitter"));
    }

    /** Lecture d'une chaine de caracteres de la transaction entree a l'ecran */
    static String readString(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
            return tokenizer.nextToken();
        else
            throw new Exception("Autre parametre attendu");
    }

    /**
     * Lecture d'un int java de la transaction entree a l'ecran
     */
    static int readInt(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
        {
            String token = tokenizer.nextToken();
            try
            {
                return Integer.valueOf(token).intValue();
            }
            catch (NumberFormatException e)
            {
                throw new Exception("Nombre attendu a la place de \"" + token + "\"");
            }
        }
        else
            throw new Exception("Autre parametre attendu");
    }

    static Date readDate(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
        {
            String token = tokenizer.nextToken();
            try
            {
                return FormatDate.convertirDate(token);
            }
            catch (IllegalArgumentException e)
            {
                throw new Exception("Date dans un format invalide - \"" + token + "\"");
            }
        }
        else
            throw new Exception("Autre parametre attendu");
    }

}

