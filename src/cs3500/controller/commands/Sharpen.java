package cs3500.controller.commands;

import cs3500.controller.IProcessorCommands;
import cs3500.model.IImage;
import cs3500.model.IImageProcessorModel;
import cs3500.model.Kernel;

/**
 * Represents the command to Blur an image.
 */
public class Sharpen implements IProcessorCommands {

  private final String name;
  private final String destName;
  private final Double[][] kernel;

  /**
   * * Initialises the name of the given image and the destination name of the modified image.
   *
   * @param name     name of given image
   * @param destName destination name of the modified image
   */
  public Sharpen(String name, String destName) {
    this.name = name;
    this.destName = destName;
    this.kernel = new Double[][]{
        {-0.125, -0.125, -0.125, -0.125, -0.125},
        {-0.125, 0.25, 0.25, 0.25, -0.125},
        {-0.125, 0.25, 1.0, 0.25, -0.125},
        {-0.125, 0.25, 0.25, 0.25, -0.125},
        {-0.125, -0.125, -0.125, -0.125, -0.125}};
  }

  /**
   * Calls the Sharpen function object upon a model, to sharpen an image.
   *
   * @param i an image processor model
   */
  @Override
  public void runThis(IImageProcessorModel i) {
    IImage imageToEdit = i.getImage(this.name);
    i.filter("sharpened", new Kernel(this.kernel), imageToEdit, this.destName);
  }
}
