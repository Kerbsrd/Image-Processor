package cs3500.model;

import java.util.Objects;

/**
 * A class that represents a single pixel of an image.
 * Each pixel has three color components: red, green and
 * blue. Each of these components have a value between 0
 * and 255, which determines the overall color of the pixel
 * in the (r,g,b) color scheme.
 * NOTE: If r=g=b, pixel is in greyscale.
 */
public class Pixel implements IPixel {
  private final int[] rgb = new int[3];

  /**
   * Default constructor for a pixel. Initialises the pixel to a black pixel with (r,g,b) values
   * 0 for all.
   */
  public Pixel() {
    this(0, 0, 0);
  }

  /**
   * Constructor to initialise a pixel and its
   * (r,g,b) components.
   *
   * @param r red component
   * @param g green component
   * @param b blue component
   */
  public Pixel(int r, int g, int b) throws IllegalArgumentException {
    if (0 <= r && r <= 255 && 0 <= g && g <= 255 && 0 <= b && b <= 255) {
      rgb[0] = r;
      rgb[1] = g;
      rgb[2] = b;
    } else {
      throw new IllegalArgumentException("Invalid pixel");
    }
  }

  /**
   * Returns the red component of a pixel's
   * (r,g,b) values.
   *
   * @return the red component value
   */
  @Override
  public int getRedValue() {
    return rgb[0];
  }

  /**
   * Returns the green component of a pixel's
   * (r,g,b) values.
   *
   * @return the green component value
   */
  @Override
  public int getGreenValue() {
    return rgb[1];
  }

  /**
   * Returns the blue component of a pixel's
   * (r,g,b) values.
   *
   * @return the blue component value
   */
  @Override
  public int getBlueValue() {
    return rgb[2];
  }

  /**
   * Returns the maximum value out of the three (r,g,b) components.
   *
   * @return the maximum value
   */
  @Override
  public int getValue() {
    return (int) Math.max(Math.max(rgb[0], rgb[1]), rgb[2]);
  }

  /**
   * Returns the average of the three (r,g,b) values to modify intensity.
   *
   * @return the average of the three (r,g,b)
   */
  @Override
  public int getIntensity() {
    return (int) Math.round((rgb[0] + rgb[1] + rgb[2]) / 3);
  }

  /**
   * Returns the weighted sum of the three (r,g,b) components according to the formula:
   * 0.2126r + 0.7152g + 0.0722b.
   *
   * @return 0.2126r + 0.7152g + 0.0722b rounded to the nearest int
   */
  @Override
  public int getLuma() {
    return (int) Math.round((rgb[0] * 0.2126)
        + (rgb[1] * 0.7152)
        + (rgb[2] * 0.0722));
  }


  /**
   * Changes the pixel's (r,g,b) value by replacing each r,g, and b component with the given value.
   *
   * @param value the value to replace each component by
   */
  public Pixel changePixel(int value) {
    return new Pixel(value, value, value);
  }

  /**
   * Returns the (r,g,b) values of a pixel as a string of 3 numbers.
   *
   * @return the String of 3 values
   */
  @Override
  public String getString() {
    String group = "";
    for (int i = 0; i < 2; i++) {
      group = group + this.rgb[i] + " ";
    }
    return group;
  }

  /**
   * Override the equals to base it off of the pixel's rgb values.
   *
   * @param o input object to compare a pixel to.
   * @return true if the hash matches, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pixel pixel = (Pixel) o;
    return pixel.hashCode() == this.hashCode();
  }

  /**
   * Hashcode works differently with arrays, so we need to hash each element.
   *
   * @return an int that represents the pixel.
   */
  @Override
  public int hashCode() {
    int code = Objects.hash(rgb[0], rgb[1], rgb[2]);
    return code;
  }

  /**
   * For testing purposes.
   *
   * @return string representation of a pixel based on its rgb values.
   */
  @Override
  public String toString() {
    String r = String.valueOf(rgb[0]) + String.valueOf(rgb[1]) + String.valueOf(rgb[2]);
    return r;
  }

}

