package dao;

import model.Categorie;
import model.Produit;

import java.util.List;

public interface CategorieDAO {
    public Categorie find(int id);
    public void create(Categorie c);
    public void delete(Categorie c) throws Exception;
    public void update(Categorie p);
    public List<Categorie> findAll();
    public List<Produit> findProduits(Categorie c);
    public int getLastId();
}
