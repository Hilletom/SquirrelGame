package de.hsa.games.fatsquirrel.core.bots;

import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.core.entities.EntityContext;
import de.hsa.games.fatsquirrel.core.entities.EntityEnum;
import de.hsa.games.fatsquirrel.core.playerBased.MiniSquirrel;
import de.hsa.games.fatsquirrel.core.utils.XY;

public class MiniSquirrelBot extends MiniSquirrel {
	public MiniSquirrelBot(int id, XY xy, int energy, int masterid) {
		super(id, xy, energy, masterid);
	}

	public void nextstep(EntityContext context) {
		super.nextstep(context);
	}

	public class ControllerContextImpl implements ControllerContext{

		@Override
		public XY getViewLowerLeft() {
			int x = getPosition().getX() - 10;
			int y = getPosition().getY() - 10;
			return new XY (x,y);
		}

		@Override
		public XY getViewUpperRight() {
			int x = getPosition().getX() + 10;
			int y = getPosition().getY() + 10;
			return new XY (x,y);
		}

		@Override
		public XY locate() {
			return null;
		}

		@Override
		public boolean isMine(XY xy) {
			return false;
		}

		@Override
		public void implode(int impactRadius) {

		}

		@Override
		public XY directionOfMaster() {
			return null;
		}

		@Override
		public long getRemainingSteps() {
			return 0;
		}

		@Override
		public EntityEnum getEntityAt(XY xy) {
			
			return null;
		}

		@Override
		public void move(XY direction) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void spawnMiniBot(XY direction, int energy) {			
		}

		@Override
		public int getEnergy() {
			return 0;
		}
		
	}
}
