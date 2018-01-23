import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.image.*;

public class EllipseTool extends Tool{
    private double x1,y1,x2,y2;
	private Ellipse2D ellipse;
	private BufferedImage img;
    final ImageIcon icon = new ImageIcon("icons/circle.png");
    final ImageIcon selectedIcon = new ImageIcon("icons/circle-selected.png");

	public EllipseTool(Window window,ToolsManager manage) {
        super(window,manage,"Ellipse Tool");
        toolInfo = "Ellipse Tool - Click and drag to make an ellipse. Change size using slider.";
        setIcon(icon);
    }

    public void resetIcon(){super.setIcon(icon);}
    public void setClickedIcon(){super.setIcon(selectedIcon);}

	//SUMMARY: When clicked: keep track of coords and the current image
	//		   When dragged: redraw the image before the first click and then draw the ellipse framed by the rectangle defined by the original point and the cursor
	public void draggedAction(Graphics2D g2, Point2D start, Point2D end){
		x2 = end.getX();
		y2 = end.getY();
		ellipse.setFrame(x1 - Math.abs(x1-x2), //Gets coord of top left of rectangle that frames the ellipse
						y1 - Math.abs(y1-y2),
						2 * Math.abs(x1-x2), //Width
						2 * Math.abs(y1-y2)); //Height
		g2.drawImage(img,0,0,null);
		g2.draw(ellipse);
	}

    public void clickedAction(Graphics2D g2, Point2D start){
    	x1 = start.getX();
		y1 = start.getY();
		img = ImagePanel.deepCopy(imagePanel.getImage());
		ellipse = new Ellipse2D.Double();
    }
}
