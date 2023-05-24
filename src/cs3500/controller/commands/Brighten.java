package cs3500.controller.commands;

import cs3500.controller.IProcessorCommands;
import cs3500.model.IImage;
import cs3500.model.IImageProcessorModel;

/**
 * Represents the command to brighten an image.
 */
public class Brighten implements IProcessorCommands {

  private final int value;
  private final String name;
  private final String destName;

  /**
   * Initialises the increment/decrement value, the name of the given image and the destination
   * name of the modified image.
   *
   * @param value    the increment
   * @param name     name of given image
   * @param destName destination name of the modified image
   */
  public Brighten(int value, String name, String destName) {
    this.value = value;
    this.name = name;
    this.destName = destName;
  }

  /**
   * Calls the brighten function object upon a model, to brighten an image.
   *
   * @param i an image processor model
   */
  @Override
  public void runThis(IImageProcessorModel i) {
    IImage imageToEdit = i.getImage(this.name);
    i.brightenImage(this.value, imageToEdit, this.destName);
  }
}
