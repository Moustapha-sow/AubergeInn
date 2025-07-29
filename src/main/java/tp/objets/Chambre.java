package tp.objets;

import org.bson.Document;

//import java.util.Date;
//import java.util.LinkedList;
import java.util.*;


// classe chambre
public class Chambre
{

    private long id;
    private int idChambre;
    private String nom_chambre;
    private String type_lit;
    private double prix_base;
    private List<tp.objets.Commodite> commodites;
    private List<tp.objets.Reservation> reservations;



// constructeur de classe
    public Chambre(Document d) {
        this.idChambre = d.getInteger("idChambre");
        this.nom_chambre = d.getString("nom_chambre");
        this.type_lit = d.getString("type_lit");
        this.prix_base = d.getDouble("prix_base");
        commodites = new LinkedList<>();
        reservations = new LinkedList<>();
    }

    public Chambre(int idChambre, String nom_chambre, String type_lit, double prix_base) {
        this.idChambre = idChambre;
        this.nom_chambre = nom_chambre;
        this.type_lit = type_lit;
        this.prix_base = prix_base;
        commodites = new LinkedList<>();
        reservations = new LinkedList<>();
    }


    // Getters/Setters
    public int getIdChambre() {
        return idChambre;
    }


    public void setIdChambre(int idChambre) {
        this.idChambre = idChambre;
    }

    public String getNom_chambre() {
        return nom_chambre;
    }

    public void setNom_chambre(String nom_chambre) {
        this.nom_chambre = nom_chambre;
    }

    public String getType_lit() {
        return type_lit;
    }

    public void setType_lit(String type_lit) {
        this.type_lit = type_lit;
    }


    public void setPrix_base(float prix_base) {
        this.prix_base = prix_base;
    }

    public double getPrixTotal()
    {
        double total = prix_base;
        for (Commodite com : commodites) {
            total += com.getSurplus_prix();
        }
        return total;
    }
    public void setReservation(List<tp.objets.Reservation> reservation)
    {
        reservations = reservation;
    }


    public void setCommodites(List<Commodite> comm)
    {
        commodites = comm;
    }




    public void printInfo()
    {

        System.out.println("\n" + "Chambre : " + idChambre);
        System.out.println("Nom de la chambre : " + nom_chambre);
        System.out.println("Type de lit : " + type_lit);

        System.out.println("Prix  : " + getPrixTotal());
        if (!(commodites.size() > 0))
            System.out.println("pas de commodites.");
        else
        {
            System.out.println("Commodités : ");
            printCommodites();
        }
    }

    // fonction pour afficher les Commodites
    private void printCommodites()
    {
        for (Commodite com :
                commodites) {
            com.printInfo();
        }
    }



    public boolean estLibre() {
        Date now = new Date();

        for (Reservation res : reservations){
            if (res.getDate_debut().before(now) && res.getDate_fin().after(now)){
                return false;
            }
        }
        return true;
    }


    public Document toDocument()
    {
        return new Document().append("idChambre", idChambre)
                .append("nom de Chambre", nom_chambre)
                .append("type de Lit", type_lit)
                .append("prix de base ", getPrixTotal());

    }

// fonction pour inclure une commodités
    public boolean inclureCommodite(Commodite commodite) {
        return commodites.add(commodite);
    }

    //fonction pour supprimer une commodité
    public boolean enleverCommodite(Commodite commodite) {
        return commodites.remove(commodite);
    }


}

