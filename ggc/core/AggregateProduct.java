package ggc.core;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;

/** Classe que represnta um produto Agregado que é um tipo de Produto
 * Cada produto Agregado é composto por id, Stock total e preço maximo, como o Produto
 * E ainda pela sua recita e pelo agravamento
  */
public class AggregateProduct extends Product {

    private LinkedHashMap<Product, Integer> _recipe = new LinkedHashMap<>();

    private Double _aggravation;

    /**Contrutor
     * @param id que é o identificador do Produto Agregado
     * @param aggravation que é o agravamento do Produto Agragado
     * @param Recipe que é a receita do Produto Agregado, que contem os Produtos constituintes e as respetivas quantidades
     */
    public AggregateProduct(String id, Double aggravation, LinkedHashMap<Product, Integer> recipe) {
        super(id);
        _aggravation = aggravation;
        _recipe = recipe;
    }

    /**Devolve  o agravamento
     * @return Double _aggravation
     */
    public Double getAggravation(){
        return _aggravation;
    }

    /**Devolve uma string com os atributos do Produto Agregado
     * @return String str
     */
    @Override
    public String toString(){
        NumberFormat formatter = new DecimalFormat("0");  
        String newDVal = formatter.format(_maxPrice);
        String str1 = "";
        for(HashMap.Entry<Product, Integer> entry: _recipe.entrySet()){
            int n = entry.getValue();
            Product p = entry.getKey();
            str1 = str1 + p.getId() + ":" + n + "#";
        }
        str1 = str1.substring (0, str1.length() - 1);
        String str2 = this.getId() + "|" + newDVal + "|" + _totalStock + "|" + this.getAggravation() + "|";
        return str2 + str1;

    }
    
}
