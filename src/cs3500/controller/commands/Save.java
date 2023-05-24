package cs3500.controller.commands;


import cs3500.controller.IProcessorCommands;

import cs3500.model.IImageProcessorModel;


/**
 * Represents the command to save an image to a specified path.
 */
public class Save implements IProcessorCommands {

  private final String path;
  private final String name;

  /**
   * Initialises the path and name of the given image.
   *
   * @param path tne path to save the image to
   * @param name name to save image with
   */
  public Save(String path, String name) {
    this.path = path;
    this.name = name;
  }

  /**
   * Saves the image at a given path with a given name.
   * CHANGES HERE: Added the implementation to be able to save a non-PPM image to its required
   * format by creating an if-else block. Old code is in the if-block while new code relating to
   * the ImageIO operations are in the else block.
   *
   * @param model an image processor model
   */
  @Override
  public void runThis(IImageProcessorModel model) {
    model.save(this.path, this.name);
  }
}
