package tp.gestion;

import main.AubergeInnException;
import tp.bdd.Connexion;
import tp.collections.Chambres;
import tp.collections.Commodites;
import tp.objets.Chambre;
import tp.objets.Commodite;

import java.util.List;

public class GestionCommodite {
    private final Commodites commodites;
    private final Chambres chambres;
    private final Connexion cx;

    public GestionCommodite(Commodites commodites, Chambres chambres) throws AubergeInnException {
        this.cx = commodites.getConnexion();

        if (chambres.getConnexion() != commodites.getConnexion()) {
            throw new AubergeInnException("Les instances de chambre et de commodités n'utilisent pas la même connexion au serveur");
        }

        this.commodites = commodites;
        this.chambres = chambres;
    }

    public void ajouterCommodite(int idCommodite, String description, Double surplusPrix) throws AubergeInnException {
        if (commodites.existe(idCommodite)) {
            throw new AubergeInnException("La commodité #" + idCommodite + " existe déjà.");
        }
        commodites.ajouterCommodite(idCommodite, description, surplusPrix);
    }

    public void inclureCommodite(int idChambre, int idCommodite) throws AubergeInnException {
        Chambre chambre = chambres.getChambre(idChambre);
        if (chambre == null) {
            throw new AubergeInnException("Chambre inexistante: " + idChambre);
        }

        Commodite commodite = commodites.getCommodite(idCommodite);
        if (commodite == null) {
            throw new AubergeInnException("Commodité inexistante: " + idCommodite);
        }

        if (commodites.existeChambreCommodite(idCommodite, idChambre)) {
            throw new AubergeInnException("La chambre " + idChambre + " possède déjà la commodité " + idCommodite);
        }

        commodites.inclureCommodite(idCommodite, idChambre);
    }

    public void enleverCommodite(int idChambre, int idCommodite) throws AubergeInnException {
        Chambre chambre = chambres.getChambre(idChambre);
        if (chambre == null) {
            throw new AubergeInnException("Chambre inexistante: " + idChambre);
        }

        Commodite commodite = commodites.getCommodite(idCommodite);
        if (commodite == null) {
            throw new AubergeInnException("Commodité inexistante: " + idCommodite);
        }

        if (!commodites.existeChambreCommodite(idCommodite, idChambre)) {
            throw new AubergeInnException("La chambre " + idChambre + " ne possède pas la commodité " + idCommodite);
        }

        commodites.enleverCommodite(idCommodite, idChambre);
    }

    public Commodite getCommodite(int idCommodite) throws AubergeInnException {
        Commodite commodite = commodites.getCommodite(idCommodite);
        if (commodite == null) {
            throw new AubergeInnException("Commodité inexistante: " + idCommodite);
        }
        return commodite;
    }

    public List<Commodite> getTouteLesCommodites() {
        return commodites.getTouteLesCommodites();
    }


}
