package tp.objets;

import org.bson.Document;

import java.util.Date;


// classe Reservation
public class Reservation
{

    private Integer idReservation;
    private Integer idClient;
    private Integer  idChambre;
    private Double prixTotal;
    private Date dateDebut;
    private Date dateFin;
    private Client client;
    private Chambre chambre;


    // constructeurs de la classe

//    public Reservation(Document doc) {
//        this.idClient = doc.getInteger("idClient");
//
//       // this.idChambre = doc.getInteger("idChambre");
//
//        if (doc.getInteger("idReservation") == null || doc.getInteger("idClient") == null || doc.getInteger("idChambre") == null) {
//            throw new RuntimeException("Un champ requis est manquant dans Reservation"); }
//
//        this.prixTotal = doc.getDouble("prixTotal");
//        this.dateDebut = doc.getDate("dateDebut");
//        this.dateFin = doc.getDate("dateFin");
//    }

    public Reservation(Document d) {
      //  System.out.println("DEBUG RESERVATION DOC: " + d.toJson());

      //  try {
            this.idReservation = d.getInteger("idReservation");
            this.idClient = d.getInteger("idClient");
            this.idChambre = d.getInteger("idChambre");
            this.dateDebut = d.getDate("dateDebut");
            this.dateFin = d.getDate("dateFin");
            this.prixTotal = d.getDouble("prixTotal");

//            // vérifie si des champs sont null
//            if (idReservation == null || idClient == null || idChambre == null || dateDebut == null || dateFin == null || prixTotal == null) {
//                throw new RuntimeException("Un champ requis est manquant dans Reservation");
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("Un champ requis est manquant dans Reservation", e);
//        }
   }



    public Reservation(int idClient,int idReservation,int idChambre, Double prixTotal, Date dateDebut, Date dateFin) {
        this.idClient = idClient;
        this.idReservation = idReservation;

        this.idChambre = idChambre;
        this.prixTotal = prixTotal;

        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }


    public Double getPrixTotal() {
        return prixTotal;
    }

    public Client getClient() { return this.client; }

    public Chambre getChambre() { return this.chambre; }

   // public Client getClient() {return this.idClient;}

    public int getIdClient() { return idClient; }
    public Date getDateDebut() {
        return dateDebut;
    }

    public int getIdChambre() {
        return idChambre;
    }

    //public int getChambre() {
       // return idChambre;
    //}

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }


    public Document toDocument()
    {
        return new Document().append("idClient", idClient)
                .append("idChambre", idChambre)
                .append("prixTotal", prixTotal)
                .append("dateDebut", dateDebut)
                .append("dateFin", dateFin)
                .append("idReservation", idReservation);

    }



    public void printInfo()
    {

        System.out.println("Date de début : " + dateDebut.toString());

        System.out.println("Date de fin : " + dateFin.toString());


        System.out.println("Prix total : " + prixTotal);

    }
}
