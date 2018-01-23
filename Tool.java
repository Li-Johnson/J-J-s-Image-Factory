import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

//abstract class for all tools.
public abstract class Tool extends JButton{
    Window myWindow;
    ImagePanel imagePanel;
    ToolsManager manager;
    String toolInfo,toolName;
    ImageIcon icon,selectedIcon;

    public Tool(Window window, ToolsManager manager, String name){
        super();

        toolName = name;
        myWindow = window;
        this.manager = manager;
        imagePanel = myWindow.getImagePanel();
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        setActionCommand(name);
        addActionListener(manager);
        manager.add(this);
        

    }

    public abstract void resetIcon();
    //cant be setSelected icon becuase that's already a method for JButtons
    //but not every button has an actionListener; The whole JPanel has it.
    public abstract void setClickedIcon();

	//Not abstract because if the tool doesnt need it then it just does nothing
    public void clickedAction(Graphics2D g2, Point2D start){
        g2.draw(new Line2D.Double(start,start)); //When somebody clicked on the screen draw under the cursor
    }
    public void draggedAction(Graphics2D g2, Point2D start, Point2D end){}
    public void releasedAction(Graphics2D g2, Point2D end){}
    public String getInfo() {return toolInfo;}

}
