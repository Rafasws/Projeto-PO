package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exception.DuplicatePartnerKeyException;
import ggc.core.WarehouseManager;
//FIXME import classes

/**
 * Register new partner.
 */
class DoRegisterPartner extends Command<WarehouseManager> {

  DoRegisterPartner(WarehouseManager receiver) {
    super(Label.REGISTER_PARTNER, receiver);
    addStringField("partnerId", Message.requestPartnerKey());
    addStringField("partnerName", Message.requestPartnerName());
    addStringField("partnerAddress", Message.requestPartnerAddress());
  }

  @Override
  public void execute() throws CommandException {
    String partnerId = stringField("partnerId");
    String partnerName = stringField("partnerName");
    String partnerAddres = stringField("partnerAddress");
    try{
      _receiver.registerPartner(partnerId, partnerName, partnerAddres);
    }
    catch(DuplicatePartnerKeyException e){
      throw new DuplicatePartnerKeyException(partnerId);
    }
  }

}
