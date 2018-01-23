import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import java.awt.event.*;
import java.awt.geom.*;

public class ToolsManager extends JPanel implements ActionListener{
    private Window myWindow;
    private ImagePanel myImagePanel;
    private TopMenu myTopBar;
    private Tool currentlyUsing,defaultTool,pencil,eraser,paintbrush,rectangle,ellipse,paintbucket;
    private JButton resetImage;
    private Color color = Color.black;
    private int thickness = 5;
    private int sharpness = BasicStroke.JOIN_ROUND;


    public ToolsManager(Window window) {
        myWindow = window;
        myImagePanel = myWindow.getImagePanel();
        myTopBar = myWindow.getTopBar();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        //Add all the tools
        defaultTool = new DefaultTool(myWindow, this);
        pencil = new Pencil(myWindow, this);
        paintbrush = new Paintbrush(myWindow, this);
        rectangle = new RectangleTool(myWindow, this);
        ellipse = new EllipseTool(myWindow, this);
        eraser = new Eraser(myWindow, this);
        paintbucket = new PaintBucket(myWindow, this);
        resetImage = new JButton();
        this.currentlyUsing = defaultTool;
        this.add(resetImage);
        resetImage.addActionListener(this);

        //change look of reset image button
        resetImage.setOpaque(false);
        resetImage.setContentAreaFilled(false);
        resetImage.setBorderPainted(false);
        resetImage.setFocusPainted(false);
        resetImage.setIcon(new ImageIcon("icons/reset.png"));
    }

    //a class to set a default, makes it so it does nothing until tool is pressed
    private class DefaultTool extends Tool{
        final ImageIcon icon = new ImageIcon("icons/cursor.png");
        final ImageIcon selectedIcon = new ImageIcon("icons/cursor-selected.png");

        public DefaultTool(Window window, ToolsManager manage) {
            super(window,manage,"Reset Tool");
            setIcon(icon);
        }

        public void resetIcon(){super.setIcon(icon);}
        public void setClickedIcon(){super.setIcon(selectedIcon);}

        public void clickedAction(Graphics2D g2, Point2D start){}
        public void draggedAction(Graphics2D g2,Point2D start, Point2D end) {}
    }

    public void draggedAction(Graphics2D g2,Point2D start, Point2D end) {
        //update color because some tools change color (eraser)
        g2.setPaint(color);
        currentlyUsing.draggedAction(g2,start,end);
    }

    public void clickedAction(Graphics2D g2, Point2D start) {
        g2.setPaint(color);
        g2.setStroke(new BasicStroke(thickness,BasicStroke.CAP_ROUND,sharpness));
        currentlyUsing.clickedAction(g2,start);
    }
    public void releasedAction(Graphics2D g2, Point2D end) {
        g2.setPaint(color);
        g2.setStroke(new BasicStroke(thickness,BasicStroke.CAP_ROUND,sharpness));
        currentlyUsing.releasedAction(g2,end);
    }
    public void setCurrentlyUsing(Tool tool) {currentlyUsing = tool;}
    public void setThickness(int size){thickness = size;}
    public void setColor(Color color) {this.color = color;}
    public void setSharpness(int sharpness){this.sharpness = sharpness;}
    public Color getColor() {return color;}

    //Resets all icons on the tools.
    public void resetIcons(){
        pencil.resetIcon();
        paintbrush.resetIcon();
        eraser.resetIcon();
        rectangle.resetIcon();
        ellipse.resetIcon();
        defaultTool.resetIcon();
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        currentlyUsing.resetIcon();
        /*
        *Whenever a tool is selects, the currentlyUsing tool will be set to that tool.
        *After a tool gets selected, the icon will be changed to one that shows it's selected.
        */
        switch (e.getActionCommand()) {
            case "Pencil":
                currentlyUsing = pencil;
                break;
            case "Reset Tool":
                currentlyUsing = defaultTool;
                break;
            case "Eraser":
            	currentlyUsing = eraser;
            	break;
            case "Paintbrush":
                currentlyUsing = paintbrush;
                break;
            case "Rectangle Tool":
                currentlyUsing = rectangle;
                break;
            case "Ellipse Tool":
                currentlyUsing = ellipse;
                break;
            case "Paint Bucket":
                currentlyUsing = paintbucket;
                break;
        }
        if (e.getSource() == resetImage){
            try {
                BufferedImage image = ImagePanel.makeImage(myTopBar.getPath());
                myImagePanel.changeImage(image);
                myImagePanel.setGraphics((Graphics2D) image.getGraphics());
            } catch (NullPointerException e1) {
                myImagePanel.clearImage();
            }
        }

        //Updates the information about the currently selected tool
        myWindow.getBottomPanel().setInfoText(currentlyUsing.getInfo());
        currentlyUsing.setClickedIcon();

    }

}
