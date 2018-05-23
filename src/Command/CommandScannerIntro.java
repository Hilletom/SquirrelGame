//package Command;
//
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.lang.reflect.Method;
//
//import de.hsa.games.fatsquirrel.console.UI;
//import de.hsa.games.fatsquirrel.core.board.Board;
//import de.hsa.games.fatsquirrel.core.board.BoardView;
//import de.hsa.games.fatsquirrel.core.utils.MoveCommand;
//
//public class CommandScannerIntro implements UI {
//
//    private final Commands commands;
//    int puffer = 5;
//
//    public CommandScannerIntro(Board board){
//        commands = new Commands(board, this.puffer);
//    }
//
//    private String[] read() throws IOException {
//        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        String a = bf.readLine();
//        String[] buf = a.split(" ");
//        return buf;
//    }
//
//    public boolean execCommand(String command){
//        try {
//            Method method = commands.getClass().getMethod(command);
//            Object reval = method.invoke(commands);
//            if(reval == null){
//                return true;
//            }
//            return (Boolean)reval;
//        }catch (NoSuchMethodException e1){
//            System.out.println("No Such Utils.Command");
//            return false;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public boolean execCommand(String command, Object Arguments){
//        try {
//            Method method = commands.getClass().getMethod(command, Arguments.getClass());
//            Object reval = method.invoke(commands, Arguments);
//            return true;
//        }catch (NoSuchMethodException e1){
//            System.out.println("No Such Utils.Command");
//            return false;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    @Override
//    public MoveCommand getCommand() {
//        while(true){
//            try {
//                System.out.print("Bitte geben sie einen Befehl ein!" + '\n' + "<help> für alle Befehle!"+'\n');
//                String[] p = read();
//                if(p.length < 2){
//                    if(execCommand(p[0])){
//                        break;
//                    }
//                }else{
//                    if(execCommand(p[0],p[1])){
//                        break;
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    public void message(String string) {
//
//    }
//
//	@Override
//	public void render(BoardView view) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public MoveCommand getCommand() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void execute(Board board) {
//		// TODO Auto-generated method stub
//		
//	}
//}
