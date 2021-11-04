package ggc.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exception.InvalidDateException;
import ggc.core.WarehouseManager;
//FIXME import classes

/**
 * Advance current date.
 */
class DoAdvanceDate extends Command<WarehouseManager> {

  DoAdvanceDate(WarehouseManager receiver) {
    super(Label.ADVANCE_DATE, receiver);
    addIntegerField("days", Message.requestDaysToAdvance());
  }

  @Override
  public final void execute() throws CommandException {
    int days = integerField("days");
    if( days <= 0){
      throw new InvalidDateException(days);
    }
    try{
    _receiver.advanceDate(days);
    }
    catch(InvalidDateException e){
      throw new InvalidDateException(days);
    }
  }

}
