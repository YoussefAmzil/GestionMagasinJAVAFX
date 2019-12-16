package Interfacesgui;

import Controller.CategorieDaoImpl;
import Controller.ProduitDaoImplL;
import dao.CategorieDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Categorie;
import model.Produit;

import java.util.List;

public class FormCategorie {

    private BorderPane root=new BorderPane();
    TableView<Categorie> tablecategorie=new TableView<>();
    ObservableList<Categorie> observableTable= FXCollections.observableArrayList();
    GridPane pane = new GridPane();

    CategorieDAO pdao=new CategorieDaoImpl();
    List<Categorie> categories=pdao.findAll();

    Button addbtn=new Button("ajouter");
    Button editbtn=new Button("modifier");
    Button deletebtn=new Button("supprimer");
    Button clear=new Button("clear");

    Label designationlabel = new Label("Label");
    TextField designationinput = new TextField();

    private void initelements(){
        Label title=new Label("gestion des Categories");
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
        pane.add(designationlabel, 0, 1);
        pane.add(designationinput, 1, 1);
        pane.add(addbtn,0,5);
        pane.add(editbtn,1,5);
        pane.add(deletebtn,0,6);
        pane.add(clear,1,6);



        initTableProduct();
        VBox view=new VBox();
        TextField search= new TextField();
        VBox.setMargin(view,new Insets(60,0,0,0));
        VBox.setMargin(search,new Insets(10,0,5,0));
        view.getChildren().add(search);
        view.getChildren().add(tablecategorie);

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
        addbtn.setOnAction(event -> {
            Categorie c=new Categorie(pdao.getLastId(),designationinput.getText());
            pdao.create(c);
            this.observableTable.add(c);

        });
        deletebtn.setOnAction(event -> {
            Categorie t=tablecategorie.getSelectionModel().getSelectedItem();
            pdao.delete(t);
            this.observableTable.remove(t);
        });
        editbtn.setOnAction(event -> {
            Categorie t=tablecategorie.getSelectionModel().getSelectedItem();
            t.setLabel(designationinput.getText());
            pdao.update(t);
            this.observableTable.set(this.observableTable.indexOf(t),t);
        });
        //tableView selection
        tablecategorie.setOnMouseClicked(event -> {
            Categorie t=tablecategorie.getSelectionModel().getSelectedItem();
            this.designationinput.setText(t.getLabel());
        });

        search.setOnKeyPressed(event -> {
            if (!search.getText().isEmpty()) {
                ObservableList<Categorie> searchlist = FXCollections.observableArrayList();
                for (Categorie pr : categories) {
                    if (pr.getLabel().contains(search.getText()))
                        searchlist.add(pr);
                }
                this.observableTable.setAll(searchlist);
            }else{
                this.observableTable.setAll(categories);
            }
        });

        clear.setOnMouseClicked(events->{
            this.designationlabel.setText("");
        });
    }

    private void initTableProduct(){
        TableColumn<Categorie, Integer> categorieIdColon=new TableColumn<>("Id");
        categorieIdColon.setCellValueFactory(new PropertyValueFactory<>("id"));
        categorieIdColon.setPrefWidth(50);

        TableColumn<Categorie, String> produitCategorieColon=new TableColumn<>("Label");
        produitCategorieColon.setCellValueFactory(new PropertyValueFactory<>("label"));
        produitCategorieColon.setPrefWidth(100);

        tablecategorie.getColumns().addAll(categorieIdColon,produitCategorieColon);
        observableTable.setAll(categories);
        tablecategorie.setItems(observableTable);
    }

    public BorderPane getAll(){
        initelements();
        return this.root;
    }
}
