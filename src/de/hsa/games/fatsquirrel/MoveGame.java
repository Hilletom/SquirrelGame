package de.hsa.games.fatsquirrel;

import de.hsa.games.fatsquirrel.console.UI;
import de.hsa.games.fatsquirrel.core.board.Board;

public class MoveGame extends Thread{
	UI ui;
	Board board;
	public MoveGame(UI ui, Board board){
		this.ui = ui;
		this.board = board;
	}
	public void run() {
		while (true) {
		ui.execute(board);
		}
	}

}
