package tools;

import javax.swing.*;
import java.awt.*;

/**
 * ThicknessSelector is a spinner component that allows users
 * to select the line thickness for drawing tools.
 * It supports thickness values from 1 to 8 with a step of 1.
 */
public class ThicknessSelector extends JSpinner {

    /**
     * Creates a ThicknessSelector with a default thickness value.
     *
     * @param defaultValue the initial thickness value shown in the spinner
     */
    public ThicknessSelector(int defaultValue) {
        super(new SpinnerNumberModel(defaultValue, 1, 8, 1));
        setToolTipText("Thickness");
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1, true));
        setBackground(Color.WHITE);
        setBounds(13, 454, 54, 30);
    }

    /**
     * Returns the currently selected thickness value.
     *
     * @return the thickness as an integer
     */
    public int getThickness() {
        return (int) getValue();
    }

}
