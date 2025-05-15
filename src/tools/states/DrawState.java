package tools.states;

import functions.ToolState;
import panels.PaintCanvas;
import tools.DrawTool;

import java.awt.*;
import java.awt.event.MouseEvent;

public class DrawState implements ToolState {
    private PaintCanvas canvas;
    private DrawTool drawTool;

    public DrawState(PaintCanvas canvas) {
        this.canvas = canvas;
        this.drawTool = new DrawTool(canvas);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        drawTool = new DrawTool(canvas);
        drawTool.setColor(canvas.getCurrentColor());
        drawTool.setThickness(canvas.getLineThickness());

        canvas.getUndoTool().save();
        canvas.getRedoTool().clearHistory();
        drawTool.addPoint(e.getPoint());

        canvas.addStroke(drawTool);
        canvas.setActiveDrawTool(drawTool);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (drawTool == null) return;

        drawTool.addPoint(e.getPoint());
        Graphics2D g2d = canvas.getCanvasImage().createGraphics();
        drawTool.draw(g2d);
        g2d.dispose();
        canvas.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        drawTool.drawCursor();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        drawTool = canvas.getDrawTool();
        canvas.getUndoTool().save();
    }
}
