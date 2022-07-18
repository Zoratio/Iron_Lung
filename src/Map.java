import java.util.Scanner;
import java.util.Arrays;

/*
TODO
- Add map nodes to 2D array with reference to next node for segment purpose
- When drawing submarine path, use the information on what grid spaces are being travelled through to determine which areas of the 2D array to check for segment intersects
    - OR as an easier solution, I check all ~80 but hide any delay due to calculation under the fact it will be 'travelling' for x amount of time anyway for realism and trepidation
        - Store the onSegment at orientation at compile time so no calculations are done while the program is running other than the comparison of lines.
 */

public class Map {
    static private Scanner myScanner = new Scanner(System.in);
    double[] nextPos = new double[2];
    double[] currentPos = new double[2];

    final private Line[] walls = new Line[101];//[111];

    public Map(){
        currentPos[0] = 2;
        currentPos[1] = 4;
        LogWalls(walls);
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

    private void ListCommands(){
        System.out.println("\nCOMMANDS:\n- MOVE       - PICTURE\n- COMMANDS   - OXYGEN\n- COMPASS    - HELP");
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

        Line submarine = new Line(start_y, start_x, end_y, end_x);

        boolean crashed = false;
        for (Line wall : walls) {
            if(doIntersect(submarine, wall))
            {
                crashed = true;
            }
        }

        currentPos[0] = end_y;
        currentPos[1] = end_x;

        System.out.println("\nRELOCATE INITIATED");
        try
        {
            // Delay for 2 seconds
            Thread.sleep(2000);
        }
        catch(InterruptedException ex)
        {
            ex.printStackTrace();
        }
        System.out.println("SM13 NOW MOVING");
        try
        {
            // Delay for 5 seconds
            Thread.sleep(5000);
        }
        catch(InterruptedException ex)
        {
            ex.printStackTrace();
        }
        if (crashed){
            //have game fail
            System.out.println("CRASHED");
        }

        System.out.println("RELOCATE COMPLETE");

        System.out.println("COORDINATES: " + Arrays.toString(GetCurrentPos()));
    }

    static class Line
    {
        double[] start = new double[2];
        double[] end = new double[2];
        public Line(double start_y, double start_x, double end_y, double end_x)
        {
            start[0] = start_y;
            start[1] = start_x;
            end[0] = end_y;
            end[1] = end_x;
        }
    };

    // Given three collinear points p, q, r, the function checks if
    // point q lies on line segment 'pr'
    private boolean onSegment(double[] p, double[] q, double[] r)
    {
        if (q[1] <= Math.max(p[1], r[1]) && q[1] >= Math.min(p[1], r[1]) && q[0] <= Math.max(p[0], r[0]) && q[0] >= Math.min(p[0], r[0]))
            return true;

        return false;
    }

    // To find orientation of ordered triplet (p, q, r).
    // The function returns following values
    // 0 --> p, q and r are collinear
    // 1 --> Clockwise
    // 2 --> Counterclockwise
    private int orientation(double[] p, double[] q, double[] r)
    {
        double val = (q[0] - p[0]) * (r[1] - q[1]) - (q[1] - p[1]) * (r[0] - q[0]);

        if (val == 0) return 0; // collinear

        return (val > 0)? 1: 2; // clock or counter clock wise
    }

    // The main function that returns true if line segment 'p1q1'
    // and 'p2q2' intersect.
    private boolean doIntersect(Line submarine, Line wall)
    {
        // Find the four orientations needed for general and
        // special cases
        int o1 = orientation(submarine.start, submarine.end, wall.start);
        int o2 = orientation(submarine.start, submarine.end, wall.end);
        int o3 = orientation(wall.start, wall.end, submarine.start);
        int o4 = orientation(wall.start, wall.end, submarine.end);

        // General case
        if (o1 != o2 && o3 != o4)
            return true;

        // Special Cases
        // p1, q1 and p2 are collinear and p2 lies on segment p1q1
        if (o1 == 0 && onSegment(submarine.start, wall.start, submarine.end)) return true;

        // p1, q1 and q2 are collinear and q2 lies on segment p1q1
        if (o2 == 0 && onSegment(submarine.start, wall.end, submarine.end)) return true;

        // p2, q2 and p1 are collinear and p1 lies on segment p2q2
        if (o3 == 0 && onSegment(wall.start, submarine.start, wall.end)) return true;

        // p2, q2 and q1 are collinear and q1 lies on segment p2q2
        if (o4 == 0 && onSegment(wall.start, submarine.end, wall.end)) return true;

        return false; // Doesn't fall in any of the above cases
    }

    private void LogWalls(Line[] walls){
        walls[0] = new Line(2,2,3,2);
        walls[1] = new Line(3,2,4,2);
        walls[2] = new Line(4,2,5,2);
        walls[3] = new Line(5,2,6,2);
        walls[4] = new Line(6,2,7,2);
        walls[5] = new Line(7,2,8,3);
        walls[6] = new Line(8,3,8,4);
        walls[7] = new Line(8,4,8,5);
        walls[8] = new Line(8,5,7,6);
        walls[9] = new Line(7,6,7,7);
        walls[10] = new Line(7,7,7,8);
        walls[11] = new Line(7,8,8,8);
        walls[12] = new Line(8,8,9,9);
        walls[13] = new Line(9,9,9,10);
        walls[14] = new Line(9,10,10,9);
        walls[15] = new Line(10,9,11,8);
        walls[16] = new Line(11,8,11,7);
        walls[17] = new Line(11,7,11,6);
        walls[18] = new Line(11,6,11,5);
        walls[19] = new Line(11,5,11,4);
        walls[20] = new Line(11,4,12,3);
        walls[21] = new Line(12,3,13,3);
        walls[22] = new Line(13,3,14,2);
        walls[23] = new Line(14,2,15,3);
        walls[24] = new Line(15,3,16,4);
        walls[25] = new Line(16,4,16,5);
        walls[26] = new Line(16,5,16,6);
        walls[27] = new Line(16,6,16,7);
        walls[28] = new Line(16,7,15,8);
        walls[29] = new Line(15,8,14,8);
        walls[30] = new Line(14,8,13,7);
        walls[31] = new Line(13,7,13,8);
        walls[32] = new Line(13,8,13,9);
        walls[33] = new Line(13,9,14,10);
        walls[34] = new Line(14,10,15,10);
        walls[35] = new Line(15,10,16,9);
        walls[36] = new Line(16,9,17,10);
        walls[37] = new Line(17,10,17,11);
        walls[38] = new Line(17,11,17,12);
        walls[39] = new Line(17,12,17,13);
        walls[40] = new Line(17,13,17,14);
        walls[41] = new Line(17,14,17,15);
        walls[42] = new Line(17,15,17,16);
        walls[43] = new Line(17,16,16,17);
        walls[44] = new Line(16,17,15,18);
        walls[45] = new Line(15,18,14,18);
        walls[46] = new Line(14,18,13,17);
        walls[47] = new Line(13,17,12,16);
        walls[48] = new Line(12,16,12,15);
        walls[49] = new Line(12,15,13,14);
        walls[50] = new Line(13,14,14,13);
        walls[51] = new Line(14,13,13,12);
        walls[52] = new Line(13,12,12,12);
        walls[53] = new Line(12,12,11,11);
        walls[54] = new Line(11,11,10,12);
        walls[55] = new Line(10,12,10,13);
        walls[56] = new Line(10,13,9,14);
        walls[57] = new Line(9,14,8,14);
        walls[58] = new Line(8,14,7,15);
        walls[59] = new Line(7,15,7,16);
        walls[60] = new Line(7,16,6,17);
        walls[61] = new Line(6,17,5,18);
        walls[62] = new Line(5,18,4,18);
        walls[63] = new Line(4,18,3,18);
        walls[64] = new Line(3,18,2,17);
        walls[65] = new Line(2,17,2,16);
        walls[66] = new Line(2,16,2,15);
        walls[67] = new Line(2,15,2,14);
        walls[68] = new Line(2,14,2,13);
        walls[69] = new Line(2,13,2,12);
        walls[70] = new Line(2,12,3,11);
        walls[71] = new Line(3,11,4,10);
        walls[72] = new Line(4,10,4,9);
        walls[73] = new Line(4,9,5,8);
        walls[74] = new Line(5,8,5,7);
        walls[75] = new Line(5,7,4,6);
        walls[76] = new Line(4,6,3,6);
        walls[77] = new Line(3,6,2,6);
        walls[78] = new Line(2,6,1,5);
        walls[79] = new Line(1,5,1,4);
        walls[80] = new Line(1,4,1,3);
        walls[81] = new Line(1,3,2,2);

        walls[82] = new Line(6,10,7,11);
        walls[83] = new Line(7,11,6,11);
        walls[84] = new Line(6,11,6,10);

        walls[85] = new Line(5,13,5,14);
        walls[86] = new Line(5,14,4,14);
        walls[87] = new Line(4,14,3,14);
        walls[88] = new Line(3,14,4,13);
        walls[89] = new Line(4,13,5,13);

        walls[90] = new Line(14,5,14,6);
        walls[91] = new Line(14,6,14,7);
        walls[92] = new Line(14,7,13,6);
        walls[93] = new Line(13,6,13,5);
        walls[94] = new Line(13,5,14,5);

        walls[95] = new Line(15,13,15,14);
        walls[96] = new Line(15,14,15,15);
        walls[97] = new Line(15,15,15,16);
        walls[98] = new Line(15,16,14,15);
        walls[99] = new Line(14,15,14,14);
        walls[100] = new Line(14,14,15,13);
    }

    public static void main(String[] args){
        Map game = new Map();
    }
}