package Tools;

import Functions.Images;
import Panels.PaintCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class UndoTool extends JButton implements ActionListener, Images {
    private PaintCanvas paintCanvas;
    private ImageIcon icon = new ImageIcon("undoTool.png");
    private Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    private ImageIcon scaledIcon = new ImageIcon(scaledImage);
    private ArrayList<BufferedImage> history;
    private int historyIndex = -1;
    private RedoTool redoTool;


    public UndoTool(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;
        this.history = new ArrayList<>();

        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setToolTipText("Undo");
        setBounds(7,10,30,30);
        setBackground(new Color(245, 235, 215));
        setVisible(true);

        setIcon(scaledIcon);
        addActionListener(this);
    }

    public void setRedoTool(RedoTool redoTool) {
        this.redoTool = redoTool;
    }

    public void save(){
        while (history.size() > historyIndex + 1){
            history.removeLast();
        }

        BufferedImage copy = copyImage(paintCanvas.getCanvasImage());
        history.add(copy);
        historyIndex++;

        if (redoTool != null) {
            redoTool.clear();
        }
    }

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


    public BufferedImage copyImage(BufferedImage image){
        BufferedImage copy = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        Graphics2D g = copy.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return copy;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            undo();
        }
    }
}
