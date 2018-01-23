import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.awt.geom.*;

public class Paintbrush extends Tool{

    final ImageIcon icon = new ImageIcon("icons/brush.png");
    final ImageIcon selectedIcon = new ImageIcon("icons/brush-selected.png");

    public Paintbrush(Window window,ToolsManager manage) {
        super(window,manage,"Paintbrush");
        toolInfo = "Paintbrush - Click and drag to paint. Change size using slider.";
        setIcon(icon);
    }

    public void resetIcon(){super.setIcon(icon);}
    public void setClickedIcon(){super.setIcon(selectedIcon);}

    public void draggedAction(Graphics2D g2, Point2D start, Point2D end) {
        //Changes thickness of line
        g2.draw(new Line2D.Float(start,end));

    }

}
