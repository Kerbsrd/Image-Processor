package cs3500.controller.commands;

import cs3500.controller.IProcessorCommands;
import cs3500.model.IImage;
import cs3500.model.IImageProcessorModel;

/**
 * Represents the command to vertically flip an image.
 */
public class VFlip implements IProcessorCommands {

  private final String name;
  private final String destName;

  /**
   * Initialises the name of the given image and the destination name of the modified image.
   *
   * @param name     name of given image
   * @param destName destination name of modified image
   */
  public VFlip(String name, String destName) {
    this.name = name;
    this.destName = destName;
  }

  /**
   * Calls the flip vertically function object upon a model, to vertically flip an image.
   *
   * @param i an image processor model
   */
  @Override
  public void runThis(IImageProcessorModel i) {
    IImage imageToEdit = i.getImage(this.name);
    i.flip(imageToEdit, this.destName, false);
  }
}
