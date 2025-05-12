package Tools.States;

import Functions.ToolState;
import Panels.PaintCanvas;
import Tools.Shapes.RectangleShape;

import java.awt.*;
import java.awt.event.MouseEvent;

public class RectangleState implements ToolState {
    private final PaintCanvas canvas;
    private RectangleShape rectangleShape;
    private Point startPoint;

    public RectangleState(PaintCanvas canvas) {
        this.canvas = canvas;
        this.rectangleShape = new RectangleShape(canvas);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (canvas.isResizing()){
            return;
        }
        canvas.getUndoTool().save();
        canvas.getRedoTool().clearHistory();

        startPoint = e.getPoint();
        rectangleShape = new RectangleShape(canvas);
        rectangleShape.setStartPoint(startPoint);
        rectangleShape.setColor(canvas.getCurrentColor());
        rectangleShape.setThickness(canvas.getLineThickness());
        canvas.setRectangle(rectangleShape);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (rectangleShape == null) return;
        rectangleShape.setEndPoint(e.getPoint());
        canvas.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        canvas.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (canvas.isResizing()) return;

        if (rectangleShape != null && startPoint != null) {
            rectangleShape.finishRectangle(e.getPoint(), canvas.getCanvasImage());
            canvas.addStroke(rectangleShape);
            canvas.getRedoTool().clearHistory();
            canvas.getUndoTool().save();
            canvas.repaint();
            canvas.setRectangle(null);
        }
    }
}
