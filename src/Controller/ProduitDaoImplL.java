package Controller;

import dao.ProduitDAO;
import db.DataConnection;
import model.Categorie;
import model.Produit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProduitDaoImplL implements ProduitDAO {
    Connection cnx=(new DataConnection()).getConnection();
    Statement stm=null;
    ResultSet rs=null;

    public ProduitDaoImplL() {
        try {
            this.stm=this.cnx.createStatement();
            this.rs=null;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public Produit find(int id) {
        String sql="select *from produits where id="+id;
        try {
            rs=stm.executeQuery(sql);
            if(rs.next()) {
                Categorie c=(new CategorieDaoImpl()).find(rs.getInt("categorie_id"));
                return new Produit(rs.getInt("id"),rs.getString("design"),rs.getDouble("prix"),c);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public void create(Produit p) {
        String sql="insert into produits(design,prix,categorie_id) values('"+p.getDesign()+"','"+p.getPrix()+"','"+p.getCategorie().getId()+"')";
        try {
            if(stm.execute(sql))
                System.out.println("produit b1 ajouter");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Produit p) {
        String sql="delete from produits where id="+p.getId();
        try {
            if (stm.execute(sql))
                System.out.println("produit est supprimé");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Produit p) {
        String sql="update produits set design ='"+p.getDesign()+"',prix='"+p.getPrix()+"' where id='"+p.getId()+"' ";
        try {
            if (stm.execute(sql))
                System.out.println("produit updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Produit> findAll() {
        String sql="select *from produits";
        ArrayList<Produit> p=new ArrayList<>();
        try {
            rs=stm.executeQuery(sql);
            while(rs.next()) {
                Categorie c=(new CategorieDaoImpl()).find(rs.getInt("categorie_id"));
                p.add(new Produit(rs.getInt("id"),rs.getString("design"),rs.getDouble("prix"),c));
            }
            return p;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Produit> findAll(String key) {
        String sql="select *from produits WHERE design LIKE "+ "%" + key + "%"+"";
        ArrayList<Produit> p= new ArrayList<>();
        try {
            rs=stm.executeQuery(sql);
            while(rs.next()) {
                Categorie c=(new CategorieDaoImpl()).find(rs.getInt("categorie_id"));
                p.add(new Produit(rs.getInt("id"),rs.getString("design"),rs.getDouble("prix"),c));
            }
            return p;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getLastId() {
        String sql="select MAX(id) from produits";
        try {
            rs=stm.executeQuery(sql);
            if(rs.next()) {
                return rs.getInt(1)+1;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
