package Interfacesgui;

import Controller.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
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
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FormVente {
    private BorderPane root=new BorderPane();

    TableView<LigneCmd> tablelignecmd=new TableView<>();
    ObservableList<LigneCmd> observableTable= FXCollections.observableArrayList();
    ObservableList<Produit> pros = FXCollections.observableArrayList((new ProduitDaoImplL().findAll()));
    ObservableList<Categorie> cats = FXCollections.observableArrayList((new CategorieDaoImpl().findAll()));
    ObservableList<PaymentT> payments = FXCollections.observableArrayList(PaymentT.values());
    double total=0.0;
    Alert alert = new Alert(Alert.AlertType.ERROR);

    GridPane pane = new GridPane();
    List<LigneCmd> lcmds=new ArrayList<>();
    Client cv=new Client();
    List<Client> clients;

    Button addbtn=new Button("");
    Button editbtn=new Button("");
    Button deletebtn=new Button("");
    Button clear=new Button("");
    Button  save= new Button("");


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
    TextField paymentamount=new TextField();

    ChoiceBox<Categorie> categories = new ChoiceBox<>(cats);
    ChoiceBox<Produit> produitbox = new ChoiceBox<>(pros);
    ChoiceBox<PaymentT> paymentbox = new ChoiceBox<>(payments);



    //private void initpanes(){ scene=new Scene(root); }

    private void initelements(){
        Label title=new Label("GETION DE VENTE");
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

         addbtn.setStyle("-fx-background-color: #69779b;-fx-min-width: 100;-fx-min-height: 30;");
        addbtn.setGraphic(new Label("AJOUTER"));
        addbtn.getGraphic().setStyle("-fx-text-fill: #f0ece2;");
        deletebtn.setStyle("-fx-background-color: #69779b;-fx-min-width: 100;-fx-min-height: 30;");
        deletebtn.setGraphic(new Label("SUPPRIMER"));
        deletebtn.getGraphic().setStyle("-fx-text-fill: #f0ece2;");
        editbtn.setStyle("-fx-background-color: #69779b;-fx-min-width: 100;-fx-min-height: 30;");
        editbtn.setGraphic(new Label("MODIFIER"));
        editbtn.getGraphic().setStyle("-fx-text-fill: #f0ece2;");
        clear.setStyle("-fx-background-color: #69779b;-fx-min-width: 100;-fx-min-height: 30;");
        clear.setGraphic(new Label("INIT"));
        clear.getGraphic().setStyle("-fx-text-fill: #f0ece2;");
        save.setStyle("-fx-background-color: #69779b;-fx-min-width: 180;-fx-min-height: 30;");
        save.setGraphic(new Label("ENREGISTRER"));
        save.getGraphic().setStyle("-fx-border-color: white;");
        save.getGraphic().setStyle("-fx-text-fill: #f0ece2;");



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
        pane.add(new Label("montant"),0,8);
        pane.add(paymentamount,1,8);
        pane.add(addbtn,0,9);
        pane.add(editbtn,1,9);
        pane.add(deletebtn,0,10);
        pane.add(clear,1,10);

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
            if(this.qtei.getText().isEmpty() || this.nomclienti.getText().isEmpty() || this.prenomclienti.getText().isEmpty() || this.produitbox.getValue()==null){
                alert.setTitle("Error Dialog");
                alert.setContentText("Essayer de remplir tous les champs !!");
                alert.setHeaderText("Ooops !!!!!");
                alert.showAndWait();
            }else{
                    for(LigneCmd cc:this.lcmds){
                        if (cc.getP().getId()==this.produitbox.getValue().getId()){
                            this.total-=cc.getP().getPrix()*cc.getQte();
                            cc.setQte(Integer.parseInt(this.qtei.getText()));
                            this.total+=cc.getP().getPrix()*cc.getQte();
                            this.lcmds.set(this.lcmds.indexOf(cc),cc);
                            this.observableTable.setAll(lcmds);
                            this.totalhtprice.setText(this.total +"DH");
                            return;
                        }
                    }
                    LigneCmd lcmd=new LigneCmd(this.produitbox.getValue(),Integer.parseInt(this.qtei.getText()));
                    this.lcmds.add(lcmd);
                    this.observableTable.setAll(lcmds);
                    this.total+=lcmd.getStotal();
                    this.totalhtprice.setText(this.total +"DH");
            }
        });
        deletebtn.setOnMouseClicked(ev->{
            deletebtn.setOnAction(event -> {
                try{
                    LigneCmd t=tablelignecmd.getSelectionModel().getSelectedItem();
                    this.lcmds.remove(t);
                    this.observableTable.setAll(lcmds);
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
            this.lcmds.set(this.lcmds.indexOf(t),t);
            this.observableTable.setAll(lcmds);
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
                this.clients=new ClientDaoImpl().findAll();
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
        save.setOnMouseClicked(ev->{
            if(this.paymentamount.getText().isEmpty() || this.paymentbox.getValue()==null || Double.parseDouble(this.paymentamount.getText())>this.total){
                alert.setTitle("Error Dialog");
                alert.setContentText("Essayer de remplir les champs de paiment!!");
                alert.setHeaderText("Ooops !!!!!");
                alert.showAndWait();
            }else{
                Vente v=new Vente();
                Payment pa=new Payment();
                v.setLcmds(lcmds);
                v.setClient(cv);
                pa.setVente(v);
                pa.setType(this.paymentbox.getValue());
                pa.setMontant(Double.parseDouble(this.paymentamount.getText()));
                v=new VenteDaoImp().create(v);
                new PaymentDaoImp().create(pa);
                System.out.println(v.getId());
                alert.setAlertType(Alert.AlertType.INFORMATION);
                 alert.setHeaderText("success");
                alert.setContentText("la vente avec ID : "+v.getId()+" est bien enregistré !");
                alert.show();
            }
        });
    }
    private void initTableProduct(){
        tablelignecmd.setStyle("-fx-border-color: #69779b");
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
