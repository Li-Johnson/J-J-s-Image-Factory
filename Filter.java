import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import java.awt.event.*;

//abstract class for all filters
public abstract class Filter extends JMenuItem{
    private Window myWindow;
    private ImagePanel myImagePanel;
    private FilterManager myFilterManager;
    public Filter(Window window, FilterManager filterManage, String name) {
        myWindow = window;
        myImagePanel = myWindow.getImagePanel();
        myFilterManager = filterManage;
        this.setText(name);
        addActionListener(myFilterManager);
        myFilterManager.add(this);

    }
    public abstract void doAction(BufferedImage img);

}
