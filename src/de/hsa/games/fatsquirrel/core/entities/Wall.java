package de.hsa.games.fatsquirrel.core.entities;


import de.hsa.games.fatsquirrel.core.utils.XY;

public class Wall extends Entity {
	public Wall (int id, XY xy) {
		super(id, xy, energy);
		this.type = EntityEnum.wall;
	}

	final static int energy = -10;


}
