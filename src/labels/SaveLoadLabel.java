package labels;

import javax.swing.*;
import java.awt.*;

public class SaveLoadLabel extends JLabel {

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
