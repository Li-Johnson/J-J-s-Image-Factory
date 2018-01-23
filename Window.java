import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;


public class Window extends JFrame{
  //List of all the major components
    private ImagePanel imagePanel;
    private ToolsManager toolsPanel;
    private TopMenu topBar;
    private BottomPanel bottomPanel;

    //Window setup - size and title
    public Window() {
        //sets an icon for the application
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons/icon.png"));
        ImageIcon icon = new ImageIcon(image);
        setIconImage(icon.getImage());

        this.setTitle("J&J's Image Factory");
        this.setSize(1280,720);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        //Creates panel to load image
        imagePanel = new ImagePanel(this);
        this.add(imagePanel,BorderLayout.CENTER);

        //Creates the bar on the top
        topBar = new TopMenu(this);
        this.setJMenuBar(topBar);

        //Creates panel to hold all the tool buttons
        toolsPanel = new ToolsManager(this);
        this.add(toolsPanel,BorderLayout.LINE_END);

        //Creates a panel to hold all the color changes;
        bottomPanel = new BottomPanel(this);
        this.add(bottomPanel,BorderLayout.PAGE_END);

        //makes window open in the middle.
        setLocationRelativeTo(null);
    }

    public String toString(){
        return "J&J's Image Factory";
    }





    //getters
    public int getImagePanelWidth(){return imagePanel.getWidth();}
    public int getImagePanelHeight(){return imagePanel.getHeight();}
    public TopMenu getTopBar(){return topBar;}
    public ImagePanel getImagePanel(){return imagePanel;}
    public ToolsManager getToolsManager(){return toolsPanel;}
    public BottomPanel getBottomPanel(){return bottomPanel;}
    //public FilterManager getFilterManager(){return topBar.getFilterManager();}

}
