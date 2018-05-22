package de.hsa.games.fatsquirrel.console;

import de.hsa.games.fatsquirrel.util.ui.console.CommandTypeInfo;

public enum GameCommandType implements CommandTypeInfo{
	HELP("h", "all commands"),
	EXIT("escape", "exit game"),
	LEFT("left", "move left"),
	UP("up", "move up"),
	DOWN("down", "move down"),
	RIGHT("right", "move right"),
	MASTER_ENERGY("e", "display of current master energy"),
	SPAWN_MINI("s", "spawn one minisquirrel with 100 energy", int.class);
	private String name;
	private String description;
	private Class<Integer> minienergy;
	
	GameCommandType (String name, String description){
		this.name = name;
		this.description = description;
	}
	GameCommandType (String name, String description, Class<Integer> minienergy){
		this.name = name;
		this.description = description;
		this.minienergy = minienergy;
	}
	public String getName() {
		return name;
	}
	@Override
	public Class<Integer> getParamType() {
		return minienergy;
	}
	@Override
	public String getHelpText() {
		return description;
	}
	
}
