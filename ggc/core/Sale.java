package ggc.core;

public abstract class Sale extends Transaction {

    public Sale(int id, Product product, Partner partner, int quantity) {
        super(id, product, partner, quantity);
    }
    
}
