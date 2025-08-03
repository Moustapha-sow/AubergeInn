package tp.objets;

import org.bson.Document;
import java.util.*;


// classe commodite
public class Commodite  {


    private long id;
    private int idCommodite;
    private int idChambre;
    private String description;
    private Double surplusPrix;
    private Set<Chambre> chambres;


    // constructeurs de la classe
    public Commodite(Document doc) {

        this.idCommodite = doc.getInteger("idCommodite");
        this.description = doc.getString("description");
        this.surplusPrix = doc.getDouble("surplusPrix");
        this.chambres = new HashSet<>();

    }

    public Commodite(int idCommodite,int idChambre) {
        this.idCommodite = idCommodite;

        this.idChambre =idChambre;

    }

    public Commodite(int idCommodite, String description, Double surplusPrix) {
        this.idCommodite = idCommodite;
        this.description = description;
        this.surplusPrix = surplusPrix;
        this.chambres = new HashSet<>();
    }


    // Getters/Setters
    public int getIdCommodite() {
        return idCommodite;
    }

    public void setIdCommodite(int idCommodite) {
        this.idCommodite = idCommodite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getSurplusPrix() {
        return surplusPrix;
    }

    public void setSurplusPrix(Double surplusPrix) {
        this.surplusPrix = surplusPrix;
    }

    // fonction pour afficher les commodites diponibles
    public void printInfo()
    {
        System.out.println("\n" + "Commodité numéro : " + idCommodite);
        System.out.println("Description : " + description);

        System.out.println("Prix en surplus : " + surplusPrix);
    }


    public Document toDocument()
    {
        return new Document().append("idCommodite", idCommodite)
                .append("description", description)
                .append("surplusPrix", surplusPrix);
    }


    public Document toDocInclure()
    {
        return new Document().append("idCommodite", idCommodite)
                .append("idChambre", idChambre);
    }


    public boolean ajouterChambre(Chambre chambre) {
        return chambres.add(chambre);
    }

    public boolean enleverChambre(Chambre chambre) {
        return chambres.remove(chambre);
    }


}
