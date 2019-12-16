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
            Tab protab=new Tab("Gestion de produit",produits.getAll());
            protab.setClosable(false);
            tabs.getTabs().add(protab);
            Tab cattab=new Tab("Gestion de categorie",categorie.getAll());
            cattab.setClosable(false);
            tabs.getTabs().add(cattab);
            Tab clienttab=new Tab("Gestion de client",client.getAll());
            clienttab.setClosable(false);
            tabs.getTabs().add(clienttab);
            scene=new Scene(tabs);
            Tab ventetab=new Tab("ffref",vente.getAll());
            tabs.getTabs().add(ventetab);
        }
    @Override
    public void start(Stage window) throws Exception {
        window.setWidth(900);
        window.setHeight(690);

        initElement();
        window.setScene(scene);
        window.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
