package de.hsa.games.fatsquirrel.core;



public class HandOperatedMasterSquirrel extends MasterSquirrel{
private final static int senergy = 1000;
private int paralized=3;
	public HandOperatedMasterSquirrel(int id, XY xy) {
		super(id, xy, senergy);
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
