package Controller;

import dao.ClientDAO;
import dao.LigneCmdDao;
import dao.ProduitDAO;
import dao.VenteDao;
import db.DataConnection;
import model.LigneCmd;
import model.Vente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static Controller.AllDaoImpl.daoproduit;

public class VenteDaoImp  implements VenteDao {

    Connection cnx;
    Statement stm=null;
    ResultSet rs=null;
    ResultSet rss=null;
    ClientDAO daoclient=new ClientDaoImpl();

    public VenteDaoImp(){
        try {
            cnx = DataConnection.getInstance().getConnection();
            this.stm = this.cnx.createStatement();
            this.rs = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Vente find(int idd) {

        String sql="select *from ventes where id="+idd;
        try {
            rs=stm.executeQuery(sql);
            Vente c;
            if(rs.next()) {
                System.out.println("inside");
                c= new Vente(rs.getInt("id"), rs.getDouble("total"), (daoclient.find(rs.getInt("client_id"))),rs.getString("date") ,findAll(rs.getInt("id")));
                return c;
            }
        } catch (SQLException e) {
            System.out.println("find1");
            System.out.println(e.getMessage());
            e.getCause();
        }
        return null;
    }

    @Override
    public Vente create(Vente v) {
        double total=(v.getLcmds()).stream()
                .mapToDouble(x -> x.getStotal())
                .sum();
        LigneCmdDao cmd=new LigneCmdDaoIml();
        String sql="insert into ventes(client_id,total,date) values('"+v.getClient().getId()+"','"+total+"','"+v.getDate()+"')";
        try {
             if(!stm.execute(sql)){
                 v.setId(this.getLastId());
                 for (LigneCmd l: v.getLcmds()) {
                     cmd.create(l,v);
                 }
                 return v;
             }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return v;
    }

    @Override
    public void delete(Vente p) {
        String sql="delete from ventes where id="+p.getId();
        try {
             stm.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Vente p) {

    }

    @Override
    public List<Vente> findAll() {
        String sql="select * from ventes";
        ArrayList<Vente> c= new ArrayList<>();
        try {
            rs=stm.executeQuery(sql);

            while(rs.next()) {
                c.add(new Vente(rs.getInt("id"),rs.getDouble("total"),daoclient.find(rs.getInt("client_id")),rs.getString("date"),findAll((rs.getInt("id")))));
            }
            return c;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getLastId() {
        String sql="select MAX(id) from ventes";
        try {
            rs=stm.executeQuery(sql);
            if(rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    @Override
    public List<LigneCmd> findAll(int v) {
        ArrayList<LigneCmd> lcmds=new ArrayList<>();
        String sql="select *from lcmds where vente_id="+v;
        try {
            rss=stm.executeQuery(sql);
            while(rss.next()) {
                lcmds.add(new LigneCmd(rss.getInt("id"),daoproduit.find(rss.getInt("produit_id")),rss.getInt("qte"),rss.getDouble("stotal")));
            }
            return lcmds;
        } catch (SQLException e) {
            System.out.println("findall");
           // e.getMessage();

        }
        return null;
    }
}
