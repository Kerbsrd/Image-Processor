import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import cs3500.controller.ImageProcessorControllerImpl;
import cs3500.model.IImageProcessorModel;
import cs3500.model.ImageProcessorModelImplMock;
import cs3500.model.ImageUtil;

import static org.junit.Assert.assertEquals;

import cs3500.controller.Interaction;


import cs3500.model.ImageProcessorModelImpl;


/**
 * Tests for the controller.
 */
public class TestController {

  private Readable inputs;
  private Appendable outputs;
  private IImageProcessorModel model1;

  //private IImageProcessorModel model2;
  // note to self - controller takes in model input output
  private String welcome;

  //colored
  /*
  private Pixel white = new Pixel(255, 255, 255);
  private Pixel black = new Pixel(0, 0, 0);
  private Pixel red = new Pixel(255, 0, 0);
  private Pixel green = new Pixel(0, 255, 0);
  private Pixel blue = new Pixel(0, 0, 255);
  private Pixel maxAvg = new Pixel(85, 85, 85);
  //colors +10
  private Pixel white10 = new Pixel(255, 255, 255);
  private Pixel black10 = new Pixel(10, 10, 10);
  private Pixel red10 = new Pixel(255, 10, 10);
  private Pixel green10 = new Pixel(10, 255, 10);
  private Pixel blue10 = new Pixel(10, 10, 255);
  //colors 10
  private Pixel white010 = new Pixel(245, 245, 245);
  private Pixel black010 = new Pixel(0, 0, 0);
  private Pixel red010 = new Pixel(245, 0, 0);
  private Pixel green010 = new Pixel(0, 245, 0);
  private Pixel blue010 = new Pixel(0, 0, 245);
  //this is image1 as a matrix

  //used for luma matrix result
  private Pixel redLuma = new Pixel(54,54,54);
  private Pixel greenLuma = new Pixel(182,182,182);
  private Pixel blueLuma = new Pixel(18,18,18);
  private Pixel[][] matrix1 = new Pixel[][]{{white, red, green},
      {blue, black, blue}, {green, red, white}};
  //this is image1 red-component as a matrix
  private Pixel[][] matrix2 = new Pixel[][]{{white, white, black},
      {black, black, black}, {black, white, white}};
  //this is image1 green
  private Pixel[][] matrix3 = new Pixel[][]{{white, black, white},
      {black, black, black}, {white, black, white}};
  //this is image1 blue
  private Pixel[][] matrix4 = new Pixel[][]{{white, black, black},
      {white, black, white}, {black, black, white}};
  //this is image1 horizontal
  private Pixel[][] matrix5 = new Pixel[][]{{green, red, white},
      {blue, black, blue}, {white, red, green}};
  //this is image1 vertical
  private Pixel[][] matrix6 = new Pixel[][]{{green, red, white},
      {blue, black, blue}, {white, red, green}};
  //this is image1 value
  private Pixel[][] matrix7 = new Pixel[][]{{white, white, white},
      {white, black, white}, {white, white, white}};
  //this is image1 intensity
  private Pixel[][] matrix8 = new Pixel[][]{{white, maxAvg, maxAvg},
      {maxAvg, black, maxAvg}, {maxAvg, maxAvg, white}};
  //this is image1 luma
  private Pixel[][] matrix9 = new Pixel[][]{{white, redLuma, greenLuma},
      {blueLuma, black, blueLuma}, {greenLuma, redLuma, white}};
  //this is image1 bright +10
  private Pixel[][] matrix10 = new Pixel[][]{{white10, red10, green10},
      {blue10, black10, blue10}, {green10, red10, white10}};
  //this is image1 bright -10
  private Pixel[][] matrix010 = new Pixel[][]{{white010, red010, green010},
      {blue010, black010, blue010}, {green010, red010, white010}};
  private IImage image1 = new Image(this.matrix1, 3, 3, 255);
  private IImage image2 = new Image(this.matrix2, 3, 3, 255);
  */

  /**
   * Initiating models to be used in tests.
   */
  @Before
  public void setUp() {
    welcome = "Welcome to the program!" + System.lineSeparator()
        +
        "For all commands besides save, the second name: dest-image-name will be how the program "
        + "refers to the file henceforth." + System.lineSeparator()
        + "" + System.lineSeparator()
        + "Supported user instructions are: " + System.lineSeparator()
        + "load image-path image-name: Load an image from the specified path."
        + System.lineSeparator()
        + "save image-path image-name: Save the image with the given name to the specified path "
        + "(should include the file name and extension)." + System.lineSeparator()
        + "red-component image-name dest-image-name: Create a greyscale image with the"
        + " red-component of the given image." + System.lineSeparator()
        + "green-component image-name dest-image-name: Create a greyscale image with the "
        + "green-component of the given image." + System.lineSeparator()
        + "blue-component image-name dest-image-name: Create a greyscale image with the "
        + "blue-component of the given image." + System.lineSeparator()
        + "horizontal-flip image-name dest-image-name: Flip a given image horizontally to create a "
        + "new image." + System.lineSeparator()
        + "vertical-flip image-name dest-image-name: Flip an image vertically to create a new "
        + "image." + System.lineSeparator()
        + "value image-name dest-image-name: Modify the value of the given image."
        + System.lineSeparator()
        + "intensity image-name dest-image-name: Modify the intensity of the given image."
        + System.lineSeparator()
        + "luma image-name dest-image-name: Modify the luma of the given image."
        + System.lineSeparator()
        + "brighten increment image-name dest-image-name: Brighten or darken a given image based "
        + "on a given increment/ decrement value." + System.lineSeparator()
        + "blur image-name dest-image-name: Blur a given image." + System.lineSeparator()
        + "sharpen image-name dest-image-name: Sharpen a given image." + System.lineSeparator()
        + "greyscale image-name dest-image-name: Make a given image greyscale."
        + System.lineSeparator()
        + "sepia image-name dest-image-name: Make a given image sepia." + System.lineSeparator()
        + "run-text-file file-path: Run a text file with valid commands." + System.lineSeparator()
        + "quit or 'q': Quit the program." + System.lineSeparator();
    this.model1 = new ImageProcessorModelImpl();

  }

  /**
   * Tests for exception when given null input argument.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNI() {
    outputs = new StringBuilder("");
    ImageProcessorControllerImpl controller
        = new ImageProcessorControllerImpl(this.model1, null, outputs);
  }

  /**
   * Tests for exception when given null output argument.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNV() {
    inputs = new StringReader("");
    ImageProcessorControllerImpl controller
        = new ImageProcessorControllerImpl(this.model1, inputs, null);
  }

  /**
   * Tests for exception when given null model argument.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNM() {
    inputs = new StringReader("");
    outputs = new StringBuilder("");
    ImageProcessorControllerImpl controller
        = new ImageProcessorControllerImpl(null, inputs, outputs);
  }

  /**
   * A test to run different user inputs (without using the console) on an ImageProcessorController.
   *
   * @param model        A model of the game.
   * @param interactions What is in place of user inputs.
   */
  void testProgram(ImageProcessorModelImpl model, Interaction... interactions) {
    StringBuilder fakeUserInput = new StringBuilder();
    StringBuilder expectedOutput = new StringBuilder();

    for (Interaction interaction : interactions) {
      interaction.apply(fakeUserInput, expectedOutput);
    }

    StringReader input = new StringReader(fakeUserInput.toString());
    StringBuilder actualOutput = new StringBuilder();

    ImageProcessorControllerImpl controller = new ImageProcessorControllerImpl(model,
        input, actualOutput);
    controller.processCommand();

    assertEquals(expectedOutput.toString(), actualOutput.toString());
  }

  /**
   * Test quit after a command with a capital to represent case insensitivity.
   * Has a valid command after being quit but won't accept it, thus quit has worked.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testQuit() {
    ImageProcessorModelImpl model = new ImageProcessorModelImpl();
    testProgram(model,
        Interaction.inputs("qUit load res/image1.ppm blah"),
        Interaction.prints("Thank you for using the program <3"));
    model.getImage("blah");
  }

  /**
   * Test quit after an invalid command and with q instead of quit.
   * Purpose of load after quit is to show that another command can't be put in
   * after, so it will throw an IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void testQ() {
    ImageProcessorModelImpl model = new ImageProcessorModelImpl();
    testProgram(model,
        Interaction.inputs("loa res/image1.ppm blah q load res/image1.ppm blah"),
        Interaction.prints("Not a valid command, try again. Not a valid command, "
            + "try again. Not a valid command, try again. Thank you for using the program <3"));
    model.getImage("blah");
  }

  /**
   * loads an image and assert checks if it exists in the model.
   */
  @Test
  public void testLoad() {
    StringBuilder log = new StringBuilder();
    StringBuilder actualOutput = new StringBuilder();
    StringReader in = new StringReader("load res/image1.ppm firstImage quit");

    ImageProcessorModelImplMock model = new ImageProcessorModelImplMock(log);
    ImageProcessorControllerImpl c1 = new ImageProcessorControllerImpl(model, in, actualOutput);
    c1.processCommand();

    assertEquals(model.toString(), "Image loaded from res/image1.ppm stored as " +
        "firstImage ." + System.lineSeparator());
  }

  /**
   * Tests for exception when image not loaded and calls save.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNotLoadedSave() {
    //Testing if save works
    ImageProcessorModelImpl model = new ImageProcessorModelImpl();
    testProgram(model,
        Interaction.inputs("save res/image1.ppm firstImage "),
        Interaction.prints(welcome + "Thank you for using the program <3"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNotLoadedRed() {
    //Testing if save works
    ImageProcessorModelImpl model = new ImageProcessorModelImpl();
    testProgram(model,
        Interaction.inputs("red-component firstImage newOne"),
        Interaction.prints(welcome + "Thank you for using the program <3"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNotLoadedGreen() {
    //Testing if save works
    ImageProcessorModelImpl model = new ImageProcessorModelImpl();
    testProgram(model,
        Interaction.inputs("green-component firstImage newOne"),
        Interaction.prints(welcome + "Thank you for using the program <3"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNotLoadedBlue() {
    //Testing if save works
    ImageProcessorModelImpl model = new ImageProcessorModelImpl();
    testProgram(model,
        Interaction.inputs("blue-component firstImage newOne"),
        Interaction.prints(welcome + "Thank you for using the program <3"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNotLoadedHorizontal() {
    //Testing if save works
    ImageProcessorModelImpl model = new ImageProcessorModelImpl();
    testProgram(model,
        Interaction.inputs("horizontal-flip firstImage newOne firstImage newOne"),
        Interaction.prints(welcome + "Thank you for using the program <3"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNotLoadedVertical() {
    //Testing if save works
    ImageProcessorModelImpl model = new ImageProcessorModelImpl();
    testProgram(model,
        Interaction.inputs("vertical-flip firstImage newOne"),
        Interaction.prints(welcome + "Thank you for using the program <3"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNotLoadedValue() {
    //Testing if save works
    ImageProcessorModelImpl model = new ImageProcessorModelImpl();
    testProgram(model,
        Interaction.inputs("value firstImage newOne"),
        Interaction.prints(welcome + "Thank you for using the program <3"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNotLoadedIntensity() {
    //Testing if save works
    ImageProcessorModelImpl model = new ImageProcessorModelImpl();
    testProgram(model,
        Interaction.inputs("intensity firstImage newOne"),
        Interaction.prints(welcome + "Thank you for using the program <3"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNotLoadedLuma() {
    //Testing if save works
    ImageProcessorModelImpl model = new ImageProcessorModelImpl();
    testProgram(model,
        Interaction.inputs("luma firstImage newOne"),
        Interaction.prints(welcome + "Thank you for using the program <3"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNotLoadedBrightenPlus() {
    //Testing if save works
    ImageProcessorModelImpl model = new ImageProcessorModelImpl();
    testProgram(model,
        Interaction.inputs("brighten 10 firstImage newOne"),
        Interaction.prints(welcome + "Thank you for using the program <3"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNotLoadedBrightenMinus() {
    //Testing if save works
    ImageProcessorModelImpl model = new ImageProcessorModelImpl();
    testProgram(model,
        Interaction.inputs("brighten -10 firstImage newOne"),
        Interaction.prints(welcome + "Thank you for using the program <3"));
  }

  /**
   * Testing save command.
   */
  @Test
  public void testSave() {
    //Testing that save works.
    StringBuilder log = new StringBuilder();
    StringBuilder actualOutput = new StringBuilder();
    StringReader in = new StringReader("load res/image1.ppm firstImage save "
        + "res/woah.ppm firstImage load res/woah.ppm woah quit");

    ImageProcessorModelImplMock model = new ImageProcessorModelImplMock(log);
    ImageProcessorControllerImpl c1 = new ImageProcessorControllerImpl(model, in, actualOutput);
    c1.processCommand();

    assertEquals(model.toString(), "Image loaded from res/image1.ppm stored as firstImage ."
        + System.lineSeparator() + "Image firstImage saved to res/woah.ppm ."
        + System.lineSeparator() + "Image loaded from res/woah.ppm stored as woah ."
        + System.lineSeparator());
  }

  //Testing saving other file types

  @Test
  public void testSavePNG() {
    //Testing that save works.
    ImageProcessorModelImpl model0 = new ImageProcessorModelImpl();
    testProgram(model0,
        Interaction.inputs("load res/thinking.png firstImage save res/egg.png firstImage "
            + "quit"),
        Interaction.prints(welcome + "Thank you for using the program <3"));
    assertEquals(model0.getImage("firstImage"),
        ImageUtil.readBufferedImage("res/egg.png"));
  }

  @Test
  public void testSaveBMP() {
    //Testing that save works.
    ImageProcessorModelImpl model0 = new ImageProcessorModelImpl();
    testProgram(model0,
        Interaction.inputs("load res/cry.bmp firstImage save res/egg.bmp firstImage "
            + "quit"),
        Interaction.prints(welcome + "Thank you for using the program <3"));
    assertEquals(model0.getImage("firstImage"),
        ImageUtil.readBufferedImage("res/egg.bmp"));
  }

  @Test
  public void testSaveJPG() {
    //Testing that save works.
    ImageProcessorModelImpl model0 = new ImageProcessorModelImpl();
    testProgram(model0,
        Interaction.inputs("load res/flowerboi.jpg firstImage save res/egg.jpg firstImage "
            + "quit"),
        Interaction.prints(welcome + "Thank you for using the program <3"));
    assertEquals(model0.getImage("firstImage"),
        ImageUtil.readBufferedImage("res/egg.jpg"));

    //end of save tests
  }

  /**
   * Tests to see if the red component command works.
   */
  @Test
  public void testRedComponent() {
    StringBuilder log = new StringBuilder();
    StringBuilder actualOutput = new StringBuilder();
    StringReader in = new StringReader("load res/image1.ppm firstImage "
        + "red-component firstImage firstRed quit");

    ImageProcessorModelImplMock model = new ImageProcessorModelImplMock(log);
    ImageProcessorControllerImpl c1 = new ImageProcessorControllerImpl(model, in, actualOutput);
    c1.processCommand();

    assertEquals(model.toString(), "Image loaded from res/image1.ppm stored as " +
        "firstImage ." + System.lineSeparator()
        + "Grey scaled Red image, stored as firstRed." + System.lineSeparator());
  }

  /**
   * Tests to see if the green component command works.
   */
  @Test
  public void testGreenComponent() {
    StringBuilder log = new StringBuilder();
    StringBuilder actualOutput = new StringBuilder();
    StringReader in = new StringReader("load res/image1.ppm firstImage "
        + "green-component firstImage firstGreen quit");

    ImageProcessorModelImplMock model = new ImageProcessorModelImplMock(log);
    ImageProcessorControllerImpl c1 = new ImageProcessorControllerImpl(model, in, actualOutput);
    c1.processCommand();

    assertEquals(model.toString(), "Image loaded from res/image1.ppm stored as " +
        "firstImage ." + System.lineSeparator()
        + "Grey scaled Green image, stored as firstGreen." + System.lineSeparator());
  }

  /**
   * Tests to see if blue component command works.
   */
  @Test
  public void testBlueComponent() {

    StringBuilder log = new StringBuilder();
    StringBuilder actualOutput = new StringBuilder();
    StringReader in = new StringReader("load res/image1.ppm firstImage "
        + "blue-component firstImage firstBlue "
        + "quit");

    ImageProcessorModelImplMock model = new ImageProcessorModelImplMock(log);
    ImageProcessorControllerImpl c1 = new ImageProcessorControllerImpl(model, in, actualOutput);
    c1.processCommand();

    assertEquals(model.toString(), "Image loaded from res/image1.ppm stored as " +
        "firstImage ." + System.lineSeparator()
        + "Grey scaled Blue image, stored as firstBlue." + System.lineSeparator());
  }

  /**
   * Tests horizontal flip command.
   */
  @Test
  public void testHorizontalFlip() {
    StringBuilder log = new StringBuilder();
    StringBuilder actualOutput = new StringBuilder();
    StringReader in = new StringReader("load res/image1.ppm firstImage horizontal-flip "
        + "firstImage firstHflip quit");

    ImageProcessorModelImplMock model = new ImageProcessorModelImplMock(log);
    ImageProcessorControllerImpl c1 = new ImageProcessorControllerImpl(model, in, actualOutput);
    c1.processCommand();

    assertEquals(model.toString(), "Image loaded from res/image1.ppm stored as " +
        "firstImage ." + System.lineSeparator()
        + "Image flipped horizontally  stored as firstHflip ." + System.lineSeparator());
  }

  /**
   * Tests veritcal flip command.
   */
  @Test
  public void testVerticalFlip() {
    StringBuilder log = new StringBuilder();
    StringBuilder actualOutput = new StringBuilder();
    StringReader in = new StringReader("load res/image1.ppm firstImage vertical-flip "
        + "firstImage firstVflip quit");

    ImageProcessorModelImplMock model = new ImageProcessorModelImplMock(log);
    ImageProcessorControllerImpl c1 = new ImageProcessorControllerImpl(model, in, actualOutput);
    c1.processCommand();

    assertEquals(model.toString(), "Image loaded from res/image1.ppm stored as " +
        "firstImage ." + System.lineSeparator()
        + "Image flipped vertically  stored as firstVflip ." + System.lineSeparator());
  }

  /**
   * Tests value command.
   */
  @Test
  public void testValue() {
    StringBuilder log = new StringBuilder();
    StringBuilder actualOutput = new StringBuilder();
    StringReader in = new StringReader("load res/image1.ppm firstImage "
        + "value firstImage firstValue quit");

    ImageProcessorModelImplMock model = new ImageProcessorModelImplMock(log);
    ImageProcessorControllerImpl c1 = new ImageProcessorControllerImpl(model, in, actualOutput);
    c1.processCommand();

    assertEquals(model.toString(), "Image loaded from res/image1.ppm stored as " +
        "firstImage ." + System.lineSeparator()
        + "Grey scaled Value image, stored as firstValue." + System.lineSeparator());
  }


  @Test
  public void testIntensity() {

    StringBuilder log = new StringBuilder();
    StringBuilder actualOutput = new StringBuilder();
    StringReader in = new StringReader("load res/image1.ppm firstImage intensity "
        + "firstImage firstI quit");

    ImageProcessorModelImplMock model = new ImageProcessorModelImplMock(log);
    ImageProcessorControllerImpl c1 = new ImageProcessorControllerImpl(model, in, actualOutput);
    c1.processCommand();

    assertEquals(model.toString(), "Image loaded from res/image1.ppm stored as " +
        "firstImage ." + System.lineSeparator()
        + "Grey scaled Intensity image, stored as firstI." + System.lineSeparator());
  }

  @Test
  public void testLuma() {

    StringBuilder log = new StringBuilder();
    StringBuilder actualOutput = new StringBuilder();
    StringReader in = new StringReader("load res/image1.ppm firstImage "
        + "luma firstImage firstValue quit");

    ImageProcessorModelImplMock model = new ImageProcessorModelImplMock(log);
    ImageProcessorControllerImpl c1 = new ImageProcessorControllerImpl(model, in, actualOutput);
    c1.processCommand();

    assertEquals(model.toString(), "Image loaded from res/image1.ppm stored as " +
        "firstImage ." + System.lineSeparator()
        + "Grey scaled Luma image, stored as firstValue." + System.lineSeparator());
  }

  /**
   * Tests brighten command.
   */
  @Test
  public void testBrighten() {
    StringBuilder log = new StringBuilder();
    StringBuilder actualOutput = new StringBuilder();
    StringReader in = new StringReader("load res/image1.ppm firstImage brighten 10 "
        + "firstImage brighter brighten -10 firstImage darker quit");

    ImageProcessorModelImplMock model = new ImageProcessorModelImplMock(log);
    ImageProcessorControllerImpl c1 = new ImageProcessorControllerImpl(model, in, actualOutput);
    c1.processCommand();

    assertEquals(model.toString(), "Image loaded from res/image1.ppm stored as " +
        "firstImage ." + System.lineSeparator()
        + "Image brightened by10 stored as brighter ." + System.lineSeparator()
        + "Image darkened by -10 stored as darker ." + System.lineSeparator());
  }

  /**
   * Tests Blur command.
   */
  @Test
  public void testBlur() {
    StringBuilder log = new StringBuilder();
    StringBuilder actualOutput = new StringBuilder();
    StringReader in = new StringReader("load res/help.ppm firstImage blur firstImage blurred"
        + " quit");

    ImageProcessorModelImplMock model = new ImageProcessorModelImplMock(log);
    ImageProcessorControllerImpl c1 = new ImageProcessorControllerImpl(model, in, actualOutput);
    c1.processCommand();

    assertEquals(model.toString(), "Image loaded from res/help.ppm stored as " +
        "firstImage ." + System.lineSeparator()
        + "blurred image, stored as blurred ." + System.lineSeparator());
  }

  /**
   * Tests Sharpen command.
   */
  @Test
  public void testSharpen() {
    StringBuilder log = new StringBuilder();
    StringBuilder actualOutput = new StringBuilder();
    StringReader in = new StringReader("load res/help.ppm firstImage sharpen firstImage "
        + "sharpened quit");

    ImageProcessorModelImplMock model = new ImageProcessorModelImplMock(log);
    ImageProcessorControllerImpl c1 = new ImageProcessorControllerImpl(model, in, actualOutput);
    c1.processCommand();

    assertEquals(model.toString(), "Image loaded from res/help.ppm stored as " +
        "firstImage ." + System.lineSeparator()
        + "sharpened image, stored as sharpened ." + System.lineSeparator());
  }

  /**
   * Tests Sepia command.
   */
  @Test
  public void testSepia() {
    StringBuilder log = new StringBuilder();
    StringBuilder actualOutput = new StringBuilder();
    StringReader in = new StringReader("load res/help.ppm firstImage sepia firstImage "
        + "sepiaApplied quit");

    ImageProcessorModelImplMock model = new ImageProcessorModelImplMock(log);
    ImageProcessorControllerImpl c1 = new ImageProcessorControllerImpl(model, in, actualOutput);
    c1.processCommand();

    assertEquals(model.toString(), "Image loaded from res/help.ppm stored as " +
        "firstImage ." + System.lineSeparator()
        + "Applied sepia to image, stored as sepiaApplied ." + System.lineSeparator());
  }

  /**
   * Tests ReadFile command.
   */
  @Test
  public void testReadFile() {
    ImageProcessorModelImpl model = new ImageProcessorModelImpl();
    testProgram(model,
        Interaction.inputs("run-text-file res/scriptcommands.txt load res/lumahelp lumahelp "
            + "quit"),
        Interaction.prints(welcome + "Thank you for using the program <3"));
    //add ten test (has values that cap off pos)
    assertEquals(model.getImage("luma-pic"),
        model.getImage("lumahelp"));
  }

}

