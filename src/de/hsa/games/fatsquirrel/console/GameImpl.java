package de.hsa.games.fatsquirrel.console;

import de.hsa.games.fatsquirrel.Game;
import de.hsa.games.fatsquirrel.core.entities.Entity;
import de.hsa.games.fatsquirrel.core.playerBased.HandOperatedMasterSquirrel;


public class GameImpl extends Game{
protected UI ui;
	public GameImpl(de.hsa.games.fatsquirrel.core.utils.State state, UI ui) {
		super(state, ui);
		this.ui = ui;
	}

	@Override
	protected void render() {
		ui.render(state.getflattenedboard());
	}
	public UI getUI() {
		return ui;
	}

	@Override
	protected void processInput() {
		for (Entity entity : state.getEntityArray()) {
			if(entity != null) {
				if (entity.getClass().getSimpleName().equals("HandOperatedMasterSquirrel") ) {
				((HandOperatedMasterSquirrel)entity).setMoveCommand(ui.getCommand());
				}
			}
		}	
	}
}
