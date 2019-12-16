package model;

public class Categorie {
    private int id;
    private String label;


    public Categorie(int id,String label) {
        this.label = label;
        this.id = id;
    }

    public Categorie(String label) {
        this.label=label;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
