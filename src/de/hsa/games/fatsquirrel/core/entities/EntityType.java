package de.hsa.games.fatsquirrel.core.entities;

public class EntityType {
	protected int id;
	protected int energy;
	public EntityType (EntityType entitytype) {
		this.id = entitytype.id;
		this.energy = entitytype.energy;
	}

}
