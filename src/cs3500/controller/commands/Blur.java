package cs3500.controller.commands;

import cs3500.controller.IProcessorCommands;
import cs3500.model.IImage;
import cs3500.model.IImageProcessorModel;
import cs3500.model.Kernel;

/**
 * Represents the command to Blur an image. An image is blurred by applying a specific
 * blur matrix.
 */
public class Blur implements IProcessorCommands {

  private final String name;
  private final String destName;
  private final Double[][] kernel;

  /**
   * Initializes the name of the given image and the destination name of the modified image.
   *
   * @param name     name of given image
   * @param destName destination name of the modified image
   */
  public Blur(String name, String destName) {
    this.name = name;
    this.destName = destName;
    this.kernel = new Double[][]{
        {0.0625, 0.125, 0.0625},
        {0.125, 0.25, 0.125},
        {0.0625, 0.125, 0.0625}};
  }

  /**
   * Calls the Blur function object upon a model, to blur an image.
   *
   * @param i an image processor model
   */
  @Override
  public void runThis(IImageProcessorModel i) {
    IImage imageToEdit = i.getImage(this.name);
    i.filter("blurred", new Kernel(this.kernel), imageToEdit, this.destName);
  }

}
