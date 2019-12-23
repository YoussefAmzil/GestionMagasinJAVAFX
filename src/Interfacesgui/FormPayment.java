package Interfacesgui;

import Controller.*;
import dao.PaymentDAO;
import dao.VenteDao;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import java.util.OptionalInt;

public class FormPayment {
    private BorderPane root=new BorderPane();

    TableView<Payment> tablelignecmd=new TableView<>();
    ObservableList<Payment> observableTable= FXCollections.observableArrayList();
    ObservableList<PaymentT> payments = FXCollections.observableArrayList(PaymentT.values());
    List<Payment> paymentsdb=new ArrayList<>();
    double total=0.0,resteamount=0.0;
    Alert alert = new Alert(Alert.AlertType.ERROR);
    Vente currentvente;
    int maxId;

    GridPane pane = new GridPane();

    Button addbtn=new Button("ajouter");
    Button editbtn=new Button("modifier");
    Button deletebtn=new Button("supprimer");
    Button clear=new Button("clear");
    Button  save= new Button("enregistrer");


    Label id_vente = new Label(" Id Vente");
    TextField idventeinput = new TextField();
    Label datevente = new Label("datevente");
    TextField dateventeinput = new TextField();
    Label total_vente = new Label("Total Vente");
    TextField total_venteinput = new TextField();
    Label totalht=new Label("Total Paye: ");
    Label totalhtprice=new Label(this.total +"DH");
    TextField paymentamount=new TextField("0");
    Label reste = new Label("Reste: ");
    TextField resteinput = new TextField();

    ChoiceBox<PaymentT> paymentbox = new ChoiceBox<>(payments);



    //private void initpanes(){ scene=new Scene(root); }

    private void initelements(){
        Label title=new Label("gestion de payment");
        title.setStyle(" -fx-padding:7px;");
        title.setStyle("-fx-font-size:25px;");
        title.setTextFill(Color.WHITE);

        totalht.setStyle(" -fx-padding:7px;");
        totalht.setStyle("-fx-font-size:20px;");
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

        tablelignecmd.setMaxWidth(550);
        dateventeinput.setEditable(false);
        total_venteinput.setEditable(false);
        resteinput.setEditable(false);
        editbtn.setDisable(true);


        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.add(id_vente, 0, 1);
        pane.add(idventeinput, 1, 1);
        pane.add(datevente, 0, 2);
        pane.add(dateventeinput, 1, 2);
        pane.add(total_vente,0,3);
        pane.add(total_venteinput,1,3);
        pane.add(reste,0,4);
        pane.add(resteinput,1,4);
        pane.add(new Label("paiment"),0,5);
        pane.add(paymentbox,1,5);
        pane.add(new Label("montant"),0,6);
        pane.add(paymentamount,1,6);
        pane.add(addbtn,0,7);
        pane.add(editbtn,1,7);
        pane.add(deletebtn,0,8);
        pane.add(clear,1,8);

        initTableProduct();
        VBox view=new VBox();
        view.setPadding(new Insets(15,0,0,5));
        VBox.setMargin(view,new Insets(20,0,0,0));
        view.getChildren().add(tablelignecmd);
        HBox totalbox=new HBox(6);
        totalbox.getChildren().add(totalht);
        totalbox.getChildren().add(totalhtprice);
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
        idventeinput.setOnMouseClicked(event -> {
            try {
                currentvente= new VenteDaoImp().find(Integer.parseInt(this.idventeinput.getText()));
                this.dateventeinput.setText(currentvente.getDate());
                this.total_venteinput.setText(currentvente.getTotal()+"");
                List<Payment> z=new PaymentDaoImp().getpayments(currentvente);
                //this.paymentsdb=z;
                this.observableTable.setAll(z);
                 total=(z).stream()
                        .mapToDouble(x -> x.getMontant())
                        .sum();
                maxId=(paymentsdb).stream()
                        .mapToInt(x -> x.getId())
                        .max().getAsInt();
                 resteamount=currentvente.getTotal()-total;
                 this.resteinput.setText(resteamount+"");
                 this.totalhtprice.setText(total+"DHs");

            } catch (NumberFormatException e) {
                alert.setContentText("Essayer de donner un nombre dans ce champs");
                alert.show();
            }
        });

        addbtn.setOnMouseClicked(ev->{
            if (this.paymentbox.getValue()==null || this.paymentamount.getText().isEmpty()){
                alert.setContentText("essayez de remplir tous les champs !!!");
                alert.show();
            }else if(Double.parseDouble(this.paymentamount.getText()) > this.resteamount || Double.parseDouble(this.paymentamount.getText())<=0){
                alert.setContentText("le montant doit etre inferieur ou egale a reste !!!");
                alert.show();
            }else{
                Payment np=new Payment(currentvente,Double.parseDouble(paymentamount.getText()),paymentbox.getValue());
                maxId++;
                np.setId(maxId);
                this.total+=np.getMontant();
                this.resteamount-=np.getMontant();
                this.resteinput.setText(resteamount+"");
                this.totalhtprice.setText(total+"DHs");
                this.observableTable.add(np);
                this.paymentsdb.add(np);
            }
        });
        deletebtn.setOnMouseClicked(v->{
            Payment np=this.tablelignecmd.getSelectionModel().getSelectedItem();
            maxId--;
            this.total-=np.getMontant();
            this.resteamount+=np.getMontant();
            this.resteinput.setText(resteamount+"");
            this.totalhtprice.setText(total+"DHs");
            this.paymentsdb.remove(np);
            this.observableTable.setAll(paymentsdb);
        });

        save.setOnMouseClicked(ev->{
            if(!this.paymentsdb.isEmpty()) {
                PaymentDAO daopayment = new PaymentDaoImp();
                for (Payment pi : this.paymentsdb) {
                    daopayment.create(pi);
                }
            }else{
                alert.setContentText("Ajouter un element au moins ");
                alert.show();
            }

        });
        clear.setOnMouseClicked(v->{
            this.paymentbox=null;
            this.paymentamount.setText("0");
        });

    }
    private void initTableProduct(){
        TableColumn<Payment, Integer> idpayment=new TableColumn<>("id payment");
        idpayment.setCellValueFactory(new PropertyValueFactory<>("id"));
        idpayment.setPrefWidth(100);

        TableColumn<Payment, Double> montant=new TableColumn<>("montant");
        montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
        montant.setPrefWidth(80);

        TableColumn<Payment, String> type=new TableColumn<>("type paiment");
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        type.setPrefWidth(100);

        TableColumn<Payment, String> datetab=new TableColumn<>("date");
        datetab.setCellValueFactory(new PropertyValueFactory<>("date"));
        datetab.setPrefWidth(100);

        tablelignecmd.getColumns().addAll(idpayment,montant,type,datetab);
        observableTable.setAll(paymentsdb);
        tablelignecmd.setItems(observableTable);
    }

    public BorderPane getAll(){
        initelements();
        return this.root;
    }
}
