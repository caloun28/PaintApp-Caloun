package tools.states;

import functions.ToolState;
import panels.PaintCanvas;
import tools.DropperTool;

import java.awt.event.MouseEvent;

/**
 * DropperState manages the behavior of the color picker (dropper) tool.
 * It allows users to select a color from the canvas and updates the cursor accordingly.
 */
public class DropperState implements ToolState {
    private PaintCanvas paintCanvas;
    private DropperTool dropper;

    /**
     * Constructs a DropperState with the given canvas.
     *
     * @param canvas The canvas from which colors will be picked.
     */
    public DropperState(PaintCanvas canvas) {
        this.paintCanvas = canvas;
        this.dropper = new DropperTool(canvas);
    }

    /**
     * Picks the color from the canvas at the location where the mouse is pressed.
     *
     * @param e Mouse event containing the coordinates of the click.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        paintCanvas.getDropperTool().findColor(x, y);
    }

    /**
     * Updates the cursor appearance to match the dropper tool.
     *
     * @param e Mouse event with current cursor location.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        dropper.dropperCursor();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}