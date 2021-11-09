package ggc.core;

//FIXME import classes (cannot import from pt.tecnico or ggc.app)

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;


import ggc.core.exception.BadEntryException;
import ggc.core.exception.ImportFileException;
import ggc.core.exception.UnavailableFileException;
import ggc.core.exception.MissingFileAssociationException;

import ggc.app.exception.DuplicatePartnerKeyException;
import ggc.app.exception.FileOpenFailedException;
import ggc.app.exception.InvalidDateException;
import ggc.app.exception.UnknownPartnerKeyException;
import ggc.app.exception.UnknownProductKeyException;
import ggc.app.exception.UnknownTransactionKeyException;

/** Façade for access. */
public class WarehouseManager implements Serializable {

  /** Name of file storing current warehouse. */
  private String _filename = "";

  /** The wharehouse itself. */
  private Warehouse _warehouse = new Warehouse();

  //FIXME define other attributes
  //FIXME define constructor(s)
  //FIXME define other methods
  
  public String getFileName(){
    return _filename;
  }

  public void setFileName(String filename) {
    _filename = filename;
  }

  /**
   * @@throws IOException
   * @@throws FileNotFoundException
   * @@throws MissingFileAssociationException
   */

  public void save() throws IOException, FileNotFoundException, MissingFileAssociationException {
    //FIXME implement serialization method
    ObjectOutputStream oos = null;
    try{
    oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(_filename)));
    oos.writeObject(_warehouse);
    }
    finally{
      if (oos != null){
        oos.close();
      }
    }
  }

  /**
   * @@param filename
   * @@throws MissingFileAssociationException
   * @@throws IOException
   * @@throws FileNotFoundException
   */
  public void saveAs(String filename) throws MissingFileAssociationException, FileNotFoundException, IOException {
    _filename = filename;
    save();
  }

  /**
   * @@param filename
   * @@throws UnavailableFileException
   */
  public void load(String filename) throws  FileOpenFailedException, ClassNotFoundException, IOException {
    //FIXME implement serialization method
    _filename = filename;
    ObjectInputStream obIn = null;
    try {
      obIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)));
      _warehouse = (Warehouse) obIn.readObject();
    } finally {
      if (obIn != null){
        obIn.close();
      }
    }
    //ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)));
    //_warehouse = (Warehouse) ois.readObject();
    //ois.close(); 
  }

  /**
   * @param textfile
   * @throws ImportFileException
   */
  public void importFile(String textfile) throws ImportFileException {
    try {
      _warehouse.importFile(textfile);

    } catch (IOException | BadEntryException|  UnavailableFileException e) {
      throw new ImportFileException(textfile, e);
    }
  }

  /**Date functions*/

  public void advanceDate(int days) throws InvalidDateException{
    _warehouse.advanceDate(days);
  }

  public int displayDate(){
    return _warehouse.displayDate();
  }

  /**Partner functions*/

  public void registerPartner(String id, String name, String address) throws DuplicatePartnerKeyException {
    _warehouse.registerPartner(id, name, address);
  }

  public String showAllPartners(){
    return _warehouse.showAllPartners();
  }

  public String showPartner(String id) throws UnknownPartnerKeyException{
    return _warehouse.showPartner(id);
  }

  public String showAvailableBatches(){
    return _warehouse.showAvailableBatches();
  }

  public String showAllProducts(){
    return _warehouse.showAllProducts();
  }

  public String showBatchesByPartner(String id) throws UnknownPartnerKeyException {
    return _warehouse.showBatchesByPartner(id);
  }

  public String showBatchesByProduct(String id) throws UnknownProductKeyException {
    return _warehouse.showBatchesByProduct(id);
  }

  public String showBatchesPrice(Double price){
    return _warehouse.showBatchesPrice(price);
  }

  public LinkedHashMap<String, Product> getProducts(){
    return _warehouse.getMapOfProducts();
  }

  public LinkedHashMap<String, Partner> getPartners(){
    return _warehouse.getMapOfPartners();
  }

  public void registerAcquisition(String idPartner, String idProduct, Double price, int amount){
    _warehouse.registerAcquisition(idPartner, idProduct, price, amount);
  }

  public String showTransaction(int id) throws UnknownTransactionKeyException {
    return _warehouse.showTransaction(id);
  }

  public String showTransactionsPaid(String partnerId) {
    return _warehouse.showTransactionsPaid(partnerId);
  }


}
