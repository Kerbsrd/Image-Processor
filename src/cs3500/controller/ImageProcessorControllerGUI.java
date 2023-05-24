package cs3500.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cs3500.controller.commands.Blur;
import cs3500.controller.commands.Brighten;
import cs3500.controller.commands.Grey;
import cs3500.controller.commands.Greyscale;
import cs3500.controller.commands.HFlip;
import cs3500.controller.commands.Load;
import cs3500.controller.commands.Save;
import cs3500.controller.commands.Sepia;
import cs3500.controller.commands.Sharpen;
import cs3500.controller.commands.VFlip;
import cs3500.model.IImageProcessorModel;
import cs3500.model.IPixel;
import cs3500.view.IImageProcessorView;

/**
 * An implementation of a controller for the GUI-based program that controls the model and the
 * GUI view by acting as an action-listener object.
 */
public class ImageProcessorControllerGUI implements ActionListener {

  private IImageProcessorView view;
  private IImageProcessorModel model;
  private String currentName;
  private String destinationName;


  /**
   * Initialises the fields of the controller by setting the given model and the given GUI based
   * view.
   *
   * @param model the model
   * @param view  the GUI view
   */
  public ImageProcessorControllerGUI(IImageProcessorModel model, IImageProcessorView view) {
    this.model = model;
    this.view = view;
     this.currentName = "";
     this.destinationName = "";
  }


  public void runProgram() {
    this.view.refresh();
  }


  /**
   * Overrides the method that checks what action has been performed by the user.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {

      //if blur is called
      case "Blur":
        try {
          this.view.renderMessage("Blurring image...");
          String s0 = this.view.inputByUser();
          IProcessorCommands blur = new Blur(this.currentName, s0);
          blur.runThis(this.model);
          this.currentName = s0; //reset current name for other commands
          this.view.renderImage(this.model.getImage(s0)); //refresh the image
          this.view.renderMessage("Image blurred");
        } catch (IllegalArgumentException e1) {
          this.view.renderMessage("Blur failed");
        }
        break;

      //if sharpen is called
      case "Sharpen":
        try {
          this.view.renderMessage("Sharpening image...");
          String s0 = this.view.inputByUser();
          IProcessorCommands sharpen = new Sharpen(this.currentName, s0);
          sharpen.runThis(this.model);
          this.currentName = s0;
          this.view.renderImage(this.model.getImage(s0));
          this.view.renderMessage("Image sharpened");
        } catch (IllegalArgumentException e2) {
          this.view.renderMessage("Sharpen failed");
        }
        break;

      //if sepia is called
      case "Sepia":
        try {
          this.view.renderMessage("Making image sepia...");
          String s0 = this.view.inputByUser();
          IProcessorCommands sepia = new Sepia(this.currentName, s0);
          sepia.runThis(this.model);
          this.currentName = s0;
          this.view.renderImage(this.model.getImage(s0));
          this.view.renderMessage("Sepia filter applied");
        } catch (IllegalArgumentException e3) {
          this.view.renderMessage("Sepia failed");
        }
        break;

      //if grey is called
      case "Grey":
        try {
          this.view.renderMessage("Greyscaling image...");
          String s0 = this.view.inputByUser();
          IProcessorCommands grey = new Grey(this.currentName, s0);
          grey.runThis(this.model);
          this.currentName = s0;
          this.view.renderImage(this.model.getImage(s0));
          this.view.renderMessage("Image brightened");
        } catch (IllegalArgumentException e4) {
          this.view.renderMessage("Brighten failed");
        }
        break;

      //if greyscale red is called
      case "Red Greyscale":
        try {
          this.view.renderMessage("Greyscaling image with red component...");
          String s0 = this.view.inputByUser();
          IProcessorCommands redG = new Greyscale(this.currentName, s0,
                  IPixel::getRedValue, "Red");
          redG.runThis(this.model);
          this.currentName = s0;
          this.view.renderMessage("Image greyscaled with red");
        } catch (IllegalArgumentException e5) {
          this.view.renderMessage("Image greyscaled with red failed");
        }
        break;

      //if greyscale green is called
      case "Green Greyscale":
        try {
          this.view.renderMessage("Greyscaling image with green component...");
          String s0 = this.view.inputByUser();
          IProcessorCommands greenG = new Greyscale(this.currentName, s0,
                  IPixel::getGreenValue, "Green");
          greenG.runThis(this.model);
          this.currentName = s0;
          this.view.renderMessage("Image greyscaled with green");
        } catch (IllegalArgumentException e6) {
          this.view.renderMessage("Brighten failed");
        }
        break;

      //if greyscale blue is called
      case "Blue Greyscale":
        try {
          this.view.renderMessage("Greyscaling image with blue component...");
          String s0 = this.view.inputByUser();
          IProcessorCommands blueG = new Greyscale(this.currentName, s0,
                  IPixel::getBlueValue, "Blue");
          blueG.runThis(this.model);
          this.currentName = s0;
          this.view.renderMessage("Image greyscaled with blue");
        } catch (IllegalArgumentException e7) {
          this.view.renderMessage("Brighten failed");
        }
        break;

      //if brighten is called
      case "Brighten":
        try {
          this.view.renderMessage("Brightening image...");
          String s0 = this.view.inputByUser();
          IProcessorCommands brighten =
                  new Brighten(Integer.parseInt(this.view.brightenInputByUser()),
                  this.currentName, s0);
          brighten.runThis(this.model);
          this.currentName = s0;
          this.view.renderImage(this.model.getImage(s0));
          this.view.renderMessage("Image brightened");
        } catch (IllegalArgumentException e8) {
          this.view.renderMessage("Brighten failed");
        }
        break;

      //horizontal flip is called
      case "Horizontal Flip":
        try {
          this.view.renderMessage("Horizontally flipping image...");
          String s0 = this.view.inputByUser();
          IProcessorCommands hflip = new HFlip(this.currentName, s0);
          hflip.runThis(this.model);
          this.currentName = s0;
          this.view.renderImage(this.model.getImage(s0));
          this.view.renderMessage("Image flipped horizontally");
        } catch (IllegalArgumentException e9) {
          this.view.renderMessage("Horizontal flip failed");
        }
        break;

      //vertical flip is called
      case "Vertical Flip":
        try {
          this.view.renderMessage("Vertically flipping image...");
          String s0 = this.view.inputByUser();
          IProcessorCommands vflip = new VFlip(this.currentName, s0);
          vflip.runThis(this.model);
          this.currentName = s0;
          this.view.renderImage(this.model.getImage(s0));
          this.view.renderMessage("Image flipped vertically");
        } catch (IllegalArgumentException e9) {
          this.view.renderMessage("Vertically flip failed");
        }
        break;

      //load is called
      case "Load":
        try {
          String s = this.view.loadOptions();
          String name = this.view.inputByUser();
          this.currentName = name;
          IProcessorCommands load = new Load(s, name);
          load.runThis(this.model);
          view.renderImage(this.model.getImage(name));
          this.view.renderMessage("Loading file...");
        } catch (IllegalArgumentException e10) {
          this.view.renderMessage("Load failed");
        }
        break;

      //save is called
      case "Save":
        try {
          this.view.renderMessage("Saving image...");
          String s0 = this.view.inputByUser();
          IProcessorCommands save = new Save(s0, this.currentName);
          save.runThis(this.model);
        } catch (IllegalArgumentException e11) {
          this.view.renderMessage("Save failed :(");
        }
        break;

      default:
        throw new IllegalArgumentException("Error!");
    }
  }
}
