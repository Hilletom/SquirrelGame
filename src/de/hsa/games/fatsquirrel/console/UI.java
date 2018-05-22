package de.hsa.games.fatsquirrel.console;

import de.hsa.games.fatsquirrel.core.Board;
import de.hsa.games.fatsquirrel.core.BoardView;
import de.hsa.games.fatsquirrel.core.MoveCommand;

public interface UI {
	public void render (BoardView view);
	public MoveCommand getCommand();
//	Command ReadCommand();
	public void execute(Board board);

}
