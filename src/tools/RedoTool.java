package tools;

import functions.Images;
import panels.PaintCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class RedoTool extends JButton implements ActionListener, Images {
    private PaintCanvas paintCanvas;
    private ImageIcon icon = new ImageIcon("redoTool.png");
    private Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    private ImageIcon scaledIcon = new ImageIcon(scaledImage);
    private ArrayList<BufferedImage> redoHistory;
    private int redoIndex = -1;

    public RedoTool(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;
        this.redoHistory = new ArrayList<>();

        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setToolTipText("Redo");
        setBounds(43,60,30,30);
        setBackground(new Color(245, 235, 215));
        setVisible(true);

        setIcon(scaledIcon);
        addActionListener(this);
    }

    public void clear() {
        redoHistory.clear();
        redoIndex = -1;
    }

    public void redo(){
        if (redoIndex >= 0) {
            BufferedImage redoImage = redoHistory.get(redoIndex);
            redoIndex--;

            if (paintCanvas.getUndoTool() != null) {
                paintCanvas.getUndoTool().save();
            }

            paintCanvas.setCanvasImage(copyImage(redoImage));
            paintCanvas.repaint();
        }
    }

    public BufferedImage copyImage(BufferedImage image) {
        BufferedImage copy = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        Graphics2D g = copy.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return copy;
    }

    public void addToRedo(BufferedImage image) {
        while (redoHistory.size() > redoIndex + 1) {
            redoHistory.removeLast();
        }

        redoHistory.add(copyImage(image));
        redoIndex++;
    }

    public void clearHistory() {
        redoHistory.clear();
        redoIndex = -1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            redo();
        }
    }
}
