package de.hsa.games.fatsquirrel.core.playerBased;

import de.hsa.games.fatsquirrel.core.entities.EntityContext;
import de.hsa.games.fatsquirrel.core.entities.EntityEnum;
import de.hsa.games.fatsquirrel.core.utils.XY;
import java.util.logging.Logger;
import java.util.logging.Level;

public class MiniSquirrel extends MasterSquirrel {
    private static final Logger LOGGER = Logger.getLogger(MiniSquirrel.class.getName());
    private int masterid;
    private int paralized = 2;
    XY xyns = new XY(getPosition());

    public MiniSquirrel(int id, XY xy, int energy, int masterid) {
        super(id, xy, energy);
        this.masterid = masterid;
        this.type = EntityEnum.miniSquirell;
        LOGGER.finest( "HandOperatedMasterSquirrel is stuned"+getEnergy());
    }

    public void nextstep(EntityContext context) {

        if (!Stun()) {

//            context.tryMove(this, MiniMove(context, xyns.randomizer()));          //miniSquirrel immer noch geistig behindert (suicid) verbindung mit bot?
            context.tryMove(this, xyns.randomizer());
            updateenergy(-1);
        }
        if (getEnergy() <= 0) {
            context.kill(this);
        }
        if (true) {

            System.out.println("m: " + getEnergy());
        }
    }

    public int getmasterid() {
        return masterid;
    }


    protected boolean Stun() {
        if (paralized >= 3) {
            return false;
        } else {
            LOGGER.finest( "MiniSquirrel is stuned"+getId());
            System.out.println("mini is stuned");
            paralized++;
            return true;
        }
    }

    public void paralized() {
        paralized = 0;
    }

    private XY MiniMove(EntityContext context, XY npos) {
        XY cnpos = npos;
        switch (context.getEntityType(npos)) {
            case "BadBeast":
            case "Wall":
            case "BadPlant":
            case "MiniSquirrel":
                cnpos = MiniMove(context, xyns.randomizer());
                break;
            case "GoodPlant":
            case "GoodBeast":
            case "null":
                break;
            case "MasterSquirrel":
            case "HandOperatedMasterSquirrel":
                if (mini(this)) {
                    break;
                } else {
                    cnpos = MiniMove(context, xyns.randomizer());
                    break;
                }
        }
        return cnpos;
    }

    public boolean isMine(int masterID){
        if(masterID == this.masterid){
            return true;
        }else{
            return false;
        }
    }

    public int getMasterid() {
        return masterid;
    }

    public void setMasterid(int masterid) {
        this.masterid = masterid;
    }

    public int getParalized() {
        return paralized;
    }

    public void setParalized(int paralized) {
        this.paralized = paralized;
    }

}
