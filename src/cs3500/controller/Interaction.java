package cs3500.controller;

/**
 * An interface to be used when making mock tests.
 */
public interface Interaction {

  /**
   * Applies given input to the output.
   *
   * @param input  user input.
   * @param output result.
   */
  void apply(StringBuilder input, StringBuilder output);

  /**
   * Appends lines of each action output.
   *
   * @param lines printed output.
   * @return a string of appended outputs.
   */
  static Interaction prints(String... lines) {
    return (input, output) -> {
      for (String line : lines) {
        output.append(line);
      }
    };
  }

  /**
   * Inputs given as a string.
   *
   * @param in string that represents inputs.
   * @return inputs are appended to current string of inputs.
   */
  static Interaction inputs(String in) {
    return (input, output) -> {
      input.append(in);
    };
  }
}
