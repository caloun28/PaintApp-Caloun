package Tools.States;

import Functions.ToolState;
import Panels.PaintCanvas;
import Tools.FillTool;

import java.awt.event.MouseEvent;

public class FillState implements ToolState {
    private PaintCanvas canvas;
    private FillTool fillTool;

    public FillState(PaintCanvas canvas) {
        this.canvas = canvas;
        this.fillTool = new FillTool(canvas);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (fillTool != null) {
            canvas.getUndoTool().save();
            canvas.getRedoTool().clearHistory();

            int oldColor = canvas.getCanvasImage().getRGB(e.getX(), e.getY());
            int newColor = canvas.getCurrentColor().getRGB();
            fillTool.fill(e.getX(), e.getY(), oldColor, newColor);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        fillTool.fillCursor();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}