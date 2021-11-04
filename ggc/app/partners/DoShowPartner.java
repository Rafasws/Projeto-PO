package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exception.UnknownPartnerKeyException;
import ggc.core.WarehouseManager;
//FIXME import classes

/**
 * Show partner.
 */
class DoShowPartner extends Command<WarehouseManager> {

  DoShowPartner(WarehouseManager receiver) {
    super(Label.SHOW_PARTNER, receiver);
    addStringField("id", Message.requestPartnerKey());
    //FIXME add command fields
  }

  @Override
  public void execute() throws CommandException {
    String id = stringField("id");
    try {
      _display.addLine(_receiver.showPartner(id));
      _display.display();
    }
    catch (UnknownPartnerKeyException e) {
      throw new UnknownPartnerKeyException(id);
    }
  }
  //FIXME implement command
  

}
