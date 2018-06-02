package de.hsa.games.fatsquirrel.core.entities;

import de.hsa.games.fatsquirrel.core.playerBased.MasterSquirrel;
import de.hsa.games.fatsquirrel.core.utils.XY;
import javafx.scene.paint.Color;

public enum EntityEnum {
    badPlant, goodPlant, badBeast, goodBeast, masterSquirell, miniSquirell, wall;

    public static int getDanger(EntityEnum entityEnum){

        switch(entityEnum){
            case wall:
                return 1;
            case badBeast:
                return 2;
            case badPlant:
                return 2;
            case goodBeast:
                return 1;
            case goodPlant:
                return 1;
            case miniSquirell:
                return 0;
            case masterSquirell:
                return 0;
                default:
                    return 0;
        }


    }


    public static EntityEnum getEntityByString(String name){
        switch (name) {
            case "BadBeast": {
                return badBeast;
            }
            case "GoodBeast": {
                return goodBeast;
            }
            case "BadPlant": {
                return badPlant;
            }
            case "GoodPlant": {
                return goodPlant;
            }
            case "HandOperatedMasterSquirrel":{
                return masterSquirell;
            }
            case "MasterSquirrel": {
                return masterSquirell;
            }
            case "MiniSquirrel": {
                return miniSquirell;
            }
            case "Wall": {
                return wall;
            }
            default:
                return null;
        }
    }

    public static String getEntityType(Entity entity) {
        switch(entity.getClass().getName()) {
            case "BadBeast":
                return "BadBeast";
            case "GoodBeast":
                return "GoodBeast";
            case "BadPlant":
                return "BadPlant";
            case "GoodPlant":
                return "GoodPlant";
            case "Wall":
                return "Wall";
            case "MasterSquirrel":
                return "MasterSquirrel";
            case "MiniSquirrel":
                return "MiniSquirrel";
            case "HandOperatedMasterSquirrel":
                return "HandOperatedMasterSquirrel";
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return this.name();
    }
}
