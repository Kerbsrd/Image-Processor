package cs3500.controller.commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.Scanner;

import cs3500.controller.IImageProcessorController;
import cs3500.controller.IProcessorCommands;
import cs3500.controller.ImageProcessorControllerImpl;
import cs3500.model.IImageProcessorModel;

/**
 * Represents the command to execute a list of valid commands from a given text file in the .txt
 * format.
 */
public class RunText implements IProcessorCommands {

  private final String path;
  private StringBuilder sb;

  /**
   * Executes the list of commands on the file at a given path. While a line of command exists,
   * scans the command and executes it.
   *
   * @param path the pathname of the text file
   */
  public RunText(String path) {
    this.path = path;
  }

  /**
   * Runs the text file of commands in the image processor program.
   *
   * @param i an image processor model
   */
  @Override
  public void runThis(IImageProcessorModel i) {
    Scanner sc = null;

    //checks if a .txt file exists at the path
    try {
      sc = new Scanner(new FileInputStream(path));
    } catch (FileNotFoundException e) {
      System.out.println("File " + path + " not found!");
    }

    //while commands exist, execute them by passing them to the controller
    while (sc.hasNext()) {
      String command = sc.nextLine();
      IImageProcessorController controller = new ImageProcessorControllerImpl(i,
          new StringReader(command),
          System.out);
      controller.processCommand();
    }

  }
}

