package tp.objets;

import org.bson.Document;

//import java.util.Date;
//import java.util.LinkedList;
import java.util.*;


// classe chambre
public class Chambre
{

    //private long id;
    private int idChambre;
    private String nomChambre;
    private String typeLit;
    private Double prixBase;
    private List<tp.objets.Commodite> commodites;
    private List<tp.objets.Reservation> reservations;
    private List<Integer> idCommodites = new LinkedList<>();
    private Double total;



// constructeur de classe
    public Chambre(Document d) {
        Integer id = d.getInteger("idChambre");
        this.idChambre = (id != null) ? id : 0;
        this.nomChambre = d.getString("nomChambre");
        this.typeLit = d.getString("typeLit");
        this.prixBase = d.getDouble("prixBase");
        commodites = new LinkedList<>();
        reservations = new LinkedList<>();
    }

    public Chambre(int idChambre, String nomChambre, String typeLit, Double prixBase) {
        this.idChambre = idChambre;
        this.nomChambre = nomChambre;
        this.typeLit = typeLit;
        this.prixBase = prixBase;
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

    public Double getprixBase() {
        return prixBase;
    }
    
    public String getNomChambre() {
        return this.nomChambre;
    }

    public void setNomChambre(String nomChambre) {
        this.nomChambre = nomChambre;
    }

    public String getTypeLit() {
        return typeLit;
    }

    public void setTypeLit(String typeLit) {
        this.typeLit = typeLit;
    }


    public void setPrixBase(Double prixBase) {
        this.prixBase = prixBase;
    }

    public List<Integer> getCommodites()
    {
        return idCommodites;
    }

   // public double getPrixTotal() {return total;}

    public Double getPrixTotal()
    {
        Double total = prixBase;
        for (Commodite com : commodites) {
            total += com.getSurplusPrix();
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
        System.out.println("Nom de la chambre : " + nomChambre);
        System.out.println("Type de lit : " + typeLit);

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
            if (res.getDateDebut().before(now) && res.getDateFin().after(now)){
                return false;
            }
        }
        return true;
    }


    public Document toDocument()
    {
        return new Document().append("idChambre", idChambre)
                .append("nom de Chambre", nomChambre)
                .append("type de Lit", typeLit)
                .append("prixBase", prixBase);

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

