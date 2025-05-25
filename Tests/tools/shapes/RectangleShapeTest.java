package tools.shapes;

import org.junit.jupiter.api.Test;
import panels.PaintCanvas;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class RectangleShapeTest {

    @Test
    void finishRectangleTest() {
        PaintCanvas paintCanvas = new PaintCanvas();
        RectangleShape rectangleShape = new RectangleShape(paintCanvas);

        rectangleShape.setStartPoint(new Point(10, 10));
        rectangleShape.setColor(Color.RED);
        rectangleShape.setThickness(1);

        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        rectangleShape.finishRectangle(new Point(30, 30), image);

        int rgb = image.getRGB(10, 10);
        Color pixelColor = new Color(rgb, true);

        assertEquals(Color.RED.getRed(), pixelColor.getRed(), "Top-left pixel R value mismatch");
        assertEquals(Color.RED.getGreen(), pixelColor.getGreen(), "Top-left pixel G value mismatch");
        assertEquals(Color.RED.getBlue(), pixelColor.getBlue(), "Top-left pixel B value mismatch");
    }
}