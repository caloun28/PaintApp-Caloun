package labels;

import javax.swing.*;
import java.awt.*;

/**
 * ShapeLabel is a custom JLabel used to display the "Tools" section in the UI.
 * It has a fixed size, border, centered text, and custom font styling.
 */
public class ToolLabel extends JLabel {

    /**
     * Creates a new ToolLabel with predefined styling and layout.
     * Sets size, position, text, font, alignment, and border.
     */
    public ToolLabel() {
        setBorder(BorderFactory.createLineBorder(new Color(182, 165, 131),2,false));
        setBounds(10,174,60,325);
        setVisible(true);
        setLayout(null);
        setText("Tools");
        setFont(new Font("Segoe UI", Font.PLAIN, 13));
        setVerticalAlignment(SwingConstants.TOP);
        setHorizontalAlignment(SwingConstants.CENTER);
    }
}
