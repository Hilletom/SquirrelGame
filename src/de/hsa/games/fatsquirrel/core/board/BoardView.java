package de.hsa.games.fatsquirrel.core.board;

import de.hsa.games.fatsquirrel.core.utils.XY;

public interface BoardView {
	public String getEntityTyp (int x, int y);
	public XY getSize();

}
