package de.hsa.games.fatsquirrel.core;

public class MiniSquirrel extends MasterSquirrel {
    private int masterid;
    private int paralized = 2;
    XY xyns = new XY(getPosition());

    public MiniSquirrel(int id, XY xy, int energy, int masterid) {
        super(id, xy, energy);
        this.masterid = masterid;
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


    private boolean Stun() {
        if (paralized >= 3) {
            return false;
        } else {
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


}
