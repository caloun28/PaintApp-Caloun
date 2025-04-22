package Tools;

import Functions.Strokes;
import Panels.PaintCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EraserTool extends JButton implements ActionListener, Strokes {
    private ImageIcon drawIcon = new ImageIcon("eraserTool.png");
    private PaintCanvas paintCanvas;
    public EraserTool(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;

        Image scaledImage = drawIcon.getImage().getScaledInstance(11, 20, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setToolTipText("Eraser");
        setIcon(scaledIcon);

        setBounds(145,25,20,20);
        setBackground(new Color(245, 235, 215));
        setVisible(true);

        addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            if (paintCanvas.getCurrentTool() == ToolType.ERASER) {
                paintCanvas.setToolMode(ToolType.NONE);
            } else {
                paintCanvas.setToolMode(ToolType.ERASER);
            }
        }
    }

    private ArrayList<Point> points = new ArrayList<>();
    private int thickness;

    public void addPoint(Point p) {
        points.add(p);
    }

    public void handErase(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(thickness));
        g2d.setColor(Color.WHITE);
        for (int i = 1; i < points.size(); i++) {
            Point p1 = points.get(i - 1);
            Point p2 = points.get(i);
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    @Override
    public void draw(Graphics2D g2d) {
        handErase(g2d);
    }
}
