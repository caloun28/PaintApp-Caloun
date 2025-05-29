package tools;

import org.junit.jupiter.api.Test;
import panels.PaintCanvas;

import java.awt.image.BufferedImage;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for SaveTool.
 */
class SaveToolTest {

    /**
     * Tests saving the current drawing.
     *
     * Sets a blank canvas image on PaintCanvas,
     * triggers the save action,
     * and verifies that a file named "drawing.dat" is created.
     * The test cleans up by deleting the file afterward.
     */
    @Test
    void saveDrawingTest() {
        PaintCanvas paintCanvas = new PaintCanvas();
        SaveTool saveTool = new SaveTool(paintCanvas);
        BufferedImage canvasImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        paintCanvas.setCanvasImage(canvasImage);

        saveTool.actionPerformed(null);
        File savedFile = new File("drawing.dat");

        assertTrue(savedFile.exists(), "The drawing file should be saved as 'drawing.dat'");

        if (savedFile.exists()) {
            savedFile.delete();
        }
    }
}