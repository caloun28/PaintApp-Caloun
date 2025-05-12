package Panels;

import Functions.Images;
import Functions.Strokes;
import Functions.ToolState;
import Tools.Shapes.EllipseShape;
import Tools.Shapes.LineShape;
import Tools.Shapes.RectangleShape;
import Tools.States.*;
import Tools.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PaintCanvas extends JPanel implements MouseListener, MouseMotionListener {

    private ToolState currentState;
    private ToolType currentTool = ToolType.NULL;
    private BufferedImage canvasImage;
    private Color currentColor = Color.BLACK;
    private int lineThickness = 3;
    private ArrayList<Strokes> strokes = new ArrayList<>();

    private DrawTool drawTool;
    private EraserTool eraseTool;
    private FillTool fillTool;
    private DropperTool dropperTool;

    private LineShape straightLine;
    private RectangleShape rectangle;
    private EllipseShape ellipse;

    private ColorPalette colorPalette;
    private UndoTool undoTool;
    private RedoTool redoTool;
    private SaveTool saveTool;
    private LoadTool loadTool;

    private Point lastPoint;
    private boolean resizing = false;

    public PaintCanvas() {
        setBackground(Color.WHITE);
        setBounds(90, 10, 1740, 750);
        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        setVisible(true);

        canvasImage = new BufferedImage(1740, 750, BufferedImage.TYPE_INT_ARGB);

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    // region Set&Get

    public boolean isResizing() {
        return resizing;
    }

    public BufferedImage getCanvasImage() {
        return canvasImage;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public int getLineThickness() {
        return lineThickness;
    }

    public UndoTool getUndoTool() {
        return undoTool;
    }

    public RedoTool getRedoTool() {
        return redoTool;
    }

    public DropperTool getDropperTool() {
        return dropperTool;
    }

    public void setCanvasImage(BufferedImage canvasImage) {
        this.canvasImage = canvasImage;
    }

    public void setCurrentColor(Color color) {
        this.currentColor = color;
        if (colorPalette != null) colorPalette.updateColor(color);
    }

    public void setLineThickness(int thickness) {
        this.lineThickness = thickness;
    }

    public void setUndoTool(UndoTool tool) {
        this.undoTool = tool;
        undoTool.save();
    }

    public void setRedoTool(RedoTool tool) {
        this.redoTool = tool;
    }

    public void setColorPalette(ColorPalette palette) {
        this.colorPalette = palette;
    }

    public void setDrawTool(DrawTool tool) {
        this.drawTool = tool;
    }

    public DrawTool getDrawTool() {
        return drawTool;
    }

    public void setEraserTool(EraserTool tool) {
        this.eraseTool = tool;
    }

    public void setFillTool(FillTool tool) {
        this.fillTool = tool;
    }

    public void setDropperTool(DropperTool tool) {
        this.dropperTool = tool;
    }

    public void addStroke(Strokes stroke) {
        strokes.add(stroke);
    }

    public void setActiveDrawTool(DrawTool tool) {
        this.drawTool = tool;
    }

    public void setActiveEraserTool(EraserTool tool) {
        this.eraseTool = tool;
    }

    public void setLoadTool(LoadTool loadTool) {
        this.loadTool = loadTool;
    }

    public void setSaveTool(SaveTool saveTool) {
        this.saveTool = saveTool;
    }

    public void setStraightLine(LineShape straightLine) {
        this.straightLine = straightLine;
    }

    public void setRectangle(RectangleShape rectangle) {
        this.rectangle = rectangle;
    }

    public void setEllipse(EllipseShape ellipse) {
        this.ellipse = ellipse;
    }

    public ToolType getCurrentTool() {
        return currentTool;
    }


    public void setToolMode(ToolType toolType) {
        this.currentTool = toolType;

        switch (toolType) {
            case DRAW -> currentState = new DrawState(this);
            case ERASER -> currentState = new EraserState(this);
            case LINE -> currentState = new LineState(this);
            case RECTANGLE -> currentState = new RectangleState(this);
            case FILL -> currentState = new FillState(this);
            case DROPPER -> currentState = new DropperState(this);
            case ELLIPSE -> currentState = new EllipseState(this);
            default -> currentState = null;
        }
    }
    // endregion

    public void resizeCanvasImage(int newWidth, int newHeight) {
        BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(canvasImage, 0, 0, null);
        g.dispose();
        canvasImage = newImage;
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getX() >= getWidth() - 20 && e.getY() >= getHeight() - 20) {
            resizing = true;
            lastPoint = e.getPoint();
            return;
        }
        resizing = false;
        if (currentState != null) {
            currentState.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (resizing) {
            resizing = false;
            return;
        }
        if (currentState != null) {
            currentState.mouseReleased(e);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (resizing) {
            int newW = getWidth() + (e.getX() - lastPoint.x);
            int newH = getHeight() + (e.getY() - lastPoint.y);
            if (newW > 1 && newW < 1800 && newH > 1 && newH < 1020) {
                setBounds(90, 10, newW, newH);
                resizeCanvasImage(newW, newH);
                revalidate();
                repaint();
                lastPoint = e.getPoint();
            }
        } else if (currentState != null) {
            currentState.mouseDragged(e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(canvasImage, 0, 0, null);

        Graphics2D g2d = (Graphics2D) g;
        int size = 10;
        int x = getWidth() - size;
        int y = getHeight() - size;
        g2d.setColor(Color.GRAY);
        g2d.fillRect(x, y, size, size);

        if (straightLine != null) straightLine.draw(g2d);
        if (rectangle != null) rectangle.draw(g2d);
        if (ellipse != null) ellipse.draw(g2d);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (e.getX() >= getWidth() - 20 && e.getY() >= getHeight() - 20) {
            setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
        } else if (currentState != null) {
            currentState.mouseMoved(e);
        }
    }

    public void saveDrawing(String filename) {
        try {
            ImageIO.write(canvasImage, "png", new File(filename));
        } catch (IOException e) {
            System.out.println("Can't save drawing: " + filename);
        }
    }

    public void loadDrawing(String filename) {
        BufferedImage loadedImage;
        try {
            loadedImage = ImageIO.read(new File(filename));

            if (loadedImage != null) {
                canvasImage = loadedImage;
                repaint();
            }
        } catch (IOException e) {
            System.out.println("Can't load drawing: " + filename);
        }

    }
}
