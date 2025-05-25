package tools;

import org.junit.jupiter.api.Test;
import panels.PaintCanvas;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class UndoToolTest {

    @Test
    void saveTest() {
        PaintCanvas paintCanvas = new PaintCanvas();
        BufferedImage testImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = testImage.createGraphics();
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 50, 50);
        g.dispose();
        paintCanvas.setCanvasImage(testImage);

        UndoTool undoTool = new UndoTool(paintCanvas);
        undoTool.save();

        assertEquals(1, undoTool.getHistory().size(), "History should contain one image after save()");
        assertEquals(0, undoTool.getHistoryIndex(), "History index should be 0 after first save");

        BufferedImage savedImage = undoTool.getHistory().getFirst();
        assertNotSame(testImage, savedImage, "Saved image should be a copy, not the original reference");
    }
}