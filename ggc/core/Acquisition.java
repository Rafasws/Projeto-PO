package ggc.core;

public class Acquisition extends Transaction{

    public Acquisition(int id, Product product, Partner partner, int quantity) {
        super(id, product, partner, quantity);
    }

    public String toString(){
        String str = "COMPRA|" + _id + "|" + _partner.getId() + "|" + _product.getId() + "|" + _quantity + "|" + _value + "|" + _paymentDate;
        return str;
    }
}
