import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import java.awt.event.*;

public class InvertColors extends Filter{

    public InvertColors(Window window, FilterManager filterManage){
        super(window, filterManage, "Invert Colors");
    }

    public void doAction(BufferedImage img){
        //loops through every pixel
        for (int x = 0; x < img.getWidth(); x++){
            for (int y = 0; y < img.getHeight(); y++){
                //gets the rgb value of pixel and subtracts it from 255 to find the inverse color.
                int rgb = img.getRGB(x, y);
                Color color = new Color(rgb, true);
                color = new Color(255 - color.getRed(),
                                255 - color.getGreen(),
                                255 - color.getBlue());
                //set the rgb value to calculated value
                img.setRGB(x, y, color.getRGB());
            }
        }

    }
}
