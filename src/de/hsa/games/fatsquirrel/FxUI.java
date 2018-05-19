package de.hsa.games.fatsquirrel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

import de.hsa.games.fatsquirrel.console.GameCommandType;
import de.hsa.games.fatsquirrel.core.Board;
import de.hsa.games.fatsquirrel.core.BoardView;
import de.hsa.games.fatsquirrel.core.Entity;
import de.hsa.games.fatsquirrel.core.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.core.MoveCommand;
import de.hsa.games.fatsquirrel.core.XY;
import de.hsa.games.fatsquirrel.util.ui.console.Command;
import de.hsa.games.fatsquirrel.util.ui.console.CommandScanner;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class FxUI extends Scene implements UI {
	private static final int CELL_SIZE = 30;
	Canvas boardCanvas;
	Label msgLabel;
	static int puffer;
	private PrintStream outputStream = System.out;
	private BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
	private CommandScanner commandscanner = new CommandScanner(inputReader);
	private int x;
	private int y;

	public FxUI(Parent parent, Canvas boardCanvas, Label msgLabel) {
		super(parent);
		this.boardCanvas = boardCanvas;
		this.msgLabel = msgLabel;
	}

	public static FxUI createInstance(XY boardSize) {
		Canvas boardCanvas = new Canvas(boardSize.getX() * CELL_SIZE, boardSize.getY() * CELL_SIZE);
		Label statusLabel = new Label();
		VBox top = new VBox();
		top.getChildren().add(boardCanvas);
		top.getChildren().add(statusLabel);
		statusLabel.setText("Hallo Welt");
		final FxUI fxUI = new FxUI(top, boardCanvas, statusLabel);
		fxUI.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				switch(keyEvent.getCode()) {
				case ESCAPE:
					System.exit(0);
					break;
				case UP:
					puffer = 8;
					break;
				case DOWN:
					puffer = 2;
					break;
				case RIGHT:
					puffer = 6;
					break;
				case LEFT:
					puffer = 4;
					break;
				default:
					break;
				}
			}

			
			
		});
		return fxUI;
	}

	@Override
	public void render(final BoardView view) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				repaintBoardCanvas(view);
			}
		});
	}

	private void repaintBoardCanvas(BoardView view) {
		GraphicsContext gc = boardCanvas.getGraphicsContext2D();
		gc.clearRect(0, 0, boardCanvas.getWidth(), boardCanvas.getHeight());
		XY viewSize = view.getSize();
		for(int x2 = 0 ; x2 < viewSize.getX() ; x2++) {
			for(int y2 = 0 ; y2 < viewSize.getY() ; y2++) {
				String type = view.getEntityTyp(x2, y2);
				if(x2 == 0) {
					x = 0;
				}else {
					x = x2 * CELL_SIZE;
				}
				if(y2 == 0) {
					y = 0;
				}else {
					y = y2 * CELL_SIZE;
				}
				switch(type) {
				case "BadBeast":{
					gc.setFill(Color.RED);
					gc.fillOval(x,y,CELL_SIZE,CELL_SIZE);
					break;
				}
				case "GoodBeast":{
					gc.setFill(Color.GREEN);
					gc.fillOval(x,y,CELL_SIZE,CELL_SIZE);
					break;
				}
				case "BadPlant":{
					gc.setFill(Color.RED);
					gc.fillRect(x, y, CELL_SIZE, CELL_SIZE);
					break;
				}
				case "GoodPlant":{
					gc.setFill(Color.GREEN);
					gc.fillRect(x,y,CELL_SIZE,CELL_SIZE);
					break;
				}
				case "HandOperatedMasterSquirrel":
				case "MasterSquirrel":{
					gc.setFill(Color.BLUE);
					gc.fillOval(x,y,CELL_SIZE,CELL_SIZE);
					break;
				}
				case "MiniSquirrel":{
					gc.setFill(Color.BLUE);
					gc.fillRect(x,y,CELL_SIZE,CELL_SIZE);
					break;
				}
				case "Wall":{
					gc.setFill(Color.ORANGE);
					gc.fillRect(x, y, CELL_SIZE, CELL_SIZE);
					break;
				}
				default:
					gc.fill();
					break;
			}
			}
		}
	}
	
	

//	@Override
//	public void message(final String msg) {
//		Platform.runLater(new Runnable() {
//			@Override
//			public void run() {
//				msgLabel.setText(msg);
//			}
//		});
//	}

	@Override
	public MoveCommand getCommand() {
		return new MoveCommand(puffer);
	}

	@Override
	public void execute(Board board) {
		
	
	}	
}