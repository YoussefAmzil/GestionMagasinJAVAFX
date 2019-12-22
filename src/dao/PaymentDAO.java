package dao;

import model.Payment;
import model.Vente;

import java.util.List;

public interface PaymentDAO {
    public List getpayments(Vente v);
    public void create(Payment p);
}
