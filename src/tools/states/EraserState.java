package tools.states;

import functions.ToolState;
import panels.PaintCanvas;
import tools.EraserTool;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * EraserState handles the eraser tool behavior on the canvas.
 * It manages the drawing logic and state when erasing parts of the image.
 */
public class EraserState implements ToolState {
    private PaintCanvas paintCanvas;
    private EraserTool eraserTool;

    /**
     * Constructs an EraserState with the given canvas.
     *
     * @param canvas The drawing canvas where erasing will occur.
     */
    public EraserState(PaintCanvas canvas) {
        this.paintCanvas = canvas;
        this.eraserTool = new EraserTool(canvas);
    }

    /**
     * Initializes erasing by setting thickness, saving canvas state,
     * and adding the starting point of the stroke.
     *
     * @param e Mouse event with the starting point of erasing.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        eraserTool = new EraserTool(paintCanvas);
        eraserTool.setThickness(paintCanvas.getLineThickness());

        Graphics2D g2d = paintCanvas.getCanvasImage().createGraphics();
        eraserTool.draw(g2d);
        g2d.dispose();
        paintCanvas.repaint();

        paintCanvas.getUndoTool().save();
        paintCanvas.getRedoTool().clearHistory();
        eraserTool.addPoint(e.getPoint());
        paintCanvas.addStroke(eraserTool);
        paintCanvas.setActiveEraserTool(eraserTool);
    }

    /**
     * Continues erasing as the mouse is dragged by drawing new segments.
     *
     * @param e Mouse event with the current drag point.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (eraserTool == null) return;

        eraserTool.addPoint(e.getPoint());
        Graphics2D g2d = paintCanvas.getCanvasImage().createGraphics();
        eraserTool.draw(g2d);
        g2d.dispose();
        paintCanvas.repaint();
    }

    /**
     * Updates the cursor to indicate the eraser tool is active.
     *
     * @param e Mouse event with current cursor position.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        eraserTool.eraserCursor();
    }

    /**
     * Finalizes the erasing operation by saving the current canvas state.
     *
     * @param e Mouse event at the point of release.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        paintCanvas.getUndoTool().save();
    }
}
