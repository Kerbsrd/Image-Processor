package cs3500.model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import javax.imageio.ImageIO;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {


  /**
   * Read an image file in the PPM format and return the file as an IImage.
   *
   * @param filename the path of the file.
   * @return the image found as an IImage
   */
  public static IImage readPPM(String filename) throws FileNotFoundException {
    Scanner sc = null;
    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }

    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();


    //create an image in the form of a matrix

    Pixel[][] matrix = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();

        matrix[i][j] = new Pixel(r, g, b);
      }
    }

    IImage image = new Image(matrix, height, width, maxValue);

    return image;
  }

  /**
   * Reads a given image in any non-PPM file format such as PNG, JPG, BMP, etc. Takes in a given
   * file path and returns the image found at the path as an IImage. If no file is found, throws
   * an error.
   * CHANGES MADE : This method has been added to support other file formats to be saved to the
   * program.
   *
   * @param filename the path of the file to be read
   * @return the image found as an IImage
   */
  public static IImage readBufferedImage(String filename) {
    try {
      BufferedImage image = ImageIO.read(new File(filename));
      Pixel[][] matrix = new Pixel[image.getHeight()][image.getWidth()];

      for (int i = 0; i < image.getWidth(); i++) {
        for (int j = 0; j < image.getHeight(); j++) {
          Color c = null;
          try {
            //gets the rgb colors
            c = new Color(image.getRGB(i, j));
          } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds!");
          }

          int r = c.getRed();
          int g = c.getGreen();
          int b = c.getBlue();
          matrix[j][i] = new Pixel(r, g, b);
        }
      }
      //creates new image to return with the matrix obtained
      IImage newImage = new Image(matrix, image.getHeight(), image.getWidth(), 255);

      return newImage;

    } catch (IOException e) {
      throw new RuntimeException("Error reading file!");
    }

  }

  /**
   * Clamps the values of a given number to the valid RGB value range which is from 0 to 255.
   *
   * @param number the value to be clamped
   * @return the clamped value
   */
  public static int clamp(int number) {
    if (number > 255) {
      return 255;
    } else if (number < 0) {
      return 0;
    }
    return number;
  }
}
