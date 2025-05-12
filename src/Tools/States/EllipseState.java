package Tools.States;

import Functions.ToolState;
import Panels.PaintCanvas;
import Tools.Shapes.EllipseShape;

import java.awt.*;
import java.awt.event.MouseEvent;

public class EllipseState implements ToolState {
    private PaintCanvas paintCanvas;
    private EllipseShape ellipse;

    public EllipseState(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;
        this.ellipse = new EllipseShape(paintCanvas);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        paintCanvas.getUndoTool().save();
        paintCanvas.getRedoTool().clearHistory();

        ellipse.setStart(e.getPoint());
        ellipse.setColor(paintCanvas.getCurrentColor());
        ellipse.setThickness(paintCanvas.getLineThickness());
        paintCanvas.setEllipse(ellipse);
    }

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

    @Override
    public void mouseDragged(MouseEvent e) {
        if (ellipse != null) {
            ellipse.setEndPoint(e.getPoint());
            paintCanvas.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        paintCanvas.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
}
