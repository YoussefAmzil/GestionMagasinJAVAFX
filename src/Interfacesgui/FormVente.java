package Interfacesgui;

import Controller.CategorieDaoImpl;
import Controller.ClientDaoImpl;
import Controller.LigneCmdDaoIml;
import Controller.ProduitDaoImplL;
import dao.LigneCmdDao;
import dao.ProduitDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import model.Categorie;
import model.Client;
import model.LigneCmd;
import model.Produit;

import java.util.List;

public class FormVente {
    private BorderPane root=new BorderPane();

    TableView<LigneCmd> tablelignecmd=new TableView<>();
    ObservableList<LigneCmd> observableTable= FXCollections.observableArrayList();
    ObservableList<Produit> pros = FXCollections.observableArrayList((new ProduitDaoImplL().findAll()));
    ObservableList<Categorie> cats = FXCollections.observableArrayList((new CategorieDaoImpl().findAll()));
    Alert alert = new Alert(Alert.AlertType.ERROR);


    GridPane pane = new GridPane();
    LigneCmdDao pdao=new LigneCmdDaoIml();
    List<LigneCmd> lcmds=pdao.findAll();

    Button addbtn=new Button("ajouter");
    Button editbtn=new Button("modifier");
    Button deletebtn=new Button("supprimer");
    Button clear=new Button("clear");

    Label nomclient = new Label(" nom Client");
    TextField nomclienti = new TextField();
    Label prenomclient = new Label("prenom Client");
    TextField prenomclienti = new TextField();
    Label qte = new Label("quantite");
    TextField qtei = new TextField();

    ChoiceBox<Categorie> categories = new ChoiceBox<>(cats);
    ChoiceBox<Produit> produitbox = new ChoiceBox<>(pros);



    //private void initpanes(){ scene=new Scene(root); }

    private void initelements(){
        Label title=new Label("gestion des commande");
        title.setStyle(" -fx-padding:7px;");
        title.setStyle("-fx-font-size:25px;");
        title.setTextFill(Color.WHITE);

        HBox titletop=new HBox();
        titletop.setAlignment(Pos.CENTER);
        titletop.getChildren().add(title);
        titletop.setPadding(new Insets(20));
        titletop.setStyle("-fx-background-color: black;");

        addbtn.setMinSize(100,30);
        deletebtn.setMinSize(100,30);
        editbtn.setMinSize(100,30);
        clear.setMinSize(100,30);

        pane.setPadding(new Insets(20));
        pane.setHgap(15);
        pane.setVgap(15);
        pane.add(nomclient, 0, 1);
        pane.add(prenomclient, 0, 3);
        pane.add(nomclienti, 1, 1);
        pane.add(prenomclienti, 1, 3);
        Label labelcat = new Label("categorie");
        Label labelPRODUIT = new Label("produit");
        pane.add(labelcat,0,4);
        pane.add(categories,1,4);
        pane.add(labelPRODUIT,0,5);
        pane.add(produitbox,1,5);
        pane.add(qte,0,6);
        pane.add(qtei,1,6);
        pane.add(addbtn,0,7);
        pane.add(editbtn,1,7);
        pane.add(deletebtn,0,8);
        pane.add(clear,1,8);

        initTableProduct();
        VBox view=new VBox();
        view.getChildren().add(tablelignecmd);

        HBox footer=new HBox();
        Label footertext=new Label("Created By YOUSSEF AMZIL");
        footertext.setStyle(" -fx-padding:3px;");
        footertext.setStyle("-fx-font-size:12px;");
        footertext.setTextFill(Color.WHITE);
        footer.setAlignment(Pos.CENTER);
        footer.getChildren().add(footertext);
        footer.setPadding(new Insets(15));
        footer.setStyle("-fx-background-color: black;");

        root.setTop(titletop);
        root.setCenter(view);
        root.setRight(pane);
        root.setBottom(footer);

        //on add button
    }

    private void initTableProduct(){
        TableColumn<LigneCmd, String> produitIdColon=new TableColumn<>("produit");
        produitIdColon.setCellValueFactory(p-> p.getValue().getP().getDesign());
        produitIdColon.setPrefWidth(50);


        tablelignecmd.getColumns().add(produitIdColon);
        observableTable.setAll(lcmds);
        tablelignecmd.setItems(observableTable);
        System.out.println(lcmds.size());
    }

    public BorderPane getAll(){
        initelements();
        return this.root;
    }
}
