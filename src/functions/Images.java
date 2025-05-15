package functions;

import java.awt.image.BufferedImage;

/**
 * Defines image utility functions.
 */
public interface Images {
    /**
     * Returns a deep copy of the given BufferedImage.
     * Used for saving and restoring canvas states in undo/redo.
     * @param image The original BufferedImage representing the current canvas state
     * that needs to be duplicated without altering the original.
     * @return A new BufferedImage that is an identical, independent copy of the input.
     */
    BufferedImage copyImage(BufferedImage image);
}
