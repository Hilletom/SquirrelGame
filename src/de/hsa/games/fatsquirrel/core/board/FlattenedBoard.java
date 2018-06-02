package de.hsa.games.fatsquirrel.core.board;

import de.hsa.games.fatsquirrel.core.entities.*;
import de.hsa.games.fatsquirrel.core.playerBased.MasterSquirrel;
import de.hsa.games.fatsquirrel.core.playerBased.MiniSquirrel;
import de.hsa.games.fatsquirrel.core.playerBased.PlayerEntity;
import de.hsa.games.fatsquirrel.core.utils.XY;
import java.util.logging.Logger;
import java.util.logging.Level;
public class FlattenedBoard implements BoardView, EntityContext {
	private static final Logger LOGGER = Logger.getLogger(FlattenedBoard.class.getName());
	private Board board;
	private Entity[][] map;
	
	public FlattenedBoard(Board board) {
		this.board=board;
		map = this.board.flatten();
	}
	
	public void update() {
		map = board.flatten();
	}

	@Override
	public XY getSize() {
		return new XY (map.length,map[0].length);
	}
	
	public void move(Entity entity, XY poso, XY posn) {
		entity.setXy(posn);
		map[poso.getX()][poso.getY()] = null;
		map[posn.getX()][posn.getY()] = entity;
	}


	@Override
	public void tryMove(MiniSquirrel minisquirrel, XY direction) {
		Entity target = map[direction.getX()][direction.getY()];
		LOGGER.log(Level.FINEST,"MiniSquirrel" + minisquirrel.getId() + " tries to move from "
				+ minisquirrel.getPosition().toString()
				+ " in a direction of " + direction.toString());
		switch(getEntityType(direction)) {
		case "BadBeast":
			minisquirrel.updateenergy(target.getEnergy());
			if(((BadBeast)target).Bite()) {
				killandReplace(target);
				move(minisquirrel, minisquirrel.getPosition(), direction);
			}
			break;
		case "GoodBeast":
		case "BadPlant":
		case "GoodPlant":
			minisquirrel.updateenergy(target.getEnergy());
			killandReplace(target);
			move(minisquirrel, minisquirrel.getPosition(), direction);
			break;
		case "Wall":
			minisquirrel.updateenergy(target.getEnergy());
			minisquirrel.paralized();
			break;
		case "MiniSquirrel":
			if(minisquirrel.getmasterid()!=((MiniSquirrel)target).getmasterid()) {
				kill(minisquirrel);
				kill(target);
			}break;
		case "MasterSquirrel":
		case "HandOperatedMasterSquirrel":
			if(((MasterSquirrel)target).mini(minisquirrel)) {
				target.updateenergy(minisquirrel.getEnergy());
				kill(minisquirrel);
			}else {
				target.updateenergy(150);
				kill(minisquirrel);
			}break;
		case "null":
			move(minisquirrel, minisquirrel.getPosition(), direction);
			break;
		}
	}


	@Override
	public void tryMove(GoodBeast goodbeast, XY direction) {
		Entity target = map[direction.getX()][direction.getY()];
		switch(getEntityType(direction)) {
		case "BadBeast":
		case "GoodBeast":
		case "BadPlant":
		case "GoodPlant":
		case "Wall":
			break;
		case "MiniSquirrel":
		case "MasterSquirrel":
		case "HandOperatedMasterSquirrel":
			target.updateenergy(goodbeast.getEnergy());
			killandReplace(goodbeast);
			break;
		case "null":{
			move(goodbeast, goodbeast.getPosition(), direction);
			break;
		}
		}
	}


	@Override
	public void tryMove(BadBeast badbeast, XY direction) {
		Entity target = map[direction.getX()][direction.getY()];
		switch(getEntityType(direction)) {
		case "BadBeast":
		case "GoodBeast":
		case "BadPlant":
		case "GoodPlant":
		case "Wall":
			break;
		case "MiniSquirrel":
		case "MasterSquirrel":
		case "HandOperatedMasterSquirrel":
			target.updateenergy(badbeast.getEnergy());
			if(badbeast.Bite()) {
			killandReplace(badbeast);
			}
			break;
		case "null":{
			move(badbeast, badbeast.getPosition(), direction);
			break;
		}
		}
	}


	@Override
	public void tryMove(MasterSquirrel master, XY direction) {
		Entity target = map[direction.getX()][direction.getY()];
		switch(getEntityType(direction)) {
		case "BadBeast":
			master.updateenergy(target.getEnergy());
			if(((BadBeast)target).Bite()) {
				killandReplace(target);
				move(master, master.getPosition(), direction);
			}
			break;
		case "GoodBeast":
		case "BadPlant":
		case "GoodPlant":
			master.updateenergy(target.getEnergy());
			killandReplace(target);
			move(master, master.getPosition(), direction);
			break;
		case "Wall":
			master.updateenergy(target.getEnergy());
			master.paralized();
			break;
		case "MiniSquirrel":
			if(master.mini((MiniSquirrel)target)) {
				master.updateenergy(target.getEnergy());
				kill(target);
				move(master, master.getPosition(), direction);
			}else {
				master.updateenergy(150);
				kill(target);
				move(master, master.getPosition(), direction);
			}break;
		case "MasterSquirrel":
		case "HandOperatedMasterSquirrel":
			break;
		case "null":{
			move(master, master.getPosition(), direction);
			break;
		}
		}
	}


	@Override
	public PlayerEntity nearestPlayer(XY playerpos) {
		PlayerEntity nearest = null;
		int dis= 7;
		for(int x = playerpos.getX() - 6; x<=playerpos.getX()+6; x++) {
			for(int y = playerpos.getY() - 6; y <= playerpos.getY()+6;y++) {
				if(x >= 0 && y >= 0 && x < map.length && y < map[0].length && map[x][y] != null) {
					if (map[x][y] instanceof PlayerEntity) {
						XY postarget = new XY (x,y);
					if(dis>XY.Distance(playerpos,postarget)) {
					nearest = (PlayerEntity) map[x][y];
					dis = XY.Distance(playerpos,postarget);
					}
					}
				}
			}
		}
		return nearest;
	}
	
	@Override
	public void kill(Entity entity) {
		EntitySet entityset = new EntitySet();
		map[entity.getPosition().getX()][entity.getPosition().getY()] = null;
		entityset.removeentity(entity, board.getEntityArray());
	}


	@Override
	public void killandReplace(Entity entity) {
		EntitySet entityset = new EntitySet();
		map[entity.getPosition().getX()][entity.getPosition().getY()] = null;
		entityset.removeentity(entity, board.getEntityArray());
		switch(entity.getClass().getSimpleName()) {
		case "BadBeast":{
			board.PlaceBadBeast();
			break;
		}
		case "GoodBeast":{
			board.PlaceGoodBeast();
			break;
		}
		case "BadPlant":{
			board.PlaceBadPlant();
			break;
		}
		case "GoodPlant":{
			board.PlaceGoodPlant();
			break;
		}
		}
	}


	@Override
	public String getEntityType(XY xy) {
		if(map[xy.getX()][xy.getY()] == null) {
			return "null";
		}
        String name = map[xy.getX()][xy.getY()].getClass().getSimpleName();
		switch(name) {
		case "BadBeast":
			return "BadBeast";
		case "GoodBeast":
			return "GoodBeast";
		case "BadPlant":
			return "BadPlant";
		case "GoodPlant":
			return "GoodPlant";
		case "Wall":
			return "Wall";
		case "MasterSquirrel":
			return "MasterSquirrel";
		case "MiniSquirrel":
			return "MiniSquirrel";
		case "HandOperatedMasterSquirrel":
			return "HandOperatedMasterSquirrel";
		default:
			return null;
		}
	}


	@Override
	public EntityEnum getEntityTyp(int x, int y) {
		if(map[x][y] == null) {
			return null;
		}
        String name = map[x][y].getClass().getSimpleName();
		return EntityEnum.getEntityByString(name);
	}
	
	public String toString() {
		String view = "";
		for (int y= 0; y<map[0].length;y++) {
			String line = "";
			for (int x= 0; x<map.length;x++) {
				switch(getEntityTyp(x,y)) {
				    case masterSquirell:
					line += "M";
					break;
                    case badBeast:
					line += "B";
					break;
                    case goodBeast:
					line += "G";
					break;
                    case badPlant:
					line += "b";
					break;
                    case goodPlant:
					line += "g";
					break;
				case wall:
					line += "X";
					break;
                    case miniSquirell:
					line += "m";
					break;
				default:
					line += ".";
					break;
				}
			}
			view += line + "\n";
		}
		return view;
	}
}
