package Frames;

import Panels.ControlPanel;
import Panels.PaintCanvas;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    private final PaintCanvas paintCanvas = new PaintCanvas();
    ControlPanel controlPanel = new ControlPanel(paintCanvas);


    public MainFrame() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        setTitle("PaintApp");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(254, 255, 233, 255));
        setSize(1920, 1080);
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setLayout(null);


        add(controlPanel);

        add(paintCanvas);

        setVisible(true);
    }
}
