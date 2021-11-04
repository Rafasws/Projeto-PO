package ggc.app.products;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
//FIXME import classes

/**
 * Show batches supplied by partner.
 */
class DoShowBatchesByPartner extends Command<WarehouseManager> {

  DoShowBatchesByPartner(WarehouseManager receiver) {
    super(Label.SHOW_BATCHES_SUPPLIED_BY_PARTNER, receiver);
    addStringField("id", Message.requestPartnerKey());
  }

  @Override
  public final void execute() throws CommandException {
    String partnerId = stringField("id");
    _display.addLine(_receiver.showBatchesByPartner(partnerId));
    _display.display();
  }

}
