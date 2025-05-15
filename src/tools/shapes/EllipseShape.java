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

public class EllipseShape extends JButton implements ActionListener, Strokes, Serializable {

    private transient PaintCanvas paintCanvas;
    private Point startPoint;
    private Point endPoint;
    private Color color;
    private int thickness;
    private transient ImageIcon icon = new ImageIcon("ellipse.png");
    private transient Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    private transient ImageIcon scaledIcon = new ImageIcon(scaledImage);

    public EllipseShape(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;


        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setToolTipText("Ellipse");

        setBounds(25,510,30,30);
        setBackground(new Color(245, 235, 215));
        setVisible(true);

        setIcon(scaledIcon);
        addActionListener(this);
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }


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

    public void setStart(Point start) {
        this.startPoint = start;
        this.endPoint = start;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (paintCanvas.getCurrentTool() == ToolType.ELLIPSE) {
            paintCanvas.setToolMode(ToolType.NULL);
        }else{
            paintCanvas.setToolMode(ToolType.ELLIPSE);
        }
    }

    public void finishEllipse(Point endPoint, BufferedImage canvasImage) {
        setEndPoint(endPoint);
        Graphics2D g2d = canvasImage.createGraphics();
        draw(g2d);
        g2d.dispose();
    }

}
