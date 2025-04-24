package Panels;

import Tools.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ControlPanel extends JPanel implements ChangeListener {

    private PaintCanvas paintCanvas;
    private JLabel toolsLabel = new JLabel();
    private JLabel thicknessLable = new JLabel();
    private ThicknessSelector thicknessSelector = new ThicknessSelector(2);

    public ControlPanel(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;

        DrawTool drawTool = new DrawTool(paintCanvas);
        paintCanvas.setDrawStroke(drawTool);
        EraserTool eraserTool = new EraserTool(paintCanvas);
        paintCanvas.setEraseStroke(eraserTool);
        FillTool fillTool = new FillTool(paintCanvas);
        paintCanvas.setFillTool(fillTool);

        ColorPalette colorPalette = new ColorPalette(paintCanvas);

        setBounds(0, 0, 1920, 80);
        setBackground(new Color(245, 235, 215));
        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        setVisible(true);
        setLayout(null);

        toolsLabel.setBounds(145,5,30,15);
        toolsLabel.setOpaque(false);
        toolsLabel.setText("Tools");
        toolsLabel.setFont(new Font("SEGOE UI", Font.PLAIN, 12));


        thicknessLable.setBounds(220,5,55,15);
        thicknessLable.setOpaque(false);
        thicknessLable.setText("Thickness");
        thicknessLable.setFont(new Font("SEGOE UI", Font.PLAIN, 12));

        thicknessSelector.addChangeListener(this);


        add(toolsLabel);
        add(thicknessLable);
        add(drawTool);
        add(eraserTool);
        add(fillTool);
        add(colorPalette);
        add(thicknessSelector);
    }


    @Override
    public void stateChanged(ChangeEvent e) {
        paintCanvas.setLineThickness(thicknessSelector.getThickness());
    }
}
