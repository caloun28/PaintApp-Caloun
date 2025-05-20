package tools.states;

import functions.ToolState;
import panels.PaintCanvas;
import tools.shapes.RectangleShape;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * RectangleState handles user interactions to draw rectangles on the canvas.
 * It tracks mouse events to create, update, and finalize a rectangle shape.
 */
public class RectangleState implements ToolState {
    private final PaintCanvas paintCanvas;
    private RectangleShape rectangleShape;
    private Point startPoint;

    /**
     * Creates a RectangleState for the given PaintCanvas.
     *
     * @param canvas The drawing canvas where rectangles are created.
     */
    public RectangleState(PaintCanvas canvas) {
        this.paintCanvas = canvas;
        this.rectangleShape = new RectangleShape(canvas);
    }

    /**
     * Starts drawing a rectangle by setting the start point, color, and thickness.
     * Saves undo history and clears redo history.
     *
     * @param e Mouse event with initial press coordinates.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (paintCanvas.isResizing()){
            return;
        }
        paintCanvas.getUndoTool().save();
        paintCanvas.getRedoTool().clearHistory();

        startPoint = e.getPoint();
        rectangleShape = new RectangleShape(paintCanvas);
        rectangleShape.setStartPoint(startPoint);
        rectangleShape.setColor(paintCanvas.getCurrentColor());
        rectangleShape.setThickness(paintCanvas.getLineThickness());
        paintCanvas.setRectangle(rectangleShape);
    }

    /**
     * Updates the rectangle's end point as the mouse is dragged, and repaints.
     *
     * @param e Mouse event with current drag coordinates.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (rectangleShape == null) return;
        rectangleShape.setEndPoint(e.getPoint());
        paintCanvas.repaint();
    }

    /**
     * Sets default cursor when the mouse moves.
     *
     * @param e Mouse event with current cursor position.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        paintCanvas.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    /**
     * Completes the rectangle drawing on mouse release,
     * applies the rectangle to the canvas, updates undo/redo history, and repaints.
     *
     * @param e Mouse event with release coordinates.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (paintCanvas.isResizing()) return;

        if (rectangleShape != null && startPoint != null) {
            rectangleShape.finishRectangle(e.getPoint(), paintCanvas.getCanvasImage());
            paintCanvas.addStroke(rectangleShape);
            paintCanvas.getRedoTool().clearHistory();
            paintCanvas.getUndoTool().save();
            paintCanvas.repaint();
            paintCanvas.setRectangle(null);
        }
    }
}
