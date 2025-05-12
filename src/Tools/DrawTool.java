package Tools;

import Functions.Strokes;
import Panels.PaintCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;


public class DrawTool extends JButton implements ActionListener, Strokes, Serializable {

    private transient ImageIcon icon = new ImageIcon("drawTool.png");
    private transient PaintCanvas paintCanvas;
    private ArrayList<Point> points = new ArrayList<>();
    private int thickness;
    private Color color;
    private transient Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    private transient ImageIcon scaledIcon = new ImageIcon(scaledImage);

    public DrawTool(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;


        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setToolTipText("Draw Tool");

        setBounds(25,50,30,30);
        setBackground(new Color(245, 235, 215));
        setVisible(true);

        addActionListener(this);
        setIcon(scaledIcon);
    }

    public DrawTool() {}

    public void drawCursor(){
        paintCanvas.setCursor(getToolkit().createCustomCursor(icon.getImage(), new Point(0, 31), "cursor"));
    }


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



    public void addPoint(Point p) {
        points.add(p);
    }

    public void handDraw(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(thickness));
        g2d.setColor(color);
        for (int i = 1; i < points.size(); i++) {
            Point p1 = points.get(i - 1);
            Point p2 = points.get(i);
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }



    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void draw(Graphics2D g2d) {
        handDraw(g2d);
    }

}
