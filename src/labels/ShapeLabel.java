package labels;

import javax.swing.*;
import java.awt.*;

public class ShapeLabel extends JLabel {

    public ShapeLabel() {
        setBorder(BorderFactory.createLineBorder(new Color(182, 165, 131),2,false));
        setBounds(10,400,60,160);
        setVisible(true);
        setLayout(null);
        setText("Shapes");
        setFont(new Font("Segoe UI", Font.PLAIN, 13));
        setVerticalAlignment(SwingConstants.TOP);
        setHorizontalAlignment(SwingConstants.CENTER);
    }
}
