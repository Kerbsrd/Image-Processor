package cs3500.controller.commands;

import java.io.IOException;

import cs3500.controller.IProcessorCommands;
import cs3500.model.IImage;
import cs3500.model.IImageProcessorModel;

import cs3500.model.ImageUtil;

/**
 * Represents the command to load an image.
 * CHANGES MADE : runThis() method modified to support other image formats by adding an if-else
 * block which calls the particular ImageUtil method as required.
 */
public class Load implements IProcessorCommands {

  private final String path;
  private final String name;

  /**
   * Initialises the path and name of the given image.
   *
   * @param path path to load image from
   * @param name name to save the image with
   */
  public Load(String path, String name) {
    this.path = path;
    this.name = name;
  }

  /**
   * Calls the load function object upon a model, to load an image.
   *
   * @param i an image processor model
   */
  @Override
  public void runThis(IImageProcessorModel i) {
    //if the image is a PPM file
    if (this.path.contains(".ppm")) {
      try {
        IImage img = ImageUtil.readPPM(path);
        i.addImage(img, name);
        System.out.println("Image " + this.path + " loaded!");
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    //if the image is not a PPM file
    else {
      IImage img = ImageUtil.readBufferedImage(path);
      i.addImage(img, name);
    }
    i.load(this.path, this.name);
  }
}
