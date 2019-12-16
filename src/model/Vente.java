package model;

import java.util.ArrayList;
import java.util.List;

public class Vente {
    private int id;
    private double total;
    private Client client;
    private List<LigneCmd> lcmds;

    public Vente(int id, double total, Client client) {
        this.id = id;
        this.total = total;
        this.client = client;
    }

    public Vente() {
        this.lcmds=new ArrayList<>();
    }

    public Vente(int id, double total, Client client, List<LigneCmd> cmd) {
        this.id = id;
        this.total = total;
        this.client = client;
        this.lcmds=cmd;
    }



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    public List<LigneCmd> getLcmds() {
        return lcmds;
    }
    public void setLcmds(List<LigneCmd> lcmds) {
        this.lcmds = lcmds;
    }
}
