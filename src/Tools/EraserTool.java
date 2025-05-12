package Tools;

import Functions.Strokes;
import Panels.PaintCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class EraserTool extends JButton implements ActionListener, Strokes {
    private ImageIcon eraserIcon = new ImageIcon("eraserTool.png");
    private PaintCanvas paintCanvas;
    private ArrayList<Point> points = new ArrayList<>();
    private int thickness;
    private Image scaledImage = eraserIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    private ImageIcon scaledIcon = new ImageIcon(scaledImage);

    public EraserTool(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;

        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setToolTipText("Eraser");
        setIcon(scaledIcon);

        setBounds(25,160,30,30);
        setBackground(new Color(245, 235, 215));
        setVisible(true);

        addActionListener(this);

    }

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
