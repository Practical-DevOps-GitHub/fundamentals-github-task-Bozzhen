public class SteganographyLogic {
    /**
     * Given a GImage containing a hidden message, finds the hidden message
     * contained within it and returns a boolean array containing that message.
     * <p/>
     * A message has been hidden in the input image as follows.  For each pixel
     * in the image, if that pixel has a red component that is an even number,
     * the message value at that pixel is false.  If the red component is an odd
     * number, the message value at that pixel is true.
     *
     * @param source The image containing the hidden message.
     * @return The hidden message, expressed as a boolean array.
     */
    public static boolean[][] findMessage(GImage source) {
        int[][] pixelArray = source.getPixelArray();
        // Create a boolean array to store the hidden message
        boolean[][] message = new boolean[pixelArray.length][pixelArray[0].length];

        // Iterate through each pixel in the image
        for (int i = 0; i < pixelArray.length; i++) {
            for (int j = 0; j < pixelArray[0].length; j++) {
                // Check if the red component is an odd number
                message[i][j] = pixelArray[i][j] % 2 != 0;
            }
        }
        return message;
    }

    /**
     * Hides the given message inside the specified image.
     * <p/>
     * The image will be given to you as a GImage of some size, and the message will
     * be specified as a boolean array of pixels, where each white pixel is denoted
     * false and each black pixel is denoted true.
     * <p/>
     * The message should be hidden in the image by adjusting the red channel of all
     * the pixels in the original image.  For each pixel in the original image, you
     * should make the red channel an even number if the message color is white at
     * that position, and odd otherwise.
     * <p/>
     * You can assume that the dimensions of the message and the image are the same.
     * <p/>
     *
     * @param message The message to hide.
     * @param source  The source image.
     * @return A GImage whose pixels have the message hidden within it.
     */
    public static GImage hideMessage(boolean[][] message, GImage source) {
        int[][] pixelArray = source.getPixelArray();

        // Get the dimensions of         the image
        int width = pixelArray.length;
        int height = pixelArray[0].length;

        // Create a new pixel array for the hidden image
        int[][] hiddenPixelArray = new int[width][height];

        // Iterate through each pixel in the image
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // Copy the original pixel values to the hidden image
                hiddenPixelArray[x][y] = pixelArray[x][y];

                // Adjust the red channel based on the message value
                if (message[x][y]) {
                    // If the message pixel is black, make the red channel odd
                    hiddenPixelArray[x][y] |= 1;
                } else {
                    // If the message pixel is white, make the red channel even
                    hiddenPixelArray[x][y] &= ~1;
                }
            }
        }

        // Create a new GImage with the hidden pixel array

      return new GImage(hiddenPixelArray);
    }
}
