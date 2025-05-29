package panels;

import org.junit.jupiter.api.Test;
import tools.ToolType;
import tools.states.*;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for PaintCanvas.
 */
class PaintCanvasTest {
    PaintCanvas paintCanvas = new PaintCanvas();

    /**
     * Tests setting the tool mode on PaintCanvas.
     *
     * Sets various tool modes and verifies that the current tool and corresponding state
     * are correctly updated. Also verifies that setting null clears the current tool and state.
     */
    @Test
    void setToolMode() {
        paintCanvas.setToolMode(ToolType.DRAW);
        assertEquals(ToolType.DRAW, paintCanvas.getCurrentTool());
        assertTrue(paintCanvas.getCurrentState() instanceof DrawState);

        paintCanvas.setToolMode(ToolType.ERASER);
        assertEquals(ToolType.ERASER, paintCanvas.getCurrentTool());
        assertTrue(paintCanvas.getCurrentState() instanceof EraserState);

        paintCanvas.setToolMode(ToolType.LINE);
        assertEquals(ToolType.LINE, paintCanvas.getCurrentTool());
        assertTrue(paintCanvas.getCurrentState() instanceof LineState);

        paintCanvas.setToolMode(ToolType.RECTANGLE);
        assertEquals(ToolType.RECTANGLE, paintCanvas.getCurrentTool());
        assertTrue(paintCanvas.getCurrentState() instanceof RectangleState);

        paintCanvas.setToolMode(ToolType.FILL);
        assertEquals(ToolType.FILL, paintCanvas.getCurrentTool());
        assertTrue(paintCanvas.getCurrentState() instanceof FillState);

        paintCanvas.setToolMode(ToolType.DROPPER);
        assertEquals(ToolType.DROPPER, paintCanvas.getCurrentTool());
        assertTrue(paintCanvas.getCurrentState() instanceof DropperState);

        paintCanvas.setToolMode(ToolType.ELLIPSE);
        assertEquals(ToolType.ELLIPSE, paintCanvas.getCurrentTool());
        assertTrue(paintCanvas.getCurrentState() instanceof EllipseState);

        paintCanvas.setToolMode(null);
        assertNull(paintCanvas.getCurrentTool());
        assertNull(paintCanvas.getCurrentState());
    }

    /**
     * Tests resizing the canvas image.
     *
     * Creates a red 100x100 image, sets it as the canvas image, resizes it to a larger size,
     * and verifies that the new image has correct dimensions and preserves the red color.
     */
    @Test
    void resizeCanvasTest() {

        BufferedImage originalImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = originalImage.createGraphics();
        g.setColor(Color.RED);
        g.fillRect(0, 0, 100, 100);
        g.dispose();
        paintCanvas.setCanvasImage(originalImage);

        int newWidth = 200;
        int newHeight = 150;
        paintCanvas.resizeCanvasImage(newWidth, newHeight);

        BufferedImage resizedImage = paintCanvas.getCanvasImage();
        assertEquals(newWidth, resizedImage.getWidth());
        assertEquals(newHeight, resizedImage.getHeight());

        Color color = new Color(resizedImage.getRGB(10, 10), true);
        assertEquals(Color.RED.getRGB(), color.getRGB());
    }
}