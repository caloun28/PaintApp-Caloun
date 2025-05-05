package Tools;

import Panels.PaintCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class DropperTool extends JButton implements ActionListener {
    private ImageIcon icon = new ImageIcon("dropperTool.png");
    private PaintCanvas paintCanvas;
    private Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    private ImageIcon scaledIcon = new ImageIcon(scaledImage);

    public DropperTool(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;


        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setToolTipText("Draw Tool");

        setBounds(25,200,30,30);
        setBackground(new Color(245, 235, 215));
        setVisible(true);

        addActionListener(this);
        setIcon(scaledIcon);
    }

    public void dropperCursor(){
        paintCanvas.setCursor(getToolkit().createCustomCursor(icon.getImage(), new Point(4, 31), "DropperCursor"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            if (paintCanvas.getCurrentTool() == ToolType.DROPPER) {
                paintCanvas.setToolMode(ToolType.NULL);
            } else {
                paintCanvas.setToolMode(ToolType.DROPPER);
            }
        }
    }

    public void findColor(int x, int y){
        BufferedImage canvasImage = paintCanvas.getCanvasImage();


        if (x < 0 || y < 0 || x >= paintCanvas.getWidth() || y >= paintCanvas.getHeight()) return;
        int rgb = canvasImage.getRGB(x, y);

        Color color = new Color(rgb, true);

        paintCanvas.setCurrentColor(color);
    }

}
