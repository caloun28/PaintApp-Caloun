package Panels.Canvas;

import Functions.Strokes;
import Tools.Shapes.RectangleShape;
import Tools.Shapes.LineShape;
import Tools.*;

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
    private ToolType currentTool = ToolType.NULL;
    private DrawTool drawStroke;
    private EraserTool eraseStroke;
    private FillTool fillTool;
    private DropperTool dropperTool;
    private ArrayList<Strokes> strokes = new ArrayList<>();
    private boolean drawing = false;
    private boolean resize = false;
    private boolean erasing = false;
    private boolean filling = false;
    private boolean dropping = false;
    private boolean makingLine = false;
    private boolean makingRectangle = false;
    private int lineThickness = 3;
    private Color currentColor = Color.BLACK;
    private BufferedImage canvasImage;
    private ColorPalette colorPalette;
    private UndoTool undoTool;
    private RedoTool redoTool;
    private LineShape straightLine;
    private Point startPoint;
    private RectangleShape rectangle;

    public PaintCanvas() {

        setBackground(Color.WHITE);
        setBounds(90, 10, 1740, 750);
        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        setVisible(true);
        canvasImage = new BufferedImage(1740, 750, BufferedImage.TYPE_INT_ARGB);


        addMouseListener(this);
        addMouseMotionListener(this);

    }

    public RectangleShape getRectangle() {
        return rectangle;
    }

    public void setRectangle(RectangleShape rectangle) {
        this.rectangle = rectangle;
    }

    public void setColorPalette(ColorPalette colorPalette) {
        this.colorPalette = colorPalette;
    }

    public void setRedoTool(RedoTool redoTool) {
        this.redoTool = redoTool;
    }

    public RedoTool getRedoTool() {
        return redoTool;
    }

    public void setStraightLine(LineShape straightLine) {
        this.straightLine = straightLine;
    }

    public UndoTool getUndoTool() {
        return undoTool;
    }

    public void setUndoTool(UndoTool undoTool) {
        this.undoTool = undoTool;
        undoTool.save();
    }

    public void setToolMode(ToolType tool) {
        this.currentTool = tool;
    }

    public void setFillTool(FillTool fillTool) {
        this.fillTool = fillTool;
    }

    public void setDropperTool(DropperTool dropperTool) {
        this.dropperTool = dropperTool;
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

    public void setCanvasImage(BufferedImage canvasImage) {
        this.canvasImage = canvasImage;
        repaint();
    }

    public void setLineThickness(int thickness) {
        this.lineThickness = thickness;
    }

    public BufferedImage getCanvasImage() {
        return canvasImage;
    }

    public void resizeCanvasImage(int newWidth, int newHeight) {
        BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(canvasImage, 0, 0, null);
        g.dispose();
        canvasImage = newImage;
        repaint();
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
        if (colorPalette != null) {
            colorPalette.updateColor(currentColor);
        }
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
                undoTool.save();
                redoTool.clearHistory();
                drawStroke.addPoint(e.getPoint());
                strokes.add(drawStroke);
                break;

            case ERASER:

                erasing = true;
                eraseStroke = new EraserTool(this);
                eraseStroke.setThickness(lineThickness);
                undoTool.save();
                redoTool.clearHistory();
                eraseStroke.addPoint(e.getPoint());
                strokes.add(eraseStroke);
                break;

            case FILL:

                if (fillTool != null) {
                    filling = true;
                    undoTool.save();
                    redoTool.clearHistory();
                    int oldColor = canvasImage.getRGB(e.getX(), e.getY());
                    int newColor = currentColor.getRGB();
                    fillTool.fill(e.getX(), e.getY(), oldColor, newColor);

                    if (redoTool != null) {
                        redoTool.clearHistory();
                    }
                }
                break;

            case DROPPER:
                dropping = true;
                int x = e.getX();
                int y = e.getY();
                dropperTool.findColor(x, y);
                break;
        }

        if (e.getX() >= getWidth() - 20 && e.getY() >= getHeight() - 20) {
            filling = false;
            drawing = false;
            erasing = false;
            dropping = false;
            makingLine = false;
            makingRectangle = false;
            resize = true;

            lastPoint = e.getPoint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (drawing || erasing || filling) {
            undoTool.save();
        }

        resize = false;
        drawing = false;
        erasing = false;
        filling = false;
        dropping = false;
        makingLine = false;
        makingRectangle = false;

        switch (currentTool) {
            case LINE:
                if (startPoint != null) {
                    straightLine.finishDrawing(e.getPoint(), canvasImage);
                    strokes.add(straightLine);
                    undoTool.save();
                    redoTool.clearHistory();
                    repaint();
                    startPoint = null;
                }
                break;

            case RECTANGLE:
                if (startPoint != null) {
                    rectangle.finishRectangle(e.getPoint(), canvasImage);
                    strokes.add(rectangle);
                    undoTool.save();
                    redoTool.clearHistory();
                    repaint();
                    startPoint = null;
                }

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (resize) {
            filling = false;
            erasing = false;
            drawing = false;
            dropping = false;
            makingLine = false;
            makingRectangle = false;
            int newW = getWidth() + (e.getX() - lastPoint.x);
            int newH = getHeight() + (e.getY() - lastPoint.y);

            if (newW > 1 && newW < 1800 && newH > 1 && newH < 1020) {
                setBounds(90, 10, newW, newH);
                resizeCanvasImage(newW, newH);
                revalidate();
                repaint();
                lastPoint = e.getPoint();
            }
            return;
        }

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

            case LINE:
                if (!makingLine) {
                    makingLine = true;
                    startPoint = e.getPoint();
                    straightLine = new LineShape(this);
                    straightLine.setColor(currentColor);
                    straightLine.setThickness(lineThickness);
                    straightLine.setStartPoint(startPoint);
                }
                if (straightLine != null) {
                    straightLine.setEndPoint(e.getPoint());
                    repaint();
                }
                break;

            case RECTANGLE:
                if (!makingRectangle) {
                    makingRectangle = true;
                    startPoint = e.getPoint();
                    rectangle = new RectangleShape(this);
                    rectangle.setColor(currentColor);
                    rectangle.setThickness(lineThickness);
                    rectangle.setStartPoint(startPoint);
                }
                if (rectangle != null) {
                    rectangle.setEndPoint(e.getPoint());
                    repaint();
                }
                break;
        }


    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (e.getX() >= getWidth() - 20 && e.getY() >= getHeight() - 20) {
            setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
        } else {
            switch (currentTool) {
                case FILL:
                    fillTool.fillCursor();
                    break;
                case DRAW:
                    drawStroke.drawCursor();
                    break;
                case ERASER:
                    eraseStroke.eraserCursor();
                    break;
                case DROPPER:
                    dropperTool.dropperCursor();
                    break;
                case NULL:
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    break;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (makingLine && straightLine != null) {
            straightLine.draw((Graphics2D) g);
        }

        if (makingRectangle && rectangle != null) {
            rectangle.draw((Graphics2D) g);
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GRAY);
        g.drawImage(canvasImage, 0, 0, null);

        int size = 10;
        int x = getWidth() - size;
        int y = getHeight() - size;

        g2d.fillRect(x, y, size, size);
    }
}
