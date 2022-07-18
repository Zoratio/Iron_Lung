import java.util.Arrays;
import java.util.Scanner;

public class Commands {
    Map map;
    Move move;
    final private Pictures pictures = new Pictures();

    public Commands(Map map, Move move){
        this.map = map;
        this.move = move;
    }
    public void listCommands(){
        System.out.println("\nCOMMANDS:\n- MOVE       - PICTURE\n- COMMANDS   - OXYGEN\n- COMPASS    - HELP");
    }

    public void commands(Scanner myScanner) {
        System.out.print("\nENTER COMMAND:");
        String command = myScanner.nextLine();

        switch (command) {
            case "COMMANDS" -> listCommands();
            case "MOVE" -> move.move(myScanner, map);
            case "PICTURE" -> pictures.picture(move.currentPos, map.picSpots);
            case "COMPASS" -> System.out.println("\n315  000  045\n270   *   090\n225  180  135");
            case "OXYGEN" -> System.out.println("\n*FATAL ERROR: MALFUNCTION DETECTED*");
            case "HELP" -> System.out.println("\n............");
            default -> System.out.println("\nUNKNOWN COMMAND: ENTER 'COMMANDS' FOR LIST.");
        }
        System.out.print("\n################################################################\n");
        System.out.println("\nCOORDINATES: " + Arrays.toString(move.getCurrentPos()));

        commands(myScanner);
    }
}
