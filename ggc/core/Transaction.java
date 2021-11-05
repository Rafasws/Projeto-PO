package ggc.core;

import java.io.Serializable;

public abstract class Transaction implements Serializable{

    private int _id;
    
    private Double _value;

    private Date _paymentDate;
    
    public Transaction(int id){
        _id = id;
    }
}
