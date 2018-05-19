package de.hsa.games.fatsquirrel.core;


public class BadBeast extends Character {
	private int counter=0;
	private int paralized=3;
	public BadBeast(int id, XY xy) {
		super(id, xy, energy);
	}

	final static int energy = -150;
	public void nextstep(EntityContext context) {
		XY xyns = new XY(getPosition());
		if (!Stun()) {
			if(Agro(context)) {
				context.tryMove(this, Chase(context));
			}else {
		context.tryMove(this, xyns.randomizer());
			}
		}	
	}
	public boolean Bite() {
		if (counter<6){
		counter++;
		return false;
		}else {
			return true;
		}
	}
	public boolean Stun() {
		if (paralized>=3) {
			paralized=0;
		return false;
		}else {
			paralized++;
			return true;
		}
		}
	private boolean Agro(EntityContext context) {
		PlayerEntity nplayerentity = context.nearestPlayer(this.getPosition());
		if(nplayerentity != null) {
			return true;
		}else {
			return false;
		}
	}
	private XY Chase(EntityContext context) {
		int x = this.getPosition().getX();
		int y = this.getPosition().getY();
		XY prey = context.nearestPlayer(this.getPosition()).getPosition();
		if(prey.getX()>this.getPosition().getX()) {
			x=x+1;
		}
		if(prey.getX()<this.getPosition().getX()) {
			x=x-1;			
		}
		if(prey.getY()>this.getPosition().getY()) {
			y=y+1;			
		}
		if(prey.getY()<this.getPosition().getY()) {
			y=y-1;
		}
		return new XY (x,y);
	}

}
