package cs3500.controller.commands;

import cs3500.controller.IProcessorCommands;
import cs3500.model.IImage;
import cs3500.model.IImageProcessorModel;


/**
 * Represents the command to Blur an image.
 */
public class Sepia implements IProcessorCommands {

  private final String name;
  private final String destName;
  private final double[][] filter;

  /**
   * Initializes the name of the given image and the destination name of the modified image.
   * Sepia has its own pre-defined matrix.
   *
   * @param name     name of given image
   * @param destName destination name of the modified image
   */
  public Sepia(String name, String destName) {
    this.name = name;
    this.destName = destName;
    this.filter = new double[][]{
        {0.393, 0.769, 0.189},
        {0.349, 0.686, 0.168},
        {0.272, 0.534, 0.131}};
  }

  /**
   * Calls the ColorTransformation function object upon a model, to blur an image.
   *
   * @param i an image processor model
   */
  @Override
  public void runThis(IImageProcessorModel i) {
    IImage imageToEdit = i.getImage(this.name);
    i.colorTransformation("sepia", this.filter, imageToEdit, this.destName);
  }
}
