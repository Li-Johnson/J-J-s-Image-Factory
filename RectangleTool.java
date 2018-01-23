import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.image.*;
public class RectangleTool extends Tool {
	private double x1,y1,x2,y2;
	private Rectangle2D rectangle;
	private BufferedImage img;
    final ImageIcon icon = new ImageIcon("icons/box.png");
    final ImageIcon selectedIcon = new ImageIcon("icons/box-selected.png");

	public RectangleTool(Window window,ToolsManager manage) {
        super(window,manage,"Rectangle Tool");
		toolInfo = "Rectangle Tool - Click and drag to make a rectangle. Change size using slider.";
		setIcon(icon);
    }

    public void resetIcon(){super.setIcon(icon);}
    public void setClickedIcon(){super.setIcon(selectedIcon);}

	//SUMMARY: When clicked: keep track of coords and the current image
	//		   When dragged: redraw the image before the first click and then draw the rectangle defined by the original point and the cursor
	public void draggedAction(Graphics2D g2, Point2D start, Point2D end){
		x2 = end.getX();
		y2 = end.getY();
		rectangle.setRect(Math.min(x1,x2), //Gets coord of top left of rectangle
						Math.min(y1,y2),
						Math.abs(x1-x2), //Width
						Math.abs(y1-y2)); //Height
		g2.drawImage(img,0,0,null);
		g2.draw(rectangle);
	}

    public void clickedAction(Graphics2D g2, Point2D start){
    	x1 = start.getX();
		y1 = start.getY();
		img = ImagePanel.deepCopy(imagePanel.getImage());
		rectangle = new Rectangle2D.Double();
    }




}
