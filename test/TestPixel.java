import org.junit.Test;

import cs3500.model.IPixel;
import cs3500.model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the pixel.
 */
public class TestPixel {

  //default pixel
  private IPixel p1 = new Pixel();

  //valid pixels
  private IPixel p2 = new Pixel(255, 30, 53); //this is a coloured pixel
  private IPixel p3 = new Pixel(52, 52, 52); //this is a greyscale pixel
  private IPixel p4 = new Pixel(0, 0, 0);

  @Test
  public void testPixelConstructorValid() {
    assertEquals(255, this.p2.getRedValue());
    assertEquals(30, this.p2.getGreenValue());
    assertEquals(53, this.p2.getBlueValue());

    assertEquals(52, this.p3.getRedValue());
    assertEquals(52, this.p3.getGreenValue());
    assertEquals(52, this.p3.getBlueValue());
  }

  /**
   * Checking if our non-default constructor throws an exception.
   * The test titles explain where they are finding the error,
   * an input too large or negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPixelConstructorInvalidRedOver() {
    //invalid pixels : red
    IPixel p4 = new Pixel(256, 12, 34);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPixelConstructorInvalidRedNeg() {
    IPixel p5 = new Pixel(-1, 12, 34);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPixelConstructorInvalidGreenOver() {
    IPixel p6 = new Pixel(12, 270, 34);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPixelConstructorInvalidGreenNeg() {
    IPixel p7 = new Pixel(12, -12, 34);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPixelConstructorInvalidBlueOver() {
    IPixel p8 = new Pixel(12, 12, 600);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPixelConstructorInvalidBlueNeg() {
    IPixel p9 = new Pixel(12, 12, -3);
  }
  ///end of pixel invalid tests////

  /**
   * Test checking our getRedValue method.
   */
  @Test
  public void testGetRedValue() {
    assertEquals(0, this.p1.getRedValue());
    assertEquals(255, this.p2.getRedValue());
    assertEquals(52, this.p3.getRedValue());
  }

  /**
   * Test checking our getGreenValue method.
   */
  @Test
  public void testGetGreenValue() {
    assertEquals(0, this.p1.getGreenValue());
    assertEquals(30, this.p2.getGreenValue());
    assertEquals(52, this.p3.getGreenValue());
  }

  /**
   * Test checking our getBlueValue method.
   */
  @Test
  public void testGetBlueValue() {
    assertEquals(0, this.p1.getBlueValue());
    assertEquals(53, this.p2.getBlueValue());
    assertEquals(52, this.p3.getBlueValue());
  }

  /**
   * Test checking our getValue method.
   * This method gets the max of the 3 rgb values.
   */
  @Test
  public void testGetValue() {
    assertEquals(0, this.p1.getValue());
    assertEquals(255, this.p2.getValue());
    assertEquals(52, this.p3.getValue());
  }

  /**
   * Test checking our getIntensity method.
   */
  @Test
  public void testGetIntensity() {
    assertEquals(0, this.p1.getIntensity());
    assertEquals(255, this.p2.getIntensity());
    assertEquals(52, this.p3.getIntensity());
  }

  /**
   * Test checking our getLuma method.
   */
  @Test
  public void testGetLuma() {
    assertEquals(0, this.p1.getLuma());
    assertEquals(255, this.p2.getLuma());
    assertEquals(52, this.p3.getLuma());
  }

  /**
   * Test our toString method.
   */
  @Test
  public void testToString() {
    assertEquals("2553053", p2.toString());
    assertEquals("525252b", p3.toString());
    assertEquals("000", p4.toString());
  }

}
