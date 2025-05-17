package tools.shapes;

import functions.Strokes;
import panels.PaintCanvas;
import tools.ToolType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * RectangleShape is a tool button that allows users to draw rectangles on the PaintCanvas.
 * It handles tool selection, shape drawing, and final rendering.
 */
public class RectangleShape extends JButton implements ActionListener, Strokes, Serializable {

    private transient PaintCanvas paintCanvas;
    private Point startPoint;
    private Point endPoint;
    private Color color;
    private int thickness;
    private transient ImageIcon icon = new ImageIcon("res//rectangle.png");
    private transient Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    private transient ImageIcon scaledIcon = new ImageIcon(scaledImage);

    /**
     * Creates a RectangleShape button with a rectangle icon, sets its size, appearance,
     * tooltip, and adds a click listener to toggle the rectangle drawing mode.
     *
     * @param paintCanvas The canvas where the rectangle will be drawn.
     */
    public RectangleShape(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;


        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setToolTipText("Rectangle");

        setBounds(25,470,30,30);
        setBackground(new Color(245, 235, 215));
        setVisible(true);

        setIcon(scaledIcon);
        addActionListener(this);
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    /**
     * Handles button clicks to toggle rectangle tool mode on the canvas.
     *
     * @param e The action event triggered by clicking the button.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (paintCanvas.getCurrentTool() == ToolType.RECTANGLE) {
            paintCanvas.setToolMode(ToolType.NULL);
        }else{
            paintCanvas.setToolMode(ToolType.RECTANGLE);
        }
    }

    /**
     * Draws the rectangle from the start to end point using the selected color and stroke thickness.
     *
     * @param g2d The graphics context to draw the rectangle on.
     */
    @Override
    public void draw(Graphics2D g2d) {
        if (startPoint != null && endPoint != null) {
            int x = Math.min(startPoint.x, endPoint.x);
            int y = Math.min(startPoint.y, endPoint.y);
            int width = Math.abs(endPoint.x - startPoint.x);
            int height = Math.abs(endPoint.y - startPoint.y);

            g2d.setColor(color);
            g2d.setStroke(new BasicStroke(thickness));
            g2d.drawRect(x, y, width, height);
        }
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Finalizes the rectangle drawing by setting the end point and rendering it onto the canvas image.
     *
     * @param endPoint The end point where the mouse was released.
     * @param canvasImage The image where the rectangle will be permanently drawn.
     */
    public void finishRectangle(Point endPoint, BufferedImage canvasImage){
        setEndPoint(endPoint);
        Graphics2D g2d = canvasImage.createGraphics();
        draw(g2d);
        g2d.dispose();
    }
}
