import java.util.Scanner;
import java.util.Arrays;

public class Map {
    static private Scanner myScanner = new Scanner(System.in);
    double[] nextPos = new double[2];
    double[] currentPos = new double[2];

    final private int[][] grid = new int[18][18];

    public Map(){
        MakeGrid();
        System.out.print("DECADES AGO, EVERY KNOWN STAR AND HABITABLE PLANET VANISHED, LEAVING ONLY THOSE WHO WERE ON SPACE STATIONS ORR STARSHIPS. THIS EVENT BECAME KNOWN AS THE " +
                "QUIET RAPTURE. \n\nWITH SUPPLIES DWINDLING AND INFRASTRUCTURE CRUMBLING, SURVIVORS ARE SEARCHING FOR ANY TRACE OF NATURAL RESOURCES IN A UNIVERSE OF BARREN MOONS, LIT " +
                "BY THE GHOSTLIGHT OF VANISHING STARS. \n\nONE SUCH MOON HOLDS A STRANGE ANOMALY: AN OCEAN OF BLOOD. YOU ARE A CONVICT, TASKED WITH EXPLORING THIS ANOMALY, IN A MAKESHIFT " +
                "SUBMARINE NICKNAMED THE IRON LUNG. IT WAS NOT DESIGNED FOR THIS DEPTH, \nSO YOU WILL BE WELDED INSIDE AND THE FORWARD WINDOW WILL BE CLOSED. \n\nTHERE WAS NO TIME FOR " +
                "TRAINING. \n\nIF SUCCESSFUL, YOU EARN YOUR FREEDOM.\n\n-Enter any key to begin-");
        myScanner.nextLine();
        IronLungGUI gameMap = new IronLungGUI();
        System.out.println("\n################################################################\n\nTWO WEEKS AGO, WE CONDUCTED AN EXPLORATION OF MOON AT-5 FOR THE FIRST TIME SINCE THE QUIET RAPTURE, LEADING TO THE DISCOVERY OF A FOURTH BLOOD OCEAN. A TRENCH " +
                "BENEATH THE OCEAN'S SURFACE HAS SEVERAL POINTS OF INTEREST.\n\nYOUR TASK IS TO PHOTOGRAPH THESE POINTS OF INTEREST WITH THE SM13'S CAMERA. PHOTOS MUST BE TAKEN AT THE POINT OF " +
                "INTEREST.\n\nSINCE YOU CAN'T NAVIGATE BY SIGHT, PAY ATTENTION TO YOUR COORDINATES AND CONSULT THE MAP. COMPLETE YOUR TASK AND RETURN TO THE SURFACE BEFORE OXYGEN RUNS OUT.\n\nGOOD LUCK.\n");
        ListCommands();
        Commands();
    }

    private void MakeGrid() {   //*y then x*
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = 0;
            }
        }
        grid[5][6] = 1;
        grid[4][17] = 1;
        grid[15][7] = 1;
        grid[14][17] = 1;


    }

    private void ListCommands(){
        System.out.println("\nCOMMANDS:\n- COORDINATES  - PICTURE\n- NORTH        - NORTH-EAST\n- EAST         - EAST-WEST\n- WEST         - SOUTH-WEST\n- SOUTH        - SOUTH-NORTH\n- COMMANDS     - OXYGEN\n- COMPASS      - HELP");
    }

    private void Commands() {
        System.out.print("\nENTER COMMAND:");
        String command = myScanner.nextLine();

        switch (command){
            case "COMMANDS":
                ListCommands();
                break;
            case "NORTH":
                nextPos[0] = 1;
                nextPos[1] = 0;
                Move(nextPos);
                break;
            case "NORTH-EAST":
                nextPos[0] = 1;
                nextPos[1] = 1;
                Move(nextPos);
                break;
            case "EAST":
                nextPos[0] = 0;
                nextPos[1] = 1;
                Move(nextPos);
                break;
            case "SOUTH-EAST":
                nextPos[0] = -1;
                nextPos[1] = 1;
                Move(nextPos);
                break;
            case "SOUTH":
                nextPos[0] = -1;
                nextPos[1] = 0;
                Move(nextPos);
                break;
            case "SOUTH-WEST":
                nextPos[0] = -1;
                nextPos[1] = -1;
                Move(nextPos);
                break;
            case "WEST":
                nextPos[0] = 0;
                nextPos[1] = -1;
                Move(nextPos);
                break;
            case "NORTH-WEST":
                nextPos[0] = 1;
                nextPos[1] = -1;
                Move(nextPos);
                break;
            case "COORDINATES":
                System.out.println(Arrays.toString(GetCurrentPos()));
                break;
            case "COMPASS":
                System.out.println("\nNW  N  NE\nW   +   E\nSW  S  SE");
                break;
            case "OXYGEN":
                System.out.println("\n*FATAL ERROR: MALFUNCTION DETECTED*");
                break;
            case "HELP":
                System.out.println("\n............");
                break;
            default:
                System.out.println("\nUNKNOWN COMMAND: ENTER 'COMMANDS' FOR LIST.");
                break;
        }
        System.out.print("\n################################################################\n");

        Commands();
    }

    private double[] GetCurrentPos() {
        return currentPos;
    }

    private void Move(double[] pos) {
        currentPos[0] += nextPos[0];
        currentPos[1] += nextPos[1];
        /*if (grid[currentPos[0]][currentPos[1]] == 0 ||grid[currentPos[0]][currentPos[1]] == 1){ //1 is picture coordinate
            //safe
        }
        else if (grid[currentPos[0]][currentPos[1]] == 2){
            //bang
        }*/
    }

    public static void main(String[] args){
        Map game = new Map();
    }
}