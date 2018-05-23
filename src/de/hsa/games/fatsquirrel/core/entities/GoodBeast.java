package de.hsa.games.fatsquirrel.core.entities;

import de.hsa.games.fatsquirrel.core.playerBased.PlayerEntity;
import de.hsa.games.fatsquirrel.core.utils.XY;

public class GoodBeast extends Character {
	private int paralized=3;
	public GoodBeast(int id, XY xy) {
		super(id, xy, ENERGY);
		this.type = EntityEnum.goodBeast;
	}

	final static int ENERGY = 200;
	
	
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
	private boolean Stun() {
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
		if(prey.getX()<this.getPosition().getX()) {
			x=x+1;
		}
		if(prey.getX()>this.getPosition().getX()) {
			x=x-1;			
		}
		if(prey.getY()<this.getPosition().getY()) {
			y=y+1;			
		}
		if(prey.getY()>this.getPosition().getY()) {
			y=y-1;
		}
		return new XY (x,y);
	}
}
