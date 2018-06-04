package de.hsa.games.fatsquirrel.core.entities;

import de.hsa.games.fatsquirrel.core.playerBased.PlayerEntity;
import de.hsa.games.fatsquirrel.core.utils.XY;
import de.hsa.games.fatsquirrel.core.playerBased.MasterSquirrel;
import de.hsa.games.fatsquirrel.core.playerBased.MiniSquirrel;

public interface EntityContext {
	
	public XY getSize();
	public void tryMove (MiniSquirrel minisquirrel, XY direction);
	public void tryMove (GoodBeast goodbeast, XY direction);
	public void tryMove (BadBeast badbeast, XY direction);
	public void tryMove (MasterSquirrel master, XY direction);
	public PlayerEntity nearestPlayer (XY playerpos);

	public void kill (Entity entity);
	public void killandReplace (Entity entity);
	public String getEntityType (XY xy);
	public void spawnMini (int energy, MasterSquirrel masterSquirrel);
	public Entity getEntityAt (XY xy);
}
