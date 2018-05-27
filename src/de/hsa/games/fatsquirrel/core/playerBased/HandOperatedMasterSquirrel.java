package de.hsa.games.fatsquirrel.core.playerBased;


import de.hsa.games.fatsquirrel.core.entities.EntityContext;
import de.hsa.games.fatsquirrel.core.entities.EntityEnum;
import de.hsa.games.fatsquirrel.core.utils.XY;
import java.util.logging.Logger;
import java.util.logging.Level;

public class HandOperatedMasterSquirrel extends MasterSquirrel {
	private static final Logger LOGGER = Logger.getLogger(HandOperatedMasterSquirrel.class.getName());
private final static int senergy = 1000;
private int paralized=3;
	public HandOperatedMasterSquirrel(int id, XY xy) {
		super(id, xy, senergy);
		this.type = EntityEnum.masterSquirell;
	}
	public void nextstep(EntityContext context) {
		if(!Stun()) {
		context.tryMove(this, newPos());
		}
	}
private boolean Stun() {
	if (paralized>=3) {
	return false;
	}else {
		LOGGER.log( Level.FINEST, "HandOperatedMasterSquirrel is stuned"+getId());
		System.out.println("you are stuned");
		paralized++;
		return true;
	}
	}
public void paralized() {
	paralized =0;
}
public XY newPos () {
	XY xyn = nMoveCommand.xy;
	int x = xyn.getX() + getPosition().getX();
	int y = xyn.getY() + getPosition().getY();
	return new XY (x,y);
}
	

}
