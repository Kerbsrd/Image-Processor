package cs3500.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import cs3500.controller.commands.Blur;
import cs3500.controller.commands.Grey;
import cs3500.controller.commands.Greyscale;
import cs3500.controller.commands.HFlip;
import cs3500.controller.commands.Load;
import cs3500.controller.commands.RunText;
import cs3500.controller.commands.Save;
import cs3500.controller.commands.Sepia;
import cs3500.controller.commands.Sharpen;
import cs3500.controller.commands.VFlip;
import cs3500.model.IImageProcessorModel;
import cs3500.model.IPixel;
import cs3500.controller.commands.Brighten;


/**
 * This class represents the controller of an interactive image application.
 * This controller offers a simple text interface in which the user can
 * type instructions to manipulate a spreadsheet.
 *
 * <p>This controller works with any Readable to read its inputs and
 * any Appendable to transmit output. This controller directly uses
 * the Appendable object (i.e. there is no official "view")
 */

public class ImageProcessorControllerImpl implements IImageProcessorController {

  private final Readable readable;

  private final Appendable appendable;

  private final IImageProcessorModel processor;

  private final Map<String, Function<Scanner, IProcessorCommands>> knownCommands;

  /**
   * Create a controller to work with the specified sheet (model),
   * readable (to take inputs) and appendable (to transmit output).
   *
   * @param processor  the processor to work with (the model)
   * @param readable   the Readable object for inputs
   * @param appendable the Appendable objects to transmit any output
   */
  public ImageProcessorControllerImpl(IImageProcessorModel processor,
                                      Readable readable, Appendable appendable) {
    if (processor == null || readable == null || appendable == null) {
      throw new IllegalArgumentException("Processor, readable or appendable is null");
    } else {
      this.processor = processor;
      this.appendable = appendable;
      this.readable = readable;
      this.knownCommands = new HashMap<String, Function<Scanner, IProcessorCommands>>();

      knownCommands.put("save", s -> new Save(s.next(), s.next()));
      knownCommands.put("load", s -> new Load(s.next(), s.next()));
      knownCommands.put("red-component", s -> new Greyscale(s.next(), s.next(),
          IPixel::getRedValue, "Red"));
      knownCommands.put("green-component", s -> new Greyscale(s.next(), s.next(),
          IPixel::getGreenValue, "Green"));
      knownCommands.put("blue-component", s -> new Greyscale(s.next(), s.next(),
          IPixel::getBlueValue, "Blue"));
      knownCommands.put("horizontal-flip", s -> new HFlip(s.next(), s.next()));
      knownCommands.put("vertical-flip", s -> new VFlip(s.next(), s.next()));
      knownCommands.put("value", s -> new Greyscale(s.next(), s.next(), IPixel::getValue,
          "Value"));
      knownCommands.put("intensity", s -> new Greyscale(s.next(), s.next(),
          IPixel::getIntensity, "Intensity"));
      knownCommands.put("luma", s -> new Greyscale(s.next(), s.next(), IPixel::getLuma,
          "Luma"));
      knownCommands.put("brighten", s -> new Brighten(s.nextInt(), s.next(), s.next()));
      knownCommands.put("blur", s -> new Blur(s.next(), s.next()));
      knownCommands.put("sharpen", s -> new Sharpen(s.next(), s.next()));
      knownCommands.put("greyscale", s -> new Grey(s.next(), s.next()));
      knownCommands.put("sepia", s -> new Sepia(s.next(), s.next()));
      knownCommands.put("run-text-file", s -> new RunText(s.next()));
    }
  }

  /**
   * Displays the menu of commands to the user.
   * Processes the given command line entered by the user, while there are
   * commands to be read. If the user presses q or enters quit, the program ends.
   */
  public void processCommand() {
    Scanner sc = new Scanner(this.readable);

    while (sc.hasNext()) {
      IProcessorCommands c;
      String in = sc.next();

      //if user enters "quit" or "q" the program quits
      if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
        writeMessage("Thank you for using the program <3");
        return;
      } else { //a program command is entered
        Function<Scanner, IProcessorCommands> cmd =
            knownCommands.getOrDefault(in, null);
        if (cmd == null) {
          writeMessage("Not a valid command, try again. ");
        } else {
          c = cmd.apply(sc);
          c.runThis(this.processor);
        }
      }
    }

  }

  /**
   * Writes a given message to be displayed for the user.
   *
   * @param message the given message
   * @throws IllegalStateException if error occurs while displaying message
   */
  protected void writeMessage(String message) throws IllegalStateException {
    try {
      appendable.append(message);

    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  /**
   * Prints the menu of commands that the user can enter.
   *
   * @throws IllegalStateException if error occurs
   */
  protected void printMenu() throws IllegalStateException {
    writeMessage("For all commands besides save, the second name: dest-image-name"
        + " will be how the program refers to the file henceforth."
        + System.lineSeparator());
    writeMessage(System.lineSeparator()
        + "Supported user instructions are: " + System.lineSeparator());
    writeMessage("load image-path image-name: "
        + "Load an image from the specified path."
        + System.lineSeparator());
    writeMessage("save image-path image-name: "
        + "Save the image with the given name to the specified path "
        + "(should include the file name and extension)."
        + System.lineSeparator());
    writeMessage("red-component image-name dest-image-name: "
        + "Create a greyscale image with the red-component of the given image."
        + System.lineSeparator());
    writeMessage("green-component image-name dest-image-name: "
        + "Create a greyscale image with the green-component of the given image."
        + System.lineSeparator());
    writeMessage("blue-component image-name dest-image-name: "
        + "Create a greyscale image with the blue-component of the given image."
        + System.lineSeparator());
    writeMessage("horizontal-flip image-name dest-image-name: "
        + "Flip a given image horizontally to create a new image."
        + System.lineSeparator());
    writeMessage("vertical-flip image-name dest-image-name: "
        + "Flip an image vertically to create a new image."
        + System.lineSeparator());
    writeMessage("value image-name dest-image-name: "
        + "Modify the value of the given image."
        + System.lineSeparator());
    writeMessage("intensity image-name dest-image-name: "
        + "Modify the intensity of the given image."
        + System.lineSeparator());
    writeMessage("luma image-name dest-image-name: "
        + "Modify the luma of the given image."
        + System.lineSeparator());
    writeMessage("brighten increment image-name dest-image-name: "
        + "Brighten or darken a given image based on a given increment/ decrement value."
        + System.lineSeparator());
    writeMessage("blur image-name dest-image-name: "
        + "Blur a given image."
        + System.lineSeparator());
    writeMessage("sharpen image-name dest-image-name: "
        + "Sharpen a given image."
        + System.lineSeparator());
    writeMessage("greyscale image-name dest-image-name: "
        + "Make a given image greyscale."
        + System.lineSeparator());
    writeMessage("sepia image-name dest-image-name: "
        + "Make a given image sepia."
        + System.lineSeparator());
    writeMessage("run-text-file file-path: "
        + "Run a text file with valid commands."
        + System.lineSeparator());
    writeMessage("quit or 'q': "
        + "Quit the program."
        + System.lineSeparator());

  }

  /**
   * Displays the welcome message to the user.
   *
   * @throws IllegalStateException if an error occurs displaying the message.
   */
  protected void welcomeMessage() throws IllegalStateException {
    writeMessage("Welcome to the program!" + System.lineSeparator());
    printMenu();
  }

}

