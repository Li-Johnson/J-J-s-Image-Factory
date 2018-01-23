import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.awt.geom.*;

public class Pencil extends Tool{
    //Pencil is always 1 pixel thick
    private final int thickness = 1;
    final ImageIcon icon = new ImageIcon("icons/pencil.png");
    final ImageIcon selectedIcon = new ImageIcon("icons/pencil-selected.png");

    public Pencil(Window window,ToolsManager manage) {
        super(window,manage,"Pencil");
        toolInfo = "Pencil Tool - Click and drag to draw. Size not changable.";
        setIcon(icon);
    }

    public void resetIcon(){super.setIcon(icon);}
    public void setClickedIcon(){super.setIcon(selectedIcon);}

    public void clickedAction(Graphics2D g2, Point2D start) {
        draggedAction(g2,start,start);
    }
    public void draggedAction(Graphics2D g2, Point2D start, Point2D end) {
        //Enforce 1 pixel wide pencil
        g2.setStroke(new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
        g2.draw(new Line2D.Double(start,end));

    }

}
