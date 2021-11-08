package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.util.LinkedHashMap;
import java.util.Map;

import ggc.core.Partner;
import ggc.core.Product;
import ggc.core.WarehouseManager;
//FIXME import classes

/**
 * Register order.
 */
public class DoRegisterAcquisitionTransaction extends Command<WarehouseManager> {

  public DoRegisterAcquisitionTransaction(WarehouseManager receiver) {
    super(Label.REGISTER_ACQUISITION_TRANSACTION, receiver);
    addStringField("idPartner", Message.requestPartnerKey());
    addStringField("idProduct", Message.requestProductKey());
    addRealField("price", Message.requestPrice());
    addIntegerField("amount", Message.requestAmount());
  }

  @Override
  public final void execute() throws CommandException {
    String idPartner = stringField("idPartner");
    String idProduct = stringField("idProduct");
    Double price = realField("price");
    int amount = integerField("amount");
    LinkedHashMap<String, Product> products = _receiver.getProducts();
    LinkedHashMap<String, Partner> patners = _receiver.getPartners();

    for(Map.Entry<String, Product> entry: products.entrySet()){
    }
    _receiver.registerAcquisition(idPartner, idProduct, price, amount);
    
  }

}
