package Command;


import java.io.PrintStream;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import de.hsa.games.fatsquirrel.core.board.Board;
import de.hsa.games.fatsquirrel.core.entities.Entity;
import de.hsa.games.fatsquirrel.core.playerBased.HandOperatedMasterSquirrel;

public class Commands {

    private final Board board;
    public int puffer;
    private PrintStream outputStream = System.out;

    public Commands(Board board, int puffer){
        this.board = board;
        this.puffer = puffer;
    }

    public int down(){
        return 2;
    }
    public int up(){
        return 8;
    }

    public int left(){
        return 4;
    }

    public int right(){
        return 6;
    }

    public int exit(){
        System.exit(42);
        return 0;
    }

    public int masterenergy(){
    	outputStream.println(getHandOperatedMasterSquirrel(board).getEnergy());
    	return 0;
    }
    private Entity getHandOperatedMasterSquirrel(Board board) {
		for (Entity entity : board.getEntityArray()) {
			if(entity != null) {
				if (entity.getClass().getSimpleName().equals("HandOperatedMasterSquirrel") ) {
					return entity;
				}
			}
		}
		return null;
	}
    public int spawn(String energy) {				// String
    	if(Integer.valueOf(energy)<getHandOperatedMasterSquirrel(board).getEnergy()) {
    		board.spawnMini(Integer.valueOf(energy), (HandOperatedMasterSquirrel) getHandOperatedMasterSquirrel(board));
    	}else {
			outputStream.println("not enough energy");
		}
    	return 0;
    }

    public int help(){

        List<String> lis = new ArrayList<>();

        Class<?> e1 = this.getClass();

        for(Method method : e1.getDeclaredMethods()){
            StringBuilder sb = new StringBuilder();

            for(Parameter parameter : method.getParameters()){

                sb.append(parameter.getType().getName()).append(", ");
            }
            lis.add(method.getName() + " Args: (" + sb.toString() + ")");
        }
        StringBuilder sb = new StringBuilder();
        for(String s : lis){
            sb.append(s).append('\n');
        }
        System.out.println(sb.toString());
        return 0;
    }


}
