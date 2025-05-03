package Tools;

import Panels.PaintCanvas;

import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RedoTool extends JButton implements ActionListener {
    private PaintCanvas paintCanvas;
    private ImageIcon icon = new ImageIcon("redoTool.png");
    private Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    private ImageIcon scaledIcon = new ImageIcon(scaledImage);

    public RedoTool(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;

        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setToolTipText("Redo");
        setBounds(43,10,30,30);
        setBackground(new Color(245, 235, 215));
        setVisible(true);

        setIcon(scaledIcon);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            if(paintCanvas.getCurrentTool() == ToolType.REDO){
                paintCanvas.setToolMode(ToolType.NONE);
            }else{
                paintCanvas.setToolMode(ToolType.REDO);
            }
        }
    }
}
