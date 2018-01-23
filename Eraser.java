import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.awt.geom.*;
public class Eraser extends Tool {

    final ImageIcon icon = new ImageIcon("icons/eraser.png");
    final ImageIcon selectedIcon = new ImageIcon("icons/eraser-selected.png");

	public Eraser(Window window, ToolsManager manage) {
		super(window,manage,"Eraser");
		toolInfo = "Eraser Tool - Click and drag to erase. Change size using slider.";
        setIcon(icon);
	}

    public void resetIcon(){super.setIcon(icon);}
    public void setClickedIcon(){super.setIcon(selectedIcon);}

	public void clickedAction(Graphics2D g2, Point2D start) {
		draggedAction(g2,start,start);
	}

	public void draggedAction(Graphics2D g2, Point2D start, Point2D end) {
		g2.setColor(Color.white);
		g2.draw(new Line2D.Float(start,end));
    }

}
