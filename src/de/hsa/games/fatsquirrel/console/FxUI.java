package de.hsa.games.fatsquirrel.console;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.logging.Logger;
import java.util.logging.Level;

import de.hsa.games.fatsquirrel.core.board.Board;
import de.hsa.games.fatsquirrel.core.board.BoardView;
import de.hsa.games.fatsquirrel.core.entities.Entity;
import de.hsa.games.fatsquirrel.core.entities.EntityEnum;
import de.hsa.games.fatsquirrel.core.playerBased.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.core.utils.MoveCommand;
import de.hsa.games.fatsquirrel.core.utils.XY;
import de.hsa.games.fatsquirrel.util.ui.console.CommandScanner;
import javafx.application.Platform;
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
    private static final Logger LOGGER = Logger.getLogger(FxUI.class.getName());
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

    public static FxUI createInstance(XY boardSize, Board board) {
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
                switch (keyEvent.getCode()) {
                    case ESCAPE: {
                        System.exit(0);
                        break;
                    }
                    case UP: {
                        puffer = 8;
                        break;
                    }
                    case DOWN: {
                        puffer = 2;
                        break;
                    }
                    case RIGHT: {
                        puffer = 6;
                        break;
                    }
                    case LEFT: {
                        puffer = 4;
                        break;
                    }
                    case H: {
                        help();
                        break;
                    }
                    case E: {
                        System.out.println(getHandOperatedMasterSquirrel(board));
                        break;
                    }
                    case S: {
                        if ((int) 100 < getHandOperatedMasterSquirrel(board).getEnergy()) {
                            board.spawnMini((int) 100, (HandOperatedMasterSquirrel) getHandOperatedMasterSquirrel(board));
                        } else {
                            System.out.println("not enough energy");
                        }
                        break;
                    }


                    default:
                        LOGGER.log(Level.FINE,"Wrong Command");
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
        for (int x2 = 0; x2 < viewSize.getX(); x2++) {
            for (int y2 = 0; y2 < viewSize.getY(); y2++) {
                EntityEnum type = view.getEntityTyp(x2, y2);

                if(type == null){
                    gc.fill();
                    continue;
                }

                if (x2 == 0) {
                    x = 0;
                } else {
                    x = x2 * CELL_SIZE;
                }
                if (y2 == 0) {
                    y = 0;
                } else {
                    y = y2 * CELL_SIZE;
                }
                switch (type) {
                    case badBeast: {
                        gc.setFill(Color.RED);
                        gc.fillOval(x, y, CELL_SIZE, CELL_SIZE);
                        break;
                    }
                    case goodBeast: {
                        gc.setFill(Color.GREEN);
                        gc.fillOval(x, y, CELL_SIZE, CELL_SIZE);
                        break;
                    }
                    case badPlant: {
                        gc.setFill(Color.RED);
                        gc.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                        break;
                    }
                    case goodPlant: {
                        gc.setFill(Color.GREEN);
                        gc.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                        break;
                    }
                    case masterSquirell: {
                        gc.setFill(Color.BLUE);
                        gc.fillOval(x, y, CELL_SIZE, CELL_SIZE);
                        break;
                    }
                    case miniSquirell: {
                        gc.setFill(Color.BLUE);
                        gc.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                        break;
                    }
                    case wall: {
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
    public MoveCommand getCommand() {
        return new MoveCommand(puffer);
    }

    @Override

    public void execute(Board board) {

    }

    private static void help() {
        StringBuffer stringbuffer = new StringBuffer();
        for (int i = 0; i < GameCommandType.values().length; i++) {
            stringbuffer.append(GameCommandType.values()[i].getName() + ": " + GameCommandType.values()[i].getHelpText() + "\n");
        }
        System.out.println(stringbuffer.toString());
    }

    public static Entity getHandOperatedMasterSquirrel(Board board) {
        for (Entity entity : board.getEntityArray()) {
            if (entity != null) {
                if (entity.getClass().getSimpleName().equals("HandOperatedMasterSquirrel")) {
                    return entity;
                }
            }
        }
        return null;
    }

}