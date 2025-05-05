package Panels;

import Tools.Shapes.LineShape;
import Tools.*;
import Tools.Shapes.RectangleShape;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ControlPanel extends JPanel implements ChangeListener {

    private PaintCanvas paintCanvas;
    private JLabel toolsLabel = new JLabel();
    private JLabel thicknessLabel = new JLabel();
    private JLabel paletteLabel = new JLabel();
    private JLabel undoLabel = new JLabel();
    private ThicknessSelector thicknessSelector = new ThicknessSelector(2);

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

        setBounds(0, 0, 80, 1080);
        setBackground(new Color(245, 235, 215));
        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        setVisible(true);
        setLayout(null);

        toolsLabel.setBounds(140,5,40,15);
        toolsLabel.setOpaque(false);
        toolsLabel.setText("TOOLS");
        toolsLabel.setFont(new Font("SEGOE UI", Font.PLAIN, 12));


        thicknessLabel.setBounds(218,5,60,15);
        thicknessLabel.setOpaque(false);
        thicknessLabel.setText("THICKNESS");
        thicknessLabel.setFont(new Font("SEGOE UI", Font.PLAIN, 12));

        paletteLabel.setBounds(300,5,55,15);
        paletteLabel.setOpaque(false);
        paletteLabel.setText("PALETTE");
        paletteLabel.setFont(new Font("SEGOE UI", Font.PLAIN, 12));

        undoLabel.setBounds(370,5,55,15);
        undoLabel.setOpaque(false);
        undoLabel.setText("UNDO");
        undoLabel.setFont(new Font("SEGOE UI", Font.PLAIN, 12));

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
        add(thicknessSelector);
    }


    @Override
    public void stateChanged(ChangeEvent e) {
        paintCanvas.setLineThickness(thicknessSelector.getThickness());
    }
}
