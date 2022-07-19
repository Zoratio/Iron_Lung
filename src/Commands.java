import java.util.Arrays;
import java.util.Scanner;

public class Commands {
    final static private Scanner myScanner = new Scanner(System.in);
    final private Map map = new Map(myScanner);
    final private Move move = new Move();
    final private Pictures pictures = new Pictures();

    public Commands(){
        listCommands();
        System.out.println("\nCOORDINATES: [x4.0, y2.0]");
        commands();
    }

    public void listCommands(){
        System.out.println("\nCOMMANDS:\n- MOVE       - PICTURE\n- COMMANDS   - OXYGEN\n- COMPASS    - HELP");
    }

    public void commands() {
        System.out.print("\nENTER COMMAND:");
        String command = myScanner.nextLine();

        switch (command) {
            case "COMMANDS" -> listCommands();
            case "MOVE" -> move.move(myScanner, map);
            case "PICTURE" -> pictures.picture(move.currentPos);
            case "COMPASS" -> System.out.println("\n315  000  045\n270   *   090\n225  180  135");
            case "OXYGEN" -> System.out.println("\n*FATAL ERROR: MALFUNCTION DETECTED*");
            case "HELP" -> System.out.println("\n............");
            default -> System.out.println("\nUNKNOWN COMMAND: ENTER 'COMMANDS' FOR LIST.");
        }
        System.out.print("\n################################################################\n");
        System.out.println("\nCOORDINATES: " + Arrays.toString(move.getCurrentPos()));

        commands();
    }
}
