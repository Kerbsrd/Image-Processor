package cs3500.model;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * A class to represent and PPM image, which has a height, width, maxValue of colors, and a matrix
 * to store all the pixels within. A color system has also been provided in the class so that the
 * user cannot call certain functions if the picture is already greyscale.
 */
public class Image implements IImage {

  /**
   * A 2-D array to hold all the pixels (r,g,b values) of an Image.
   */
  private Pixel[][] matrix;

  /**
   * The height of an image / number of rows.
   */
  private final int height;

  /**
   * Width of an image / number of columns.
   */
  private final int width;

  /**
   * Maximum color value the image can have.
   */
  private final int maxValue;

  /**
   * Default constructor to initialise the image, which includes the matrix of pixels that makes up
   * the image, the height, the width and the maximum color value of each (r,g,b) component.
   *
   * @throws IllegalArgumentException if any of the entered fields are null
   */
  public Image(Pixel[][] matrix, int height, int width, int maxValue)
      throws IllegalArgumentException {
    if (matrix == null || height <= 0 || width <= 0 || maxValue < 0 || maxValue > 255) {
      throw new IllegalArgumentException("Invalid image!");
    } else {
      this.matrix = matrix;
      this.height = height;
      this.width = width;
      this.maxValue = maxValue;
    }
  }

  /**
   * Blank image constructor.
   */
  public Image(int maxValue) {
    Pixel[][] temp = new Pixel[500][500];
    Pixel white = new Pixel(255, 255, 255);
    for (int i = 0; i < 500; i++) {
      for (int j = 0; j < 500; j++) {
        temp[i][j] = white;
      }
    }
    this.matrix = temp;
    this.height = 500;
    this.width = 500;
    this.maxValue = maxValue;
  }


  /**
   * Get the height of the image.
   *
   * @return the height of the image
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * Get the width of the image.
   *
   * @return the width of the image
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Gets the maximum value that each of the (r,g,b) components can have.
   *
   * @return the maxValue
   */
  @Override
  public int getMaxValue() {
    return this.maxValue;
  }

  /**
   * Gets the matrix of pixels of the image.
   *
   * @return the matrix of pixels of the image
   */
  @Override
  public Pixel[][] getMatrix() {
    return this.matrix;
  }


  /**
   * Converts an image into a buffered image by converting each pixel of the image into an RGB
   * Color object and creating a buffered image object with each such Color RGB Object.
   *
   * @return the buffered image.
   */
  public BufferedImage getBuffered() {
    BufferedImage bufferedImg = new BufferedImage(this.getWidth(),
        this.getHeight(), BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = 0; j < this.getWidth(); j++) {
        Pixel p = this.getMatrix()[i][j];

        int rgb = (p.getRedValue()<<16)
        + (p.getGreenValue()<< 8)
                + p.getBlueValue();

        bufferedImg.setRGB(j, i, rgb);
      }
    }
    return bufferedImg;
  }
}

