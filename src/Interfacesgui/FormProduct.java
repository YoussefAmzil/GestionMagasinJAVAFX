package Interfacesgui;

import Controller.CategorieDaoImpl;
import Controller.ProduitDaoImplL;
import dao.ProduitDAO;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Categorie;
import model.Produit;

import javax.xml.soap.Text;
import java.util.List;

public class FormProduct  {

    private BorderPane root=new BorderPane();
	    TableView<Produit> tableProduits=new TableView<>();
	    ObservableList<Produit> observableTable= FXCollections.observableArrayList();
	    ObservableList<Categorie> cats = FXCollections.observableArrayList((new CategorieDaoImpl().findAll()));
	    Alert alert = new Alert(Alert.AlertType.ERROR);



    GridPane pane = new GridPane();
    ProduitDAO pdao=new ProduitDaoImplL();
    List<Produit> produits=pdao.findAll();

    Button addbtn=new Button("");
    Button editbtn=new Button("");
    Button deletebtn=new Button("");
    Button clear=new Button("");

    Label designationlabel = new Label("Designation");
    TextField designationinput = new TextField();
    Label prixlabel = new Label("prix");
    TextField prixinput = new TextField();
    ChoiceBox<Categorie> choiceBox = new ChoiceBox<>(cats);



    //private void initpanes(){ scene=new Scene(root); }

    private void initelements(){
        Label title=new Label("GESTION DE PRODUIT");
        title.setStyle(" -fx-padding:7px;");
        title.setStyle("-fx-font-size:25px;");
        title.setTextFill(Color.WHITE);

        HBox titletop=new HBox();
        titletop.setAlignment(Pos.CENTER);
        titletop.getChildren().add(title);
        titletop.setPadding(new Insets(20));
        titletop.setStyle("-fx-background-color: black;");

        addbtn.setStyle("-fx-background-color: #69779b;-fx-min-width: 100;-fx-min-height: 50;");
        addbtn.setGraphic(new Label("AJOUTER"));
        addbtn.getGraphic().setStyle("-fx-text-fill: #f0ece2;");
        deletebtn.setStyle("-fx-background-color: #69779b;-fx-min-width: 100;-fx-min-height: 50;");
        deletebtn.setGraphic(new Label("SUPPRIMER"));
        deletebtn.getGraphic().setStyle("-fx-text-fill: #f0ece2;");
        editbtn.setStyle("-fx-background-color: #69779b;-fx-min-width: 100;-fx-min-height: 50;");
        editbtn.setGraphic(new Label("MODIFIER"));
        editbtn.getGraphic().setStyle("-fx-text-fill: #f0ece2;");
        clear.setStyle("-fx-background-color: #69779b;-fx-min-width: 100;-fx-min-height: 50;");
        clear.setGraphic(new Label("INIT"));
        clear.getGraphic().setStyle("-fx-text-fill: #f0ece2;");

        pane.setPadding(new Insets(0,20,20,20));
        pane.setHgap(15);
        pane.setVgap(15);
        pane.add(designationlabel, 0, 1);
        pane.add(prixlabel, 0, 3);
        pane.add(designationinput, 1, 1);
        pane.add(prixinput, 1, 3);
        Label labelcat = new Label("categorie");
        pane.add(labelcat,0,4);
        pane.add(choiceBox,1,4);
        pane.add(addbtn,0,6);
        pane.add(editbtn,1,6);
        pane.add(deletebtn,0,7);
        pane.add(clear,1,7);

        initTableProduct();
        VBox view=new VBox();
        TextField search= new TextField();
        search.setStyle("-fx-border-color: #69779b");
        VBox.setMargin(view,new Insets(60,0,0,0));
        VBox.setMargin(search,new Insets(10,0,5,0));
        view.getChildren().add(search);
        view.getChildren().add(tableProduits);

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
            if (this.designationinput.getText().isEmpty() || this.prixinput.getText().isEmpty() || this.choiceBox.getValue() == null){
                alert.setTitle("Error Dialog");
                alert.setContentText("Essayer de remplir tous les champs !!");
                alert.setHeaderText("Ooops !!!!!");
                alert.showAndWait();
                return;
            }
            try {
                Double.parseDouble(this.prixinput.getText());
            }catch (Exception e){
                alert.setTitle("Error Dialog");
                alert.setContentText("prix doit etre nombre !!");
                alert.setHeaderText("Ooops !!!!!");
                alert.showAndWait();
            }
        Produit pro=new Produit(pdao.getLastId(),designationinput.getText(),Double.parseDouble(prixinput.getText()),choiceBox.getValue());
            pdao.create(pro);
            this.observableTable.add(pro);
        });
        deletebtn.setOnAction(event -> {
            Produit t=tableProduits.getSelectionModel().getSelectedItem();
            try {
                pdao.delete(t);
                this.observableTable.remove(t);
            }catch (Exception e) {
                alert.setContentText(e.getMessage());
                alert.setTitle("impossible !!!");
                alert.show();
            }

        });
        editbtn.setOnAction(event -> {
            if (this.designationinput.getText().isEmpty() || this.prixinput.getText().isEmpty() || this.choiceBox.getValue() == null){
                alert.setTitle("Error Dialog");
                alert.setContentText("Essayer de remplir tous les champs !!");
                alert.setHeaderText("Ooops !!!!!");
                alert.showAndWait();
                return;
            }
            try {
                Double.parseDouble(this.prixinput.getText());
            }catch (Exception e){
                alert.setTitle("Error Dialog");
                alert.setContentText("prix doit etre nombre !!");
                alert.setHeaderText("Ooops !!!!!");
                alert.showAndWait();
            }
            Produit t=tableProduits.getSelectionModel().getSelectedItem();
            t.setDesign(designationinput.getText());
            t.setPrix(Double.parseDouble(prixinput.getText()));
            if (choiceBox !=null) t.setCategorie(choiceBox.getValue());
            pdao.update(t);
            this.observableTable.set(this.observableTable.indexOf(t),t);
        });
        //tableView selection
        tableProduits.setOnMouseClicked(event -> {
            Produit t=tableProduits.getSelectionModel().getSelectedItem();
            this.designationinput.setText(t.getDesign());
            this.prixinput.setText(String.valueOf(t.getPrix()));
        });
        search.setOnKeyPressed(event -> {
            if (!search.getText().isEmpty()) {
                ObservableList<Produit> searchlist = FXCollections.observableArrayList();
                for (Produit pr : produits) {
                    if (pr.getDesign().contains(search.getText()))
                        searchlist.add(pr);
                }
                this.observableTable.setAll(searchlist);
            }else this.observableTable.setAll(produits);
        });
        clear.setOnMouseClicked(event->{
            this.designationinput.setText("");
            this.prixinput.setText("");
        });
    }

    private void initTableProduct(){
        tableProduits.setStyle("-fx-border-color: #69779b");
        TableColumn<Produit, Integer> produitIdColon=new TableColumn<>("Id");
        produitIdColon.setCellValueFactory(new PropertyValueFactory<>("id"));
        produitIdColon.setPrefWidth(50);

        TableColumn<Produit, String> produitDesignationColon=new TableColumn<>("Designation");
        produitDesignationColon.setCellValueFactory(new PropertyValueFactory<>("design"));
        produitDesignationColon.setPrefWidth(100);

        TableColumn<Produit, Categorie>category=new TableColumn<>("categorie");
        category.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getCategorie()));
        category.setPrefWidth(80);

        TableColumn<Produit, Double> produitPrixColon=new TableColumn<>("Prix");
        produitPrixColon.setCellValueFactory(new PropertyValueFactory<>("prix"));
        produitPrixColon.setPrefWidth(80);

        tableProduits.getColumns().addAll(produitIdColon,produitDesignationColon,category,produitPrixColon);
        observableTable.setAll(produits);
        tableProduits.setItems(observableTable);
    }

    public BorderPane getAll(){
        initelements();
        return this.root;
    }
}
