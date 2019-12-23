package Controller;

import dao.CategorieDAO;
import db.DataConnection;
import model.Categorie;
import model.Produit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategorieDaoImpl implements CategorieDAO {
    Connection cnx;
    Statement stm = null;
    ResultSet rs = null;

    public CategorieDaoImpl() {
        try {
            cnx = DataConnection.getInstance().getConnection();
            this.stm = this.cnx.createStatement();
            this.rs = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Categorie find(int id) {
        String sql = "select *from categories where id=" + id;
        try {
            rs = stm.executeQuery(sql);
            if (rs.next()) {
                return new Categorie(rs.getInt("id"), rs.getString("label"));
            }
        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public void create(Categorie c) {
        String sql = "insert into categories(label) values('" + c.getLabel() + "')";
        try {
            if (stm.execute(sql))
                System.out.println("categories created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void delete(Categorie c) {
        String sql = "delete from categories where id=" + c.getId();
        try {
            if (stm.execute(sql)) System.out.println("categorie deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Categorie p) {
        String sql = "update categories set label ='" + p.getLabel() + "' where id='" + p.getId() + "' ";
        try {
            if (stm.execute(sql))
                System.out.println("produit updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Categorie> findAll() {
        String sql = "select *from categories";
        ArrayList<Categorie> c = new ArrayList<>();
        try {
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                c.add(new Categorie(rs.getInt("id"), rs.getString("label")));
            }
            return c;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Produit> findProduits(Categorie c) {
        String sql = "select *from produits where categorie_id=" + c.getId();
        List<Produit> p = new ArrayList<>();
        try {
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                p.add(new Produit(rs.getString("design"), rs.getDouble("prix"), c));
            }
            return p;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getLastId() {
        String sql = "select MAX(id) from categories";
        try {
            rs = stm.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt(1) + 1;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;

    }
}

