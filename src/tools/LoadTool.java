package tools;

import panels.PaintCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * LoadTool represents a button that allows loading a saved drawing
 * from a file into the PaintCanvas.
 * When clicked, it triggers loading of drawing data from "drawing.dat".
 */
public class LoadTool extends JButton implements ActionListener {

    private PaintCanvas paintCanvas;
    private ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/loadTool.png")));
    private Image scaledImage = icon.getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH);
    private ImageIcon scaledIcon = new ImageIcon(scaledImage);

    /**
     * Constructs the LoadTool button tied to the specified PaintCanvas.
     *
     * @param paintCanvas The canvas where the drawing will be loaded.
     */
    public LoadTool(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;

        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setToolTipText("Load");
        setIcon(scaledIcon);

        setBounds(43,35,26,26);
        setBackground(new Color(245, 235, 215));
        setVisible(true);

        addActionListener(this);
    }

    /**
     * Loads a drawing from the file "drawing.dat" into the PaintCanvas when the button is pressed.
     *
     * @param e The action event triggered by clicking the button.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        paintCanvas.loadDrawing("drawing.dat");
    }
}
