package tools;

import panels.PaintCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadTool extends JButton implements ActionListener {

    private PaintCanvas paintCanvas;
    private ImageIcon icon = new ImageIcon("loadTool.png");
    private Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    private ImageIcon scaledIcon = new ImageIcon(scaledImage);

    public LoadTool(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;

        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setToolTipText("Load");
        setIcon(scaledIcon);

        setBounds(43,10,30,30);
        setBackground(new Color(245, 235, 215));
        setVisible(true);

        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        paintCanvas.loadDrawing("drawing.dat");
    }
}
