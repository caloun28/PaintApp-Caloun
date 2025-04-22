package Tools;

import Panels.PaintCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FillTool extends JButton implements ActionListener {
    private ImageIcon drawIcon = new ImageIcon("fillTool.png");
    private PaintCanvas paintCanvas;

    public FillTool(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;
        Image scaledImage = drawIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setToolTipText("Fill Tool");

        setBounds(170,25,20,20);
        setBackground(new Color(245, 235, 215));
        setVisible(true);

        addActionListener(this);
        setIcon(scaledIcon);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            if (paintCanvas.getCurrentTool() == ToolType.FILL) {
                paintCanvas.setToolMode(ToolType.NONE);
            } else {
                paintCanvas.setToolMode(ToolType.FILL);
            }
        }
    }
}
