import org.junit.Test;

import cs3500.model.IImage;
import cs3500.model.Image;
import cs3500.model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Tests the Images.
 */
public class TestImage {

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

  //null matrix
  @Test(expected = IllegalArgumentException.class)
  public void testImageInvalid1() {
    //a null matrix
    Pixel[][] nullMatrix = null;

    new Image(nullMatrix, 20, 10, 255);
  }

  //zero height
  @Test(expected = IllegalArgumentException.class)
  public void testImageInvalid2() {
    new Image(this.matrix1, 0, 3, 255);
  }

  //negative height
  @Test(expected = IllegalArgumentException.class)
  public void testImageInvalid3() {
    new Image(this.matrix1, -2, 3, 255);
  }

  //zero width
  @Test(expected = IllegalArgumentException.class)
  public void testImageInvalid4() {
    new Image(this.matrix1, 3, 0, 255);
  }

  //negative width
  @Test(expected = IllegalArgumentException.class)
  public void testImageInvalid5() {
    new Image(this.matrix1, 3, -3, 255);
  }

  //maxValue is greater than 255
  @Test(expected = IllegalArgumentException.class)
  public void testImageInvalid6() {
    new Image(this.matrix1, 3, 3, 300);
  }

  //maxValue is less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testImageInvalid7() {
    new Image(this.matrix1, 3, 3, -20);
  }

  @Test
  public void testImageValid() {
    assertEquals(this.matrix1.toString(), new Image(this.matrix1,
        3, 3, 255).getMatrix().toString());
    assertEquals(this.matrix2.toString(), new Image(this.matrix2,
        3, 3, 255).getMatrix().toString());
    assertEquals(this.matrix3.toString(), new Image(this.matrix3,
        3, 3, 255).getMatrix().toString());
  }

  @Test
  public void testGetHeight() {
    IImage img1 = new Image(this.matrix1, 3, 3, 255);
    IImage img2 = new Image(this.matrix2, 3, 3, 255);
    IImage img3 = new Image(this.matrix3, 4, 6, 255);
    assertEquals(3, img1.getHeight());
    assertEquals(3, img2.getHeight());
    assertEquals(4, img3.getHeight());
  }

  @Test
  public void testGetWidth() {
    IImage img1 = new Image(this.matrix1, 3, 3, 255);
    IImage img2 = new Image(this.matrix2, 3, 3, 255);
    IImage img3 = new Image(this.matrix3, 6, 4, 255);
    assertEquals(3, img1.getHeight());
    assertEquals(3, img2.getHeight());
    assertEquals(6, img3.getHeight());
  }

  @Test
  public void testGetMaxValue() {
    IImage img1 = new Image(this.matrix1, 3, 3, 255);
    IImage img2 = new Image(this.matrix2, 3, 3, 23);
    IImage img3 = new Image(this.matrix3, 4, 4, 0);
    assertEquals(255, img1.getMaxValue());
    assertEquals(23, img2.getMaxValue());
    assertEquals(0, img3.getMaxValue());
  }

  @Test
  public void testToString() {
    IImage image = new Image(this.matrix1, 3, 3, 255);
    assertEquals(image.toString(), "255255255255000255000255000002550255025500255255255");
  }
}


