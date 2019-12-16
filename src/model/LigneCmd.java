package model;

public class LigneCmd {
    private int id;
    private Vente vente;
    private Produit p;
    private int qte;
    private double stotal;

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

    public Produit getP() {
        return p;
    }

    public void setP(Produit p) {
        this.p = p;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public double getStotal() {
        return stotal;
    }

    public void setStotal(double stotal) {
        this.stotal = stotal;
    }

    public LigneCmd(int id, Produit p, int qte, double stotal,Vente v) {
        this.id = id;
        this.p = p;
        this.qte = qte;
        this.stotal = stotal;
        this.vente=v;
    }
    public LigneCmd(Produit p, int qte) {
        this.p = p;
        this.qte = qte;
        this.stotal = p.getPrix()*qte;
    }

    public LigneCmd() {
    }
}

