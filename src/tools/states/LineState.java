package tools.states;

import functions.ToolState;
import panels.PaintCanvas;
import tools.shapes.LineShape;

import java.awt.*;
import java.awt.event.MouseEvent;

public class LineState implements ToolState {
    private final PaintCanvas canvas;
    private LineShape lineShape;
    private Point startPoint;

    public LineState(PaintCanvas canvas) {
        this.canvas = canvas;
        this.lineShape = new LineShape(canvas);
    }

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

    @Override
    public void mouseDragged(MouseEvent e) {
        if (lineShape == null) return;
        lineShape.setEndPoint(e.getPoint());
        canvas.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        canvas.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

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
