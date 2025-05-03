package Tools;

import javax.swing.*;
import java.awt.*;

public class ThicknessSelector extends JSpinner {

    public ThicknessSelector(int defaultValue) {
        super(new SpinnerNumberModel(defaultValue, 1, 8, 1));
        setToolTipText("Thickness");
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1, true));
        setBackground(Color.WHITE);
        setBounds(13, 300, 54, 30);
    }

    public int getThickness() {
        return (int) getValue();
    }

}
