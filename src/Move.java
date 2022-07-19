import java.util.Scanner;

public class Move {
    double[] currentPos = new double[2];
    static int moveNo = 0;

    public Move(){
        currentPos[0] = 2;
        currentPos[1] = 4;
    }

    public String[] getCurrentPos() {
        String[] reversed = new String[2];
        reversed[0] = "x" + Math.round(currentPos[1] * 10000.0) / 10000.0;
        reversed[1] = "y" + Math.round(currentPos[0] * 10000.0) / 10000.0;
        return reversed;
    }

    public void move(Scanner myScanner, Map map) {
        moveNo++;
        System.out.println("\n315  000  045\n270   *   090\n225  180  135");
        System.out.print("\nANGLE:");
        while (!myScanner.hasNextDouble()) {
            System.out.print("\nINVALID INPUT");
            System.out.print("\nANGLE:");
            myScanner.nextLine();
        }
        double angle_input = myScanner.nextDouble();
        myScanner.nextLine();

        System.out.print("\nDISTANCE:");
        while (!myScanner.hasNextDouble()) {
            System.out.print("\nINVALID INPUT");
            System.out.print("\nDISTANCE:");
            myScanner.nextLine();
        }
        double distance_input = myScanner.nextDouble();
        myScanner.nextLine();


        if (distance_input == 0){
            return;
        }

        double start_x = currentPos[1];
        double start_y = currentPos[0];

        double end_x = start_x + (distance_input * Math.sin(Math.toRadians(angle_input)));
        double end_y = start_y + (distance_input * Math.cos(Math.toRadians(angle_input)));

        Line submarine = new Line(start_y, start_x, end_y, end_x);

        boolean crashed = false;
        for (Line wall : map.getWalls()) {
            if(doIntersect(submarine, wall))
            {
                crashed = true;
            }
        }

        currentPos[0] = end_y;
        currentPos[1] = end_x;

        moving(crashed);
    }

    private void moving(boolean crashed) {
        System.out.println("\nRELOCATE INITIATED...");
        try
        {
            // Delay for 2 seconds
            Thread.sleep(2000);
        }
        catch(InterruptedException ex)
        {
            ex.printStackTrace();
        }
        System.out.println("SM13 NOW MOVING...");
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
            die();
        }
        else {
            System.out.println("RELOCATE COMPLETE");
        }
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
    }

    // Given three collinear points p, q, r, the function checks if
    // point q lies on line segment 'pr'
    private boolean onSegment(double[] p, double[] q, double[] r)
    {
        return q[1] <= Math.max(p[1], r[1]) && q[1] >= Math.min(p[1], r[1]) && q[0] <= Math.max(p[0], r[0]) && q[0] >= Math.min(p[0], r[0]);
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
        return o4 == 0 && onSegment(wall.start, submarine.end, wall.end);// Doesn't fall in any of the above cases
    }

    public static void die() {
        System.out.println("\n*WARNING*");
        try
        {
            // Delay for 1 seconds
            Thread.sleep(1000);
        }
        catch(InterruptedException ex)
        {
            ex.printStackTrace();
        }
        System.out.println("*COLLISION*");
        try
        {
            // Delay for 1 seconds
            Thread.sleep(1000);
        }
        catch(InterruptedException ex)
        {
            ex.printStackTrace();
        }
        System.out.println("*CRITICAL DAMAGE SUSTAINED*");
        try
        {
            // Delay for 1 seconds
            Thread.sleep(1000);
        }
        catch(InterruptedException ex)
        {
            ex.printStackTrace();
        }
        System.out.println("*LEAK DETECTED*");
        try
        {
            // Delay for 1 seconds
            Thread.sleep(1000);
        }
        catch(InterruptedException ex)
        {
            ex.printStackTrace();
        }
        System.out.println("\nNumber of moves made: " + moveNo);
        System.exit(0);
    }
}
