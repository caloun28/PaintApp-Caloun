package tools.states;

import functions.ToolState;
import panels.PaintCanvas;
import tools.shapes.EllipseShape;

import java.awt.*;
import java.awt.event.MouseEvent;
/**
 * EllipseState handles user interaction for drawing ellipses.
 * It tracks mouse events and delegates the ellipse creation to the EllipseShape tool.
 */

public class EllipseState implements ToolState {
    private PaintCanvas paintCanvas;
    private EllipseShape ellipse;

    /**
     * Constructs an EllipseState with the specified paint canvas.
     *
     * @param paintCanvas The canvas where the ellipse will be drawn.
     */
    public EllipseState(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;
        this.ellipse = new EllipseShape(paintCanvas);
    }

    /**
     * Initializes the ellipse drawing by setting start point, color, and thickness.
     * Also stores the current canvas state for undo/redo functionality.
     *
     * @param e Mouse event with the starting point of the ellipse.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        paintCanvas.getUndoTool().save();
        paintCanvas.getRedoTool().clearHistory();

        ellipse.setStart(e.getPoint());
        ellipse.setColor(paintCanvas.getCurrentColor());
        ellipse.setThickness(paintCanvas.getLineThickness());
        paintCanvas.setEllipse(ellipse);
    }

    /**
     * Finalizes the ellipse on mouse release and adds it to the canvas history.
     *
     * @param e Mouse event with the ending point of the ellipse.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (paintCanvas.isResizing()) return;

        if (ellipse != null) {
            ellipse.finishEllipse(e.getPoint(),paintCanvas.getCanvasImage());
            paintCanvas.addStroke(ellipse);
            paintCanvas.getUndoTool().save();
            paintCanvas.getRedoTool().clearHistory();
            paintCanvas.repaint();
            paintCanvas.setEllipse(null);
        }
    }

    /**
     * Updates the ellipse's end point during dragging for real-time feedback.
     *
     * @param e Mouse event with current drag position.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (ellipse != null) {
            ellipse.setEndPoint(e.getPoint());
            paintCanvas.repaint();
        }
    }

    /**
     * Sets the default cursor when the mouse is moved over the canvas.
     *
     * @param e Mouse event with current cursor position.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        paintCanvas.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
}
