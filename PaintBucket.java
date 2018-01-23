import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import java.awt.event.*;
import java.awt.color.ColorSpace;
import java.awt.geom.*;

public class PaintBucket extends Tool{

    final ImageIcon icon = new ImageIcon("icons/paintbucket.png");
    final ImageIcon selectedIcon = new ImageIcon("icons/paintbucket-selected.png");
    BufferedImage img;

    public PaintBucket(Window window, ToolsManager manage) {
        super(window,manage,"Paint Bucket");
        toolInfo = "Paint Bucket - Click on the screen to color all the pixels of same color a different color.";
        setIcon(icon);
    }

    public void resetIcon(){super.setIcon(icon);}
    public void setClickedIcon(){super.setIcon(selectedIcon);}

    public void clickedAction(Graphics2D g2, Point2D start) {
        img = myWindow.getImagePanel().getImage();
        ArrayList<Point2D> pixels = pixelsToPaint(start);
        for (Point2D q : pixels) {
            img.setRGB((int)q.getX(),(int)q.getY(),manager.getColor().getRGB());
        }
    }


    public ArrayList<Point2D> pixelsToPaint(Point2D start) {
        ArrayList<Point2D> pixels = new ArrayList<Point2D>();
        for (int x = 0; x < img.getWidth(); x++){
            for (int y = 0; y < img.getHeight(); y++){
                int rgb = img.getRGB(x, y);
                if (rgb == img.getRGB((int)start.getX(),(int)start.getY())) {
                    pixels.add(new Point2D.Double(x,y));
                }
            }
        }
        return pixels;
    }






//==============================ABANDONED=========================================//
    //Recursively figure out which pixels to paint by asking the pixels directly next to see which ones work
    /* public ArrayList<Point2D> pixelsToPaint(Point2D original, Point2D current) {
        ArrayList<Point2D> points = new ArrayList<Point2D>();
        int x1 = (int)original.getX(); //ORIGINAL PIXEL COORDS
        int y1 = (int)original.getY();
        int rgbOriginal = img.getRGB(x1, y1);
        int x2 = (int)current.getX(); //CURRENT PIXEL COORDS (current pixel is the pixel we are currently checking to see if any of the the other 8 around also work)
        int y2 = (int)current.getY();
        for (int x = 0; x < 3; x++){
            for (int y = 0; y < 3; y++){
                //Current coords of the pixel in the iteration
                int x3 = x1 - 1 + x;
                int y3 = y1 - 1 + y;
                //See if the pixel is going outwards from original(if it doesnt check, infinite recursion)
                boolean notOutward = ((x3 < 0 || y3 < 0 || x3 > img.getWidth() || y3 > img.getHeight()) //out of bounds
                                    ||((x1 > x2) && (y1 > y2) && !(x3 < x2) && !(y3 < y2)) //current is to the top left of original
                                    ||((x1 > x2) && (y1 < y2) && !(x3 < x2) && !(y3 > y2)) //bottom left
                                    ||((x1 < x2) && (y1 > y2) && !(x3 > x2) && !(y3 < y2)) // top right
                                    ||((x1 < x2) && (y1 < y2) && !(x3 > x2) && !(y3 > y2)) //bottom right
                                    );
                if (notOutward){
                    break;
                }
                int rgbAround = img.getRGB(x3, y3);
                //Checks to see if the points around have the same exact color
                if (rgbAround == rgbOriginal) {
                    points.add(new Point((int)x3,(int)y3));
                }
            }
        }
        if (points.size() == 0)
        return points;
        //Looks around and calls the method on those pixels
        ArrayList<Point2D> points1 = new ArrayList<Point2D>();
        for (Point2D q : points) {
            points1.addAll(pixelsToPaint(original,q));
        }
        return points1;
    }
*/
//=====================================================================================/

}
