package de.hsa.games.fatsquirrel.core.bots;

import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.BotControllerFactory;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.core.board.EntitySet;
import de.hsa.games.fatsquirrel.core.bots.tactics.RandomTactic;
import de.hsa.games.fatsquirrel.core.bots.tactics.Tactic1;
import de.hsa.games.fatsquirrel.core.entities.EntityContext;
import de.hsa.games.fatsquirrel.core.entities.EntityEnum;
import de.hsa.games.fatsquirrel.core.playerBased.MasterSquirrel;
import de.hsa.games.fatsquirrel.core.utils.XY;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static de.hsa.games.fatsquirrel.core.entities.EntityEnum.*;
import static de.hsa.games.fatsquirrel.core.entities.EntityEnum.miniSquirell;

public class MasterSquirrelBot extends MasterSquirrel implements BotControllerFactory, BotController {

    private BotController botController;
    Method tactic;

	public MasterSquirrelBot(int id, XY xy) {
		super(id, xy,1000);
        this.type = masterSquirell;
        this.botController = new RandomTactic();
    }

    /**
	 * Noch Nicht Fertig
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
	*/

    public void nextstep(EntityContext context){
        super.nextstep(context);

        Random rn = new Random();
        if(rn.nextInt(100) == 75){
            this.spawnMini(10, context);
        }
    }

    @Override
    public BotController createMasterBotController(int id, XY pos, String tactic) {
        return new MasterSquirrelBot(id, pos) {
            @Override
            public void nextstep(ControllerContext view) {
                    try {
                        if (this.tactic != null) {
                        Class cl = Class.forName("RandomTactic");
                        Method method = cl.getMethod("nextstep",ControllerContext.class);
                        method.invoke(view);
                        }else{
                            this.tactic.invoke(view);
                        }
                    } catch (Exception e) {
                        try {
                            this.tactic = RandomTactic.class.getMethod("nextstep");
                        } catch (NoSuchMethodException e1) {
                            e1.printStackTrace();
                        }
                    }
            }
        };
    }

    @Override
    public BotController createMiniBotController() {
        return null;
    }

    @Override
    public void nextstep(ControllerContext view) {
        botController.nextstep(view);
    }
}
