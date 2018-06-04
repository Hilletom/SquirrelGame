package de.hsa.games.fatsquirrel.core.bots;

import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.core.entities.Entity;
import de.hsa.games.fatsquirrel.core.entities.EntityContext;
import de.hsa.games.fatsquirrel.core.entities.EntityEnum;
import de.hsa.games.fatsquirrel.core.entities.EntityType;
import de.hsa.games.fatsquirrel.core.playerBased.MasterSquirrel;
import de.hsa.games.fatsquirrel.core.playerBased.MiniSquirrel;
import de.hsa.games.fatsquirrel.core.utils.MoveCommand;
import de.hsa.games.fatsquirrel.core.utils.XY;

public class ControllerContextImpl implements ControllerContext {

    private MasterSquirrel EntityBot;
    private final EntityContext context;

    public ControllerContextImpl(MasterSquirrelBot EntityBot, EntityContext context){
        this.EntityBot = EntityBot;
        this.context = context;
    }

    public ControllerContextImpl(MiniSquirrelBot EntityBot, EntityContext context){
        this.EntityBot = EntityBot;
        this.context = context;
    }

    @Override
    public XY getViewLowerLeft() {

        int viewX;
        int viewY;

        if(this.EntityBot.getType() == EntityEnum.masterSquirell){
            viewX = 5; viewY = 5;
        }else{
            viewX = 4; viewY = 4;
        }
        XY reval = new XY(0,0);

        int indexX1 = 0;
        int indexY1 = 0;
        try {
            for(int i = 0; i < viewX; i++) {
                getEntityAt(new XY (EntityBot.getPosition().getX() - i, EntityBot.getPosition().getY()));
                indexX1++;
            }
        } catch (Exception ignore) {}

        reval.setX(indexX1);

        try {
            for(int i = 0; i < viewY; i++) {
                getEntityAt(new XY (EntityBot.getPosition().getX(), EntityBot.getPosition().getY() - i));
                indexY1++;
            }
        } catch (Exception ignore) {}

        reval.setY(indexY1);
        return reval;
    }

    @Override
    public XY getViewUpperRight() {

        int viewX;
        int viewY;
        if(this.EntityBot.getType() == EntityEnum.masterSquirell){
            viewX = 5; viewY = 5;
        }else{
            viewX = 4; viewY = 4;
        }
        XY reval = new XY(0,0);

        int indexX1 = 0;
        int indexY1 = 0;
        try {
            for(int i = 0; i < viewX; i++) {
                getEntityAt(new XY (EntityBot.getPosition().getX() + i, EntityBot.getPosition().getY()));
                indexX1++;
            }
        } catch (Exception ignore) {}
        reval.setX(indexX1);

        try {
            for(int i = 0; i < viewY; i++) {
                getEntityAt(new XY (EntityBot.getPosition().getX(), EntityBot.getPosition().getY() + i));
                indexY1++;
            }
        } catch (Exception ignore) {}
        reval.setY(indexY1);

        return reval;
    }

    @Override
    public XY locate() {
        return this.EntityBot.getPosition();
    }

    @Override
    public boolean isMine(XY xy) {

        if(xy == null){
            return false;
        }

        if(context.getEntityAt(xy).getType() == EntityEnum.miniSquirell){
            if(((MiniSquirrel) context.getEntityAt(xy)).isMine(this.EntityBot.getId())){
                return true;
            }
        }
        return false;

    }

    @Override
    public void implode(int impactRadius) throws Exception {
        if(this.EntityBot.getType() != EntityEnum.miniSquirell){
            throw new Exception();
        }
        // int energyloss = 200 * (this.EntityBot.getEnergy() / impactRadius) * (1 - distance / impactRadius);
        for(int x = 0; x < context.getSize().getX(); x++) {
            for (int y = 0; y < context.getSize().getY(); y++) {
                if(context.getEntityAt(new XY(x,y)) != null){
                    int distance;
                    if(Math.abs(x) - this.EntityBot.getPosition().getX() > Math.abs(y) - this.EntityBot.getPosition().getY()){
                        distance = Math.abs(x) - this.EntityBot.getPosition().getX();
                    }else{
                        distance = Math.abs(y) - this.EntityBot.getPosition().getY();
                    }
                    context.getEntityAt(new XY(x,y)).updateenergy(200 * (this.EntityBot.getEnergy() / impactRadius) * (1 - distance / impactRadius));
                    if(context.getEntityAt(new XY(x,y)).getEnergy() >= 0){
                        context.killandReplace(context.getEntityAt(new XY(x,y)));
                    }
                }
            }
        }
        ((MiniSquirrel)this.EntityBot).getMasterid();
    }

    @Override
    public XY directionOfMaster() {
        XY reval = new XY(0,0);
        for(int x = 0; x < context.getSize().getY(); x++){
            for(int y = 0; y < context.getSize().getY(); y++){
                if(context.getEntityAt(new XY(x,y)).getType() == EntityEnum.masterSquirell){
                    if(isMine(new XY(x,y))){
                        if(x > this.EntityBot.getPosition().getX()){
                            reval.setX(1);
                        }
                        if(y > this.EntityBot.getPosition().getY()){
                            reval.setY(1);
                        }
                        if(x < this.EntityBot.getPosition().getX()){
                            reval.setX(-1);
                        }
                        if(y < this.EntityBot.getPosition().getY()){
                            reval.setY(-1);
                        }
                    }
                }
            }
        }
        return reval;
    }

    @Override
    public long getRemainingSteps() {
        return 1;
    }

    @Override
    public EntityEnum getEntityAt(XY xy) {
        // TODO Auto-generated method stub
        return EntityEnum.getEntityByString(context.getEntityType(xy));
    }

    @Override
    public void move(XY direction) {
        // TODO Auto-generated method stub
        context.tryMove(EntityBot, direction);
        //EntityBot.setMoveCommand(new MoveCommand(MoveCommand.getMoveInput(direction)));
    }

    @Override
    public void spawnMiniBot(XY direction, int energy) {
        // TODO Auto-generated method stub
        try {
            EntityBot.spawnMini(energy, context);
        }catch (Exception ignore){

        }
    }

    @Override
    public int getEnergy() {
        // TODO Auto-generated method stub
        return this.getEnergy();
    }

}
