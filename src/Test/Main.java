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

public class Main {

	public static void main(String[] args) {
		//VenteDao v=new VenteDaoImp();
		Vente n= new Vente();
		LigneCmd lcmd=new LigneCmd(new ProduitDaoImplL().find(3),4);


	}

}
