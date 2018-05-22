package de.hsa.games.fatsquirrel.util.ui.console;

import java.io.BufferedReader;
import java.io.IOException;

import de.hsa.games.fatsquirrel.console.GameCommandType;

public class CommandScanner {
	private BufferedReader inputReader;
	public CommandScanner(BufferedReader inputReader) {
			this.inputReader = inputReader;
	}
	@SuppressWarnings("null")
	public Command next() {
		String input ="";
		try {
			input = inputReader.readLine();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		String[] splittinput = input.split(" ");
		Command command = null;
		switch(splittinput[0]) {
		case "help":
			command = new Command(GameCommandType.HELP, splittinput);
		    break;
		case "exit":
			command = new Command(GameCommandType.EXIT, splittinput);
		    break;
		case "a":
			command = new Command(GameCommandType.LEFT, splittinput);
		    break;
		case "UP":
			command = new Command(GameCommandType.UP, splittinput);
		    break;
		case "s":
			command = new Command(GameCommandType.DOWN, splittinput);
		    break;
		case "d":
			command = new Command(GameCommandType.RIGHT, splittinput);
		    break;
		case "spawn":
			Integer[] integerarray = {Integer.valueOf(splittinput[1])};
			command = new Command(GameCommandType.SPAWN_MINI, integerarray);
			break;
		case "masterenergy":
			command = new Command(GameCommandType.MASTER_ENERGY, splittinput);
			break;
		default:
			System.err.println("Wrong command!");
			command = next();
			break;
		}
		return command;
	}
	}