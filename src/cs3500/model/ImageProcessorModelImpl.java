package cs3500.model;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.imageio.ImageIO;

/**
 * Represents an implementation of the image processor software. This implementation is a simple
 * image processor that works with PPM image files. It supports a variety of operations that can be
 * used to modify the PPM image, including loading an image, saving an image, creating a greyscale
 * version of the image by using its red-component, green-component, blue-component, value, luma
 * and intensity, flipping the image horizontally or vertically and changing the brightness of the
 * image.
 */
public class ImageProcessorModelImpl implements IImageProcessorModel {

  /**
   * A map to keep track of the images loaded into the program.
   */
  private Map<String, IImage> loaded;

  private IImage current;


  /**
   * Default constructor that initialises the loaded images and the edited images.
   */
  public ImageProcessorModelImpl() {
    this.loaded = new HashMap<>();
  }

  /**
   * Parameterised constructor that takes in a map of loaded images each with a given name.
   *
   * @param loaded the map of Images with given names
   * @throws IllegalArgumentException if the provided map is null
   */
  public ImageProcessorModelImpl(Map<String, IImage> loaded) throws IllegalArgumentException {
    if (loaded == null) {
      throw new IllegalArgumentException("Invalid map of loaded images!");
    } else {
      this.loaded = loaded;
    }
  }

  /**
   * Gets the specified image from the loaded images, as a buffered image.
   *
   * @param name the image to get
   * @return the image fetched
   * @throws IllegalArgumentException if image does not exist
   */
  @Override
  public IImage getImage(String name) throws IllegalArgumentException {
    if (this.loaded.containsKey(name)) {
      return this.loaded.get(name);
    } else {
      throw new IllegalArgumentException("Image not loaded");
    }
  }

  /**
   * Gets the current working image that the user is making modifications to.
   *
   * @return the current image
   */
  @Override
  public IImage getCurrent() {
    return this.current;
  }

  /**
   * Adds an image into the loaded images.
   *
   * @param image    the image to be added
   * @param destName the destination name of the image
   */
  @Override
  public void addImage(IImage image, String destName) {
    this.loaded.put(destName, image);
  }

  /**
   * Brightens an image by the given increment and saves it with the given destination name.
   * Image may be darkened if a negative increment value is passed.
   *
   * @param increment the increment to be brightened by
   * @param given     the given image name
   * @param destName  the name to save the image with
   */
  @Override
  public void brightenImage(int increment, IImage given, String destName) {
    //change the value of each of the components by the given increment
    int ht = given.getHeight();
    int wd = given.getWidth();
    Pixel[][] m = given.getMatrix();

    //new "empty" image
    IImage img = new Image(new Pixel[ht][wd], ht, wd, given.getMaxValue());

    for (int i = 0; i < ht; i++) {
      for (int j = 0; j < wd; j++) {
        Pixel p = m[i][j];
        Pixel newP = new Pixel(brightenPixel(p, Pixel::getRedValue, increment),
            brightenPixel(p, Pixel::getGreenValue, increment),
            brightenPixel(p, Pixel::getBlueValue, increment));

        img.getMatrix()[i][j] = newP;
      }
    }
    this.current = img;
    addImage(img, destName);
  }

  /**
   * Brightens a pixel by a given positive increment.
   *
   * @param p    the pixel to be brightened
   * @param func a function that takes in a pixel and returns an int
   * @return the modified value to brighten a pixel
   */
  public int brightenPixel(Pixel p, Function<Pixel, Integer> func, int increment) {
    if (func.apply(p) + increment > 255) {
      return 255;
    } else if (func.apply(p) + increment < 0) {
      return 0;
    } else {
      return func.apply(p) + increment;
    }
  }

  /**
   * Transforms the color of an image applying a greyscale filter to the image and saving it
   * with a given destination name.
   *
   * @param filterName name of the filter
   * @param filter     the filter
   * @param given      the given image name
   * @param destName   the name to save the image with
   */
  @Override
  public void colorTransformation(String filterName, double[][] filter, IImage given,
                                  String destName) {
    Pixel[][] working = new Pixel[given.getHeight()][given.getWidth()];
    for (int row = 0; row < given.getHeight(); row++) {
      for (int col = 0; col < given.getWidth(); col++) {
        Pixel x = given.getMatrix()[row][col];
        int[] rgb = new int[]{x.getRedValue(), x.getGreenValue(), x.getBlueValue()};
        Pixel applied = new Pixel(changedValue(filter[0], rgb),
            changedValue(filter[1], rgb), changedValue(filter[2], rgb));
        working[row][col] = applied;
      }
    }
    this.current = new Image(working, given.getHeight(), given.getWidth(), given.getMaxValue());
    addImage(new Image(working, given.getHeight(), given.getWidth(),
        given.getMaxValue()), destName);
  }

  /**
   * Changes the value of the (r,g,b) components of a pixel by multiplying them by a list of
   * multipliers. Clamps the new values within the valid RGB range (0 to 255) and returns the
   * weighted sum.
   *
   * @param multiplier the list of values to be multiplied by
   * @param rgb        the (r,g,b) values to be multiplied
   * @return the weighted sum of the (r,g,b) values after multiplication
   */
  private int changedValue(double[] multiplier, int[] rgb) {
    double sum = 0;
    for (int i = 0; i < multiplier.length; i++) {
      sum = sum + (multiplier[i] * rgb[i]);
    }
    return ImageUtil.clamp((int) Math.round(sum));
  }

  /**
   * Applies a filter to a given image and saves it with the given destination image name as a new
   * image.
   *
   * @param filterName name of the filter
   * @param filter     the filter
   * @param given      the given image name
   * @param destName   the name to save the image with
   */
  @Override
  public void filter(String filterName, Kernel filter, IImage given, String destName) {
    Pixel[][] working = new Pixel[given.getHeight()][given.getWidth()];
    for (int row = 0; row < given.getHeight(); row++) {
      for (int column = 0; column < given.getWidth(); column++) {
        Pixel[][] chunk = fillMatrix(given, row, column, filter);
        Pixel p = new Pixel(filterValue(chunk, filter, IPixel::getRedValue),
            filterValue(chunk, filter, IPixel::getGreenValue),
            filterValue(chunk, filter, IPixel::getBlueValue));
        working[row][column] = p;

      }
    }
    this.current = new Image(working, given.getHeight(), given.getWidth(), given.getMaxValue());
    addImage(new Image(working, given.getHeight(), given.getWidth(), given.getMaxValue()),
        destName);
  }

  /**
   * Creates the matrix of an image with respect to a filter being used. Uses the central pixel
   * of a segment to calculate the values of the surrounding pixels and applies the filter to the
   * overall image in this way.
   *
   * @param given  the given image
   * @param cRow   the row index of the centre pixel
   * @param cCol   the column index of the centre pixel
   * @param filter the filter to be applied
   * @return
   */
  private Pixel[][] fillMatrix(IImage given, int cRow, int cCol, Kernel filter) {
    int thickness = (filter.getHeight() - 1) / 2;
    Pixel[][] current = new Pixel[filter.getHeight()][filter.getWidth()];
    for (int i = 0; i < filter.getHeight(); i++) {
      for (int j = 0; j < filter.getWidth(); j++) {
        try {
          current[i][j] = given.getMatrix()[(i + cRow) - thickness][(j + cCol) - thickness];
        } catch (IndexOutOfBoundsException e) {
          current[i][j] = new Pixel(0, 0, 0);
        }
      }
    }
    return current;
  }

  /**
   * Applies and adds up the filtered values.
   *
   * @param cut    the section from the original image that the kernel is being applied on.
   * @param filter is the Kernel, which is an array of doubles.
   * @param func   A function that takes in a pixel and returns an int.
   * @return an int value from applying the kernel.
   */
  private int filterValue(Pixel[][] cut, Kernel filter, Function<Pixel, Integer> func) {
    int sum = 0;
    for (int i = 0; i < filter.getHeight(); i++) {
      for (int j = 0; j < filter.getWidth(); j++) {
        sum = (int) (sum + Math.round(func.apply(cut[i][j]) * filter.getkMatrix()[i][j]));
      }
    }
    return ImageUtil.clamp(sum);
  }

  /**
   * Greyscales an image according to its red, green or blue value components by applying a
   * specific corresponding function to modify its pixels. Saves the image with a new name,
   *
   * @param func     the function to apply
   * @param given    the given image name
   * @param destName the name to save the image with
   * @param type     the type of greyscale applied
   */
  @Override
  public void greyScale(Function<IPixel, Integer> func, IImage given, String destName,
                        String type) {
    int ht = given.getHeight();
    int wd = given.getWidth();
    Pixel[][] m = given.getMatrix();

    //new "empty" image
    IImage img = new Image(new Pixel[ht][wd], ht, wd, given.getMaxValue());

    for (int i = 0; i < ht; i++) {
      for (int j = 0; j < wd; j++) {
        int changedValue = func.apply(m[i][j]);
        Pixel p = m[i][j].changePixel(changedValue);
        img.getMatrix()[i][j] = p;
      }
    }
    this.current = img;
    addImage(img, destName);
  }

  /**
   * Flips a given image either horizontally or vertically as required and saves the new image with
   * the given name.
   *
   * @param given      the given image name
   * @param destName   the name to save the image with
   * @param horizontal true if the image is to be flipped horizontally, false if it is to be flipped
   *                   vertically
   */
  @Override
  public void flip(IImage given, String destName, boolean horizontal) {
    //rows same, columns flip
    //for every row of pixels in rgb, reverse the string set
    int ht = given.getHeight();
    int wd = given.getWidth();
    Pixel[][] m = given.getMatrix();

    //new "empty" image
    IImage img = new Image(new Pixel[ht][wd], ht, wd, given.getMaxValue());

    for (int i = 0; i < ht; i++) {
      for (int j = 0; j < wd; j++) {
        if (horizontal) {
          img.getMatrix()[i][j] = m[i][wd - 1 - j];
        } else {
          img.getMatrix()[i][j] = m[ht - 1 - i][j];
        }
      }
    }
    this.current = img;
    addImage(img, destName);
  }

  /**
   * Saves an image with a given name to a destination that is specified by a given path. The
   * image file may be in any valid extension format, i.e, PPM, PNG, JPG or BMP.
   *
   * @param path the path to save the file to
   * @param name the name of the image to be saved
   */
  @Override
  public void save(String path, String name) {
    IImage img = getImage(name);
    StringBuilder outputFile;

    //if image is of .ppm type
    if (path.contains(".ppm")) {
      outputFile = new StringBuilder("P3\n"
          + "# Created by GIMP version 2.10.32 PNM plug-in\n" + img.getWidth() + " "
          + img.getHeight() + "\n" + img.getMaxValue() + "\n");

      for (int i = 0; i < img.getHeight(); i++) {
        for (int j = 0; j < img.getWidth(); j++) {
          Pixel p = img.getMatrix()[i][j];
          outputFile.append(p.getRedValue()).append("\n");
          outputFile.append(p.getGreenValue()).append("\n");
          outputFile.append(p.getBlueValue()).append("\n");
        }
      }

      String outputString = outputFile.toString();

      try {
        FileWriter w = new FileWriter(path);
        BufferedWriter bw = new BufferedWriter(w);
        bw.write(outputString);
        bw.close();
        System.out.println("Image saved");
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    //if image is not of .ppm type
    else {
      //find what extension is used (for example .png, .jpg or .bmp)
      String extension = path.substring(path.indexOf(".") + 1);

      BufferedImage bufferedImg = img.getBuffered();

      try {
        ImageIO.write(bufferedImg, extension, new File(path));
        System.out.println("Image saved as " + name);
      } catch (IOException e) {
        throw new RuntimeException("Error saving image!");
      }
    }
  }

  /**
   * Loads a given image at a given filepath into the program and labels it with a given name.
   * Supports any valid image file, i.e, PPM, PNG, JPG or BMP.
   *
   * @param path the path of the image file
   * @param name the name to be given to the image
   */
  @Override
  public void load(String path, String name) {
    if (path.contains(".ppm")) {
      try {
        IImage img = ImageUtil.readPPM(path);
        addImage(img, name);
        this.current = img;
        System.out.println("Image " + path + " loaded!");
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    //if the image is not a PPM file
    else {
      IImage img = ImageUtil.readBufferedImage(path);
      addImage(img, name);
      this.current = img;
    }


  }

}
