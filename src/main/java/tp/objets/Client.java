package tp.objets;

import org.bson.Document;

import java.util.*;
import java.util.Set;

// classe  Client
public class Client {


    private long id;
    private int idClient;
    private String nom;
    private String prenom;
    private int age;
    private Set<Reservation> reservations;


    //Constructeur de la classe
    public Client(Document doc) {
        this.idClient = doc.getInteger("idClient");
        this.nom = doc.getString("nom");
        this.prenom = doc.getString("prenom");
        this.age = doc.getInteger("age");
    }

    public Client(int idClient, String nom, String prenom, int age) {
        this.idClient = idClient;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.reservations = new HashSet<>();
    }


    // Getters/Setters
    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // fonction pour afficher les info du client
    public void printInfo()
    {

        System.out.println("\n" + "Client : " + idClient);
        System.out.println("Nom : " + nom);
        System.out.println("Prenom : " + prenom);
        System.out.println("Age : " + age);
    }
    public Document toDocument()
    {

        return new Document().append("idClient", idClient)

                .append("nom", nom)
                .append("prenom", prenom)
                .append("age", age);

    }
}

