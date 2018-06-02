package de.hsa.games.fatsquirrel.core.board;

import de.hsa.games.fatsquirrel.core.bots.MasterSquirrelBot;
import de.hsa.games.fatsquirrel.core.entities.*;
import de.hsa.games.fatsquirrel.core.playerBased.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.core.playerBased.MasterSquirrel;
import de.hsa.games.fatsquirrel.core.playerBased.MiniSquirrel;
import de.hsa.games.fatsquirrel.core.utils.XY;

public class Board {
	BoardConfig boardconfig = new BoardConfig();
	int counter=0;
	protected int x;
	protected int y;
	EntitySet entityset = new EntitySet();
	public Board (BoardConfig config) {
		
		for(int i = 0; i<= config.getXBoard(); i++) {
			Wall wall = new Wall(Counter(), new XY(i,0));
			entityset.addentity(wall);
		}
		for(int i = 0; i<= config.getYBoard(); i++) {
			Wall wall = new Wall(Counter(), new XY (0,i));
			entityset.addentity(wall);
		}
		for(int i = 1; i< config.getXBoard(); i++) {
			Wall wall = new Wall(Counter(), new XY (i,config.getYBoard()));
			entityset.addentity(wall);
		}
		for(int i = 1; i<= config.getYBoard(); i++) {
			Wall wall = new Wall(Counter(), new XY (config.getXBoard(),i));
			entityset.addentity(wall);
		}

/*Doppelte wand
		for(int i = 2; i<= config.getXBoard()-2; i++) {
			Wall wall = new Wall(Counter(), new XY (i,2));
			entityset.addentity(wall);
		}
		for(int i = 2; i<= config.getYBoard()-2; i++) {
			Wall wall = new Wall(Counter(), new XY (2,i));
			entityset.addentity(wall);
		}
		for(int i = 3; i< config.getXBoard()-2; i++) {
			Wall wall = new Wall(Counter(), new XY (i,config.getYBoard()-2));
			entityset.addentity(wall);
		}
		for(int i = 3; i<= config.getYBoard()-2; i++) {
			Wall wall = new Wall(Counter(), new XY (config.getXBoard()-2,i));
			entityset.addentity(wall);
		}
*/
        for(int i=0; i<config.getPlayer();i++) {
            PlaceMasterBot();
        }
        /**
         * Kein Handoperated
		for(int i=0; i<config.getPlayer();i++) {
			PlaceHandOperatedMasterSquirrel();
		}
         */

		for(int i=0; i<config.getBot();i++) {
			PlaceMasterSquirrel();
		}
		for(int i=0; i<config.getCountBB();i++) {
			PlaceBadBeast();
		}
		for(int i=0; i<config.getCountGB();i++) {
			PlaceGoodBeast();
		}
		for(int i=0; i<config.getCountBP();i++) {
			PlaceBadPlant();
		}
		for(int i=0; i<config.getCountGP();i++) {
			PlaceGoodPlant();
		}
		for(int i=0; i<config.getCountW();i++) {
			Wall wall = new Wall(Counter(), RandomPos());
			entityset.addentity(wall);
		}
		
	}
	public int getRandomX() {
		int x = (int)(Math.random() * boardconfig.getXBoard());
		return x;
	}
	public int getRandomY() {
		int y = (int)(Math.random() * boardconfig.getYBoard());
		return y;
	}
	public XY RandomPos () {
		x = getRandomX();
		y = getRandomY();	
			for(Entity entity : entityset.getEntityArray()) {
				if (entity != null) {
					if(entity.getPosition().getX() == x && entity.getPosition().getY() ==y) {
					RandomPos();
					}						
				}
			}return new XY (x,y);
		}

	
	public int Counter() {
		counter++;
		return counter;
	}
	
	public void PlaceBadBeast() {
		BadBeast badbeast = new BadBeast(Counter(), RandomPos());
		entityset.addentity(badbeast);
	}
	public void PlaceMasterBot() {
		MasterSquirrelBot masterSquirrelBot = new MasterSquirrelBot(counter, RandomPos(),this.entityset);
		entityset.addentity(masterSquirrelBot);
	}
	
	public void PlaceGoodBeast() {
		GoodBeast goodbeast = new GoodBeast(Counter() , RandomPos());
		entityset.addentity(goodbeast);
	}
	
	public void PlaceBadPlant() {
		BadPlant badplant = new BadPlant(Counter(), RandomPos());
		entityset.addentity(badplant);
	}
	
	public void PlaceGoodPlant() {
		GoodPlant goodplant = new GoodPlant(Counter(), RandomPos());
		entityset.addentity(goodplant);
	}
	public void PlaceHandOperatedMasterSquirrel() {
		HandOperatedMasterSquirrel handoperatedmastersquirrel = new HandOperatedMasterSquirrel(Counter(), RandomPos());
		entityset.addentity(handoperatedmastersquirrel);
	}
	public void PlaceMasterSquirrel() {
		MasterSquirrel mastersquirrel = new MasterSquirrel(Counter(), RandomPos());
		entityset.addentity(mastersquirrel);
	}
	public Entity[][] flatten(){
		Entity map[][] = new Entity [boardconfig.getXBoard()+1][boardconfig.getYBoard()+1];
		for (Entity entity : entityset.getEntityArray())
			if(entity != null) {
			map[entity.getPosition().getX()][entity.getPosition().getY()]= entity;}
		return map;
	}
	public void updateCharacter (EntityContext context) {
		entityset.entitynextstep(context);
	}
	public Entity[] getEntityArray() {
		return entityset.getEntityArray();
	}
	public void spawnMini(int sharedenergy, HandOperatedMasterSquirrel master) {
		MiniSquirrel minisquirrel = new MiniSquirrel(Counter(), MiniPos(master.getPosition()), sharedenergy, master.getId());
		entityset.addentity(minisquirrel);
		master.updateenergy(-sharedenergy);
	}
	private XY ranMiniPos(XY masterpos) {
		int x;
		int y;
		switch ((int)(Math.random() * 8)+1) {
		case 1:
			x = masterpos.getX();
			y = masterpos.getY() + 1;
			return new XY (x,y);
		case 2:
			x = masterpos.getX() + 1;
			y = masterpos.getY() + 1;
			return new XY (x,y);
		case 3:
			x = masterpos.getX() + 1;
			y = masterpos.getY();
			return new XY (x,y);
		case 4:
			x = masterpos.getX() + 1;
			y = masterpos.getY() - 1;
			return new XY (x,y);
		case 5:
			x = masterpos.getX();
			y = masterpos.getY() - 1;
			return new XY (x,y);
		case 6:
			x = masterpos.getX() - 1;
			y = masterpos.getY() - 1;
			return new XY (x,y);
		case 7:
			x = masterpos.getX() - 1;
			y = masterpos.getY();
			return new XY (x,y);
		case 8:
			x = masterpos.getX() - 1;
			y = masterpos.getY() + 1;
			return new XY (x,y);
		default:
			x = masterpos.getX();
			y = masterpos.getY();
			return new XY (x,y);
		}
	}
		private XY MiniPos(XY masterpos) {
		int x = ranMiniPos(masterpos).getX();
		int y = ranMiniPos(masterpos).getY();
		for(Entity entity : entityset.getEntityArray()) {
			if (entity != null) {
				if(entity.getPosition().getX() == x && entity.getPosition().getY() == y ) {
				return MiniPos(masterpos);
				}						
			}
		}return new XY (x,y);
	}

}
