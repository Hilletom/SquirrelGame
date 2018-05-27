package de.hsa.games.fatsquirrel.core.playerBased;


import de.hsa.games.fatsquirrel.core.entities.EntityContext;
import de.hsa.games.fatsquirrel.core.entities.EntityEnum;
import de.hsa.games.fatsquirrel.core.utils.XY;
import java.util.logging.Logger;
import java.util.logging.Level;

public class MasterSquirrel extends PlayerEntity {
	private static final Logger LOGGER = Logger.getLogger(MasterSquirrel.class.getName());
	private int paralized=3;
	public MasterSquirrel(int id, XY xy) {
		super(id, xy, senergy);
//		energy += senergy;  
//		updateenergy(energy);
	}
	public MasterSquirrel(int id, XY xy, int energy) {
		super(id, xy, energy);
		this.type = EntityEnum.masterSquirell;
		LOGGER.log( Level.FINEST, "HandOperatedMasterSquirrel: "+getEnergy()+ "ID: "+ getId());
	}

	static final int senergy = 1000;
	public void nextstep(EntityContext context) {
		XY xyns = new XY(getPosition());
		if(Stun()) {
		context.tryMove(this, xyns.randomizer());
		}
	}
private boolean Stun() {
	if (paralized>=3) {
	return true;
	}else {
		LOGGER.log( Level.FINEST, "MasterSquirrel is stuned"+getId());
		paralized++;
		return false;
	}
	}
public void paralized() {
	paralized =0;
}
	public boolean mini (MiniSquirrel mini) {
		if(mini.getmasterid()==this.getId())
			return true;
		else
			return false;
	}
}
