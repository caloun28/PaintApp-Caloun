package tools;

import panels.PaintCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * DropperTool represents an eyedropper button that allows users to pick colors
 * from the PaintCanvas. When activated, it changes the cursor to an eyedropper icon
 * and lets the user select a pixel color from the canvas.
 */
public class DropperTool extends JButton implements ActionListener {
    private ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/dropperTool.png")));
    private PaintCanvas paintCanvas;
    private Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    private ImageIcon scaledIcon = new ImageIcon(scaledImage);

    /**
     * Constructs a DropperTool button associated with the given PaintCanvas.
     * Initializes the button icon, appearance, and action listener.
     *
     * @param paintCanvas The canvas from which colors will be picked.
     */
    public DropperTool(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;


        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setToolTipText("Dropper Tool");

        setBounds(25,354,30,30);
        setBackground(new Color(245, 235, 215));
        setVisible(true);

        addActionListener(this);
        setIcon(scaledIcon);
    }

    /**
     * Sets the cursor on the PaintCanvas to a custom eyedropper cursor.
     */
    public void dropperCursor(){
        paintCanvas.setCursor(getToolkit().createCustomCursor(icon.getImage(), new Point(4, 31), "DropperCursor"));
    }

    /**
     * Handles the button click to toggle eyedropper tool activation.
     * When active, the PaintCanvas tool mode is set to DROPPER.
     */
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

    /**
     * Finds and sets the color at the specified (x, y) position on the canvas image.
     * If the coordinates are out of bounds, the method returns without changes.
     *
     * @param x The x-coordinate of the pixel.
     * @param y The y-coordinate of the pixel.
     */
    public void findColor(int x, int y){
        BufferedImage canvasImage = paintCanvas.getCanvasImage();


        if (x < 0 || y < 0 || x >= paintCanvas.getWidth() || y >= paintCanvas.getHeight()) return;
        int rgb = canvasImage.getRGB(x, y);

        Color color = new Color(rgb, true);

        paintCanvas.setCurrentColor(color);
    }

}
