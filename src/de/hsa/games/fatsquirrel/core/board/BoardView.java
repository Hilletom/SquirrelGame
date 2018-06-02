package de.hsa.games.fatsquirrel.core.board;

import de.hsa.games.fatsquirrel.core.entities.EntityEnum;
import de.hsa.games.fatsquirrel.core.utils.XY;

public interface BoardView {
	public EntityEnum getEntityTyp (int x, int y);
	public XY getSize();

}
