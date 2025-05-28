package tools;

import panels.PaintCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * SaveTool represents a button that allows saving the current drawing
 * from the PaintCanvas to a file.
 * When clicked, it triggers saving of the drawing data to "drawing.dat".
 */
public class SaveTool extends JButton implements ActionListener {

    private PaintCanvas paintCanvas;
    private ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/saveTool.png")));
    private Image scaledImage = icon.getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH);
    private ImageIcon scaledIcon = new ImageIcon(scaledImage);

    /**
     * Constructs a SaveTool button linked to the given PaintCanvas.
     * Sets up the button appearance and action listener.
     *
     * @param paintCanvas the PaintCanvas this tool interacts with
     */
    public SaveTool(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;

        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setToolTipText("Save");
        setIcon(scaledIcon);

        setBounds(8,35,26,26);
        setBackground(new Color(245, 235, 215));
        setVisible(true);

        addActionListener(this);
    }

    /**
     * Handles button click events.
     * If this button is clicked, triggers saving the current drawing
     * to the file "drawing.dat".
     *
     * @param e the ActionEvent triggered by the button click
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        paintCanvas.saveDrawing("drawing.dat");
    }
}
