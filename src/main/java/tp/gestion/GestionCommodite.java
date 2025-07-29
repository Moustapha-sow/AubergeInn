package tp.gestion;

import main.AubergeInnException;
import tp.bdd.Connexion;
import tp.collections.Chambres;
import tp.collections.Commodites;
import tp.objets.Chambre;
import tp.objets.Commodite;


// classe GestionCommodite
public class GestionCommodite {
    private final Commodites commodites;
    private final Chambres chambres;
    private final Connexion cx;


  // constructeur de la classe
    public GestionCommodite(Commodites commodites, Chambres chambres)
            throws AubergeInnException {

        this.cx = commodites.getConnexion();

        // Vérifie que les instances de chambre et de commodites utilisent la même connexion au serveur
        if (chambres.getConnexion() != commodites.getConnexion())
            throw new AubergeInnException("Les instances de chambre et de commodites n'utilisent pas la même connexion au serveur");


        this.commodites = commodites;
        this.chambres = chambres;
    }

    /**
     * Ajoute une commodité avec les informations fournies dans la BD
     *
     */
    public void ajouterCommodite(int idCommodite, String description, float surplus_Prix) throws Exception {
        try {

            if (commodites.existe(idCommodite))
                throw new AubergeInnException("La commodité " + idCommodite + " existe déjà.");

            // Ajout d'une commodité dans la table des commodités
            commodites.ajouterCommodite(idCommodite, description, surplus_Prix);
        } catch (Exception e) {
            throw e;
        }
    }


    /**
     * Inclusion d'une commodité à une chambre.
     *
     */
    public void inclureCommodite(int idChambre, int idCommodite)
            throws Exception {
        try {



            Chambre chambre = chambres.getChambre(idChambre);
            if (chambre == null)
                throw new AubergeInnException("Chambre inexistante: " + idChambre);

            // Vérifier que la commodite existe
            Commodite commodite = commodites.getCommodite(idCommodite);
            if (commodite == null)
                throw new AubergeInnException("Commodité inexistante: " + idCommodite);


            if (commodites.existeChambreCommodite(idCommodite, idChambre))
                throw new AubergeInnException("La chambre " + idChambre + " possède déjà la commodité " + idCommodite);

            // Ajouter la commodité à la chambre
            commodites.inclureCommodite(idCommodite, idChambre);

        } catch (Exception e) {
            throw e;
        }
    }



    /**
     * Enlève une commodité d'une chambre.
     *

     */
    public void enleverCommodite(int idChambre, int idCommodite)
            throws Exception {
        try {



            Chambre chambre = this.chambres.getChambre(idChambre);
            if (chambre == null)
                throw new AubergeInnException("Chambre inexistante: " + idChambre);


            Commodite commodite = commodites.getCommodite(idCommodite);
            if (commodite == null)
                throw new AubergeInnException("Commodité inexistante: " + idCommodite);

            // Vérifier si la chambre a cette commodité à enlever
            if (!commodites.existeChambreCommodite(idCommodite, idChambre))
                throw new AubergeInnException("La chambre " + idChambre + " ne possède pas la commodité " + idCommodite);

            commodites.enleverCommodite(idCommodite, idChambre);
        } catch (Exception e) {

            throw e;
        }
    }

}

