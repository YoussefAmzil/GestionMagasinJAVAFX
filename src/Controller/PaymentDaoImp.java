package Controller;

import dao.PaymentDAO;
import model.Payment;
import model.PaymentT;
import model.Vente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImp implements PaymentDAO {
    Connection cnx=(new db.DataConnection()).getConnection();
    Statement stm=null;
    ResultSet rs=null;

    public PaymentDaoImp( ) {

        try {
            this.stm=this.cnx.createStatement();
            this.rs=null;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    @Override
    public List getpayments(Vente v) {
        String sql = "select *from payments where id_vente=" + v.getId();
        ArrayList<Payment> p = new ArrayList<>();
        try {
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                p.add(new Payment(rs.getInt("id"),new VenteDaoImp().find(rs.getInt("id_vente")),rs.getDouble("montant"), rs.getString("date"), PaymentT.valueOf(rs.getString("type"))));
            }
            return p;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void create(Payment p) {
        String sql="insert into payments(id_vente,montant,date,type) values('"+p.getVente().getId()+"','"+p.getMontant()+"','"+p.getDate()+"','"+p.getType()+"')";
        try {
            stm.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
