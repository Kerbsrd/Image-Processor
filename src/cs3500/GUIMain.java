package cs3500;

import cs3500.controller.ImageProcessorControllerGUI;
import cs3500.model.IImageProcessorModel;
import cs3500.model.ImageProcessorModelImpl;
import cs3500.view.IImageProcessorView;
import cs3500.view.ImageProcessorViewGUI;
  /**
   * Represents the class with the main method that runs the GUI program.
   */
  public class GUIMain {

    /**
     * Main method that runs the GUI program.
     *
     * @param args the given array of String arguments
     */
    public static void main(String[] args) {
      IImageProcessorModel model = new ImageProcessorModelImpl();
      //model.load("res/help.ppm", "help");
      IImageProcessorView view = new ImageProcessorViewGUI(model);
      ImageProcessorControllerGUI program = new ImageProcessorControllerGUI(model, view);
      program.runProgram();

    }
}
