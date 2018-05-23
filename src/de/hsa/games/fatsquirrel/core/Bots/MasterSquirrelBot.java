package de.hsa.games.fatsquirrel.core.Bots;

import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.core.*;

public class MasterSquirrelBot extends MasterSquirrel {

    private final ControllerContextImpl controllerContext;

	public MasterSquirrelBot(int id, XY xy, EntitySet entitySet) {
		super(id, xy);
        this.controllerContext = new ControllerContextImpl(entitySet);
    }

	public void nextstep() {
        controllerContext.move(new XY(0,1));
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
		public String getEntityAt(XY xy) {
			// TODO Auto-generated method stub

            for(Entity entity : entitySet.getEntityArray()){
                if(entity.getXy() == xy){
                    return entity.
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