package de.hsa.games.fatsquirrel.botapi;

import de.hsa.games.fatsquirrel.core.utils.XY;

public interface BotControllerFactory {
	public BotController createMasterBotController(int id, XY pos, String tactic);
	public BotController createMiniBotController();

}
