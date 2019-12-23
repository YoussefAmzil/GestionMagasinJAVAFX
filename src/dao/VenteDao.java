package dao;

import model.LigneCmd;
import model.Vente;

import java.util.List;

public interface VenteDao {
    public Vente find(int id);
    public Vente create(Vente v);
    public void delete(Vente p);
    public void update(Vente p);
    public List<Vente> findAll();
    public int getLastId();
    List<LigneCmd> findAll(int v);
}
