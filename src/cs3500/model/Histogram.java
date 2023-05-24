package cs3500.model;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

/**
 * A class that represents a histogram. A histogram is a visualisation of image data in the format
 * of a chart that maps each color component of RGB values and the intensity to the density of
 * each component in the image.
 */
public class Histogram extends JPanel {

  private Map<Integer, Integer> redValues;

  private Map<Integer, Integer> greenValues;

  private Map<Integer, Integer> blueValues;

  private Map<Integer, Integer> intensityValues;

  /**
   * Initialises the red, green, blue and intensity values of the histogram. Each of these channels
   * can have a value between 0 and 255.
   */
  public Histogram() {
    this.redValues = initialiseValues();
    this.greenValues = initialiseValues();
    this.blueValues = initialiseValues();
    this.intensityValues = initialiseValues();
  }

  private Map<Integer,Integer> initialiseValues() {
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    for (int i = 0; i < 255; i++) {
      map.put(i, 0);
    }
    return map;
  }

  /**
   * Initialises a histogram for a given image by mapping its red, green, blue and intensity values
   * on the chart. Each of these channels can have a value between 0 and 255.
   *
   * @param i the given image
   */
  public void initialiseHistogram(IImage i) {
    this.redValues = initialiseValues();
    this.greenValues = initialiseValues();
    this.blueValues = initialiseValues();
    this.intensityValues = initialiseValues();

    Pixel[][] m = i.getMatrix();
    Pixel current;
    for (int j = 0; j < m.length; j++) {
      for (int k = 0; k < m[0].length; k++) {
        current = m[j][k];
        this.redValues.put(current.getRedValue(), this.redValues.get(current.getRedValue()) + 1);
        this.greenValues.put(current.getGreenValue(), this.greenValues.get(current.getGreenValue())
            + 1);
        this.blueValues.put(current.getRedValue(), this.blueValues.get(current.getBlueValue()) + 1);
        this.intensityValues.put(current.getIntensity(), this.intensityValues.get
            (current.getIntensity()) + 1);
      }
    }
  }

  /**
   * Paints the histogram values as a chart that can be displayed to the user. Makes a two
   * dimensional graph with four coloured lines, where each line represents the red, green, blue
   * and intensity channels.
   * @param g the <code>Graphics</code> object to protect
   */
  @Override
  public void paintComponent(Graphics g) {

    //calls superclass constructor
    super.paintComponent(g);

    //create a new 2D graphic element
    Graphics2D graph = (Graphics2D) g;

    //setting red, green, blue and black as colors
    Color red = new Color(1, 0, 0, 0.5F);
    Color green = new Color(0, 1, 0, 0.5F);
    Color blue = new Color(0, 0, 1, 0.5F);
    Color grey = new Color(0.5F, 0.5F, 0.5F, 0.5F);

    //drawing each component channel as a colored line
    this.makeLineGraph(this.redValues, red, graph);
    this.makeLineGraph(this.greenValues, green, graph);
    this.makeLineGraph(this.blueValues, blue, graph);
    this.makeLineGraph(this.intensityValues, grey, graph);
  }

  /**
   * Makes a line graph using a map of integers to integers and a given color to make the graph in.
   * Also takes in a given 2D graphics object to paint the line graph for a component.
   * @param m the map of integers to integers
   * @param c the Color to make the graph
   * @param g the 2D graphic
   */
  private void makeLineGraph(Map<Integer, Integer> m, Color c, Graphics2D g) {
    for(Map.Entry<Integer, Integer> entry : m.entrySet()) {
      g.setColor(c);
      g.drawLine(entry.getKey(), 300,
          entry.getKey(), 300 - (entry.getValue() % 200));
    }
  }

}
