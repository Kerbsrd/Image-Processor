package cs3500.model;

/**
 * This class represents a filter for an image. A filter is made up of kernels, which are a 2D array
 * of numbers, having odd dimensions (like 3x3, 5x5...).Given a pixel in the image and a channel,
 * the result of the filter can be computed for that pixel and channel.
 */
public class Kernel {

  //A 2D array of kernels
  Double[][] kMatrix;


  /**
   * Initialises the kernels of a filter.
   *
   * @param kMatrix the 2D matrix of kernels
   */
  public Kernel(Double[][] kMatrix) {
    this.kMatrix = kMatrix;
  }

  /**
   * Gets the matrix kernel of the filter.
   *
   * @return the matrix of the filter
   */
  public Double[][] getkMatrix() {
    return this.kMatrix;
  }

  /**
   * Gets the height of the filter.
   *
   * @return the height of the filter
   */
  public int getHeight() {
    return this.kMatrix.length;
  }

  /**
   * Gets the width of the filter.
   *
   * @return the width of the filter
   */
  public int getWidth() {
    return this.kMatrix[0].length;
  }
}
