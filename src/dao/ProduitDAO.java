package dao;

import java.util.List;

import model.Produit;

public interface ProduitDAO {
	public Produit find(int id);
	public void create(Produit p);
	public void delete(Produit p);
	public void update(Produit p);
	public List<Produit> findAll();
	public List<Produit> findAll(String key);
	public int getLastId();
	
}
