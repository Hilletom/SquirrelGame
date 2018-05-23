package de.hsa.games.fatsquirrel.core.playerBased;

import de.hsa.games.fatsquirrel.core.entities.Character;
import de.hsa.games.fatsquirrel.core.utils.MoveCommand;
import de.hsa.games.fatsquirrel.core.utils.XY;

public abstract class PlayerEntity extends Character {
	protected MoveCommand nMoveCommand;

	public PlayerEntity(int id, XY xy, int energy) {
		super(id, xy, energy);
	}
	public void setMoveCommand(MoveCommand movecommand) {
		nMoveCommand = movecommand;
	}
	

}
