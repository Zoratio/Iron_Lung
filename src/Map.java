import java.util.Scanner;
import java.util.Arrays;

public class Map {
    final private Move.Line[] walls = new Move.Line[101];

    public Map(Scanner myScanner){
        logWalls(walls);
        System.out.print("""
                DECADES AGO, EVERY KNOWN STAR AND HABITABLE PLANET VANISHED, LEAVING ONLY THOSE WHO WERE ON SPACE STATIONS OR STARSHIPS. THIS EVENT BECAME KNOWN AS THE QUIET RAPTURE.\s

                WITH SUPPLIES DWINDLING AND INFRASTRUCTURE CRUMBLING, SURVIVORS ARE SEARCHING FOR ANY TRACE OF NATURAL RESOURCES IN A UNIVERSE OF BARREN MOONS, LIT BY THE GHOSTLIGHT OF VANISHING STARS.\s

                ONE SUCH MOON HOLDS A STRANGE ANOMALY: AN OCEAN OF BLOOD. YOU ARE A CONVICT, TASKED WITH EXPLORING THIS ANOMALY, IN A MAKESHIFT SUBMARINE NICKNAMED THE IRON LUNG. IT WAS NOT DESIGNED FOR THIS DEPTH,\s
                SO YOU WILL BE WELDED INSIDE AND THE FORWARD WINDOW WILL BE CLOSED.\s

                THERE WAS NO TIME FOR TRAINING.\s

                IF SUCCESSFUL, YOU EARN YOUR FREEDOM.

                -PRESS ENTER TO BEGIN-""");
        myScanner.nextLine();
        IronLungGUI gameMap = new IronLungGUI("Map", "src/resources/IronLungMap.png",0, 0);
        System.out.println("""

                ################################################################

                TWO WEEKS AGO, WE CONDUCTED AN EXPLORATION OF MOON AT-5 FOR THE FIRST TIME SINCE THE QUIET RAPTURE, LEADING TO THE DISCOVERY OF A FOURTH BLOOD OCEAN. A TRENCH BENEATH THE OCEAN'S SURFACE HAS SEVERAL POINTS OF INTEREST.

                YOUR TASK IS TO PHOTOGRAPH THESE POINTS OF INTEREST WITH THE SM13'S CAMERA. PHOTOS MUST BE TAKEN AT THE POINT OF INTEREST.

                SINCE YOU CAN'T NAVIGATE BY SIGHT, PAY ATTENTION TO YOUR COORDINATES AND CONSULT THE MAP. COMPLETE YOUR TASK AND RETURN TO THE SURFACE BEFORE OXYGEN RUNS OUT.

                GOOD LUCK.
                """);
    }

    public Move.Line[] getWalls(){
        return walls;
    }

    private void logWalls(Move.Line[] walls){
        walls[0] = new Move.Line(2,2,3,2);
        walls[1] = new Move.Line(3,2,4,2);
        walls[2] = new Move.Line(4,2,5,2);
        walls[3] = new Move.Line(5,2,6,2);
        walls[4] = new Move.Line(6,2,7,2);
        walls[5] = new Move.Line(7,2,8,3);
        walls[6] = new Move.Line(8,3,8,4);
        walls[7] = new Move.Line(8,4,8,5);
        walls[8] = new Move.Line(8,5,7,6);
        walls[9] = new Move.Line(7,6,7,7);
        walls[10] = new Move.Line(7,7,7,8);
        walls[11] = new Move.Line(7,8,8,8);
        walls[12] = new Move.Line(8,8,9,9);
        walls[13] = new Move.Line(9,9,9,10);
        walls[14] = new Move.Line(9,10,10,9);
        walls[15] = new Move.Line(10,9,11,8);
        walls[16] = new Move.Line(11,8,11,7);
        walls[17] = new Move.Line(11,7,11,6);
        walls[18] = new Move.Line(11,6,11,5);
        walls[19] = new Move.Line(11,5,11,4);
        walls[20] = new Move.Line(11,4,12,3);
        walls[21] = new Move.Line(12,3,13,3);
        walls[22] = new Move.Line(13,3,14,2);
        walls[23] = new Move.Line(14,2,15,3);
        walls[24] = new Move.Line(15,3,16,4);
        walls[25] = new Move.Line(16,4,16,5);
        walls[26] = new Move.Line(16,5,16,6);
        walls[27] = new Move.Line(16,6,16,7);
        walls[28] = new Move.Line(16,7,15,8);
        walls[29] = new Move.Line(15,8,14,8);
        walls[30] = new Move.Line(14,8,13,7);
        walls[31] = new Move.Line(13,7,13,8);
        walls[32] = new Move.Line(13,8,13,9);
        walls[33] = new Move.Line(13,9,14,10);
        walls[34] = new Move.Line(14,10,15,10);
        walls[35] = new Move.Line(15,10,16,9);
        walls[36] = new Move.Line(16,9,17,10);
        walls[37] = new Move.Line(17,10,17,11);
        walls[38] = new Move.Line(17,11,17,12);
        walls[39] = new Move.Line(17,12,17,13);
        walls[40] = new Move.Line(17,13,17,14);
        walls[41] = new Move.Line(17,14,17,15);
        walls[42] = new Move.Line(17,15,17,16);
        walls[43] = new Move.Line(17,16,16,17);
        walls[44] = new Move.Line(16,17,15,18);
        walls[45] = new Move.Line(15,18,14,18);
        walls[46] = new Move.Line(14,18,13,17);
        walls[47] = new Move.Line(13,17,12,16);
        walls[48] = new Move.Line(12,16,12,15);
        walls[49] = new Move.Line(12,15,13,14);
        walls[50] = new Move.Line(13,14,14,13);
        walls[51] = new Move.Line(14,13,13,12);
        walls[52] = new Move.Line(13,12,12,12);
        walls[53] = new Move.Line(12,12,11,11);
        walls[54] = new Move.Line(11,11,10,12);
        walls[55] = new Move.Line(10,12,10,13);
        walls[56] = new Move.Line(10,13,9,14);
        walls[57] = new Move.Line(9,14,8,14);
        walls[58] = new Move.Line(8,14,7,15);
        walls[59] = new Move.Line(7,15,7,16);
        walls[60] = new Move.Line(7,16,6,17);
        walls[61] = new Move.Line(6,17,5,18);
        walls[62] = new Move.Line(5,18,4,18);
        walls[63] = new Move.Line(4,18,3,18);
        walls[64] = new Move.Line(3,18,2,17);
        walls[65] = new Move.Line(2,17,2,16);
        walls[66] = new Move.Line(2,16,2,15);
        walls[67] = new Move.Line(2,15,2,14);
        walls[68] = new Move.Line(2,14,2,13);
        walls[69] = new Move.Line(2,13,2,12);
        walls[70] = new Move.Line(2,12,3,11);
        walls[71] = new Move.Line(3,11,4,10);
        walls[72] = new Move.Line(4,10,4,9);
        walls[73] = new Move.Line(4,9,5,8);
        walls[74] = new Move.Line(5,8,5,7);
        walls[75] = new Move.Line(5,7,4,6);
        walls[76] = new Move.Line(4,6,3,6);
        walls[77] = new Move.Line(3,6,2,6);
        walls[78] = new Move.Line(2,6,1,5);
        walls[79] = new Move.Line(1,5,1,4);
        walls[80] = new Move.Line(1,4,1,3);
        walls[81] = new Move.Line(1,3,2,2);

        walls[82] = new Move.Line(6,10,7,11);
        walls[83] = new Move.Line(7,11,6,11);
        walls[84] = new Move.Line(6,11,6,10);

        walls[85] = new Move.Line(5,13,5,14);
        walls[86] = new Move.Line(5,14,4,14);
        walls[87] = new Move.Line(4,14,3,14);
        walls[88] = new Move.Line(3,14,4,13);
        walls[89] = new Move.Line(4,13,5,13);

        walls[90] = new Move.Line(14,5,14,6);
        walls[91] = new Move.Line(14,6,14,7);
        walls[92] = new Move.Line(14,7,13,6);
        walls[93] = new Move.Line(13,6,13,5);
        walls[94] = new Move.Line(13,5,14,5);

        walls[95] = new Move.Line(15,13,15,14);
        walls[96] = new Move.Line(15,14,15,15);
        walls[97] = new Move.Line(15,15,15,16);
        walls[98] = new Move.Line(15,16,14,15);
        walls[99] = new Move.Line(14,15,14,14);
        walls[100] = new Move.Line(14,14,15,13);
    }
}