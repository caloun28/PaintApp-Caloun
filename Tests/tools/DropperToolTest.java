package tools;

import org.junit.jupiter.api.Test;
import panels.PaintCanvas;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for DropperTool.
 */
class DropperToolTest {

    /**
     * Tests the findColor method.
     *
     * Sets a known color on a pixel of the canvas image,
     * uses the dropper tool to pick the color at that pixel,
     * and verifies that the canvas's current color matches the expected color.
     */
    @Test
    void findColor() {
        PaintCanvas canvas = new PaintCanvas();

        BufferedImage image = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        Color expectedColor = new Color(0, 128, 255);
        image.setRGB(5, 5, expectedColor.getRGB());
        canvas.setCanvasImage(image);

        DropperTool dropperTool = new DropperTool(canvas);

        dropperTool.findColor(5, 5);

        Color actualColor = canvas.getCurrentColor();
        assertEquals(expectedColor, actualColor);
    }
}