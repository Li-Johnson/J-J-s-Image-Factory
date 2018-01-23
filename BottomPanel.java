import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.colorchooser.*;
public class BottomPanel extends JPanel implements ActionListener,ChangeListener{
    private Window myWindow;
    private JLabel info;
    private JButton cc;
    private Color color;
    private JSlider thickness;
    private JCheckBox sharpnessType;

    public BottomPanel(Window window){
        // 100,1 is the padding horizontal,vertical
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 1));
        myWindow = window;
        color = Color.black;

        //Color Button
        cc = new JButton("Color");
        cc.addActionListener(this);
        cc.setOpaque(false);
        cc.setContentAreaFilled(false);
        cc.setBorderPainted(false);
        cc.setFocusPainted(false);
        cc.setIcon(new ImageIcon("icons/color.png"));
        add(cc);

        //Information on the current tool
        info = new JLabel();
        this.add(info, FlowLayout.LEFT);

        //Thickness Slider (puts it in a section so the label can be with the actual slider)
        JComponent thicknessSection = new JPanel();
        thicknessSection.setLayout(new BoxLayout(thicknessSection, BoxLayout.PAGE_AXIS));
        JLabel sliderLabel = new JLabel("Thickness", JLabel.CENTER);
        sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        thicknessSection.add(sliderLabel);
        thickness = new JSlider(JSlider.HORIZONTAL,0, 50, 5);
        thickness.addChangeListener(this);
        thickness.setMajorTickSpacing(10);
        thickness.setMinorTickSpacing(1);
        thickness.setPaintTicks(true);
        thickness.setPaintLabels(true);
        thicknessSection.add(thickness);
        add(thicknessSection);

        //Sharpness JCheckBox
        sharpnessType = new JCheckBox("Sharp Shapes");
        sharpnessType.addActionListener(this);
        add(sharpnessType);

    }

    //sets the info text for each tool onto the JLabel
    public void setInfoText(String text){
        info.setText(text);
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Color":
                Color temp = color;
                color = JColorChooser.showDialog(null, "Select your color", color);
                //If they click cancel or the x button
                if (color == null) {
                    color = temp;
                }
                myWindow.getToolsManager().setColor(color);
                break;
            case "Sharp Shapes":
                //Changes whether it is sharp or not
                if(sharpnessType.isSelected()){
                    myWindow.getToolsManager().setSharpness(BasicStroke.JOIN_MITER);
                } else {
                    myWindow.getToolsManager().setSharpness(BasicStroke.JOIN_ROUND);
                }
                break;
        }
    }
    //whenever slider is slid, the thickness will be set to the new value.
    public void stateChanged(ChangeEvent e){
        JSlider source = (JSlider) e.getSource();
        myWindow.getToolsManager().setThickness((int)source.getValue());
    }





}
