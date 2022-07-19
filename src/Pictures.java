import java.util.Arrays;

public class Pictures {
    public int pictureCount;
    public boolean[] picSpots = new boolean[4];

    public Pictures(){
        pictureCount = 0;
        Arrays.fill(picSpots, Boolean.FALSE);
    }

    public void picture(double[] currentPos) {
        if (Math.round(currentPos[0]) == 5 && Math.round(currentPos[1]) == 6){
            if (!picSpots[0]){
                picSpots[0] = true;
                pictureSwitch();
            }
            else{
                System.out.println("\nImage already acquired. Visit a new location.");
            }
        }
        else if (Math.round(currentPos[0]) == 4 && Math.round(currentPos[1]) == 17){
            if (!picSpots[1]){
                picSpots[1] = true;
                pictureSwitch();
            }
            else{
                System.out.println("\nImage already acquired. Visit a new location.");
            }
        }
        else if (Math.round(currentPos[0]) == 15 && Math.round(currentPos[1]) == 7){
            if (!picSpots[2]){
                picSpots[2] = true;
                pictureSwitch();
            }
            else{
                System.out.println("\nImage already acquired. Visit a new location.");
            }
        }
        else if (Math.round(currentPos[0]) == 14 && Math.round(currentPos[1]) == 17){
            if (!picSpots[3]){
                picSpots[3] = true;
                pictureSwitch();
            }
            else{
                System.out.println("\nImage already acquired. Visit a new location.");
            }
        }
        else{
            System.out.println("\nSM13 camera is not within 0.5 range of a specified location.");
        }
    }

    private void pictureSwitch() {
        System.out.println("\nProcessing image...");
        try
        {
            // Delay for 4 seconds
            Thread.sleep(4000);
        }
        catch(InterruptedException ex)
        {
            ex.printStackTrace();
        }
        switch (pictureCount){
            case 0:
                pictureCount++;
                IronLungGUI picture1 = new IronLungGUI("1", "src/resources/SM13Image1.png",500, 0);
                break;
            case 1:
                pictureCount++;
                IronLungGUI picture2 = new IronLungGUI("2", "src/resources/SM13Image2.png",700, 10);
                break;
            case 2:
                pictureCount++;
                IronLungGUI picture3 = new IronLungGUI("3", "src/resources/SM13Image3.png",900, 20);
                break;
            case 3:
                pictureCount++;
                IronLungGUI picture4 = new IronLungGUI("4", "src/resources/SM13Image4.png",1100, 30);
                Move.die();
            default:
                break;
        }
    }
}
