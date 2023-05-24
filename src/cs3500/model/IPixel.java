package cs3500.model;

/**
 * An interface that represents a pixel of an image. Every pixel has a red, green and blue
 * component that makes up its (r,g,b) values.
 */
public interface IPixel {

  /**
   * Returns the red component of a pixel's
   * (r,g,b) values.
   *
   * @return the red component value
   */
  public int getRedValue();

  /**
   * Returns the green component of a pixel's
   * (r,g,b) values.
   *
   * @return the green component value
   */
  public int getGreenValue();

  /**
   * Returns the blue component of a pixel's
   * (r,g,b) values.
   *
   * @return the blue component value
   */
  public int getBlueValue();

  /**
   * Returns the maximum value out of the three (r,g,b) components.
   *
   * @return the maximum value
   */
  public int getValue();

  /**
   * Returns the average of the three (r,g,b) values to modify intensity.
   *
   * @return the average of the three (r,g,b)
   */
  public int getIntensity();

  /**
   * Returns the weighted sum of the three (r,g,b) components according to the formula:
   * 0.2126r + 0.7152g + 0.0722b.
   *
   * @return 0.2126r + 0.7152g + 0.0722b rounded to the nearest int
   */
  public int getLuma();


  /**
   * Returns the (r,g,b) values of a pixel as a string of 3 numbers.
   *
   * @return the String of 3 values
   */
  public String getString();
}