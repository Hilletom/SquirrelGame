package de.hsa.games.fatsquirrel.console;

import de.hsa.games.fatsquirrel.core.board.Board;
import de.hsa.games.fatsquirrel.core.board.BoardView;
import de.hsa.games.fatsquirrel.core.utils.MoveCommand;

public interface UI {
	public void render (BoardView view);
	public MoveCommand getCommand();
//	Command ReadCommand();
	public void execute(Board board);

}
