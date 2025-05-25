package tools;

import org.junit.jupiter.api.Test;
import panels.PaintCanvas;

import java.awt.image.BufferedImage;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class SaveToolTest {

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