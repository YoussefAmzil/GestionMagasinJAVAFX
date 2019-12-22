package Interfacesgui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class InitGui extends Application {
        Scene scene=null;
        TabPane tabs=new TabPane();

        public void initElement(){
            FormProduct produits = new FormProduct();
            FormCategorie categorie = new FormCategorie();
            FormClient client = new FormClient();
            FormVente vente=new FormVente();
            FormPayment paym=new FormPayment();
            Tab protab=new Tab("Gestion de produit",produits.getAll());
            protab.setClosable(false);
            Tab cattab=new Tab("Gestion de categorie",categorie.getAll());
            cattab.setClosable(false);
            Tab clienttab=new Tab("Gestion de client",client.getAll());
            clienttab.setClosable(false);
            scene=new Scene(tabs);
            Tab ventetab=new Tab("Gestion de vente",vente.getAll());
            ventetab.setClosable(false);
            Tab paymenttab=new Tab("Gestion de payment",paym.getAll());
            paymenttab.setClosable(false);
            tabs.getTabs().addAll(protab,cattab,clienttab,ventetab,paymenttab);
        }
    @Override
    public void start(Stage window) throws Exception {
        window.setWidth(950);
        window.setHeight(690);

        initElement();
        window.setScene(scene);
        window.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
