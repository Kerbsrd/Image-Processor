package cs3500.controller.commands;

import cs3500.controller.IProcessorCommands;
import cs3500.model.IImage;
import cs3500.model.IImageProcessorModel;


/**
 * Represents the command to Grey via color transformation an image.
 */
public class Grey implements IProcessorCommands {

  private final String name;
  private final String destName;
  private final double[][] filter;


  /**
   * Initialises the name of the given image, the destination name of the given image and the
   * kernels of the filter used for greyscaling an image.
   *
   * @param name     name of the given image
   * @param destName name of the destination image
   */
  public Grey(String name, String destName) {
    this.name = name;
    this.destName = destName;
    this.filter = new double[][]{
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722}};
  }

  /**
   * Calls the filter image function object upon a model, to greyscale an image.
   *
   * @param i an image processor model
   */
  @Override
  public void runThis(IImageProcessorModel i) {
    IImage imageToEdit = i.getImage(this.name);
    i.colorTransformation("grey", this.filter, imageToEdit, this.destName);
  }
}