package ggc.core;

import java.io.Serializable;
/** Classe abstrata que represnta um produto
 * Cada produto é composto por id, Stock total e preço maximo
  */
public abstract class Product implements Serializable{

    protected String _id;

    protected int _totalStock;  

    protected Double _maxPrice;

    /**Contrutor 
     * @param id que é o identificador do prduto */
    public Product(String id){
        _id = id;
        _totalStock = 0;
    }

    /**Devolve o identificador do produto
     * @return String _id
     */
    public String getId(){
        return _id;
    }

    /**altera o preço maximo do produto
     * @param k que é o novo preço maximo
     */
    public void setMaxPrice(Double k){
        _maxPrice = k;
    }

    /**altera o stock do produto
     * @param n que é o novo stock total
     */
    public void setTotalStock(int n){
        _totalStock = n;
    }
}
