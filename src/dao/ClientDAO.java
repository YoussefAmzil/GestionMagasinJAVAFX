package dao;

import model.Client;

import java.util.List;

public interface ClientDAO {
    public Client find(int id);
    public void create(Client p);
    public void delete(Client p);
    public void update(Client p);
    public List<Client> findAll();
    public Client findAll(String key);
    public int getLastId();
}
