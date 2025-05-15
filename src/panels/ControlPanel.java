package panels;

import tools.shapes.EllipseShape;
import tools.shapes.LineShape;
import tools.*;
import tools.shapes.RectangleShape;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * The ControlPanel class is the main sidebar panel that initializes
 * and organizes all drawing tools and controls for the PaintCanvas.
 */
public class ControlPanel extends JPanel implements ChangeListener {

    private PaintCanvas paintCanvas;
    private JLabel toolsLabel = new JLabel();
    private JLabel thicknessLabel = new JLabel();
    private JLabel paletteLabel = new JLabel();
    private JLabel undoLabel = new JLabel();
    private ThicknessSelector thicknessSelector = new ThicknessSelector(2);

    /**
     * Constructs the control panel and registers all tools to the given canvas.
     * Add the tools to be visible on the panel.
     * @param paintCanvas The main canvas this control panel manages tools for.
     */
    public ControlPanel(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;

        DrawTool drawTool = new DrawTool(paintCanvas);
        paintCanvas.setDrawTool(drawTool);

        EraserTool eraserTool = new EraserTool(paintCanvas);
        paintCanvas.setEraserTool(eraserTool);

        FillTool fillTool = new FillTool(paintCanvas);
        paintCanvas.setFillTool(fillTool);

        DropperTool dropperTool = new DropperTool(paintCanvas);
        paintCanvas.setDropperTool(dropperTool);

        UndoTool undoTool = new UndoTool(paintCanvas);
        paintCanvas.setUndoTool(undoTool);

        ColorPalette colorPalette = new ColorPalette(paintCanvas);
        paintCanvas.setColorPalette(colorPalette);

        RedoTool redoTool = new RedoTool(paintCanvas);
        paintCanvas.setRedoTool(redoTool);

        LineShape straightLine = new LineShape(paintCanvas);
        paintCanvas.setStraightLine(straightLine);

        RectangleShape rectangleShape = new RectangleShape(paintCanvas);
        paintCanvas.setRectangle(rectangleShape);

        EllipseShape ellipseShape = new EllipseShape(paintCanvas);
        paintCanvas.setEllipse(ellipseShape);

        SaveTool saveTool = new SaveTool(paintCanvas);
        paintCanvas.setSaveTool(saveTool);

        LoadTool loadTool = new LoadTool(paintCanvas);
        paintCanvas.setLoadTool(loadTool);

        setBounds(0, 0, 80, 1080);
        setBackground(new Color(245, 235, 215));
        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        setVisible(true);
        setLayout(null);

        thicknessSelector.addChangeListener(this);

        add(toolsLabel);
        add(thicknessLabel);
        add(paletteLabel);
        add(undoLabel);
        add(drawTool);
        add(eraserTool);
        add(fillTool);
        add(colorPalette);
        add(dropperTool);
        add(undoTool);
        add(redoTool);
        add(straightLine);
        add(rectangleShape);
        add(ellipseShape);
        add(saveTool);
        add(loadTool);
        add(thicknessSelector);
    }

    /**
     * Called when the thickness selector value changes.
     * Updates the line thickness on the canvas.
     *
     * @param e The change event triggered by the selector.
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        paintCanvas.setLineThickness(thicknessSelector.getThickness());
    }
}
