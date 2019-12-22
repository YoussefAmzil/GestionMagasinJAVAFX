package Controller;

import dao.LigneCmdDao;
import model.LigneCmd;
import model.Vente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LigneCmdDaoIml implements LigneCmdDao {
    Connection cnx=(new db.DataConnection()).getConnection();
    Statement stm=null;
    ResultSet rs=null;

    public LigneCmdDaoIml() {
        try {
            this.stm=this.cnx.createStatement();
            this.rs=null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public LigneCmd find(int id) {
        String sql="select *from lcmds where id="+id;
        try {
            rs=stm.executeQuery(sql);
            if(rs.next()) {
                return new LigneCmd(rs.getInt("id"),new ProduitDaoImplL().find(rs.getInt("produit_id")),rs.getInt("qte"),rs.getDouble("stotal"),new VenteDaoImp().find(rs.getInt("vente_id")));
            }
        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void create(LigneCmd c, Vente v) {
        String sql="insert into lcmds(produit_id,qte,stotal,vente_id) values('"+c.getP().getId()+"','"+c.getQte()+"','"+c.getP().getPrix()*c.getQte()+"','"+v.getId()+"')";
        try {
            if(!stm.execute(sql)){
                c.setId(this.getLastId());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(LigneCmd p) {
        String sql="delete from lcmds where id="+p.getId();
        try {
            stm.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(LigneCmd p) {

    }

    @Override
    public List<LigneCmd> findAll() {
        String sql="select * from lcmds";
        ArrayList<LigneCmd> c= new ArrayList<>();
        try {
            rs=stm.executeQuery(sql);
            while(rs.next()) {
                c.add(new LigneCmd(rs.getInt("id"), (new ProduitDaoImplL()).find(rs.getInt("produit_id")), rs.getInt("qte"), rs.getDouble("stotal"),(new VenteDaoImp()).find(rs.getInt("vente_id")) ));
            }
            return c;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<LigneCmd> findAll(int v) {
        ArrayList<LigneCmd> lcmds=new ArrayList<>();
        String sql="select *from lcmds where vente_id="+v;
        try {
            rs=stm.executeQuery(sql);
            while(rs.next()) {
                lcmds.add(new LigneCmd(rs.getInt("id"),new ProduitDaoImplL().find(rs.getInt("produit_id")),rs.getInt("qte"),rs.getDouble("stotal"),new VenteDaoImp().find(v)));
            }
            return lcmds;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getLastId() {
        String sql="select MAX(id) from lcmds";
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
