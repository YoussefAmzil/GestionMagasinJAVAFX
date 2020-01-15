package Interfacesgui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class InitGui extends Application {
        Scene scene=null;
        TabPane tabs=new TabPane();


        public void initElement(){
            tabs.setTabMinHeight(50);
            tabs.setMinWidth(100);

            FormProduct produits = new FormProduct();
            FormCategorie categorie = new FormCategorie();
            FormClient client = new FormClient();
            FormVente vente=new FormVente();
            FormPayment paym=new FormPayment();
            Tab protab=new Tab("",produits.getAll());
            protab.setClosable(false);
            Tab cattab=new Tab("",categorie.getAll());
            cattab.setClosable(false);
            Tab clienttab=new Tab("",client.getAll());
            clienttab.setClosable(false);
            Tab ventetab=new Tab("",vente.getAll());
            ventetab.setClosable(false);
            Tab paymenttab=new Tab("",paym.getAll());
            paymenttab.setClosable(false);

            clienttab.setStyle(" -fx-background-color: #69779b;");
            clienttab.setGraphic(new Label("Gestion des clients"));
            clienttab.getGraphic().setStyle("-fx-text-fill: #f0ece2;");

            protab.setStyle(" -fx-background-color: #69779b;");
            protab.setGraphic(new Label("Gestion des produits"));
            protab.getGraphic().setStyle("-fx-text-fill: #f0ece2;");

            ventetab.setStyle(" -fx-background-color: #69779b;");
            ventetab.setGraphic(new Label("Gestion des ventes"));
            ventetab.getGraphic().setStyle("-fx-text-fill: #f0ece2;");

            paymenttab.setStyle(" -fx-background-color: #69779b;");
            paymenttab.setGraphic(new Label("Gestion des payments"));
            paymenttab.getGraphic().setStyle("-fx-text-fill: #f0ece2;");

            cattab.setStyle(" -fx-background-color: #69779b;");
            cattab.setGraphic(new Label("Gestion des Categories"));
            cattab.getGraphic().setStyle("-fx-text-fill: #f0ece2;");




            this.tabs.getTabs().addAll(protab,cattab,clienttab,ventetab,paymenttab);
            protab.setOnSelectionChanged(e-> {
                protab.setContent(new FormProduct().getAll());
                ventetab.setContent(new FormVente().getAll());
            });
            cattab.setOnSelectionChanged(ev->{
                ventetab.setContent(new FormVente().getAll());
            });

            scene=new Scene(tabs);

        }
    @Override
    public void start(Stage window) throws Exception {
        window.setWidth(950);
        window.setHeight(700);
        initElement();
        window.setScene(scene);
        window.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
