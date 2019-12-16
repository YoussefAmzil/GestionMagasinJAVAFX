package model;

public class Client {

    private int id;
    private String nom;
    private String prenom;
    private String telephone;
    private String city;

    public Client(int id, String nom, String prenom, String telephone, String city) {
        super();
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.city = city;
    }

    public Client(String nom, String prenom, String telephone, String city) {
        this.nom=nom;
        this.prenom=prenom;
        this.telephone=telephone;
        this.city=city;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
}
