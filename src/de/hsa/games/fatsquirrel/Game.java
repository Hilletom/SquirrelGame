package de.hsa.games.fatsquirrel;


import de.hsa.games.fatsquirrel.console.UI;

public abstract class Game extends Thread{
	final int FPS = 10;
	long timestamp;
    long oldTimestamp;
	public void run() {
	    while (true) {
	    	oldTimestamp = System.currentTimeMillis();
	        render();
	        processInput();
	        update();
	        timestamp = System.currentTimeMillis();
	        if(timestamp-oldTimestamp <= FPS*10) {
	          try {
	            Thread.sleep(FPS*10 - (timestamp-oldTimestamp) );
	          } catch (InterruptedException e) {
	            e.printStackTrace();
	            }
	          }
	        }
	    }
	
	protected de.hsa.games.fatsquirrel.core.utils.State state;
	public Game (de.hsa.games.fatsquirrel.core.utils.State state, UI ui) {
		this.state = state;
	}
	protected abstract void render();
	protected abstract void processInput();
	protected void update() {
		state.update();
	}
}
