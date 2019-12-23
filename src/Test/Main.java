package Test;

import Controller.*;
import dao.CategorieDAO;
import dao.LigneCmdDao;
import dao.ProduitDAO;
import dao.VenteDao;
import model.Categorie;
import model.LigneCmd;
import model.Produit;
import model.Vente;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		//VenteDao v=new VenteDaoImp();
		//Vente n= new Vente();
		Vente g=new  VenteDaoImp().find(35);
		System.out.println(g.getTotal());
		System.out.println(g.getLcmds().size());


	}

}
