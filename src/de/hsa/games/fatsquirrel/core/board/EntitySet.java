package de.hsa.games.fatsquirrel.core.board;


import de.hsa.games.fatsquirrel.core.board.BoardConfig;
import de.hsa.games.fatsquirrel.core.entities.Character;
import de.hsa.games.fatsquirrel.core.entities.Entity;
import de.hsa.games.fatsquirrel.core.entities.EntityContext;

public class EntitySet {
	BoardConfig boardconfig = new BoardConfig();
	private Entity[] entityarray = new Entity[boardconfig.getXBoard()*boardconfig.getYBoard()];
	public Entity [] getEntityArray() {
		return entityarray;
	}
	
	public void addentity (Entity entity) {
		for (int j = 0; j <= entityarray.length; j++) {
			if(entityarray[j] == null) {
				entityarray[j] = entity;
				return;
			}
			
		}System.out.println("Max Anzahl an Entitys erreicht");
		return;
	}
	public void removeentity (Entity entity, Entity[] entityarray) {
		for (int j = 0; j <= entityarray.length; j++) {
				if(entityarray[j] == entity) {
					entityarray[j] = null;
					return;
	}}}
	public void EntitytoString() {
		for (int g = 0; g <= entityarray.length; g++) {
			if(entityarray[g] != null) {
		System.out.println(entityarray[g]);
		
		}else {
			break;
		}
		}
	}
	public void entitynextstep(EntityContext context) {
		for(Entity entity : entityarray) {
			if(entity == null) {
				break;		
			}else {
				if(entity instanceof Character) {
				Character character = (Character) entity;
				character.nextstep(context);
				}}}
		}}
