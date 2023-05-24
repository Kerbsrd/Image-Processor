package cs3500.model;

import java.awt.image.BufferedImage;

/**
 * An interface that represents an image with a given height, width, matrix of pixels, color system
 * and maximum value of that color system.
 */
public interface IImage {

  /**
   * Get the height of the image.
   *
   * @return the height of the image
   */
  public int getHeight();

  /**
   * Get the width of the image.
   *
   * @return the width of the image
   */
  public int getWidth();

  /**
   * Gets the maximum value that each of the (r,g,b) components can have.
   *
   * @return the maxValue
   */
  public int getMaxValue();

  /**
   * Gets the matrix of pixels of the image.
   *
   * @return the matrix of pixels of the image
   */
  public Pixel[][] getMatrix();


  /**
   * Get a displayable or non-ppm formatted image.
   * turns our image into a buffered one.
   *
   * @return BufferedImage
   */
  public BufferedImage getBuffered();
}