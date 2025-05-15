package frames;

import panels.ControlPanel;
import panels.PaintCanvas;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

/**
 * Class that makes frame for the app.
 */
public class MainFrame extends JFrame{

    private PaintCanvas paintCanvas = new PaintCanvas();
    private ControlPanel controlPanel = new ControlPanel(paintCanvas);
    private ImageIcon mainIcon = new ImageIcon("res//mainIcon.png");

    /**
     * Constructor that sets the look af main frame, it's size and functions like if it's resizable.
     */
    public MainFrame() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        setTitle("PaintApp");
        setIconImage(mainIcon.getImage());
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
