package tools;

import org.junit.jupiter.api.Test;
import panels.PaintCanvas;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;
class DrawToolTest {

    @Test
    void addPoint() {

        PaintCanvas paintCanvas = new PaintCanvas();
        DrawTool drawTool = new DrawTool(paintCanvas);
        Point point = new Point(10, 20);

        drawTool.addPoint(point);

        assertEquals(1, drawTool.getPoints().size(), "The number of points should be 1");
        assertEquals(point, drawTool.getPoints().getFirst(), "The added point should be 10, 20");
    }
}