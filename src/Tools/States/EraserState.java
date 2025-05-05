package Tools.States;

import Functions.ToolState;
import Panels.PaintCanvas;
import Tools.EraserTool;

import java.awt.*;
import java.awt.event.MouseEvent;

public class EraserState implements ToolState {
    private PaintCanvas canvas;
    private EraserTool eraserTool;

    public EraserState(PaintCanvas canvas) {
        this.canvas = canvas;
        this.eraserTool = new EraserTool(canvas);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        eraserTool.setThickness(canvas.getLineThickness());
        canvas.getUndoTool().save();
        canvas.getRedoTool().clearHistory();
        eraserTool.addPoint(e.getPoint());
        canvas.addStroke(eraserTool);
        canvas.setActiveEraserTool(eraserTool);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (eraserTool == null) return;

        eraserTool.addPoint(e.getPoint());
        Graphics2D g2d = canvas.getCanvasImage().createGraphics();
        eraserTool.draw(g2d);
        g2d.dispose();
        canvas.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        eraserTool.eraserCursor();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        canvas.getUndoTool().save();
    }
}
