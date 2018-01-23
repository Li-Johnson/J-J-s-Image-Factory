import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import java.awt.event.*;
import java.awt.color.ColorSpace;

public class Grayscale extends Filter{

    public Grayscale(Window window, FilterManager filterManage){
        super(window, filterManage, "Grayscale");
    }

    //goes through every pixel and calculates the black/white counterpart for that pixel
    public void doAction(BufferedImage img){
        for (int x = 0; x < img.getWidth(); x++){
            for (int y = 0; y < img.getHeight(); y++){
                int rgb = img.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = (rgb & 0xFF);

                int avg = (r + g + b) / 3;
                rgb =  (avg<<16) | (avg<<8) | avg;
                img.setRGB(x, y, rgb);
            }
        }
    }
}
