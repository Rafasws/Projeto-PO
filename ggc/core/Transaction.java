package ggc.core;

import java.io.Serializable;

public abstract class Transaction implements Serializable{

    protected int _id;

    protected Partner _partner;

    protected Product _product;
    
    protected Double _value;

    protected Date _paymentDate;
    
    protected int _quantity;
    
    public Transaction(int id, Product product, Partner partner, int quantity){
        _id = id;
        _product = product;
        _partner = partner;
        _quantity = quantity;
    }
}
