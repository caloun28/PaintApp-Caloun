package tools;

import panels.PaintCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorPalette extends JButton implements ActionListener {
    private PaintCanvas paintCanvas;
    private ImageIcon icon = new ImageIcon("colorPalette.png");

    public ColorPalette(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;
        Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setToolTipText("Color Palette");

        setBounds(25,310,30,30);
        setBackground(new Color(245, 235, 215));
        setVisible(true);

        setIcon(scaledIcon);
        addActionListener(this);
    }

    public void updateColor(Color color) {
        setBackground(color);
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            Color selectedColor = JColorChooser.showDialog(this, "Vyber barvu", Color.BLACK);
            if (selectedColor != null) {
                paintCanvas.setCurrentColor(selectedColor);
            }
        }
    }
}
