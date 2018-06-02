package de.hsa.games.fatsquirrel.util.ui.console;
import java.util.logging.Logger;
import java.util.logging.Level;
@SuppressWarnings("serial")
public class ScanException extends Exception {
	private static final Logger LOGGER = Logger.getLogger(CommandScanner.class.getName());
	ScanException(){

		super("Wrong Command");
		LOGGER.warning( "Wrong Command");
	}
	ScanException(String string){

		super(string);
	}
}
