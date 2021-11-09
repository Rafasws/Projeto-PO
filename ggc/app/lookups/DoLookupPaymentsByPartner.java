package ggc.app.lookups;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
//FIXME import classes

/**
 * Lookup payments by given partner.
 */
public class DoLookupPaymentsByPartner extends Command<WarehouseManager> {

  public DoLookupPaymentsByPartner(WarehouseManager receiver) {
    super(Label.PAID_BY_PARTNER, receiver);
    addStringField("partnerId", Message.requestPartnerKey());
  }

  @Override
  public void execute() throws CommandException {
    String partnerId = stringField("partnerId");
    _display.addLine(_receiver.showTransactionsPaid(partnerId));
    _display.display();
  }

}
