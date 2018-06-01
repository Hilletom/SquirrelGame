package de.hsa.games.fatsquirrel.console;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import Command.Commands;
import de.hsa.games.fatsquirrel.core.board.Board;
import de.hsa.games.fatsquirrel.core.board.BoardView;
import de.hsa.games.fatsquirrel.core.utils.MoveCommand;
import de.hsa.games.fatsquirrel.util.ui.console.CommandScanner;

public class ConsoleUI implements UI{
    private static final Logger LOGGER = Logger.getLogger(ConsoleUI.class.getName());
	int puffer;
	private Commands commands;
	private BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
	private CommandScanner commandscanner = new CommandScanner(inputReader);
	public ConsoleUI() {
		
	}

	@Override
	public void render(BoardView view) {
		System.out.print(view.toString());
	}

	@Override
	public MoveCommand getCommand(){
		return new MoveCommand(puffer);
	}
	
	@Override
	public void execute(Board board) {
		this.commands = new Commands(board, this.puffer);
        try {
            System.out.print("Bitte geben sie einen Befehl ein!" + '\n' + "<help> für alle Befehle!"+'\n');
            String[] p = read();
            if(p.length < 2){
                execCommand(p[0]);
            }else{
                execCommand(p[0],p[1]);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
		
		
	}
	
    public Object execCommand(String command){
        try {
            Method method = commands.getClass().getMethod(command);
            Object reval = method.invoke(commands);
            puffer = (int)reval;
            return reval;
        }catch (NoSuchMethodException e1){
            LOGGER.fine ("No Such Utils.Command");
            System.out.println("No Such Utils.Command");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object execCommand(String command, String Arguments){
        try {
            Method method = commands.getClass().getMethod(command, Arguments.getClass());
            Object reval = method.invoke(commands, Arguments);
            return reval;
        }catch (NoSuchMethodException e1){
            LOGGER.fine("No Such Utils.Command with arguments");
            System.out.println("No Such Utils.Command with arguments");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private String[] read() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String a = bf.readLine();
        String[] buf = a.split(" ");
        return buf;
    }


}
