package de.hsa.games.fatsquirrel;
import de.hsa.games.fatsquirrel.console.ConsoleUI;
import de.hsa.games.fatsquirrel.console.FxUI;
import de.hsa.games.fatsquirrel.console.GameImpl;
import de.hsa.games.fatsquirrel.console.UI;
import de.hsa.games.fatsquirrel.core.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Launcher extends Application{
	static boolean fx = true;
	public static void main(String[]args) {
		
		if(fx) {
			Application.launch(args);
		}else {		
	    UI ui = new ConsoleUI();
		Board board = new Board(new BoardConfig());
		State state = new State(board);
		startGame(state, board, ui);
		}
	}
	public static void startGame(State state, Board board, UI ui) {
		
		GameImpl gt = new GameImpl(state, ui);
		MoveGame mt = new MoveGame(ui, board);
		
		gt.start();
		mt.start();
		
	}
	 @Override
	    public void start(Stage primaryStage) {
		
		Board board = new Board(new BoardConfig());
		State state = new State(board);
//		 ...
		 
	        FxUI fxUI = FxUI.createInstance(state.getflattenedboard().getSize(),board);
//	        final Game game = ...
	         
	        primaryStage.setScene(fxUI);
	        primaryStage.setTitle("Diligent Squirrel");
	        fxUI.getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
	            @Override
	            public void handle(WindowEvent evt) {
	                System.exit(-1);     
	            }
	        });
	        primaryStage.show();   
	        
	        startGame(state, board, fxUI);    
	    }

}
