package Controller;

import dao.LigneCmdDao;
import dao.VenteDao;
import model.LigneCmd;
import model.Vente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VenteDaoImp implements VenteDao {

    Connection cnx=(new db.DataConnection()).getConnection();
    Statement stm=null;
    ResultSet rs=null;

    public VenteDaoImp(){
        try {
            this.stm=this.cnx.createStatement();
            this.rs=null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Vente find(int id) {
        String sql="select *from ventes where id="+id;
        try {
            rs=stm.executeQuery(sql);
            if(rs.next()) {
                //return new Vente(rs.getInt("id"), rs.getDouble("total"), (new ClientDaoImpl().find(rs.getInt("client_id"))),new LigneCmdDaoIml().find(id));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Vente create(Vente v) {
        double total=(v.getLcmds()).stream()
                .mapToDouble(x -> x.getStotal())
                .sum();
        LigneCmdDao cmd=new LigneCmdDaoIml();
        String sql="insert into ventes(client_id,total) values('"+v.getClient().getId()+"','"+total+"')";
        try {
            System.out.println("error");
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
            Vente f= new Vente();
            while(rs.next()) {
                f.setId(rs.getInt("id"));
                c.add(new Vente(rs.getInt("id"),rs.getDouble("total"),new ClientDaoImpl().find(rs.getInt("client_id")),(new LigneCmdDaoIml()).findAll(f),rs.getString("date")));
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
}
