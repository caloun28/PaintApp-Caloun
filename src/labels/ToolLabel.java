package labels;

import javax.swing.*;
import java.awt.*;

public class ToolLabel extends JLabel {

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
