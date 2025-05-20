package labels;

import javax.swing.*;
import java.awt.*;

public class UndoRedoLabel extends JLabel {

    public UndoRedoLabel() {setBorder(BorderFactory.createLineBorder(new Color(182, 165, 131),2,false));
        setBounds(5,92,70,62);
        setVisible(true);
        setLayout(null);
        setText("Undo|Redo");
        setFont(new Font("Segoe UI", Font.PLAIN, 12));
        setVerticalAlignment(SwingConstants.TOP);
        setHorizontalAlignment(SwingConstants.CENTER);
    }
}
