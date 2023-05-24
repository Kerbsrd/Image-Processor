import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import cs3500.model.ImageUtil;
//import org.junit.Before;

import cs3500.model.operations.BrightenImageOperation;
import cs3500.model.operations.GreyscaleImageOperation;
import cs3500.model.operations.HorizontalFlipImageOperation;
import cs3500.model.IImage;
import cs3500.model.operations.IImageOperation;
import cs3500.model.IImageProcessorModel;
import cs3500.model.IPixel;
import cs3500.model.Image;
import cs3500.model.ImageProcessorModelImpl;
import cs3500.model.Pixel;
import cs3500.model.operations.VerticalFlipImageOperation;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the model.
 */
public class TestModel {

  //colored pixels
  private Pixel white = new Pixel(255, 255, 255);
  private Pixel black = new Pixel(0, 0, 0);
  private Pixel red = new Pixel(255, 0, 0);
  private Pixel green = new Pixel(0, 255, 0);
  private Pixel blue = new Pixel(0, 0, 255);
  private Pixel yellow = new Pixel(255, 255, 0);
  private Pixel rand1 = new Pixel(12, 45, 67);
  private Pixel rand2 = new Pixel(34, 90, 240);
  private Pixel rand3 = new Pixel(10, 17, 45);
  private Pixel greyRand1 = new Pixel(12, 12, 12);
  private Pixel greyRand2 = new Pixel(56, 56, 56);

  private Pixel[][] matrix1 = new Pixel[][]{{white, red, green},
      {blue, black, blue}, {green, red, white}};
  private Pixel[][] matrix2 = new Pixel[][]{{rand1, rand2, rand3},
      {red, green, blue}, {yellow, white, white}};

  private Pixel[][] matrix3 = new Pixel[][]{{greyRand1, greyRand1, black, white},
      {greyRand1, greyRand1, black, white}, {greyRand2, greyRand1, greyRand2, white},
      {greyRand2, white, black, black}};


  private Pixel[][] matrix = new Pixel[][]{{
      new Pixel(255, 0, 0), new Pixel(12, 45, 67)},
      {new Pixel(0, 0, 255), new Pixel(10, 17, 45)}};
  private Pixel[][] matrixBrightenedBy2 = new Pixel[][]{{
      new Pixel(255, 2, 2), new Pixel(14, 47, 69)},
      {new Pixel(2, 2, 255), new Pixel(12, 19, 47)}};

  private Pixel[][] matrixDarkenedBy2 = new Pixel[][]{{
      new Pixel(253, 0, 0), new Pixel(10, 43, 65)},
      {new Pixel(0, 0, 253), new Pixel(8, 15, 43)}};

  private Pixel[][] matrixGRed = new Pixel[][]{{
      new Pixel(255, 255, 255), new Pixel(12, 12, 12)},
      {new Pixel(0, 0, 0), new Pixel(10, 10, 10)}};

  private Pixel[][] matrixGGreen = new Pixel[][]{{
      new Pixel(0, 0, 0), new Pixel(45, 45, 45)},
      {new Pixel(0, 0, 0), new Pixel(17, 17, 17)}};

  private Pixel[][] matrixGBlue = new Pixel[][]{{
      new Pixel(0, 0, 0), new Pixel(67, 67, 67)},
      {new Pixel(255, 255, 255), new Pixel(45, 45, 45)}};

  private Pixel[][] matrixGValue = new Pixel[][]{{
      new Pixel(255, 255, 255), new Pixel(67, 67, 67)},
      {new Pixel(255, 255, 255), new Pixel(45, 45, 45)}};

  private Pixel[][] matrixGIntensity = new Pixel[][]{{
      new Pixel(85, 85, 85), new Pixel(42, 42, 42)},
      {new Pixel(85, 85, 85), new Pixel(24, 24, 24)}};

  private Pixel[][] matrixGLuma = new Pixel[][]{{
      new Pixel(54, 54, 54), new Pixel(35, 35, 35)},
      {new Pixel(54, 54, 54), new Pixel(22, 22, 22)}};

  private Pixel[][] matrixVertical = new Pixel[][]{{
      new Pixel(0, 0, 255), new Pixel(10, 17, 45)},
      {new Pixel(255, 0, 0), new Pixel(12, 45, 67)}};

  private Pixel[][] matrixHorizontal = new Pixel[][]{{
      new Pixel(12, 45, 67), new Pixel(255, 0, 0)},
      {new Pixel(10, 17, 45), new Pixel(0, 0, 255)}};

  private Pixel[][] matrixGrey = new Pixel[][]{{
      new Pixel(12, 12, 12), new Pixel(3, 3, 3)},
      {new Pixel(255, 255, 255), new Pixel(0, 0, 0)}};

  private Pixel[][] matrixGreyBrightenedBy5 = new Pixel[][]{{
      new Pixel(17, 17, 17), new Pixel(8, 8, 8)},
      {new Pixel(255, 255, 255), new Pixel(5, 5, 5)}};

  private Pixel[][] matrixGreyDarkenedBy5 = new Pixel[][]{{
      new Pixel(7, 7, 7), new Pixel(0, 0, 0)},
      {new Pixel(250, 250, 250), new Pixel(0, 0, 0)}};

  private Pixel[][] matrixGreyVerticalFlip = new Pixel[][]{{
      new Pixel(255, 255, 255), new Pixel(0, 0, 0)},
      {new Pixel(12, 12, 12), new Pixel(3, 3, 3)}};

  private Pixel[][] matrixGreyHorizontalFlip = new Pixel[][]{{
      new Pixel(3, 3, 3), new Pixel(12, 12, 12)},
      {new Pixel(0, 0, 0), new Pixel(255, 255, 255)}};

  //images
  private IImage image1 = new Image(this.matrix1, 3, 3, 255);
  private IImage image2 = new Image(this.matrix2, 3, 3, 255);
  private IImage image3 = new Image(this.matrix3, 4, 4, 255);

  //an image and its modifications
  private IImage image = new Image(this.matrix, 2, 2, 255);
  private IImage imageBrightenedby2 = new Image(this.matrixBrightenedBy2,
      2, 2, 255);
  private IImage imageDarkenedby2 = new Image(this.matrixDarkenedBy2,
      2, 2, 255);
  private IImage imageGRed = new Image(this.matrixGRed, 2, 2, 255);
  private IImage imageGGreen = new Image(this.matrixGGreen, 2, 2, 255);
  private IImage imageGBlue = new Image(this.matrixGBlue, 2, 2, 255);
  private IImage imageGValue = new Image(this.matrixGValue, 2, 2, 255);
  private IImage imageGIntensity = new Image(this.matrixGIntensity, 2, 2, 255);
  private IImage imageGLuma = new Image(this.matrixGLuma, 2, 2, 255);
  private IImage imageVertical = new Image(this.matrixVertical, 2, 2, 255);
  private IImage imageHorizontal = new Image(this.matrixHorizontal, 2, 2, 255);

  //a greyscale image and its modifications
  private IImage greyImage = new Image(this.matrixGrey, 2, 2, 255);
  private IImage greyImageBrightenedby5 = new Image(this.matrixGreyBrightenedBy5,
      2, 2, 255);
  private IImage greyImageDarkenedby5 = new Image(this.matrixGreyDarkenedBy5,
      2, 2, 255);
  private IImage greyImageVerticalFlip = new Image(this.matrixGreyVerticalFlip,
      2, 2, 255);
  private IImage greyImageHorizontalFlip = new Image(this.matrixGreyHorizontalFlip,
      2, 2, 255);

  /*
   * //test valid models
   *
   * @Test public void testConstructorValid() {
   * <p>
   * Map<String, IImage> loadTest = new HashMap<String, IImage>();
   * loadTest.put("img1", this.image1);
   * loadTest.put("img2", this.image2);
   * <p>
   * IImageProcessorModel m1 = new ImageProcessorModelImpl();
   * IImageProcessorModel m2 = new ImageProcessorModelImpl(loadTest);
   * }
   */

  //  /**
  //   * Initiating models to be used in tests.
  //   */
  //  @Before
  //  public void setUp() {
  //
  //  }

  //test invalid model when provided map is null
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalid1() {
    Map<String, IImage> loadTest = null;
    IImageProcessorModel m1 = new ImageProcessorModelImpl(loadTest);
  }

  //test the getImage method to get an image when it exists in loaded images
  @Test
  public void testGetImageExists() {
    Map<String, IImage> loadTest = new HashMap<String, IImage>();
    loadTest.put("img1", this.image1);
    loadTest.put("img2", this.image2);
    loadTest.put("img3", this.image3);

    IImageProcessorModel m1 = new ImageProcessorModelImpl(loadTest);

    assertEquals(this.image1, m1.getImage("img1"));
    assertEquals(this.image2, m1.getImage("img2"));
    assertEquals(this.image3, m1.getImage("img3"));
  }

  //test the getImage method when image does not exist
  @Test(expected = IllegalArgumentException.class)
  public void testGetImageDoesNotExist1() {
    Map<String, IImage> loadTest = new HashMap<String, IImage>();
    loadTest.put("img1", this.image1);
    loadTest.put("img2", this.image2);

    IImageProcessorModel m1 = new ImageProcessorModelImpl(loadTest);

    m1.getImage("img3");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetImageDoesNotExist2() {
    IImageProcessorModel m1 = new ImageProcessorModelImpl();
    m1.getImage("image");
  }

  /*
  //tests addImage()
  @Test
  public void testAddImage() {
    IImageProcessorModel m1 = new ImageProcessorModelImpl();
    m1.addImage(this.image1, "image1");
    m1.addImage(this.image2, "image2");
    m1.addImage(this.image3, "image3");
  }
  */

  //testing the brighten image operation function object

  /*
   * //testing the controller
   *
   * @Test public void testBrightenOperationController() {
   * IImageOperation brighten1 = new BrightenImageOperation(20);
   * IImageOperation brighten2 = new BrightenImageOperation(100);
   * IImageOperation brighten3 = new BrightenImageOperation(255);
   * IImageOperation brighten4 = new BrightenImageOperation(0);
   * IImageOperation brighten5 = new BrightenImageOperation(-1);
   * IImageOperation brighten6 = new BrightenImageOperation(-20);
   * IImageOperation brighten7 = new BrightenImageOperation(-213);
   * }
   */

  //testing run
  @Test
  public void testBrightenOperationRun() {
    IImageOperation brighten1 = new BrightenImageOperation(2);
    assertEquals(this.imageBrightenedby2, brighten1.run(this.image));

    IImageOperation brighten2 = new BrightenImageOperation(-2);
    assertEquals(this.imageDarkenedby2, brighten2.run(this.image));

    IImageOperation brighten3 = new BrightenImageOperation(5);
    assertEquals(this.greyImageBrightenedby5, brighten1.run(this.greyImage));

    IImageOperation brighten4 = new BrightenImageOperation(-5);
    assertEquals(this.greyImageDarkenedby5, brighten1.run(this.greyImage));
  }

  //testing the greyscale image operation function object

  /*
   * //testing the constructor
   *
   * @Test public void testGreyscaleOperationController() {
   * IImageOperation greyscaleRed = new GreyscaleImageOperation(IPixel::getRedValue);
   * IImageOperation greyscaleGreen = new GreyscaleImageOperation(IPixel::getGreenValue);
   * IImageOperation greyscaleBlue = new GreyscaleImageOperation(IPixel::getBlueValue);
   * IImageOperation greyscaleValue = new GreyscaleImageOperation(IPixel::getValue);
   * IImageOperation greyscaleIntensity = new GreyscaleImageOperation(IPixel::getIntensity);
   * IImageOperation greyscaleLuma = new GreyscaleImageOperation(IPixel::getLuma);
   * }
   */

  //testing run
  @Test
  public void testGreyScaleOperationRun() {
    IImageOperation greyscaleRed = new GreyscaleImageOperation(IPixel::getRedValue);
    assertEquals(this.imageGRed, greyscaleRed.run(this.image));

    IImageOperation greyscaleGreen = new GreyscaleImageOperation(IPixel::getGreenValue);
    assertEquals(this.imageGGreen, greyscaleGreen.run(this.image));

    IImageOperation greyscaleBlue = new GreyscaleImageOperation(IPixel::getBlueValue);
    assertEquals(this.imageGBlue, greyscaleBlue.run(this.image));

    IImageOperation greyscaleValue = new GreyscaleImageOperation(IPixel::getValue);
    assertEquals(this.imageGValue, greyscaleValue.run(this.image));

    IImageOperation greyscaleIntensity = new GreyscaleImageOperation(IPixel::getIntensity);
    assertEquals(this.imageGIntensity, greyscaleIntensity.run(this.image));

    IImageOperation greyscaleLuma = new GreyscaleImageOperation(IPixel::getLuma);
    assertEquals(this.imageGLuma, greyscaleLuma.run(this.image));

    //greyscale operations on a greyscale image
    IImageOperation greyscaleGreyRed = new GreyscaleImageOperation(IPixel::getRedValue);
    assertEquals(this.greyImage, greyscaleGreyRed.run(this.greyImage));

    IImageOperation greyscaleGreyGreen = new GreyscaleImageOperation(IPixel::getGreenValue);
    assertEquals(this.greyImage, greyscaleGreyGreen.run(this.greyImage));

    IImageOperation greyscaleGreyBlue = new GreyscaleImageOperation(IPixel::getBlueValue);
    assertEquals(this.greyImage, greyscaleGreyBlue.run(this.greyImage));

    IImageOperation greyscaleGreyValue = new GreyscaleImageOperation(IPixel::getValue);
    assertEquals(this.greyImage, greyscaleGreyValue.run(this.greyImage));

    IImageOperation greyscaleGreyIntensity = new GreyscaleImageOperation(IPixel::getIntensity);
    assertEquals(this.greyImage, greyscaleGreyIntensity.run(this.greyImage));

    IImageOperation greyscaleGreyLuma = new GreyscaleImageOperation(IPixel::getLuma);
    assertEquals(this.greyImage, greyscaleGreyLuma.run(this.greyImage));
  }

  //testing the vertical flip image operation function object

  /*
   * //testing the constructor
   *
   * @Test public void testVerticalFlipOperationController() {
   * IImageOperation vertical = new VerticalFlipImageOperation();
   * }
   */

  //testing run
  @Test
  public void testVerticalFlipOperationRun() {
    IImageOperation vertical = new VerticalFlipImageOperation();
    assertEquals(this.imageVertical, vertical.run(this.image));

    IImageOperation verticalGrey = new VerticalFlipImageOperation();
    assertEquals(this.greyImageVerticalFlip, verticalGrey.run(this.greyImage));
  }

  //testing the horizontal flip image operation function object

  /*
   * //testing the constructor
   *
   * @Test public void testHorizontalFlipOperationController() {
   * IImageOperation horizontal = new VerticalFlipImageOperation();
   * }
   */

  //testing run
  @Test
  public void testHorizontalFlipOperationRun() {
    IImageOperation horizontal = new HorizontalFlipImageOperation();
    assertEquals(this.imageHorizontal, horizontal.run(this.image));

    IImageOperation horizontalGrey = new HorizontalFlipImageOperation();
    assertEquals(this.greyImageHorizontalFlip, horizontalGrey.run(this.greyImage));
  }

  /**
   * Makes sure the matrix actually has values.
   *
   * @param i given image.
   * @return true if all of the pixels are not null, else false.
   */
  public boolean filledPixels(IImage i) {
    Pixel[][] matrix = i.getMatrix();
    boolean result = true;
    for (int row = 0; row < matrix.length; row++) {
      for (int j = 0; j < matrix[0].length; j++) {
        if (matrix[row][j] == null) {
          result = false;
        }
      }
    }
    return result;
  }

  //Testing other image types and that they can be properly read and turned into a ppm format
  //which should go through the helper above and confirm that the pixels have been made
  //like a ppm

  @Test
  public void testPNG() {
    assertEquals(filledPixels(ImageUtil.readBufferedImage("res/thinking.png")), true);
  }

  @Test
  public void testBMP() {
    assertEquals(filledPixels(ImageUtil.readBufferedImage("res/cry.bpm")), true);
  }

  @Test
  public void testJPG() {
    assertEquals(filledPixels(ImageUtil.readBufferedImage("res/flowerboi.jpg")), true);
  }

}
