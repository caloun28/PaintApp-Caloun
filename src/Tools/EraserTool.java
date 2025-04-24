package Tools;

import Functions.Strokes;
import Panels.PaintCanvas;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EraserTool extends JButton implements ActionListener, Strokes {
    private ImageIcon eraserIcon = new ImageIcon("eraserTool.png");
    private PaintCanvas paintCanvas;
    private ArrayList<Point> points = new ArrayList<>();
    private int thickness;
    private Image scaledImage = eraserIcon.getImage().getScaledInstance(10, 20, Image.SCALE_SMOOTH);
    private ImageIcon scaledIcon = new ImageIcon(scaledImage);

    public EraserTool(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;

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

    public Image getScaledImage() {
        return scaledImage;
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

    @Override
    public void setColor(Color color) {
    }
}
