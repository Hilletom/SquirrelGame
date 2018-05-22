package de.hsa.games.fatsquirrel.core;


public abstract class  Entity {
	private final int id;
	private int energy;
	private XY xy;
	
	protected Entity(int id, XY xy, int energy) {
		this.id = id;
		this.setXy(xy);
		this.energy = energy;
	}
	public Entity(Entity entity) {
		this.id = entity.id;
		this.setXy(entity.getXy());
	}
	
	public int getId() {
		return id;
	}
	public int getEnergy() {
		return energy;
	}
	public void updateenergy(int energyn) {
		if((energy += energyn)<=0){
			energy = 0;
		}else {
			energy += energyn;
		}
	}
	
	public String toString() {
		XY xys =  new XY(getXy());
		return "ID: " + id +" Type: "+ this.getClass().getName() +" x: "+ xys.getX() + " y: "+ xys.getY() + " Energy: " + energy;
	}
	public XY getPosition() {
		return getXy();
	}
	public XY getXy() {
		return xy;
	}
	public void setXy(XY xy) {
		this.xy = xy;
	}

	

}
