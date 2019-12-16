package model;

import model.Categorie;

public class Produit {
	private int id;
	private String design;
	private double prix;
	private Categorie categorie;

	public Produit(int id,String design, double prix,Categorie c) {
		super();
		this.id=id;
		this.design = design;
		this.prix = prix;
		this.categorie=c;
	}

	public Produit(String design, double prix,Categorie c) {
		super();
		this.design = design;
		this.prix = prix;
		this.categorie=c;
	}
	public Produit() {

	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDesign() {
		return design;
	}


	public void setDesign(String design) {
		this.design = design;
	}


	public double getPrix() {
		return prix;
	}


	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	@Override
	public String toString() {
		return this.design;
	}
}
