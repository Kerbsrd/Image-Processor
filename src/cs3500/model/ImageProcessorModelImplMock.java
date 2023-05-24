package cs3500.model;

import java.util.function.Function;

/**
 * A mock model class used for testing.
 */
public class ImageProcessorModelImplMock implements IImageProcessorModel {

  StringBuilder log;

  /**
   * Initialises the log of the mock model class.
   *
   * @param log history of actions
   */
  public ImageProcessorModelImplMock(StringBuilder log) {
    this.log = log;
  }

  @Override
  public IImage getImage(String name) throws IllegalArgumentException {
    return null;
  }

  @Override
  public void addImage(IImage image, String destName) {
    //not needed for anything
  }

  @Override
  public void brightenImage(int increment, IImage given, String destName) {
    String rep;
    if (increment < 0) {
      rep = " darkened by ";
    } else {
      rep = " brightened by";
    }
    this.log.append("Image").append(rep).append(increment).append(" stored as ").append(
        destName).append(" .").append(System.lineSeparator());
  }


  public void colorTransformation(String filterName, double[][] filter, IImage given,
                                  String destName) {
    this.log.append("Applied ").append(filterName).append(" to image, stored as "
    ).append(destName).append(" .").append(System.lineSeparator());
  }


  @Override
  public void filter(String filterName, Kernel filter, IImage given, String destName) {
    this.log.append(filterName).append(" image, stored as "
    ).append(destName).append(" .").append(System.lineSeparator());
  }

  @Override
  public void greyScale(Function<IPixel, Integer> func, IImage given, String destName,
                        String type) {
    this.log.append("Grey scaled ").append(type).append(" image, stored as "
    ).append(destName).append(".").append(System.lineSeparator());
  }

  @Override
  public void flip(IImage given, String destName, boolean horizontal) {
    String c;
    if (horizontal) {
      c = "horizontally ";
    } else {
      c = "vertically ";
    }
    this.log.append("Image flipped ").append(c).append(" stored as ").append(destName
    ).append(" .").append(System.lineSeparator());
  }

  @Override //modify
  public void save(String path, String name) {
    this.log.append("Image " + name + " saved to "
        + path + " ." + System.lineSeparator());
  }

  @Override
  public void load(String path, String name) {
    this.log.append("Image loaded from ").append(path).append(" stored as "
    ).append(name).append(" .").append(System.lineSeparator());
  }

  @Override
  public IImage getCurrent() {
    return null;
  }

  @Override
  public String toString() {
    return log.toString();
  }
}
