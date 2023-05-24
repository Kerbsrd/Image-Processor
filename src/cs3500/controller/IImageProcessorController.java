package cs3500.controller;

/**
 * Represents an interface for an Image Processor Controller, contains the
 * key function to that is needed for the program to work, which processes the commands
 * inputted.
 */
public interface IImageProcessorController {

  /**
   * Displays the menu of commands to the user.
   * Processes the given command line entered by the user, while there are
   * commands to be read. If the user presses q or enters quit, the program ends.
   */
  public void processCommand();


}
