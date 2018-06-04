package de.hsa.games.fatsquirrel.core.bots.tactics;

import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.core.utils.XY;

public class RandomTactic implements BotController{

    @Override
    public void nextstep(ControllerContext view) {
        XY xyns = new XY(view.locate());
        view.move(xyns.randomizer());
    }

}
