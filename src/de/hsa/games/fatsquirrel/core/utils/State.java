package de.hsa.games.fatsquirrel.core.utils;


import de.hsa.games.fatsquirrel.core.board.Board;
import de.hsa.games.fatsquirrel.core.board.FlattenedBoard;
import de.hsa.games.fatsquirrel.core.entities.Entity;
import de.hsa.games.fatsquirrel.core.entities.EntityContext;

public class State{
//	private int highscore = 10;
	private Board board;
	private FlattenedBoard flattenedboard;
	
	public State (Board board){
		this.board = board;
		flattenedboard = new FlattenedBoard(board);
		
		
	}
	public void update () {
		board.updateCharacter((EntityContext) flattenedboard);
		flattenedboard.update();
		
	}
	public FlattenedBoard getflattenedboard() {
		return flattenedboard;
	}
	public Entity[] getEntityArray() {
		return board.getEntityArray();
	}
	

}
