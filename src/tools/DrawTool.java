package tools;

import functions.Strokes;
import panels.PaintCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * DrawTool represents a freehand drawing tool button with an icon.
 * Users can draw on the PaintCanvas by adding points which are connected with lines.
 * Supports setting color and stroke thickness.
 * Implements the Strokes interface for drawing functionality.
 */
public class DrawTool extends JButton implements ActionListener, Strokes, Serializable {

    private transient ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/drawTool.png")));
    private transient PaintCanvas paintCanvas;
    private ArrayList<Point> points = new ArrayList<>();
    private int thickness;
    private Color color;
    private transient Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    private transient ImageIcon scaledIcon = new ImageIcon(scaledImage);

    /**
     * Constructs a DrawTool button linked to the specified PaintCanvas.
     * Initializes the icon, button appearance, and action listener.
     *
     * @param paintCanvas The canvas on which drawing occurs.
     */
    public DrawTool(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;

        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setToolTipText("Draw Tool");

        setBounds(25,204,30,30);
        setBackground(new Color(245, 235, 215));
        setVisible(true);

        addActionListener(this);
        setIcon(scaledIcon);
    }

    /**
     * Sets a custom cursor representing the draw tool icon on the PaintCanvas.
     */
    public void drawCursor(){
        paintCanvas.setCursor(getToolkit().createCustomCursor(icon.getImage(), new Point(0, 31), "cursor"));
    }

    /**
     * Handles button click to toggle the draw tool selection on the PaintCanvas.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            if (paintCanvas.getCurrentTool() == ToolType.DRAW) {
                paintCanvas.setToolMode(ToolType.NULL);
            } else {
                paintCanvas.setToolMode(ToolType.DRAW);
            }
        }
    }

    /**
     * Adds a new point to the current drawing stroke.
     *
     * @param p The point to add.
     */
    public void addPoint(Point p) {
        points.add(p);
    }

    /**
     * Draws the freehand stroke by connecting all added points with lines.
     *
     * @param g2d The graphics context to draw on.
     */
    public void handDraw(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(thickness));
        g2d.setColor(color);
        for (int i = 1; i < points.size(); i++) {
            Point p1 = points.get(i - 1);
            Point p2 = points.get(i);
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Draws the current stroke using the graphics context.
     *
     * @param g2d The graphics context.
     */
    @Override
    public void draw(Graphics2D g2d) {
        handDraw(g2d);
    }

}
