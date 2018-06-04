package de.hsa.games.fatsquirrel.core.bots.tactics;

import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.core.entities.EntityEnum;
import de.hsa.games.fatsquirrel.core.utils.XY;

import java.util.ArrayList;
import java.util.List;

public class Tactic1 implements BotController{
    @Override
    public void nextstep(ControllerContext view) {
        /**
         * Random
         XY xyns = new XY(getPosition());
         if(this.Stun()) {
         view.move(xyns.randomizer());
         }


        int leftMax = view.getViewLowerLeft().getX();
        int rightMax = view.getViewUpperRight().getX();

        int upMax = view.getViewUpperRight().getY();
        int downMax = view.getViewUpperRight().getY();

        XY pos = new XY(0,0);

        pos.setX(8 - leftMax);



        EntityEnum[][] map = new EntityEnum[indexX][indexY];

        List<XY> dangerZone = new ArrayList<>();
        List<XY> wealthZone = new ArrayList<>();

        XY param = view.getViewUpperRight();
        param.setX(param.getX() - 2);

        for(int y = 0; y < map[0].length; y++){
            for(int x = 0; x < map.length; x++){
                map[x][y] = view.getEntityAt(new XY(x + this.getPosition().getX(),y + this.getPosition().getY()));
            }
        }

        for(int y = 0; y < indexY; y++){
            for(int x = 0; x < indexX; x++){
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
                down += EntityEnum.getDanger(view.getEntityAt(vector2));
            }
            if(vector2.getY() == 0){
                up += EntityEnum.getDanger(view.getEntityAt(vector2));
            }
            if(vector2.getX() == 0){
                left += EntityEnum.getDanger(view.getEntityAt(vector2));
            }
            if(vector2.getX() == 2){
                right += EntityEnum.getDanger(view.getEntityAt(vector2));
            }
        }

        for(XY vector2 : wealthZone){
            if(vector2.getY() == 2){
                down -= EntityEnum.getDanger(view.getEntityAt(vector2));
            }
            if(vector2.getY() == 0){
                up -= EntityEnum.getDanger(view.getEntityAt(vector2));
            }
            if(vector2.getX() == 0){
                left -= EntityEnum.getDanger(view.getEntityAt(vector2));
            }
            if(vector2.getX() == 2){
                right -= EntityEnum.getDanger(view.getEntityAt(vector2));
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

        view.move(moveMent);
         */
    }

}
