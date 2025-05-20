package tools.states;

import functions.ToolState;
import panels.PaintCanvas;
import tools.FillTool;

import java.awt.event.MouseEvent;

/**
 * FillState handles the behavior of the fill (bucket) tool.
 * It applies a flood fill algorithm to recolor connected pixels.
 */
public class FillState implements ToolState {
    private PaintCanvas paintCanvas;
    private FillTool fillTool;

    /**
     * Constructs a FillState with the given canvas.
     *
     * @param canvas The drawing canvas on which the fill will be applied.
     */
    public FillState(PaintCanvas canvas) {
        this.paintCanvas = canvas;
        this.fillTool = new FillTool(canvas);
    }

    /**
     * Triggers the fill operation at the clicked point.
     * Saves the canvas state before filling and clears redo history.
     *
     * @param e Mouse event containing the clicked coordinates.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        paintCanvas.getUndoTool().save();
        paintCanvas.getRedoTool().clearHistory();

        if (fillTool != null) {
            int oldColor = paintCanvas.getCanvasImage().getRGB(e.getX(), e.getY());
            int newColor = paintCanvas.getCurrentColor().getRGB();
            fillTool.fill(e.getX(), e.getY(), oldColor, newColor);
        }
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    /**
     * Updates the cursor to the fill (bucket) icon.
     *
     * @param e Mouse event containing the current cursor position.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        fillTool.fillCursor();
    }

    /**
     * Saves the canvas state after the fill operation is complete.
     *
     * @param e Mouse event indicating the mouse release.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        paintCanvas.getUndoTool().save();
        paintCanvas.getRedoTool().clearHistory();
    }
}