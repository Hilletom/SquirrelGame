package de.hsa.games.fatsquirrel.core.board;


import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.core.board.BoardConfig;
import de.hsa.games.fatsquirrel.core.bots.ControllerContextImpl;
import de.hsa.games.fatsquirrel.core.bots.MasterSquirrelBot;
import de.hsa.games.fatsquirrel.core.bots.MiniSquirrelBot;
import de.hsa.games.fatsquirrel.core.entities.Character;
import de.hsa.games.fatsquirrel.core.entities.Entity;
import de.hsa.games.fatsquirrel.core.entities.EntityContext;
import de.hsa.games.fatsquirrel.core.entities.EntityEnum;
import de.hsa.games.fatsquirrel.core.utils.XY;

import java.util.logging.Logger;
import java.util.logging.Level;

public class EntitySet {
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	BoardConfig boardconfig = new BoardConfig();
	private Entity[] entityarray = new Entity[boardconfig.getXBoard()*boardconfig.getYBoard()];
	public Entity [] getEntityArray() {
		return entityarray;
	}
	
	public void addentity (Entity entity) {
		for (int j = 0; j <= entityarray.length; j++) {
			if(entityarray[j] == null) {
				entityarray[j] = entity;
				LOGGER.finer ( "New Entity"+ entityarray[j] );
				return;
			}
			
		}System.out.println("Max Anzahl an Entitys erreicht");
		return;
	}

	public void removeentity (Entity entity, Entity[] entityarray) {
		for (int j = 0; j <= entityarray.length; j++) {
				if(entityarray[j] == entity) {
					LOGGER.finer( "Removed Entity"+ entityarray[j] );
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
                if(entity instanceof BotController) {
                    BotController character = (BotController) entity;
                    if(entity.getType() == EntityEnum.masterSquirell){
                        character.nextstep(new ControllerContextImpl((MasterSquirrelBot) entity,context));
                        continue;
                    }
                    if(entity.getType() == EntityEnum.masterSquirell){
                        character.nextstep(new ControllerContextImpl((MiniSquirrelBot) entity,context));
                        continue;
                    }
                }
				if(entity instanceof Character) {
				    Character character = (Character) entity;
				    character.nextstep(context);
				}
			}
		}
	}

		public Entity getEntityAt(XY xy){
		for(Entity entity : this.entityarray){
			if(entity.getPosition() == xy){
				return entity;
			}
		}
		return null;
	}
}
