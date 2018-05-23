package de.hsa.games.fatsquirrel.core.entities;


import de.hsa.games.fatsquirrel.core.utils.XY;

public class GoodPlant extends Entity {
	public GoodPlant(int id, XY xy) {
		super(id, xy, energy);
		this.type = EntityEnum.goodPlant;
	}

	final static int energy = 100;


}
