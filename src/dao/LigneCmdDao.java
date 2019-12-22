package dao;

import model.LigneCmd;
import model.Vente;

import java.util.List;

public interface LigneCmdDao {
    public LigneCmd find(int id);
    public void create(LigneCmd p, Vente v);
    public void delete(LigneCmd p);
    public void update(LigneCmd p);
    public List<LigneCmd> findAll();
    public List<LigneCmd> findAll(int key);
    public int getLastId();
}
