package Tools.Shapes;

import Functions.Strokes;
import Panels.PaintCanvas;
import Tools.ToolType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class RectangleShape extends JButton implements ActionListener, Strokes {

    private PaintCanvas paintCanvas;
    private Point startPoint;
    private Point endPoint;
    private Color color;
    private int thickness;
    private ImageIcon icon = new ImageIcon("rectangle.png");
    private Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    private ImageIcon scaledIcon = new ImageIcon(scaledImage);

    public RectangleShape(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;


        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setToolTipText("Color Palette");

        setBounds(25,400,30,30);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (paintCanvas.getCurrentTool() == ToolType.RECTANGLE) {
            paintCanvas.setToolMode(ToolType.NULL);
        }else{
            paintCanvas.setToolMode(ToolType.RECTANGLE);
        }
    }

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

    public void finishRectangle(Point endPoint, BufferedImage canvasImage){
        setEndPoint(endPoint);
        Graphics2D g2d = canvasImage.createGraphics();
        draw(g2d);
        g2d.dispose();
    }
}
