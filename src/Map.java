import java.util.Currency;
import java.util.Scanner;
import java.util.Arrays;

public class Map {
    static private Scanner myScanner = new Scanner(System.in);
    double[] nextPos = new double[2];
    double[] currentPos = new double[2];

    final private int[][] grid = new int[18][18];

    public Map(){
        MakeGrid();
        System.out.print("DECADES AGO, EVERY KNOWN STAR AND HABITABLE PLANET VANISHED, LEAVING ONLY THOSE WHO WERE ON SPACE STATIONS OR STARSHIPS. THIS EVENT BECAME KNOWN AS THE " +
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
        currentPos[0] = 2;
        currentPos[1] = 4;
        for (int i = 0; i < grid.length; i++) {
            //Arrays.fill(grid, 0);
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = 0;
            }
        }
        grid[5][6] = 1;
        grid[4][17] = 1;
        grid[15][7] = 1;
        grid[14][17] = 1;

        //mark the grid wall points with 2
    }

    private void ListCommands(){
        System.out.println("\nCOMMANDS:\n- COORDINATES  - PICTURE\n- COMMANDS     - OXYGEN\n- COMPASS      - HELP\n- MOVE");
    }

    private void Commands() {
        System.out.print("\nENTER COMMAND:");
        String command = myScanner.nextLine();

        switch (command){
            case "COMMANDS":
                ListCommands();
                break;
            case "MOVE":
                Move();
                break;
            case "COORDINATES":
                System.out.println(Arrays.toString(GetCurrentPos()));
                break;
            case "COMPASS":
                System.out.println("\n315  000  045\n270   *   090\n225  180  135");
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

    private void Move() {
        System.out.print("\nANGLE:");
        double angle_input = Double.parseDouble((myScanner.nextLine()));
        System.out.print("\nDISTANCE:");
        int distance_input = Integer.parseInt((myScanner.nextLine()));

        double start_x = currentPos[1];
        double start_y = currentPos[0];

        double end_x = start_x + (distance_input * Math.sin(Math.toRadians(angle_input)));
        double end_y = start_y + (distance_input * Math.cos(Math.toRadians(angle_input)));

        lineFromPoints(start_x, start_y, end_x, end_y);
    }
    private void lineFromPoints(double start_x, double start_y, double end_x, double end_y)
    {
        double gradient = (end_x - end_y)/(start_y - start_x);
        double y_intercept = (gradient * (start_y) + (start_y - start_x) * (end_y))/(start_y - start_x);

        if ((start_y - start_x) < 0) {
            System.out.println("\nThe line passing through points START and END is: " + gradient + " gradient x - " + "y = " + y_intercept + " intercept");
        }
        else {
            System.out.println("\nThe line passing through points START and END is: " + gradient + " gradient x + " + "y = " + y_intercept + " intercept");
        }

        currentPos[0] = end_y;
        currentPos[1] = end_x;
    }

    public static void main(String[] args){
        Map game = new Map();
    }
}