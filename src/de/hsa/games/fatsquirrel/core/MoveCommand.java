package de.hsa.games.fatsquirrel.core;

public class MoveCommand {
	public final XY xy;
	int x;
	int y;
	public MoveCommand (int input){
		xy = MoveVector(input);
	}
	public XY MoveVector(int input) {
		
		switch (input) {
			case 2:
				y = 1;
				return new XY(x, y);
			case 3:
				x = 1;
				y = 1;
				return new XY(x, y);
			case 6:
				x = 1;
				return new XY(x, y);
			case 9:
				x = 1;
				y =  - 1;
				return new XY(x, y);
			case 8:
				y = - 1;
				return new XY(x, y);
			case 7:
				x = - 1;
				y = - 1;
				return new XY(x, y);
			case 4:
				x = - 1;
				return new XY(x, y);
			case 1:
				x = - 1;
				y = 1;
				return new XY(x, y);
			case 5:
				return new XY(x, y);
			} return new XY(x, y);
		}
		public static int getMoveInput(XY direction){
			if(direction.getX() == 0 && direction.getY() == 0){
				return 5;
			}else if(direction.getX() == 0 && direction.getY() == 1){
				return 2;
			}else if(direction.getX() == 1 && direction.getY() == 1){
				return 3;
			}else if(direction.getX() == 1 && direction.getY() == 0){
				return 6;
			}else if(direction.getX() == 1 && direction.getY() == -1){
				return 9;
			}else if(direction.getX() == 0 && direction.getY() == -1){
				return 8;
			}else if(direction.getX() == -1 && direction.getY() == -1){
				return 7;
			}else if(direction.getX() == -1 && direction.getY() == 0){
			    return 4;
			}else if(direction.getX() == -1 && direction.getY() == 1){
                return 1;
			}
			return 5;
		}
	}
