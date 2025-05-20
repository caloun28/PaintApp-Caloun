package labels;

import javax.swing.*;
import java.awt.*;

/**
 * ShapeLabel is a custom JLabel used to display the "Shapes" section in the UI.
 * It has a fixed size, border, centered text, and custom font styling.
 */
public class ShapeLabel extends JLabel {

    /**
     * Creates a new ShapeLabel with predefined styling and layout.
     * Sets size, position, text, font, alignment, and border.
     */
    public ShapeLabel() {
        setBorder(BorderFactory.createLineBorder(new Color(182, 165, 131),2,false));
        setBounds(10,519,60,170);
        setVisible(true);
        setLayout(null);
        setText("Shapes");
        setFont(new Font("Segoe UI", Font.PLAIN, 13));
        setVerticalAlignment(SwingConstants.TOP);
        setHorizontalAlignment(SwingConstants.CENTER);
    }
}
