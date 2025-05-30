package tools;

import functions.Images;
import panels.PaintCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

/**
 * RedoTool represents a button that allows redoing
 * the last undone drawing action on the PaintCanvas.
 * When clicked, it restores the canvas to a previous redo state
 * if available.
 */
public class RedoTool extends JButton implements ActionListener, Images {
    private PaintCanvas paintCanvas;
    private ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/redoTool.png")));
    private Image scaledImage = icon.getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH);
    private ImageIcon scaledIcon = new ImageIcon(scaledImage);
    private ArrayList<BufferedImage> redoHistory;
    private int redoIndex = -1;

    /**
     * Constructs a RedoTool button linked to the given PaintCanvas.
     * Sets up the button appearance and action listener.
     *
     * @param paintCanvas the PaintCanvas this tool interacts with
     */
    public RedoTool(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;
        this.redoHistory = new ArrayList<>();

        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setToolTipText("Redo");
        setBounds(43,117,26,26);
        setBackground(new Color(245, 235, 215));
        setVisible(true);

        setIcon(scaledIcon);
        addActionListener(this);
    }

    /**
     * Redoes the last undone action by restoring
     * the canvas image from the redo history.
     * Also saves the current state to the undo history before redoing.
     */
    public void redo(){
        if (redoIndex >= 0) {
            BufferedImage redoImage = redoHistory.get(redoIndex);

            if (paintCanvas.getUndoTool() != null) {
                UndoTool undoTool = paintCanvas.getUndoTool();
                if (undoTool.getHistoryIndex() < undoTool.getHistory().size() - 1) {
                    undoTool.setHistoryIndex(undoTool.getHistoryIndex() + 1);
                }
            }

            paintCanvas.setCanvasImage(copyImage(redoImage));
            paintCanvas.repaint();

            redoIndex--;
        }
    }

    /**
     * Creates a deep copy of the given BufferedImage.
     *
     * @param image the BufferedImage to copy
     * @return a new BufferedImage identical to the original
     */
    public BufferedImage copyImage(BufferedImage image) {
        BufferedImage copy = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        Graphics2D g = copy.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return copy;
    }

    /**
     * Adds a new image state to the redo history.
     * Removes any redo states beyond the current index before adding the new one.
     *
     * @param image the BufferedImage state to add to redo history
     */
    public void addToRedo(BufferedImage image) {
        while (redoHistory.size() > redoIndex + 1) {
            redoHistory.remove(redoHistory.size() - 1);
        }

        redoHistory.add(copyImage(image));
        redoIndex++;
    }

    /**
     * Clears redo history and resets index.
     * Alias for clear().
     */
    public void clearHistory() {
        redoHistory.clear();
        redoIndex = -1;
    }

    /**
     * Handles button click events.
     * If this button is clicked, triggers the redo operation.
     *
     * @param e the ActionEvent triggered by the button click
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            redo();
        }
    }
}
