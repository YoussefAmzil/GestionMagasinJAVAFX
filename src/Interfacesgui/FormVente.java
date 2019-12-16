package Interfacesgui;

import Controller.CategorieDaoImpl;
import Controller.ClientDaoImpl;
import Controller.LigneCmdDaoIml;
import Controller.ProduitDaoImplL;
import dao.LigneCmdDao;
import dao.ProduitDAO;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableDoubleValue;
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

import java.util.ArrayList;
import java.util.List;

public class FormVente {
    private BorderPane root=new BorderPane();

    TableView<LigneCmd> tablelignecmd=new TableView<>();
    ObservableList<LigneCmd> observableTable= FXCollections.observableArrayList();
    ObservableList<Produit> pros = FXCollections.observableArrayList((new ProduitDaoImplL().findAll()));
    ObservableList<Categorie> cats = FXCollections.observableArrayList((new CategorieDaoImpl().findAll()));
    ObservableList<Categorie> payments = FXCollections.observableArrayList();
    double total=0.0;
    Alert alert = new Alert(Alert.AlertType.ERROR);


    GridPane pane = new GridPane();
    List<LigneCmd> lcmds=new ArrayList<>();
    Client cv=new Client();
    List<Client> clients =new ClientDaoImpl().findAll();

    Button addbtn=new Button("ajouter");
    Button editbtn=new Button("modifier");
    Button deletebtn=new Button("supprimer");
    Button clear=new Button("clear");
    Button  save= new Button("enregistrer");


    Label nomclient = new Label(" nom Client");
    TextField nomclienti = new TextField();
    Label prenomclient = new Label("prenom Client");
    TextField prenomclienti = new TextField();
    Label qte = new Label("quantite");
    TextField qtei = new TextField();
    Label telephone=new Label("telephone");
    TextField teleinp=new TextField();
    Label totalht=new Label("Total HT: ");
    Label totalttc=new Label("Total TTC: ");
    Label totalhtprice=new Label(this.total +"DH");
    Label totalttcprice=new Label(this.total +"DH");

    ChoiceBox<Categorie> categories = new ChoiceBox<>(cats);
    ChoiceBox<Produit> produitbox = new ChoiceBox<>(pros);
    ChoiceBox<Categorie> paymentbox = new ChoiceBox<>(payments);



    //private void initpanes(){ scene=new Scene(root); }

    private void initelements(){
        Label title=new Label("gestion des commande");
        title.setStyle(" -fx-padding:7px;");
        title.setStyle("-fx-font-size:25px;");
        title.setTextFill(Color.WHITE);

        totalht.setStyle(" -fx-padding:7px;");
        totalht.setStyle("-fx-font-size:20px;");
        totalttc.setStyle(" -fx-padding:7px;");
        totalttc.setStyle("-fx-font-size:20px;");
        totalttcprice.setStyle(" -fx-padding:7px;");
        totalttcprice.setStyle("-fx-font-size:20px;");
        totalhtprice.setStyle(" -fx-padding:7px;");
        totalhtprice.setStyle("-fx-font-size:20px;");

        HBox titletop=new HBox();
        titletop.setAlignment(Pos.CENTER);
        titletop.getChildren().add(title);
        titletop.setPadding(new Insets(20));
        titletop.setStyle("-fx-background-color: black;");

        addbtn.setMinSize(100,30);
        deletebtn.setMinSize(100,30);
        editbtn.setMinSize(100,30);
        clear.setMinSize(100,30);
        save.setMinSize(200,30);
        save.setStyle("-fx-border-color: black; -fx-border-width: 1px;");



        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.add(nomclient, 0, 1);
        pane.add(prenomclient, 0, 3);
        pane.add(nomclienti, 1, 1);
        pane.add(prenomclienti, 1, 3);pane.add(telephone,0,2);
        pane.add(teleinp,1,2);
        Label labelcat = new Label("categorie");
        Label labelPRODUIT = new Label("produit");
        pane.add(labelcat,0,4);
        pane.add(categories,1,4);
        pane.add(labelPRODUIT,0,5);
        pane.add(produitbox,1,5);
        pane.add(qte,0,6);
        pane.add(qtei,1,6);
        pane.add(new Label("paiment"),0,7);
        pane.add(paymentbox,1,7);
        pane.add(addbtn,0,8);
        pane.add(editbtn,1,8);
        pane.add(deletebtn,0,9);
        pane.add(clear,1,9);

        initTableProduct();
        VBox view=new VBox();
        view.setPadding(new Insets(15,0,0,5));
        VBox.setMargin(view,new Insets(20,0,0,0));
        view.getChildren().add(tablelignecmd);
        HBox totalbox=new HBox(6);
        totalbox.getChildren().add(totalht);
        totalbox.getChildren().add(totalhtprice);
        totalbox.getChildren().add(totalttc);
        totalbox.getChildren().add(totalttcprice);
        view.getChildren().add(totalbox);


        HBox footer=new HBox();
        Label footertext=new Label("Created By YOUSSEF AMZIL");
        footertext.setStyle(" -fx-padding:3px;");
        footertext.setStyle("-fx-font-size:12px;");
        footertext.setTextFill(Color.WHITE);
        footer.setAlignment(Pos.CENTER);
        footer.getChildren().add(footertext);
        footer.setPadding(new Insets(15));
        footer.setStyle("-fx-background-color: black;");

        VBox rght= new VBox();
        VBox.setMargin(save,new Insets(0,0,0,16));
        rght.getChildren().add(pane);
        rght.getChildren().add(save);

        root.setTop(titletop);
        root.setCenter(view);
        root.setRight(rght);
        root.setBottom(footer);

        //on add button

        addbtn.setOnMouseClicked(ev->{
            if(this.qtei.getText().isEmpty() || this.nomclienti.getText().isEmpty() || this.prenomclienti.getText().isEmpty() || this.categories.getValue() == null || this.produitbox.getValue()==null){
                alert.setTitle("Error Dialog");
                alert.setContentText("Essayer de remplir tous les champs !!");
                alert.setHeaderText("Ooops !!!!!");
                alert.showAndWait();
            }else{
                    LigneCmd lcmd=new LigneCmd(this.produitbox.getValue(),Integer.parseInt(this.qtei.getText()));
                    this.observableTable.add(lcmd);
                    this.total+=lcmd.getStotal();
                    this.totalhtprice.setText(this.total +"DH");
            }
        });
        deletebtn.setOnMouseClicked(ev->{
            deletebtn.setOnAction(event -> {
                try{
                    LigneCmd t=tablelignecmd.getSelectionModel().getSelectedItem();
                    this.observableTable.remove(t);
                    this.total-=t.getStotal();
                    this.totalhtprice.setText(this.total +"DH");
                }catch (Exception e){
                    this.alert.setContentText("choisissez un element !!!");
                    this.alert.showAndWait();
                }

            });
        });
        editbtn.setOnMouseClicked(ev->{
            LigneCmd t=tablelignecmd.getSelectionModel().getSelectedItem();
            this.total-=t.getStotal();
            t.setQte(Integer.parseInt(this.qtei.getText()));
            t.setP(this.produitbox.getValue());
            this.total+=t.getStotal();
            this.observableTable.set(this.observableTable.indexOf(t),t);
            this.totalhtprice.setText(String.valueOf(this.total)+"DH");
        });
        //change product list on categorie
        categories.setOnAction(ev->{
            System.out.println(this.categories.getValue().getLabel());
            this.pros.setAll(new CategorieDaoImpl().findProduits(this.categories.getValue()));
        });
        //get client with his name
        teleinp.setOnKeyTyped(ev->{
            if(!this.teleinp.getText().isEmpty()){
                for (Client i:this.clients
                     ) {
                    if(i.getTelephone().contains(teleinp.getText())){
                        System.out.println("in");
                        this.cv=i;
                        break;
                    }
                }
                this.prenomclienti.setText(cv.getPrenom());
                this.nomclienti.setText(cv.getNom());
            }else cv=null;
        });
    }
    private void initTableProduct(){
        TableColumn<LigneCmd, Produit> produitIdColon=new TableColumn<>("produit");
        produitIdColon.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getP()));
        produitIdColon.setPrefWidth(80);

        TableColumn<LigneCmd, Integer> quantite=new TableColumn<>("quantite");
        quantite.setCellValueFactory(new PropertyValueFactory<>("qte"));
        quantite.setPrefWidth(80);

        TableColumn<LigneCmd, Double> stotal=new TableColumn<>("sous total");
        stotal.setCellValueFactory(new PropertyValueFactory<>("stotal"));
        stotal.setPrefWidth(80);

        tablelignecmd.getColumns().addAll(produitIdColon,quantite,stotal);
        observableTable.setAll(lcmds);
        tablelignecmd.setItems(observableTable);
    }


    public BorderPane getAll(){
        initelements();
        return this.root;
    }
}
