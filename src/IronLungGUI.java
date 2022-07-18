import javax.swing.*;
import java.awt.*;

public class IronLungGUI extends JFrame {

    public IronLungGUI(String title, String path, int frameBoundX, int frameBoundY){
        JFrame frame = new JFrame(); //JFrame Creation
        frame.setTitle("Map"); //Add the title to frame
        frame.setLayout(null); //Terminates default flow layout
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Terminate program on close button
        frame.setBounds(frameBoundX, frameBoundY, 495, 518); //Sets the position of the frame
        frame.setResizable(false);
        frame.setAlwaysOnTop( true );

        Container c = frame.getContentPane(); //Gets the content layer
        JLabel label = new JLabel(); //JLabel Creation
        label.setIcon(new ImageIcon(path)); //Sets the image to be displayed as an icon
        Dimension size = label.getPreferredSize(); //Gets the size of the image
        label.setBounds(0, 0, size.width, size.height); //Sets the location of the image

        c.add(label); //Adds objects to the container
        frame.setVisible(true); // Exhibit the frame
    }
}
