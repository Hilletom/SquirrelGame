package de.hsa.games.fatsquirrel.core.bots;

import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.core.board.EntitySet;
import de.hsa.games.fatsquirrel.core.entities.Entity;
import de.hsa.games.fatsquirrel.core.entities.EntityContext;
import de.hsa.games.fatsquirrel.core.entities.EntityEnum;
import de.hsa.games.fatsquirrel.core.entities.EntityType;
import de.hsa.games.fatsquirrel.core.playerBased.MasterSquirrel;
import de.hsa.games.fatsquirrel.core.utils.MoveCommand;
import de.hsa.games.fatsquirrel.core.utils.XY;

import java.util.ArrayList;
import java.util.List;

public class MasterSquirrelBot extends MasterSquirrel {

    private final ControllerContextImpl controllerContext;

	public MasterSquirrelBot(int id, XY xy, EntitySet entitySet) {
		super(id, xy);
        this.controllerContext = new ControllerContextImpl(entitySet);
    }

	public void nextstep(EntityContext context) {
		EntityEnum[][] map = new EntityEnum[10][10];

		List<XY> dangerZone = new ArrayList<>();
		List<XY> wealthZone = new ArrayList<>();

		XY param = controllerContext.getViewUpperRight();
		param.setX(param.getX() - 2);

		for(int y = 0; y <= 3; y++){
			for(int x = 0; x <= 3; x++){
				map[y][x] = controllerContext.getEntityAt(new XY(param.getX() + x,param.getY() + y));
			}
		}

		for(int y = 0; y <= 9; y++){
			for(int x = 0; x <= 9; x++){
			    if(map[x][y] == null){
			        continue;
                }
				switch(map[y][x]){
					case masterSquirell:
						break;
                    case miniSquirell:
						break;
                    case goodPlant:
						wealthZone.add(new XY(x,y));
                    case badPlant:
						dangerZone.add(new XY(x,y));
                    case goodBeast:
						wealthZone.add(new XY(x,y));
                    case wall:
						dangerZone.add(new XY(x,y));
                    case badBeast:
						dangerZone.add(new XY(x,y));
				}
			}
		}

		//CalcMove
		int right = 0;
		int left = 0;
		int up = 0;
		int down = 0;

		XY moveMent = new XY(0,0);

		for(XY vector2 : dangerZone){
			if(vector2.getY() == 2){
				down += EntityEnum.getDanger(controllerContext.getEntityAt(vector2));
			}
			if(vector2.getY() == 0){
				up += EntityEnum.getDanger(controllerContext.getEntityAt(vector2));
			}
			if(vector2.getX() == 0){
				left += EntityEnum.getDanger(controllerContext.getEntityAt(vector2));
			}
			if(vector2.getX() == 2){
				right += EntityEnum.getDanger(controllerContext.getEntityAt(vector2));
			}
		}

		for(XY vector2 : wealthZone){
			if(vector2.getY() == 2){
				down -= EntityEnum.getDanger(controllerContext.getEntityAt(vector2));
			}
			if(vector2.getY() == 0){
				up -= EntityEnum.getDanger(controllerContext.getEntityAt(vector2));
			}
			if(vector2.getX() == 0){
				left -= EntityEnum.getDanger(controllerContext.getEntityAt(vector2));
			}
			if(vector2.getX() == 2){
				right -= EntityEnum.getDanger(controllerContext.getEntityAt(vector2));
			}
		}

		if(up > down){
			moveMent.setY(-1);
		}else if(up < down){
			moveMent.setY(1);
		}else if(up == down){
			moveMent.setY(0);
		}

		if(right > left){
			moveMent.setX(1);
		}else if(right < left){
			moveMent.setX(-1);
		}else if(right == left){
			moveMent.setX(0);
		}

		controllerContext.move(moveMent);



	}

	public class ControllerContextImpl implements ControllerContext{

	    private final EntitySet entitySet;

	    public ControllerContextImpl(EntitySet entitySet){
            this.entitySet = entitySet;
        }

		@Override
		public XY getViewLowerLeft() {
			int x = getPosition().getX() - 15;
			int y = getPosition().getY() - 15;
			return new XY (x,y);
		}

		@Override
		public XY getViewUpperRight() {
			int x = getPosition().getX() + 15;
			int y = getPosition().getY() + 15;
			return new XY (x,y);
		}

		@Override
		public EntityEnum getEntityAt(XY xy) {
			// TODO Auto-generated method stub

            for(Entity entity : entitySet.getEntityArray()){
                if(entity != null) {
                    if (entity.getXy() == xy) {
                        return entity.getType();
                    }
                }
            }

			return null;
		}

		@Override
		public void move(XY direction) {
			// TODO Auto-generated method stub
            setMoveCommand(new MoveCommand(MoveCommand.getMoveInput(direction)));
		}

		@Override
		public void spawnMiniBot(XY direction, int energy) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int getEnergy() {
			// TODO Auto-generated method stub
			return this.getEnergy();
		}
		
	}
}
