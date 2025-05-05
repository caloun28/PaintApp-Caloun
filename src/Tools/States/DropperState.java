package Tools.States;

import Functions.ToolState;
import Panels.PaintCanvas;
import Tools.DropperTool;

import java.awt.event.MouseEvent;

public class DropperState implements ToolState {
    private PaintCanvas canvas;
    private DropperTool dropper;

    public DropperState(PaintCanvas canvas) {
        this.canvas = canvas;
        this.dropper = new DropperTool(canvas);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        canvas.getDropperTool().findColor(x, y);
    }

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