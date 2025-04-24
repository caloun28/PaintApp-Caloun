package Panels;

import Functions.Strokes;
import Tools.DrawTool;
import Tools.EraserTool;
import Tools.FillTool;
import Tools.ToolType;

import javax.imageio.IIOImage;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class PaintCanvas extends JPanel implements MouseListener, MouseMotionListener {

    private Point lastPoint;
    private ToolType currentTool = ToolType.NONE;
    private DrawTool drawStroke;
    private EraserTool eraseStroke;
    private FillTool fillTool;
    private ArrayList<Strokes> strokes = new ArrayList<>();
    private boolean drawing = false;
    private boolean resize = false;
    private boolean erasing = false;
    private int lineThickness = 3;
    private Color currentColor = Color.BLACK;
    private BufferedImage canvasImage;

    public PaintCanvas() {

        setBackground(Color.WHITE);
        setBounds(10, 90, 1740, 750);
        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        setVisible(true);
        canvasImage = new BufferedImage(1740, 750, BufferedImage.TYPE_INT_ARGB);


        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void setToolMode(ToolType tool) {
        this.currentTool = tool;
    }

    public void setFillTool(FillTool fillTool) {
            this.fillTool = fillTool;
    }

    public void setDrawStroke(DrawTool drawStroke) {
        this.drawStroke = drawStroke;
    }

    public void setEraseStroke(EraserTool eraseStroke) {
        this.eraseStroke = eraseStroke;
    }

    public ToolType getCurrentTool() {
        return currentTool;
    }

    public void setLineThickness(int thickness) {
        this.lineThickness = thickness;
    }

    public BufferedImage getCanvasImage() {
        return canvasImage;
    }


    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
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
                drawStroke.setColor(currentColor);
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

            case FILL:
                if (fillTool != null) {
                    int oldColor = canvasImage.getRGB(e.getX(), e.getY());

                    int newColor = currentColor.getRGB();

                    fillTool.fill(e.getX(), e.getY(), oldColor, newColor);
                }
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
                    Graphics2D g2d = canvasImage.createGraphics();
                    drawStroke.draw(g2d);

                    g2d.dispose();
                    repaint();
                }
                break;

            case ERASER:
                if (erasing && eraseStroke != null) {
                    eraseStroke.addPoint(e.getPoint());
                    Graphics2D g2d = canvasImage.createGraphics();
                    eraseStroke.draw(g2d);

                    g2d.dispose();
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
            switch (currentTool){
                case FILL:
                    Point fillHotspot = new Point(0,26);
                    setCursor(getToolkit().createCustomCursor(fillTool.getScaledIcon().getImage(),fillHotspot,"fillCursor"));
                    break;
                case DRAW:
                    Point drawHotspot = new Point(0,31);
                    setCursor(getToolkit().createCustomCursor(drawStroke.getScaledIcon().getImage(),drawHotspot,"drawCursor"));
                    break;
                case ERASER:
                    Point eraserHotspot = new Point(0,20);
                    setCursor(getToolkit().createCustomCursor(eraserImage,eraserHotspot,"eraserCursor"));
                    break;
                case NONE:
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    break;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GRAY);
        g.drawImage(canvasImage, 0, 0, null);

        int size = 10;
        int x = getWidth() - size;
        int y = getHeight() - size;

        g2d.fillRect(x, y, size, size);
    }
}
