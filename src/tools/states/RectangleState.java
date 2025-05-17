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
    private final PaintCanvas canvas;
    private RectangleShape rectangleShape;
    private Point startPoint;

    /**
     * Creates a RectangleState for the given PaintCanvas.
     *
     * @param canvas The drawing canvas where rectangles are created.
     */
    public RectangleState(PaintCanvas canvas) {
        this.canvas = canvas;
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

    /**
     * Updates the rectangle's end point as the mouse is dragged, and repaints.
     *
     * @param e Mouse event with current drag coordinates.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (rectangleShape == null) return;
        rectangleShape.setEndPoint(e.getPoint());
        canvas.repaint();
    }

    /**
     * Sets default cursor when the mouse moves.
     *
     * @param e Mouse event with current cursor position.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        canvas.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    /**
     * Completes the rectangle drawing on mouse release,
     * applies the rectangle to the canvas, updates undo/redo history, and repaints.
     *
     * @param e Mouse event with release coordinates.
     */
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
