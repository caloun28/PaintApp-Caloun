package functions;

import java.awt.*;

/**
 * Represents a drawable stroke element that can be rendered on a canvas.
 */
public interface Strokes{
    /**
     * Draws the stroke using the given Graphics2D context.
     * @param g2d The Graphics2D context used to render the stroke onto the canvas.
     */
    void draw(Graphics2D g2d);

    /**
     * Sets the color of the stroke.
     * @param color The desired color to apply to the stroke when drawing.
     */
    void setColor(Color color);
}
