package cs3500;

import java.io.InputStreamReader;
import cs3500.controller.ImageProcessorControllerImpl;
import cs3500.model.IImageProcessorModel;
import cs3500.model.ImageProcessorModelImpl;

/**
 * Represents the class with the main method that runs the actual program.
 */
public class Main {

  /**
   * Main method that runs the program.
   *
   * @param args the given array of String arguments
   */
  public static void main(String[] args) {
    IImageProcessorModel model = new ImageProcessorModelImpl();
    Readable rd = new InputStreamReader(System.in);
    Appendable ap = System.out;
    ImageProcessorControllerImpl controller = new ImageProcessorControllerImpl(model, rd, ap);
    controller.processCommand();
  }
}