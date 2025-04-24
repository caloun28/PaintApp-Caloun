package Tools;

import Panels.PaintCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class FillTool extends JButton implements ActionListener {
    private ImageIcon icon = new ImageIcon("fillTool.png");
    private PaintCanvas paintCanvas;
    private Image scaledImage = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    private ImageIcon scaledIcon = new ImageIcon(scaledImage);

    public FillTool(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;


        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setToolTipText("Fill Tool");

        setBounds(170, 25, 20, 20);
        setBackground(new Color(245, 235, 215));
        setVisible(true);

        addActionListener(this);
        setIcon(scaledIcon);
    }

    public ImageIcon getScaledIcon() {
        return scaledIcon;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            if (paintCanvas.getCurrentTool() == ToolType.FILL) {
                paintCanvas.setToolMode(ToolType.NONE);
            } else {
                paintCanvas.setToolMode(ToolType.FILL);
            }
        }
    }


    /**
     * Recursively fills all connected pixels of the same color (oldColor) with the newColor,
     * starting from the specified (x, y) position.
     * I used ChatGPT for a hint - https://i.postimg.cc/y6mxBZCy/rada.png
     * @param x The x-coordinate of the starting pixel.
     * @param y The y-coordinate of the starting pixel.
     * @param oldColor The color to be replaced.
     * @param newColor The color to replace oldColor.
     */
    public void fill(int x, int y, int oldColor, int newColor) {
        BufferedImage canvasImage = paintCanvas.getCanvasImage();


        if (x < 0 || y < 0 || x >= paintCanvas.getWidth() || y >= paintCanvas.getHeight()) return;

        if (canvasImage.getRGB(x, y) != oldColor) return;

        if (oldColor == newColor) return;

        LinkedList<Point> points = new LinkedList<>();
        points.add(new Point(x, y));

        while (!points.isEmpty()) {
            Point point = points.poll();
            int px = point.x;
            int py = point.y;

            if (px < 0 || py < 0 || px >= paintCanvas.getWidth() || py >= paintCanvas.getHeight()) continue;

            if (canvasImage.getRGB(px, py) != oldColor) continue;

            canvasImage.setRGB(px, py, newColor);


            points.add(new Point(px - 1, py));
            points.add(new Point(px + 1, py));
            points.add(new Point(px, py - 1));
            points.add(new Point(px, py + 1));
        }


        paintCanvas.repaint();
    }
}
