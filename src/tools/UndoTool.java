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
 * UndoTool represents a button for undo functionality in the PaintCanvas.
 * It maintains a history of canvas images to revert to previous states.
 * When clicked, it restores the canvas image to the previous snapshot if available.
 * It also integrates with RedoTool to enable redo actions.
 */
public class UndoTool extends JButton implements ActionListener, Images {
    private PaintCanvas paintCanvas;
    private ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/undoTool.png")));
    private Image scaledImage = icon.getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH);
    private ImageIcon scaledIcon = new ImageIcon(scaledImage);
    private ArrayList<BufferedImage> history;
    private int historyIndex = -1;
    private RedoTool redoTool;

    /**
     * Creates an UndoTool button associated with the given PaintCanvas.
     * Initializes the button appearance and event listener.
     *
     * @param paintCanvas the PaintCanvas to manage undo actions on
     */
    public UndoTool(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;
        this.history = new ArrayList<>();

        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setToolTipText("Undo");
        setBounds(8,117,26,26);
        setBackground(new Color(245, 235, 215));
        setVisible(true);

        setIcon(scaledIcon);
        addActionListener(this);
    }

    /**
     * Saves a snapshot of the current canvas image into the undo history.
     * Removes any redo history beyond the current index to maintain consistency.
     * Clears the redo history in the linked RedoTool if it exists.
     */
    public void save(){
        while (history.size() > historyIndex + 1) {
            history.remove(history.size() - 1);
        }
        BufferedImage copy = copyImage(paintCanvas.getCanvasImage());
        history.add(copy);
        historyIndex = history.size() - 1;

        if (redoTool != null) {
            redoTool.clearHistory();
        }

    }

    /**
     * Performs the undo operation by reverting the canvas to the previous image
     * in the history if possible.
     * Also adds the current state to the redo history for possible redo.
     */
    public void undo(){
        if (historyIndex > 0) {
            if (paintCanvas.getRedoTool() != null) {
                BufferedImage currentState = copyImage(paintCanvas.getCanvasImage());
                paintCanvas.getRedoTool().addToRedo(currentState);
            }

            historyIndex--;
            BufferedImage previous = history.get(historyIndex);
            paintCanvas.setCanvasImage(copyImage(previous));
            paintCanvas.repaint();

        }
    }

    /**
     * Creates and returns a deep copy of the given BufferedImage.
     *
     * @param image the BufferedImage to copy
     * @return a new BufferedImage that is a copy of the original
     */
    public BufferedImage copyImage(BufferedImage image){
        BufferedImage copy = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        Graphics2D g = copy.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return copy;
    }

    public ArrayList<BufferedImage> getHistory() {
        return history;
    }

    public int getHistoryIndex() {
        return historyIndex;
    }

    /**
     * Handles button click events. When the UndoTool button is clicked,
     * triggers the undo operation.
     *
     * @param e the ActionEvent triggered by button click
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            undo();
        }
    }

    public void setHistoryIndex(int historyIndex) {
        this.historyIndex = historyIndex;
    }
}
