package de.hsa.games.fatsquirrel.botapi;

import de.hsa.games.fatsquirrel.core.entities.EntityEnum;
import de.hsa.games.fatsquirrel.core.utils.XY;

public interface ControllerContext {
	public XY getViewLowerLeft();
	public XY getViewUpperRight();
	public EntityEnum getEntityAt(XY xy);
	public void move(XY direction);
	public void spawnMiniBot(XY direction, int energy);
	public int getEnergy();
}
