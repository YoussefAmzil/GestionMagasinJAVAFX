package Interfacesgui;

import Controller.CategorieDaoImpl;
import Controller.ClientDaoImpl;
import Controller.ProduitDaoImplL;
import dao.ClientDAO;
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
import model.Client;
import model.Produit;

import java.util.List;

public class FormClient {
    private BorderPane root=new BorderPane();
    TableView<Client> tableclient=new TableView<>();
    ObservableList<Client> observableTable= FXCollections.observableArrayList();
    GridPane pane = new GridPane();

    ClientDAO pdao=new ClientDaoImpl();
    List<Client> clients=pdao.findAll();

    Button addbtn=new Button("ajouter");
    Button editbtn=new Button("modifier");
    Button deletebtn=new Button("supprimer");
    Button clear=new Button("clear");

    Label nomlabel = new Label("nom");
    TextField nominput = new TextField();
    Label prenomlabel = new Label("prenom");
    TextField prenominput = new TextField();
    Label telephonelabel = new Label("telephone");
    TextField telephoneinput = new TextField();
    Label citylabel = new Label("ville");
    TextField cityinput = new TextField();


    private void initelements(){
        Label title=new Label("gestion des clients");
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
        pane.add(nomlabel, 0, 1);
        pane.add(prenomlabel, 0, 2);
        pane.add(telephonelabel, 0, 3);
        pane.add(citylabel, 0, 4);
        pane.add(nominput, 1, 1);
        pane.add(prenominput, 1, 2);
        pane.add(telephoneinput, 1, 3);
        pane.add(cityinput, 1, 4);
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
        view.getChildren().add(tableclient);

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
            Client c=new Client(pdao.getLastId(),nominput.getText(),prenominput.getText(),telephoneinput.getText(),cityinput.getText());
            pdao.create(c);
            this.observableTable.add(c);
        });
        deletebtn.setOnAction(event -> {
            Client t=tableclient.getSelectionModel().getSelectedItem();
            pdao.delete(t);
            this.observableTable.remove(t);
        });
        editbtn.setOnAction(event -> {
            Client t=tableclient.getSelectionModel().getSelectedItem();
            t.setNom(nominput.getText());
            t.setPrenom(prenominput.getText());
            t.setTelephone(telephoneinput.getText());
            t.setCity(cityinput.getText());
            pdao.update(t);
            this.observableTable.set(this.observableTable.indexOf(t),t);
        });
        //tableView selection
        tableclient.setOnMouseClicked(event -> {
            Client t=tableclient.getSelectionModel().getSelectedItem();
            this.nominput.setText(t.getNom());
            this.prenominput.setText(t.getPrenom());
            this.telephoneinput.setText(t.getTelephone());
            this.cityinput.setText(t.getCity());
        });
        search.setOnKeyPressed(event -> {
            if (!search.getText().isEmpty()) {
                ObservableList<Client> searchlist = FXCollections.observableArrayList();
                for (Client pr : clients) {
                    if (pr.getPrenom().contains(search.getText()) || pr.getNom().contains(search.getText()) || pr.getTelephone().contains(search.getText()))
                        searchlist.add(pr);
                }
                this.observableTable.setAll(searchlist);
            }
        });
        clear.setOnMouseClicked(events->{
            this.nominput.setText("");
            this.prenominput.setText("");
            this.telephoneinput.setText("");
            this.cityinput.setText("");
        });

    }
    private void initTableProduct(){
        TableColumn<Client, Integer> clientIdColon=new TableColumn<>("Id");
        clientIdColon.setCellValueFactory(new PropertyValueFactory<>("id"));
        clientIdColon.setPrefWidth(50);

        TableColumn<Client, String> nomColon=new TableColumn<>("nom");
        nomColon.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nomColon.setPrefWidth(100);

        TableColumn<Client, String> prenomColon=new TableColumn<>("Prenom");
        prenomColon.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        prenomColon.setPrefWidth(80);

        TableColumn<Client, String> telephoneColon=new TableColumn<>("Telephone");
        telephoneColon.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        telephoneColon.setPrefWidth(80);

        TableColumn<Client, String> cityColon=new TableColumn<>("City");
        cityColon.setCellValueFactory(new PropertyValueFactory<>("city"));
        cityColon.setPrefWidth(80);


        tableclient.getColumns().addAll(clientIdColon,nomColon,prenomColon,telephoneColon,cityColon);
        observableTable.setAll(clients);
        tableclient.setItems(observableTable);
    }
    public BorderPane getAll(){
        initelements();
        return this.root;
    }
}
