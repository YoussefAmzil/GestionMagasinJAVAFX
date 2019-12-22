package model;

import java.time.LocalDate;

public class Payment {
    private int id;
    private Vente vente;
    private double montant;
    private String date;
    private PaymentT type;

    public Payment(int id, Vente v, double montant, String date, PaymentT type) {
        this.id = id;
        this.vente = v;
        this.montant = montant;
        this.date = date;
        this.type = type;
    }

    public Payment(Vente id_vente,double montant, String date, PaymentT type) {
        this.vente=id_vente;
        this.montant=montant;
        this.date=String.valueOf(LocalDate.now());
        this.type=type;

    }

    public Payment() {
        this.date=String.valueOf(LocalDate.now());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Vente getVente() {
        return vente;
    }

    public void setVente(Vente vente) {
        this.vente = vente;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public PaymentT getType() {
        return type;
    }

    public void setType(PaymentT type) {
        this.type = type;
    }
}
