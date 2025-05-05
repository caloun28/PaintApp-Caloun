package Functions;

import java.awt.event.MouseEvent;

public interface ToolState {
    void mousePressed(MouseEvent e);

    void mouseReleased(MouseEvent e);

    void mouseDragged(MouseEvent e);

    void mouseMoved(MouseEvent e);
}
