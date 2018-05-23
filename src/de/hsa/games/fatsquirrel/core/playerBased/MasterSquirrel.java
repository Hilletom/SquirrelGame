package de.hsa.games.fatsquirrel.core.playerBased;


import de.hsa.games.fatsquirrel.core.entities.EntityContext;
import de.hsa.games.fatsquirrel.core.entities.EntityEnum;
import de.hsa.games.fatsquirrel.core.utils.XY;

public class MasterSquirrel extends PlayerEntity {
	private int paralized=3;
	public MasterSquirrel(int id, XY xy) {
		super(id, xy, senergy);
//		energy += senergy;  
//		updateenergy(energy);
	}
	public MasterSquirrel(int id, XY xy, int energy) {
		super(id, xy, energy);
		this.type = EntityEnum.masterSquirell;
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
