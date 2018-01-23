import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import java.awt.event.*;


public class EdgeDetect extends Filter{

    public EdgeDetect(Window window, FilterManager filterManage){
        super(window, filterManage, "Edge Detect");
    }

    public void doAction(BufferedImage img){
        boolean go = true;
        String threshold;
        int intThreshold = 0;

        /*
        *a loop to ask the user what they want the edge detect threshold to be
        *if they enter a unacceptable value, a message pops up and it asks again.
        */
        while (go){
            try{
                threshold = JOptionPane.showInputDialog("Threshold: (Suggested 1~400)");
                if (threshold == null){
                    return;       //If they press cancel or the X button
                }
                intThreshold = Integer.parseInt(threshold);
                if (intThreshold < 1) {
                    throw new NumberFormatException();
                }
                go = false;
            }catch(NumberFormatException error){
                JOptionPane.showMessageDialog(null, "Positive integers only please.\n (1 - 2,147,483,647)");
            }
        }
        /*
        *loops through every pixel and if the total RGB value of the pixel is greater than the threshold,
        *it will be set to white, and if it's not greater, it will be set to black.
        *And so larger threshold = more visible edge because darker colors have lower RGB values.
        */
        for (int x = 0; x < img.getWidth(); x++){
            for (int y = 0; y < img.getHeight(); y++){
                int rgb = img.getRGB(x, y);
                Color color = new Color(rgb, true);
                if (color.getRed() + color.getGreen() + color.getBlue() > intThreshold){
                    color = new Color(255, 255, 255);
                }else{
                    color = new Color(0, 0, 0);
                }

                img.setRGB(x, y, color.getRGB());
            }
        }
    }


}
