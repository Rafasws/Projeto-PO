package ggc.core;

// FIXME import classes (cannot import from pt.tecnico or ggc.app)

import java.io.Serializable;
import java.io.IOException;

import ggc.app.exception.DuplicatePartnerKeyException;
import ggc.app.exception.InvalidDateException;
import ggc.app.exception.UnknownPartnerKeyException;
import ggc.app.exception.UnknownTransactionKeyException;
import ggc.core.exception.BadEntryException;
import ggc.core.exception.UnavailableFileException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Class Warehouse implements a warehouse.
 */
public class Warehouse implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202109192006L;

  // FIXME define attributes
  /** Date*/
  private Date _date = new Date();

  /** List of batches*/
  private List<Batch> _batchs = new ArrayList<>();

  /** Map of partners, key is id partner, value is a Partner */
  private LinkedHashMap<String, Partner> _partners = new LinkedHashMap<>();

  /** Map of products, key is id product, value is a Product */
  private LinkedHashMap<String, Product> _products = new LinkedHashMap<>();

  private List<Transaction> _transactions = new ArrayList<>();

  



  // FIXME define contructor(s)

  public LinkedHashMap<String, Product> getMapOfProducts(){
    return _products;
  }

  public LinkedHashMap<String, Partner> getMapOfPartners(){
    return _partners;
  }

  /**
   * @param txtfile filename to be loaded.
   * @throws IOException
   * @throws BadEntryException
   */

  public void importFile(String txtfile) throws IOException, BadEntryException, UnavailableFileException  {
    //FIXME implement method
    Parser parser = new Parser(this);
    parser.parseFile(txtfile);
  }

  /**
   * Advance Date
   * 
   * @param days
   * @throws InvalidDateException
   */
  public void advanceDate(int days) throws InvalidDateException{
    _date.setDate(days);
  }

  /**
   * Display Date
   * 
   * @return inteiro que representa a data
   */
  public int displayDate(){
    return _date.getDate();
  }

  /**
    * Registers a partner
    *
    * @param name is the parter's name
    * @param id is the parter's id
    * @param address is the parter's address
    * @throws DuplicatePartnerKeyException if the kis taken
    * @return Nothing
    */
  public void registerPartner(String id, String name, String address) throws DuplicatePartnerKeyException {
    for(Map.Entry<String, Partner> entry: _partners.entrySet()){
      String ENTRY = entry.getValue().getId().toUpperCase();
      String ID = id.toUpperCase();
      if(ENTRY.equals(ID)){
        throw new DuplicatePartnerKeyException(id);
      }
    }
    Partner p = new Partner(id, name, address);
    _partners.put(p.getId(), p);
  }

  /**
    * Shows all partners 
    *
    * @return String with all Partners
    */ 
  public String showAllPartners() {
    String str = "";
    for(Map.Entry<String, Partner> entry: _partners.entrySet()){
        Partner p = entry.getValue();
        str = str + p.toString();
    }
    return str; 
  }

  /**
    * Show a partner
    *
    * @param id is the parter's id
    * @throws UnknownPartnerKeyException if the kis do not exist
    * @return String with a Partner
    */
  public String showPartner(String id) throws UnknownPartnerKeyException{
    boolean exist = false; 
    for(Map.Entry<String, Partner> entry: _partners.entrySet()){
      String ENTRY = entry.getValue().getId().toUpperCase();
      String ID = id.toUpperCase();
      if(ENTRY.equals(ID)){
        exist = true;
        id = entry.getValue().getId();
      }
    }
    if(!exist){ 
      throw new UnknownPartnerKeyException(id); 
    }
    else{
      Partner s = _partners.get(id);
      return s.toString();
    } 
  }

  /**
    * Register a Simple Product
    *
    * @param id is the product's id
    */
  public void registerSimpleProduct(String id){
    Product p = new SimpleProduct(id);
    _products.put(p.getId(), p);
  }

    /**
    * Register a Simple Product
    *
    * @param id is the product's id
    */
  public void registerAggregateProduct(String id, Double aggravation, LinkedHashMap<Product, Integer> recipe){
    Product p = new AggregateProduct(id, aggravation, recipe);
    _products.put(p.getId(), p);
  }

  public String showAllProducts(){
    String str ="";
    for(Map.Entry<String, Product> entry: _products.entrySet()){
      Product p = entry.getValue();
      p.setMaxPrice(getMaxPriceProduct(p));
      p.setTotalStock(getStockProduct(p));
      str = str + p.toString() + "\n" ;
    }
    return str; 
  }

  public Product getProduct(String string) {
    return _products.get(string);
  }

  public int getStockProduct(Product p){
    int soma = 0;
    for (Batch batch : _batchs){
      if (p.getId() == batch.getProduct().getId()){
        soma += batch.getStock();
      }
    }
    return soma;
  }

  public Double getMaxPriceProduct(Product p){
    double price = 0;
    for (Batch batch : _batchs){
      if (p.getId() == batch.getProduct().getId() && price < batch.getPrice()){
        price = batch.getPrice();
      }
    }
    return price;
  }

  public void addBatch(Batch b){
    _batchs.add(b);
  }

  public String showAvailableBatches() {
    Collections.sort(_batchs, new ShortBatches());
    String str = "";
    for(Batch b: _batchs){
      str = str + b.toString();
    }
    return str; 
  }

  public String showBatchesByPartner(String id){
    Collections.sort(_batchs, new ShortBatches());
    String str = "";
    for(Batch b: _batchs){
      if(id.toLowerCase().equals(b.getPartner().getId().toLowerCase())){
        str = str + b.toString();
      }
    }
    return str;
  }

  public String showBatchesByProduct(String id){
    Collections.sort(_batchs, new ShortBatches());
    String str = "";
    for(Batch b: _batchs){
      if(id.toLowerCase().equals(b.getProduct().getId().toLowerCase())){
        str = str + b.toString();
      }
    }
    return str;
  }

  public String showBatchesPrice(Double price){
    String str = "";
    for(Batch b: _batchs){
      if(b.getPrice() < price){
        str = str + b.toString();
      }
    }
    return str;
  }

  public void registerAcquisition(String idPartner, String idProduct, Double price, int amount){
    Acquisition a = new Acquisition(_transactions.size(), _products.get(idProduct),_partners.get(idPartner), amount);
    _transactions.add(a);
  }

  public String showTransaction(int id) throws UnknownTransactionKeyException {
    try{
    Transaction t =_transactions.get(id);
    return t.toString();
    }
    catch (IndexOutOfBoundsException e) {
      throw new UnknownTransactionKeyException(id);
    }
    
  }

  public String showTransactionsPaid(String partnerId) {
    String str = "";
    for (Transaction t: _transactions){
      if (t.getPartner().getId().equals(partnerId) && t.isPaid()){
        str = str + t.toString();
      }
    }
    return str;
  }
}