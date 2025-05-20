package tools;

import panels.PaintCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * FillTool represents a fill (bucket) tool button that allows the user
 * to fill connected areas of the canvas with a selected color.
 * It changes the cursor to a fill bucket icon and implements a flood fill algorithm.
 */
public class FillTool extends JButton implements ActionListener {
    private ImageIcon icon = new ImageIcon("res//fillTool.png");
    private PaintCanvas paintCanvas;
    private Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    private ImageIcon scaledIcon = new ImageIcon(scaledImage);

    /**
     * Constructs a FillTool button tied to the given PaintCanvas.
     * Initializes the button icon, appearance, and action listener.
     *
     * @param paintCanvas The canvas to apply fill operations on.
     */
    public FillTool(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;


        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setToolTipText("Fill Tool");

        setBounds(25, 304, 30, 30);
        setBackground(new Color(245, 235, 215));
        setVisible(true);

        addActionListener(this);
        setIcon(scaledIcon);
    }

    /**
     * Sets the cursor on the PaintCanvas to a custom fill bucket cursor.
     */
    public void fillCursor(){
        paintCanvas.setCursor(getToolkit().createCustomCursor(icon.getImage(), new Point(16, 0), ""));
    }

    /**
     * Toggles the fill tool activation state when the button is clicked.
     * Activates fill mode or resets to null tool mode.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            if (paintCanvas.getCurrentTool() == ToolType.FILL) {
                paintCanvas.setToolMode(ToolType.NULL);
            } else {
                paintCanvas.setToolMode(ToolType.FILL);
            }
        }
    }


    /**
     * Recursively fills all connected pixels of the same color (oldColor) with the newColor,
     * starting from the specified (x, y) position.
     * I used ChatGPT for a hint - https://i.postimg.cc/j5tgmmnk/help.png
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
