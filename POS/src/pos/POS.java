package pos;
import javax.swing.*;
public class POS {

    public static void main(String[] args) {
        // Create the main frame (JFrame)
        JFrame frame = new JFrame("Super Mart");
        
        // Create a JPanel to hold your components
        JPanel panel = new Login();

        // Set up the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(672, 410);

        // Add the panel to the frame
        frame.add(panel);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);
        
        // Make the frame visible
        frame.setVisible(true);
         
    }
    
}
