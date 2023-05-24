package cs3500.view;

import java.awt.image.BufferedImage;

import cs3500.model.IImage;
import cs3500.model.IImageProcessorModel;
import cs3500.model.Image;

/**
 * Represents a view of the Image Processor application. This view is a GUI-based view
 * that showcases the various interactive features of the program, such as loading an image,
 * transforming an image, saving an image, etc.
 */
public interface IImageProcessorView {

  /**
   * Renders the current image loaded into the Image Processor model so that the user can see
   * which image they are currently viewing and working on.
   *
   * @param i the Image given
   */
  public void renderImage(IImage i);

  /**
   * Renders a text message on the user's viewing interface that displays a message to the user.
   *
   * @param str the message to be rendered
   */
  public void renderMessage(String str);

  /**
   * Creates a histogram for an image and renders it. A histogram is a table that stores the value-
   * frequency line chart of an image by visualising each of the RGB values and how often each
   * color value appears on the image.
   *
   * @param image the image for which the histogram is to be created
   */
  public void renderHistogram(IImage image);

  /**
   * Updates shown image.
   */
  public void refresh();

  /**
   * Inputs the increment for brightening an image from the user.
   *
   * @return input as a string.
   */
  public String brightenInputByUser();

  /**
   * Input from user for destination/how to refer to it as.
   *
   * @return String from user.
   */
  public String inputByUser();

  /**
   * File chooser for load.
   *
   * @return chosen file as a string.
   */
  public String loadOptions();

  /**
   * Sets the visibility mode of the view to true so that the GUI can be viewed by the user.
   */
  public void makeVisible();

  /**
   * Initialises the values of the histogram chart based on the image that is being displayed to
   * the user
   * @param image the image for which the histogram is to be made
   */
  public void initialiseHistogram(IImage image);
}
