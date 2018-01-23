import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import java.awt.event.*;

public class FilterManager extends JMenu implements ActionListener{

    private Window myWindow;
    private Filter grayscale, invert, edge;
    private ImagePanel myImagePanel;
    private BufferedImage img;

    public FilterManager(Window window){
        myWindow = window;
        myImagePanel = myWindow.getImagePanel();
        img = myImagePanel.getImage();
        this.setText("Filters");
        grayscale = new Grayscale(myWindow, this);
        invert = new InvertColors(myWindow, this);
        edge = new EdgeDetect(myWindow, this);
    }

    public void actionPerformed(ActionEvent e){
        //updates image (incase user changes image)
        img = myImagePanel.getImage();
        if (e.getSource() == grayscale){
            grayscale.doAction(img);
        }
        if (e.getSource() == invert){
            invert.doAction(img);
        }
        if (e.getSource() == edge){
            edge.doAction(img);
        }
        //remakes the image whenever a filter is applied.
        myImagePanel.revalidate();
        myImagePanel.repaint();
    }

}
