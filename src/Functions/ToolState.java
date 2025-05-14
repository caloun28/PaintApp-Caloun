package Functions;

import java.awt.event.MouseEvent;

/**
 * Interface for handling mouse interactions in tool states.
 *
 * Implemented by tools that respond to mouse events on a drawing canvas.
 */
public interface ToolState {

    /**
     * Called when the mouse button is pressed.
     *
     * @param e MouseEvent containing press details.
     */
    void mousePressed(MouseEvent e);

    /**
     * Called when the mouse button is released.
     *
     * @param e MouseEvent containing release details.
     */
    void mouseReleased(MouseEvent e);

    /**
     * Called when the mouse is dragged.
     *
     * @param e MouseEvent containing drag details.
     */
    void mouseDragged(MouseEvent e);

    /**
     * Called when the mouse is moved.
     *
     * @param e MouseEvent containing move details.
     */
    void mouseMoved(MouseEvent e);
}
