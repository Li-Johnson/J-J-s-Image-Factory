import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.filechooser.*;
import java.util.Properties;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TopMenu extends JMenuBar implements ActionListener {
    //directory field for the filechooser
    private String directory = System.getProperty("user.home");
    private Window myWindow;
    private JMenu file;
    private FilterManager filters;
    private JMenuItem openFile, saveFile;
    private File imgFile;
    private BufferedImage bufferedImg;
    private JMenu help;
    private JMenuItem info;
    private BufferedImage image;
    private JFrame infoPanel;
    private String path;

    //filter for filechooser
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png");

    public TopMenu(Window window) {
        myWindow = window;
        help = new JMenu();
        info = new JMenuItem();
        help.setText("Help");
        info.setText("Info");
        filters = new FilterManager(myWindow);
        file = new JMenu("File");
        openFile = new JMenuItem("open");
        saveFile = new JMenuItem("save");
        openFile.setText("Open");
        saveFile.setText("Save As");
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        info.addActionListener(this);
        this.add(file);
        this.add(filters);
        this.add(help);
        file.add(openFile);
        file.add(saveFile);
        help.add(info);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == openFile){
            //sets the opening directory as the Desktop
            JFileChooser openFile = new JFileChooser(directory + "/Desktop");
            //makes it so only image files can be selected
            openFile.setFileFilter(filter);
            int result = openFile.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION){
                //makes a new buffered image of selected image file and opens it into imagepanel.
                imgFile = openFile.getSelectedFile();
                path = imgFile.getPath();
                image = ImagePanel.makeImage(path);
                myWindow.getImagePanel().changeImage(image);
                myWindow.getImagePanel().setGraphics((Graphics2D) image.getGraphics());
            }
        }
        if (e.getSource() == saveFile){
            //saves the current graphics into a selected file
            JFileChooser saveFile = new JFileChooser(directory + "/Desktop");
            saveFile.setFileFilter(filter);
            int result = saveFile.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION){
                //bufferedImg = new BufferedImage(myWindow.getImagePanelWidth(), myWindow.getImagePanelHeight(), BufferedImage.TYPE_INT_RGB);
                bufferedImg = myWindow.getImagePanel().getBufferedImage();
                myWindow.getImagePanel().paint(bufferedImg.getGraphics());
                try{
                    ImageIO.write(bufferedImg, "png", saveFile.getSelectedFile());
                } catch(IOException ex) {
                    System.out.println("Error saving file.");
              }
            }
        }
        //Opens a new JFrame when the info button is pressed.
        if (e.getSource() == info){
            infoPanel = new JFrame("J & J's Image Factory");
            infoPanel.add(new JLabel(getFiletext("info.txt")));
            infoPanel.setSize(new Dimension(875, 150));
            infoPanel.setVisible(true);
            infoPanel.setLocationRelativeTo(null);
        }
    }

    //gets the text from a file and adds new lines for formatting in a JLabel.
    public String getFiletext(String filepath){
        String text = "<html>";
        try {
            Scanner sc = new Scanner(new File(filepath));
            while (sc.hasNext()){
                String line = sc.nextLine();
                text += line + "<br>";
                //System.out.println(sc.nextLine());
            }
            text += "</html>";
        } catch(FileNotFoundException e){
            System.out.println("File not found: " + filepath);
            System.exit(1);
        }
        return text;
    }



    //getter for other classes to know what Window is.
    public String getPath(){return path;}
    public Window getWindow(){return myWindow;}
    public FilterManager getFilterManager() {return filters;}
    public BufferedImage getImage() {return image;}
}
