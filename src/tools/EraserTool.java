package tools;

import functions.Strokes;
import panels.PaintCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * EraserTool allows users to erase drawn content by making pixels transparent.
 * Implements a custom cursor, stroke-based erasing, and integrates with PaintCanvas.
 */
public class EraserTool extends JButton implements ActionListener, Strokes {
    private ImageIcon eraserIcon = new ImageIcon("res//eraserTool.png");
    private PaintCanvas paintCanvas;
    private ArrayList<Point> points = new ArrayList<>();
    private int thickness;
    private Image scaledImage = eraserIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    private ImageIcon scaledIcon = new ImageIcon(scaledImage);

    /**
     * Creates the eraser tool button and initializes its appearance and behavior.
     *
     * @param paintCanvas The canvas on which this tool will operate.
     */
    public EraserTool(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;

        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setToolTipText("Eraser");
        setIcon(scaledIcon);

        setBounds(25,254,30,30);
        setBackground(new Color(245, 235, 215));
        setVisible(true);

        addActionListener(this);

    }

    /**
     * Sets a custom square eraser cursor at the center of the pointer.
     */
    public void eraserCursor(){
        int cursorSize = 32;
        int squareSize = 8;

        BufferedImage cursorImage = new BufferedImage(cursorSize, cursorSize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = cursorImage.createGraphics();

        int x = (cursorSize - squareSize) / 2;
        int y = (cursorSize - squareSize) / 2;

        g2d.setColor(Color.WHITE);
        g2d.fillRect(x,y,squareSize,squareSize);

        g2d.setColor(Color.BLACK);
        g2d.drawRect(x,y,squareSize-1,squareSize-1  );

        g2d.dispose();

        paintCanvas.setCursor(getToolkit().createCustomCursor(cursorImage, new Point(cursorSize/2, cursorSize/2), "Eraser"));
    }

    /**
     * Handles clicks on the eraser button to activate or deactivate the eraser tool.
     *
     * @param e The action event triggered by the button click.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            if (paintCanvas.getCurrentTool() == ToolType.ERASER) {
                paintCanvas.setToolMode(ToolType.NULL);
            } else {
                paintCanvas.setToolMode(ToolType.ERASER);
            }
        }
    }


    /**
     * Adds a point to the eraser stroke path.
     *
     * @param p Point to be added.
     */
    public void addPoint(Point p) {
        points.add(p);
    }


    /**
     * Erases part of the canvas by drawing transparent strokes between collected points.
     * Uses 'AlphaComposite.Clear' to make pixels fully transparent along the path.
     * Help - https://i.postimg.cc/rFnhtSCH/eraser-Help.png
     * @param g2d Graphics2D context used for drawing the erase path.
     */
    public void handErase(Graphics2D g2d) {
        Composite originalComposite = g2d.getComposite();

        g2d.setComposite(AlphaComposite.Clear);
        g2d.setStroke(new BasicStroke(thickness));
        for (int i = 1; i < points.size(); i++) {
            Point p1 = points.get(i - 1);
            Point p2 = points.get(i);
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        }

        g2d.setComposite(originalComposite);
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    /**
     * Draws the eraser path onto the canvas.
     *
     * @param g2d Graphics2D context used for drawing.
     */
    @Override
    public void draw(Graphics2D g2d) {
        handErase(g2d);
    }

    @Override
    public void setColor(Color color) {
    }
}
