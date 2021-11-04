package ggc.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;

import java.io.IOException;
import java.io.Serializable;

import ggc.core.WarehouseManager;
import ggc.core.exception.MissingFileAssociationException;

/**
 * Save current state to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<WarehouseManager> implements Serializable {

  private static final long serialVersionUID = 202109192006L;



  /** @param receiver */
  DoSaveFile(WarehouseManager receiver) {
    super(Label.SAVE, receiver);
  }

  @Override
  public final void execute() throws CommandException {
    //FIXME implement command and create a local Form
    try{
      if(_receiver.getFileName().isEmpty()) {
        String fileName = Form.requestString(Message.newSaveAs());
        _receiver.saveAs(fileName);
      }
      else {
        _receiver.save();
      }
    }
    catch(MissingFileAssociationException mfae){
    }
    catch (IOException e) {
      e.printStackTrace();
    }

  }

}
