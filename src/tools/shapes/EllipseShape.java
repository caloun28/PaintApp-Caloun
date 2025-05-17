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
 * EllipseShape is a tool button that allows users to draw ellipses on the PaintCanvas.
 * It handles user interactions, drawing logic, and tool state switching.
 */
public class EllipseShape extends JButton implements ActionListener, Strokes, Serializable {

    private transient PaintCanvas paintCanvas;
    private Point startPoint;
    private Point endPoint;
    private Color color;
    private int thickness;
    private transient ImageIcon icon = new ImageIcon("res//ellipse.png");
    private transient Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    private transient ImageIcon scaledIcon = new ImageIcon(scaledImage);

    /**
     * Creates an EllipseShape button, sets its icon, position, appearance, and registers the click listener.
     *
     * @param paintCanvas The canvas where the ellipse will be drawn.
     */
    public EllipseShape(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;


        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setToolTipText("Ellipse");

        setBounds(25,520,30,30);
        setBackground(new Color(245, 235, 215));
        setVisible(true);

        setIcon(scaledIcon);
        addActionListener(this);
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    /**
     * Draws the ellipse on the provided Graphics2D context.
     * @param g2d The graphics context used for drawing.
     */
    @Override
    public void draw(Graphics2D g2d) {
        if(startPoint != null && endPoint != null) {
            int x = Math.min(startPoint.x, endPoint.x);
            int y = Math.min(startPoint.y, endPoint.y);
            int width = Math.abs(startPoint.x - endPoint.x);
            int height = Math.abs(startPoint.y - endPoint.y);

            g2d.setColor(color);
            g2d.setStroke(new BasicStroke(thickness));
            g2d.drawOval(x, y, width, height);
        }
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    /**
     * Sets the starting point of the ellipse (mouse press).
     * @param start The starting point.
     */
    public void setStart(Point start) {
        this.startPoint = start;
        this.endPoint = start;
    }

    /**
     * Handles button click to toggle ellipse tool mode on the canvas.
     *
     * @param e The action event triggered by clicking the button.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (paintCanvas.getCurrentTool() == ToolType.ELLIPSE) {
            paintCanvas.setToolMode(ToolType.NULL);
        }else{
            paintCanvas.setToolMode(ToolType.ELLIPSE);
        }
    }

    /**
     * Finalizes the ellipse by drawing it on the given BufferedImage.
     *
     * @param endPoint     The point where the user released the mouse.
     * @param canvasImage  The canvas image to draw onto.
     */
    public void finishEllipse(Point endPoint, BufferedImage canvasImage) {
        setEndPoint(endPoint);
        Graphics2D g2d = canvasImage.createGraphics();
        draw(g2d);
        g2d.dispose();
    }

}
