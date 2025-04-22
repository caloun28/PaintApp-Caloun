package Panels;

import Functions.Strokes;
import Tools.DrawTool;
import Tools.EraserTool;
import Tools.ToolType;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;


public class PaintCanvas extends JPanel implements MouseListener, MouseMotionListener {

    private Point lastPoint;
    private ToolType currentTool = ToolType.NONE;
    private DrawTool drawStroke = null;
    private EraserTool eraseStroke = null;
    private ArrayList<Strokes> strokes = new ArrayList<>();
    private boolean drawing = false;
    private boolean resize = false;
    private boolean erasing = false;
    private int lineThickness = 3;

    public PaintCanvas() {
        setBackground(Color.WHITE);
        setBounds(10, 90, 1740, 750);
        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        setVisible(true);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void setToolMode(ToolType tool) {
        this.currentTool = tool;
    }

    public ToolType getCurrentTool() {
        return currentTool;
    }

    public void setLineThickness(int thickness) {
        this.lineThickness = thickness;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        switch (currentTool) {
            case DRAW:
                drawing = true;
                drawStroke = new DrawTool(this);
                drawStroke.setThickness(lineThickness);
                drawStroke.addPoint(e.getPoint());
                strokes.add(drawStroke);
                break;

            case ERASER:
                erasing = true;
                eraseStroke = new EraserTool(this);
                eraseStroke.setThickness(lineThickness);
                eraseStroke.addPoint(e.getPoint());
                strokes.add(eraseStroke);
                break;


        }


        if (e.getX() >= getWidth() - 20 && e.getY() >= getHeight() - 20) {
            drawing = false;
            erasing = false;
            resize = true;
            lastPoint = e.getPoint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        resize = false;
        drawing = false;
        erasing = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        switch (currentTool) {

            case DRAW:
                if (drawing && drawStroke != null) {
                    drawStroke.addPoint(e.getPoint());
                    repaint();
                }
                break;

            case ERASER:
                if (erasing && eraseStroke != null) {
                    eraseStroke.addPoint(e.getPoint());
                    repaint();
                }
                break;
        }

        if (resize) {
            drawing = false;
            int newW = getWidth() + (e.getX() - lastPoint.x);
            int newH = getHeight() + (e.getY() - lastPoint.y);

            if (newW > 200 && newW < 1905 && newH > 200 && newH < 960) {
                setBounds(10, 90, newW, newH);
                revalidate();
                repaint();
                lastPoint = e.getPoint();
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (e.getX() >= getWidth() - 20 && e.getY() >= getHeight() - 20) {
            setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
        } else {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GRAY);

        int size = 10;
        int x = getWidth() - size;
        int y = getHeight() - size;

        g2d.fillRect(x, y, size, size);

        g2d.setStroke(new BasicStroke(lineThickness));

        for (Strokes stroke : strokes) {
            stroke.draw(g2d);
        }
    }
}
