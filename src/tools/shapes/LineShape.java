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

public class LineShape extends JButton implements ActionListener,Strokes, Serializable {

    private transient PaintCanvas paintCanvas;
    private Point startPoint;
    private Point endPoint;
    private Color color;
    private int thickness;
    private transient ImageIcon icon = new ImageIcon("straightLine.png");
    private transient Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    private transient ImageIcon scaledIcon = new ImageIcon(scaledImage);

    public LineShape(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;


        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setToolTipText("line");

        setBounds(25,410,30,30);
        setBackground(new Color(245, 235, 215));
        setVisible(true);

        setIcon(scaledIcon);
        addActionListener(this);
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public void finishDrawing(Point endPoint, BufferedImage canvasImage) {
        setEndPoint(endPoint);
        Graphics2D g2d = canvasImage.createGraphics();
        draw(g2d);
        g2d.dispose();
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (paintCanvas.getCurrentTool() == ToolType.LINE) {
            paintCanvas.setToolMode(ToolType.NULL);
        } else {
            paintCanvas.setToolMode(ToolType.LINE);
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (startPoint != null && endPoint != null) {
            g2d.setColor(color);
            g2d.setStroke(new BasicStroke(thickness));
            g2d.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
        }
    }
}
