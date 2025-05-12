package Tools;

import Panels.PaintCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveTool extends JButton implements ActionListener {

    private PaintCanvas paintCanvas;
    private ImageIcon icon = new ImageIcon("saveTool.png");
    private Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    private ImageIcon scaledIcon = new ImageIcon(scaledImage);

    public SaveTool(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;

        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setToolTipText("Save");
        setIcon(scaledIcon);

        setBounds(7,10,30,30);
        setBackground(new Color(245, 235, 215));
        setVisible(true);

        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        paintCanvas.saveDrawing("drawing.dat");
    }
}
