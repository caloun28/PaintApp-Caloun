package labels;

import javax.swing.*;
import java.awt.*;

/**
 * ShapeLabel is a custom JLabel used to display the Save and Load buttons in the UI.
 * It has a fixed size, border, centered text, and custom font styling.
 */
public class SaveLoadLabel extends JLabel {

    /**
     * Creates a new label with predefined styling and layout.
     * Sets size, position, text, font, alignment, and border.
     */
    public SaveLoadLabel() {
        setBorder(BorderFactory.createLineBorder(new Color(182, 165, 131),2,false));
        setBounds(5,10,70,62);
        setVisible(true);
        setLayout(null);
        setText("Save|Load");
        setFont(new Font("Segoe UI", Font.PLAIN, 12));
        setVerticalAlignment(SwingConstants.TOP);
        setHorizontalAlignment(SwingConstants.CENTER);
    }
}
