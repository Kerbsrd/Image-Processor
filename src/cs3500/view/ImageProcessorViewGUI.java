package cs3500.view;


import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import cs3500.controller.ImageProcessorControllerGUI;
import cs3500.model.Histogram;
import cs3500.model.IImage;
import cs3500.model.IImageProcessorModel;

/**
 * An implementation of the Image Processor View that is a Graphic User Interface (GUI) that
 * supports user interaction and enables the user of the program to see the edits and changes they
 * can make through the image processor program. Also supports the visualisation of error messages.
 */
public class ImageProcessorViewGUI extends JFrame implements IImageProcessorView {

  //main GUI panel
  private final JPanel mainPanel;

  //pop-up that enables user to select input
  private final JFrame inputSelector;

  //enables scrolling
  private final JScrollPane mainScrollPane;

  //the controller that controls the view
  private final ActionListener controller;

  //area of display for an image
  private final JLabel picture;

  //area of display for the histogram
  private JLabel histogramPicture;

  //Input for console commands/inputs
  private TextArea textInputs;

  //select what actions you want to do to an image
  private final JMenuBar mb;

  //the histogram of the current image
  private Histogram histogram;

  /**
   * Constructor that initialises the model and sets the GUI panels.
   *
   * @param model the image model to be used for the GUI
   */
  public ImageProcessorViewGUI(IImageProcessorModel model) {
    super();

    //if the model provided is null, throws an error message
    if (model == null) {
      throw new IllegalArgumentException("No model provided!");
    }

    this.controller = new ImageProcessorControllerGUI(model, this);

    //setting up the background of a default view
    this.mainPanel = new JPanel();
    this.mainPanel.setBackground(Color.GRAY);
    this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.PAGE_AXIS));
    this.add(mainPanel);

    //adds an empty image panel
    this.picture = new JLabel();
    this.histogramPicture = new JLabel();
    this.mainPanel.add(this.picture);
    this.mainPanel.add(this.histogramPicture);


    //adds the scroll bar
    this.mainScrollPane = new JScrollPane(this.picture);
    this.mainPanel.add(mainScrollPane);

    //adds menu bar
    mb = new JMenuBar();

    //setting up what the pop-up should look up
    this.inputSelector = new JFrame("Inputs");
    this.inputSelector.setSize(150, 150);
    this.inputSelector.setLocationRelativeTo(null);
    this.inputSelector.setLayout(new FlowLayout());
    this.inputSelector.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setOperationMenu();
    this.add(mb, BorderLayout.NORTH);
    this.setVisible(true);

//    //TODO add an empty histogram panel
//    this.histogram = new Histogram();
//    JScrollPane histogramScrollPane = new JScrollPane(this.histogram);
//    histogramScrollPane.setPreferredSize(new Dimension(250, 250));
//    this.histogram.setPreferredSize(new Dimension(250, 300));
//    this.histogramPicture.add(histogramScrollPane);
//    this.add(histogramScrollPane, BorderLayout.EAST);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }

  /**
   * Sets the operations on the menus available to the user. Creates the menu for general operations
   * like load, save, etc. as well as modifying operations listed as "Greyscale Color Change" (using
   * RGB greyscale), "Filter" (using blur, sharpen, sepia and grey filters), "Flip" (to flip), etc.
   */
  private void setOperationMenu() {
    JMenu operationMenu = new JMenu("Operations");
    JMenu colorChange = new JMenu("Color Change");
    JMenu greyColors = new JMenu("Greyscale Color Values");
    JMenu filter = new JMenu("Filter");
    JMenu transformation = new JMenu("Flip");
    JMenu file = new JMenu("File");

    JMenuItem save = new JMenuItem("Save");
    save.setActionCommand("Save");
    save.addActionListener(this.controller);
    JMenuItem load = new JMenuItem("Load");
    load.setActionCommand("Load");
    load.addActionListener(this.controller);
    file.add(save);
    file.add(load);

    JMenuItem blur = new JMenuItem("Blur");
    JMenuItem sharpen = new JMenuItem("Sharpen");
    JMenuItem sepia = new JMenuItem("Sepia");
    JMenuItem grey = new JMenuItem("Grey");

    blur.setActionCommand("Blur");
    blur.addActionListener(controller);
    sharpen.setActionCommand("Sharpen");
    sharpen.addActionListener(controller);
    sepia.setActionCommand("Sepia");
    sepia.addActionListener(controller);
    grey.setActionCommand("Grey");
    grey.addActionListener(controller);
    filter.add(blur);
    filter.add(sharpen);
    filter.add(sepia);
    filter.add(grey);

    JMenuItem redGrey = new JMenuItem("Red Greyscale");
    JMenuItem greenGrey = new JMenuItem("Green Greyscale");
    JMenuItem blueGrey = new JMenuItem("Blue Greyscale");
    greyColors.add(redGrey);
    greyColors.add(greenGrey);
    greyColors.add(blueGrey);


    JMenuItem brighten = new JMenuItem("Brighten");

    //greyscale operations
    redGrey.setActionCommand("Red Greyscale");
    redGrey.addActionListener(controller);
    greenGrey.setActionCommand("Green Greyscale");
    greenGrey.addActionListener(controller);
    blueGrey.setActionCommand("Blue Greyscale");
    blueGrey.addActionListener(controller);

    brighten.setActionCommand("Brighten");
    brighten.addActionListener(controller);
    colorChange.add(greyColors);
    colorChange.add(brighten);

    JMenuItem hflip = new JMenuItem("Horizontal Flip");
    JMenuItem vflip = new JMenuItem("Vertical Flip");
    hflip.setActionCommand("Horizontal Flip");
    hflip.addActionListener(controller);
    vflip.setActionCommand("Vertical Flip");
    vflip.addActionListener(controller);
    transformation.add(hflip);
    transformation.add(vflip);

    operationMenu.add(filter);
    operationMenu.add(colorChange);
    operationMenu.add(transformation);
    operationMenu.add(file);

    mb.add(operationMenu);
  }

  /**
   * Renders the current image loaded into the Image Processor model so that the user can see
   * which image they are currently viewing and working on.
   *
   */
  public void renderImage(IImage i) {
    this.picture.setSize(i.getWidth(), i.getHeight());
    this.picture.setIcon(new ImageIcon(i.getBuffered()));

  }

  /**
   * Creates a histogram for an image and renders it. A histogram is a table that stores the value-
   * frequency line chart of an image by visualising each of the RGB values and how often each
   * color value appears on the image.
   *
   * @param image the image for which the histogram is to be created
   */
  @Override
  public void renderHistogram(IImage image) {
    this.histogram = initialiseHistogram(image);
    this.histogramPicture.setSize(image.getWidth(), image.getHeight());
    this.histogramPicture.setIcon((Icon) this.histogram);
    //TODO

  }

  /**
   * Renders a text message on the user's viewing interface that displays a message to the user as
   * a pop-up on the screen.
   *
   * @param str the message to be rendered
   */
  @Override
  public void renderMessage(String str) {
    JOptionPane text = new JOptionPane("Text");
    text.showMessageDialog(new JLabel(), str);
  }

  /**
   * Enables the user to load a valid image into the program. A valid image can in the PNG, JPG,
   * BMP or PPM formats.
   *
   * @return the pathname of the chosen file if a file is selected or null if nothing is selected
   */
  @Override
  public String loadOptions() {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter filter
        = new FileNameExtensionFilter(
        "Allowed files", "jpg", "bpm", "ppm", "png");
    fileChooser.setFileFilter(filter);
    int value = fileChooser.showOpenDialog(this);
    if (value == JFileChooser.APPROVE_OPTION) {
      File f = fileChooser.getSelectedFile();
      return f.getPath();
    }
    return null;
  }

  /**
   * Prompts the user to enter a destination name for the image that they are trying to save so
   * that it can be saved with the given new name.
   *
   * @return string
   */
  @Override
  public String inputByUser() {
    return (String) JOptionPane.showInputDialog(this.inputSelector,
        "What would you life to refer to the image as?",
        "Input", JOptionPane.PLAIN_MESSAGE,
        null, null, null);
  }

  /**
   * Prompts the user to enter a number to brighten or darken the image by.
   *
   * @return string.
   */
  @Override
  public String brightenInputByUser() {
    return (String) JOptionPane.showInputDialog(this.inputSelector,
        "Positive numbers brighten, negative darken. Brighten by?",
        "Input", JOptionPane.PLAIN_MESSAGE,
        null, null, null);
  }

  /**
   * Refresh the program to display an updated image.
   */
  @Override
  public void refresh() {
    this.repaint();
  }

  /**
   * Sets the visibility mode of the view to true so that the GUI can be viewed by the user.
   */
  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  /**
   * Initialises the values of the histogram chart based on the image that is being displayed to
   * the user
   */
  @Override
  public void initialiseHistogram(IImage image) {
    this.histogram.initialiseHistogram(image);
  }

}
