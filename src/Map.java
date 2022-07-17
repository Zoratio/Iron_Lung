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
                "TRAINING. \n\nIF SUCCESSFUL, YOU EARN YOUR FREEDOM.\n\n-PRESS ENTER TO BEGIN-");
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
        double distance_input = Double.parseDouble((myScanner.nextLine()));

        double start_x = currentPos[1];
        double start_y = currentPos[0];

        double end_x = start_x + (distance_input * Math.sin(Math.toRadians(angle_input)));
        double end_y = start_y + (distance_input * Math.cos(Math.toRadians(angle_input)));

        //lineFromPoints(start_x, start_y, end_x, end_y);
        Point p1 = new Point(start_y, start_x);
        Point q1 = new Point(end_y, end_x);
        Point p2 = new Point(3, 3);
        Point q2 = new Point(3, 5);

        if(doIntersect(p1, q1, p2, q2))
            System.out.println("Yes");
        else
            System.out.println("No");
    }

    /*private void lineFromPoints(double start_x, double start_y, double end_x, double end_y)
    {
        double a1 = end_y - start_y;
        double b1 = start_x - end_x;
        double c1 = a1 * start_x + b1 * start_y;

        double a2 = 3 - 3;
        double b2 = 3 - 5;
        double c2 = a2 * 3 + b2 * 3;

        double delta = a1 * b2 - a2 * b1;
        if (delta == 0){
            System.out.print("Don't touch?");
        }
        else {
            System.out.print("Touch?");
        }
    }*/

    /*private void lineFromPointsOLD(double start_x, double start_y, double end_x, double end_y)
    {
        double gradient = (end_x - end_y)/(start_y - start_x);
        double y_intercept = (gradient * (start_y) + (start_y - start_x) * (end_y))/(start_y - start_x);

        double gradient2 = (3 - 5)/(3 - 3);
        double y_intercept2 = (gradient2 * (3) + (3 - 3) * (5))/(3 - 3);

        double delta =

        if ((start_y - start_x) < 0) {
            System.out.println("\nThe line passing through points START and END is: " + gradient + " gradient x - " + "y = " + y_intercept + " intercept");
        }
        else {
            System.out.println("\nThe line passing through points START and END is: " + gradient + " gradient x + " + "y = " + y_intercept + " intercept");
        }


        currentPos[0] = end_y;
        currentPos[1] = end_x;
    }*/

    private class Point
    {
        double x;
        double y;
        public Point(double y, double x)
        {
            this.x = x;
            this.y = y;
        }
    };

    // Given three collinear points p, q, r, the function checks if
    // point q lies on line segment 'pr'
    private boolean onSegment(Point p, Point q, Point r)
    {
        if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
                q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y))
            return true;

        return false;
    }

    // To find orientation of ordered triplet (p, q, r).
    // The function returns following values
    // 0 --> p, q and r are collinear
    // 1 --> Clockwise
    // 2 --> Counterclockwise
    private int orientation(Point p, Point q, Point r)
    {
        double val = (q.y - p.y) * (r.x - q.x) -
                (q.x - p.x) * (r.y - q.y);

        if (val == 0) return 0; // collinear

        return (val > 0)? 1: 2; // clock or counter clock wise
    }

    // The main function that returns true if line segment 'p1q1'
    // and 'p2q2' intersect.
    private boolean doIntersect(Point p1, Point q1, Point p2, Point q2)
    {
        // Find the four orientations needed for general and
        // special cases
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        // General case
        if (o1 != o2 && o3 != o4)
            return true;

        // Special Cases
        // p1, q1 and p2 are collinear and p2 lies on segment p1q1
        if (o1 == 0 && onSegment(p1, p2, q1)) return true;

        // p1, q1 and q2 are collinear and q2 lies on segment p1q1
        if (o2 == 0 && onSegment(p1, q2, q1)) return true;

        // p2, q2 and p1 are collinear and p1 lies on segment p2q2
        if (o3 == 0 && onSegment(p2, p1, q2)) return true;

        // p2, q2 and q1 are collinear and q1 lies on segment p2q2
        if (o4 == 0 && onSegment(p2, q1, q2)) return true;

        return false; // Doesn't fall in any of the above cases
    }

    public static void main(String[] args){
        Map game = new Map();
    }
}