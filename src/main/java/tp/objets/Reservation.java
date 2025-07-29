package tp.objets;

import org.bson.Document;

import java.util.Date;


// classe Reservation
public class Reservation
{

    private long id;
    private int idClient;
    private int  idChambre;
    private double prix_total;
    private Date date_debut;
    private Date date_fin;


    // constructeurs de la classe

    public Reservation(Document doc) {
        this.idClient = doc.getInteger("idClient");

        this.idChambre = doc.getInteger("idChambre");

        this.prix_total = doc.getDouble("prix_total");
        this.date_debut = doc.getDate("dateDebut");
        this.date_fin = doc.getDate("dateFin");
    }


    public Reservation(int idClient, int idChambre, double prix_total, Date date_debut, Date date_fin) {
        this.idClient = idClient;

        this.idChambre = idChambre;
        this.prix_total = prix_total;

        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }



    public Date getDate_debut() {
        return date_debut;
    }


    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }


    public Document toDocument()
    {
        return new Document().append("idClient", idClient)
                .append("idChambre", idChambre)
                .append("prix_total", prix_total)
                .append("date_debut", date_debut)
                .append("date_fin", date_fin);
    }



    public void printInfo()
    {

        System.out.println("Date de début : " + date_debut.toString());

        System.out.println("Date de fin : " + date_fin.toString());


        System.out.println("Prix total : " + prix_total);

    }
}
