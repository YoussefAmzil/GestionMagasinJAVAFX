package Controller;

import dao.*;
import model.LigneCmd;

public class AllDaoImpl {

    static CategorieDAO daocategory=new CategorieDaoImpl();
    static ClientDAO daoclient =new ClientDaoImpl();
    static LigneCmdDao daolcmd=new LigneCmdDaoIml();
    static PaymentDAO daopayment=new PaymentDaoImp();
    static ProduitDAO daoproduit=new ProduitDaoImplL();
    static VenteDao daovente=new VenteDaoImp();

    public static CategorieDAO getDaocategory() {
        return daocategory;
    }

    public static ClientDAO getDaoclient() {
        return daoclient;
    }

    public static LigneCmdDao getDaolcmd() {
        return daolcmd;
    }

    public static PaymentDAO getDaopayment() {
        return daopayment;
    }

    public static ProduitDAO getDaoproduit() {
        return daoproduit;
    }

    public  static VenteDao getvente() {
        return daovente;
    }
}
