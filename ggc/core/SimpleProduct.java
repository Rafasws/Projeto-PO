package ggc.core;

import java.text.DecimalFormat;
import java.text.NumberFormat;
/** Classe que represnta um produto Simple que é um tipo de Produto
 * Cada produto Simple é composto por id, Stock total e preço maximo,
 * como o produto
  */
public class SimpleProduct extends Product {

    /**Contrutor
     * @param id que é o identificador do Produto Simples
     */
    public SimpleProduct(String id) {
        super(id);
    }

    /**Devolve uma string com os atributos do Produto Simples 
     * @return String str
     */
   @Override
    public String toString(){
        NumberFormat formatter = new DecimalFormat("0");  
        String newDVal = formatter.format(_maxPrice);
        return _id + "|" + newDVal + "|" + _totalStock;
    }
}