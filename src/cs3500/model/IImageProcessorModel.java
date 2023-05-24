package cs3500.model;

import java.util.function.Function;

/**
 * Represents the interface for an image processor that takes
 * in a PPM image file and supports a set of operations that can
 * alter the image as per the user's requirements.
 */
public interface IImageProcessorModel {

  /**
   * Gets the specified image from the loaded images, if it exists.
   *
   * @param name the image to get
   * @return the image fetched
   * @throws IllegalArgumentException if image does not exist
   */
  public IImage getImage(String name) throws IllegalArgumentException;

  /**
   * Adds an image into the edited images.
   *
   * @param image    the image to be added
   * @param destName the destination name of the image
   */
  public void addImage(IImage image, String destName);

  /**
   * Brightens an image by the given increment and saves it with the given destination name.
   * Image may be darkened if a negative increment value is passed.
   *
   * @param increment the increment to be brightened by
   * @param given     the given image name
   * @param destName  the name to save the image with
   */
  public void brightenImage(int increment, IImage given, String destName);

  /**
   * Transforms the color of an image applying a greyscale filter to the image and saving it
   * with a given destination name.
   *
   * @param filterName name of the filter
   * @param filter     the filter
   * @param given      the given image name
   * @param destName   the name to save the image with
   */
  public void colorTransformation(String filterName, double[][] filter, IImage given,
                                  String destName);

  /**
   * Applies a filter to a given image and saves it with the given destination image name as a new
   * image.
   *
   * @param filterName name of the filter
   * @param filter     the filter
   * @param given      the given image name
   * @param destName   the name to save the image with
   */
  public void filter(String filterName, Kernel filter, IImage given, String destName);

  /**
   * Greyscales an image according to its red, green or blue value components by applying a
   * specific corresponding function to modify its pixels. Saves the image with a new name,
   *
   * @param func     the function to apply
   * @param given    the given image name
   * @param destName the name to save the image with
   * @param type     the type of greyscale applied
   */
  public void greyScale(Function<IPixel, Integer> func, IImage given, String destName, String type);

  /**
   * Flips a given image either horizontally or vertically as required and saves the new image with
   * the given name.
   *
   * @param given      the given image name
   * @param destName   the name to save the image with
   * @param horizontal true if the image is to be flipped horizontally, false if it is to be flipped
   *                   vertically
   */
  public void flip(IImage given, String destName, boolean horizontal);

  /**
   * Saves an image with a given name to a destination that is specified by a given path. The
   * image file may be in any valid extension format, i.e, PPM, PNG, JPG or BMP.
   *
   * @param path the path to save the file to
   * @param name the name of the image to be saved
   */
  public void save(String path, String name);

  /**
   * Loads a given image at a given filepath into the program and labels it with a given name.
   * Supports any valid image file, i.e, PPM, PNG, JPG or BMP.
   *
   * @param path the path of the image file
   * @param name the name to be given to the image
   */
  public void load(String path, String name);

  /**
   * Gets the current working image that the user is making modifications to.
   *
   * @return the current image
   */
  public IImage getCurrent();
}
