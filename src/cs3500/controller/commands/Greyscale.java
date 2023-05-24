package cs3500.controller.commands;

import java.util.function.Function;

import cs3500.controller.IProcessorCommands;

import cs3500.model.IImage;
import cs3500.model.IImageProcessorModel;
import cs3500.model.IPixel;


/**
 * Represents the command to modify an image to greyscale. The greyscale image can be created in
 * six different ways by modifying: red-component, green-component, blue-component, value, intensity
 * or luma.
 */
public class Greyscale implements IProcessorCommands {
  private final String name;
  private final String destName;
  private final Function<IPixel, Integer> func;

  private final String type;

  /**
   * Initialises the name of the given image, the destination name of the modified image and
   * the specific function that determines which of the above six operations occurs to modify the
   * image into greyscale.
   *
   * @param name     name of given image
   * @param destName destination name of the modified image
   * @param func     specific function that determines the type of greyscale modification i.e.
   *                 red-component, green-component, blue-component, value, intensity or luma
   */
  public Greyscale(String name, String destName, Function<IPixel, Integer> func, String type) {
    this.name = name;
    this.destName = destName;
    this.func = func;
    this.type = type;
  }

  /**
   * Calls the greyscale function object upon a model, to make the greyscale of an image.
   *
   * @param i an image processor model
   */
  @Override
  public void runThis(IImageProcessorModel i) {
    IImage imageToEdit = i.getImage(this.name);
    i.greyScale(this.func, imageToEdit, this.destName, this.type);
  }
}
