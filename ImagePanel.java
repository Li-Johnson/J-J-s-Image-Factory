import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.awt.geom.*;

public class ImagePanel extends JComponent{
    private Window myWindow;
    private String path;
    private Graphics2D g2;
    private BufferedImage image;
    private Point2D start, end;

    public ImagePanel(Window window, String path) {
        this.path = path;
        setup(window);
    }

    //accepts the image as input to constructor
    public ImagePanel(Window window, BufferedImage img) {
        this.image = img;
        g2 = (Graphics2D) img.getGraphics();
        setup(window);
    }

    public ImagePanel(Window window) {
        clearImage();
        setup(window);
    }
    public void setup(Window window) {
        start = new Point();
        end = new Point();
        myWindow = window;
        setDoubleBuffered(false);
        this.setPreferredSize(new Dimension(500,500));
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                start.setLocation(e.getX(),e.getY());
                myWindow.getToolsManager().clickedAction(g2,start);
                revalidate();
                repaint();
            }
            public void mouseReleased(MouseEvent e) {
                end.setLocation(e.getX(),e.getY());
                myWindow.getToolsManager().releasedAction(g2,end);
                revalidate();
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                //updates coords
                end.setLocation(e.getX(),e.getY());
                //does the action in tools manager
                myWindow.getToolsManager().draggedAction(g2,start,end);
                revalidate();
                repaint();
                //updates coords
                start.setLocation(end.getX(),end.getY());
            }
        });


    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image == null) {
            image = makeImage(path);
            g2 = (Graphics2D) image.getGraphics();
        }
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(image,0,0,null);
    }
    //changes instance's image
    public void changeImage(BufferedImage img) {
        image = img;
        g2 = (Graphics2D) image.getGraphics();
        //System.out.println("asasa");
        revalidate();
        repaint();
    }

    //Makes a copy of buffered image. taken off internet
	public static BufferedImage deepCopy(BufferedImage bi) {
 		ColorModel cm = bi.getColorModel();
 		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
 		WritableRaster raster = bi.copyData(null);
 		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
    //static method to return an image based on a path. returns a blank image if invalid image
    public static BufferedImage makeImage(String path) {
        try {
            //Reads image at specified path
            BufferedImage img = ImageIO.read(new File(path));
            //Makes a new image (the one to be returned) with an ARGB colormodel <== fixes black and white issues
            BufferedImage img2 = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_RGB);
            //Copies old image into the image to be returned
            Graphics2D newGraphics = (Graphics2D) img2.getGraphics();
            newGraphics.drawImage(img,0,0,null);
            return img2;
        }catch(IOException e) {
            System.out.println("Invalid File: Expecting .jpg or .png image file.");
        }
        BufferedImage img = new BufferedImage(500,500,BufferedImage.TYPE_INT_ARGB);
        return img;

    }

    public void clearImage() {
        this.image = new BufferedImage(1280,720,BufferedImage.TYPE_INT_RGB);
        g2 = (Graphics2D) image.getGraphics();
        g2.setPaint(Color.white);
        g2.fillRect(0,0,image.getWidth(),image.getHeight());
        revalidate();
        repaint();
    }
    //getters and setters
    public Graphics2D getGraphics() {return g2;}
    public BufferedImage getImage() {return image;}
    public BufferedImage getBufferedImage() {return image;}
    public Point2D getStart() {return start;}
    public Point2D getEnd() {return end;}
    public void setStart(Point p) {start.setLocation(p.getX(),p.getY());}
    public void setEnd(Point p) {end.setLocation(p.getX(),p.getY());}
    public void setGraphics(Graphics2D g2) {this.g2 = g2;}

}
