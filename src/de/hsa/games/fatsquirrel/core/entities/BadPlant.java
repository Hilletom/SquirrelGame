package de.hsa.games.fatsquirrel.core.entities;


import de.hsa.games.fatsquirrel.core.utils.XY;

public class BadPlant extends Entity {
	public BadPlant(int id, XY xy) {
		super(id, xy, energy);
		this.type = EntityEnum.badPlant;
	}

	final static int energy = -100;

	
}
