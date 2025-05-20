package labels;

import javax.swing.*;
import java.awt.*;

/**
 * ShapeLabel is a custom JLabel used to display the Undo and Redo buttons section in the UI.
 * It has a fixed size, border, centered text, and custom font styling.
 */
public class UndoRedoLabel extends JLabel {

    /**
     * Creates a label for Undo and redo button with predefined styling and layout.
     * Sets size, position, text, font, alignment, and border.
     */
    public UndoRedoLabel() {
        setBorder(BorderFactory.createLineBorder(new Color(182, 165, 131),2,false));
        setBounds(5,92,70,62);
        setVisible(true);
        setLayout(null);
        setText("Undo|Redo");
        setFont(new Font("Segoe UI", Font.PLAIN, 12));
        setVerticalAlignment(SwingConstants.TOP);
        setHorizontalAlignment(SwingConstants.CENTER);
    }
}
