package cs3500.controller;

import cs3500.model.IImageProcessorModel;

/**
 * Represents an interface of Image Processor Commands that can be used by the Controller's
 * command line arguments entered by the user.
 */
public interface IProcessorCommands {

  /**
   * Calls a particular function object upon a model, to perform a specific operation.
   *
   * @param i an image processor model
   */
  void runThis(IImageProcessorModel i);
}
