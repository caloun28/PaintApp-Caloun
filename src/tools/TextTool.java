package tools;

import panels.PaintCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextTool extends JButton implements ActionListener {
    private PaintCanvas paintCanvas;

    public TextTool(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;

        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setToolTipText("Ellipse");

        setBounds(25,510,30,30);
        setBackground(new Color(245, 235, 215));
        setVisible(true);

        addActionListener(this);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (paintCanvas.getCurrentTool() == ToolType.TEXT){
            paintCanvas.setToolMode(ToolType.NULL);
        }else{
            paintCanvas.setToolMode(ToolType.TEXT);
        }
    }
}
