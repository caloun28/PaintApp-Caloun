package tools.states;

import functions.ToolState;
import panels.PaintCanvas;
import tools.shapes.LineShape;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * LineState manages the drawing of straight lines on the canvas.
 * It handles mouse events to create, update, and finalize a line shape.
 */
public class LineState implements ToolState {
    private final PaintCanvas canvas;
    private LineShape lineShape;
    private Point startPoint;

    /**
     * Creates a LineState for the given PaintCanvas.
     *
     * @param canvas The drawing canvas where the line will be drawn.
     */
    public LineState(PaintCanvas canvas) {
        this.canvas = canvas;
        this.lineShape = new LineShape(canvas);
    }

    /**
     * Initializes line drawing by setting start point, color, and thickness.
     * Saves undo history and clears redo stack.
     *
     * @param e Mouse event with starting coordinates.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (canvas.isResizing()){
            return;
        }
        canvas.getUndoTool().save();
        canvas.getRedoTool().clearHistory();

        startPoint = e.getPoint();
        lineShape.setStartPoint(startPoint);
        lineShape.setColor(canvas.getCurrentColor());
        lineShape.setThickness(canvas.getLineThickness());
        canvas.setStraightLine(lineShape);
    }

    /**
     * Updates the line's end point as the mouse is dragged and repaints canvas.
     *
     * @param e Mouse event with current drag coordinates.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (lineShape == null) return;
        lineShape.setEndPoint(e.getPoint());
        canvas.repaint();
    }

    /**
     * Sets the default cursor when the mouse is moved.
     *
     * @param e Mouse event with current cursor position.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        canvas.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    /**
     * Finalizes the line drawing on mouse release,
     * applies the stroke to the canvas image, and saves undo state.
     *
     * @param e Mouse event with release coordinates.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (canvas.isResizing()) return;

        if (lineShape != null && startPoint != null) {
            lineShape.finishDrawing(e.getPoint(), canvas.getCanvasImage());
            canvas.addStroke(lineShape);
            canvas.getUndoTool().save();
            canvas.getRedoTool().clearHistory();
            canvas.repaint();
            canvas.setStraightLine(null);
        }
    }
}
