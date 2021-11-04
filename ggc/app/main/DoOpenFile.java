package ggc.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.io.IOException;
import java.io.Serializable;

import ggc.app.exception.FileOpenFailedException;
import ggc.core.WarehouseManager;

//FIXME import classes

/**
 * Open existing saved state.
 */
class DoOpenFile extends Command<WarehouseManager> implements Serializable{

  private static final long serialVersionUID = 202109192006L;

  /** @param receiver */
  DoOpenFile(WarehouseManager receiver) {
    super(Label.OPEN, receiver);
    addStringField("fileName", Message.openFile());
    //FIXME maybe add command fields
  }

  @Override
  public final void execute() throws CommandException {
    String filename = stringField("fileName");
    try{
      _receiver.load(filename);
      _receiver.setFileName(filename);
    } catch (IOException | ClassNotFoundException e) {
      throw new FileOpenFailedException(filename);
   }
  }
}
