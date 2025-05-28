package tools;

import panels.PaintCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * ColorPalette is a button that opens a color chooser dialog.
 * Allows the user to select a drawing color, which updates the PaintCanvas.
 */
public class ColorPalette extends JButton implements ActionListener {
    private PaintCanvas paintCanvas;
    private ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/colorPalette.png")));

    /**
     * Creates a ColorPalette button linked to the given PaintCanvas.
     *
     * @param paintCanvas The canvas where the selected color will be applied.
     */
    public ColorPalette(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;
        Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setToolTipText("Color Palette");

        setBounds(25,404,30,30);
        setBackground(new Color(245, 235, 215));
        setVisible(true);

        setIcon(scaledIcon);
        addActionListener(this);
    }

    /**
     * Updates the button's background to show the currently selected color.
     *
     * @param color The new color to display on the button.
     */
    public void updateColor(Color color) {
        setBackground(color);
        repaint();
    }

    /**
     * Opens the color chooser dialog on button click.
     * If a color is selected, sets it as the current color in PaintCanvas.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            Color selectedColor = JColorChooser.showDialog(this, "Choose color", Color.BLACK);
            if (selectedColor != null) {
                paintCanvas.setCurrentColor(selectedColor);
            }
        }
    }
}
