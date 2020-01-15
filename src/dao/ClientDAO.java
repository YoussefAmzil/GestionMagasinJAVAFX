package dao;

import model.Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientDAO {
    public Client find(int id);
    public void create(Client p);
    public void delete(Client p) throws SQLException;
    public void update(Client p);
    public List<Client> findAll();
    public Client findAll(String key);
    public int getLastId();
}
