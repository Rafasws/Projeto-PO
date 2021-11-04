package ggc.core;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/** Classe que represnta um lote
 * Cada lote é composto por um produto, parceiro, Stock e preço
  */ 
public class Batch implements Serializable{
    

    private Product _product;

    private Partner _partner;

    private Double _price;

    private int _stock;


    /**Contrutor 
     * @param product o produto do lote
     * @param partner o parceiro do lote
     * @param price o preço de cada produto do lote
     * @param stock o stock disponivel do produto
     *  */
    public Batch(Product product, Partner partner, Double price, int stock){
    _product = product;
    _partner = partner;
    _price = price;
    _stock = stock;
    }

    /**Devolve o parceiro do lote
     * @return Partner
     */
    public Partner getPartner(){
        return _partner;
    }

    /**Devolve o produto do lote
     * @return Product product
     */
    public Product getProduct(){
        return _product;
    }

    /**Devolve o preço de cada produto
     * @return Double price 
     */
    public Double getPrice(){
        return _price;
    }

    /**Devolve o stock do produto
     * @return int stock
     */
    public int getStock(){
        return _stock;
    }

    /**Devolve uma string com os atributos do lote 
     * @return String str
     */
    @Override
    public String toString(){
        NumberFormat formatter = new DecimalFormat("0");  
        String newDVal = formatter.format(_price);
        String str = _product.getId() + "|" + _partner.getId() + "|" + newDVal + "|" + _stock + "\n";
        return str;
    }
}