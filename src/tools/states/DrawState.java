package tools.states;

import functions.ToolState;
import panels.PaintCanvas;
import tools.DrawTool;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * DrawState handles freehand drawing interactions on the canvas.
 * It manages the drawing tool state and processes mouse events for smooth sketching.
 */
public class DrawState implements ToolState {
    private PaintCanvas paintCanvas;
    private DrawTool drawTool;

    /**
     * Initializes a new drawing state with the given canvas context.
     *
     * @param canvas The drawing surface where strokes will be applied.
     */
    public DrawState(PaintCanvas canvas) {
        this.paintCanvas = canvas;
        this.drawTool = new DrawTool(canvas);
    }

    /**
     * Starts a new stroke when the mouse is pressed.
     * Initializes drawing settings and registers the tool for undo/redo tracking.
     *
     * @param e Mouse event containing the press location.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        drawTool = new DrawTool(paintCanvas);
        drawTool.setColor(paintCanvas.getCurrentColor());
        drawTool.setThickness(paintCanvas.getLineThickness());

        paintCanvas.getUndoTool().save();
        paintCanvas.getRedoTool().clearHistory();
        drawTool.addPoint(e.getPoint());

        paintCanvas.addStroke(drawTool);
        paintCanvas.setActiveDrawTool(drawTool);
    }

    /**
     * Adds points to the stroke and draws the updated shape as the mouse is dragged.
     *
     * @param e Mouse event containing the current drag location.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (drawTool == null) return;

        drawTool.addPoint(e.getPoint());
        Graphics2D g2d = paintCanvas.getCanvasImage().createGraphics();
        drawTool.draw(g2d);
        g2d.dispose();
        paintCanvas.repaint();
    }

    /**
     * Updates the cursor display while moving the mouse.
     *
     * @param e Mouse event containing the current cursor location.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        drawTool.drawCursor();
    }

    /**
     * Finalizes the stroke when the mouse is released and saves it for undo.
     *
     * @param e Mouse event containing the release location.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        drawTool = paintCanvas.getDrawTool();
        paintCanvas.getUndoTool().save();
    }
}
