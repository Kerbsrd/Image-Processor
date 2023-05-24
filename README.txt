README <3

CITATIONS
help.ppm : https://in.pinterest.com/pin/744923594628388046/
cry.bmp : https://in.pinterest.com/pin/335799715978059401/
thinking.png : https://in.pinterest.com/pin/712483603567969999/
flowerboi.jpg : https://in.pinterest.com/pin/871165121643077395/
listensusan.jpg : https://in.pinterest.com/pin/896075657082661573/
All other images have been created using GIMP and are owned by us.


INTRODUCTION
The folders contained in this zip file are:
- src (the source file of the program)
- test (the tests for the program)
- res (sample images and the JAR file required)
- README (this readme file)
- USEME (a guide to the commands supported)
- Diagram (the diagram image)

The src file contains the code that builds our program. There are two main parts to this, the
model and the controller as per the MVC pattern. We do not have a view, although it can be
implemented in future developments, because the assignment (HW 5) does not require a view.


DOCUMENTATION OF CHANGES + MAIN ADDITIONS FOR HOMEWORK 5:
- No changes in model implementation
- Function object 'Filter' added to support blurring, sharpening, greyscale and sepia. Uses objects
  of a new type 'Kernel' which represents a filter kernel.
- ImageUtil class extended by adding a readBufferedImage() method to support loading other
  file formats
- Four new command objects added in the controller: Blur, Sharpen, Greyscale and Sepia
- Save command object edited to be able to save other image formats using ImageIO functions

DOCUMENTATION OF CHANGES + MAIN ADDITIONS FOR HOMEWORK 6:
- Mock Model implementation created for testing. Model related operations moved into the model from
  the command object classes.
- New GUI controller created
- New View interface and GUI view created
- A class for Histograms created
- A class for making Image Panels created


MODEL: The model contains all the functionality of the program related to functionality.
- Contains an interface IImageProcessorModel that can be used to implement an Image Processor Model.
- Our implementation ImageProcessorModelImpl works with PPM images.
- The model also contains supporting interfaces and classes:
    - IPixel (to represent each pixel of an Image) -> Pixel (our implementation)
    - IImage (to represent an Image) -> Image (our implementation)
- We have also modified the ImageUtil class to fit our requirements. Instead of printing the
  details of a PPM file, it returns an IImage object. This is used by the load() method in
  our model implementation.
- Each operation offered by the model (for now, this is flipping, greyscale, changing brightness) is
  represented by a corresponding function.


CONTROLLER: The controller controls the model by getting some input from the user and sending it to
the model (which computes this input and returns an output). It transmits the output back to the
user.
- Contains a package 'commands' with a list of possible commands that the user can use to modify the
  image. These commands all implement the IProcessorCommands interface that can be used to create
  new commands in the future.
- Has an interface IImageProcessorController which can be used to make implementations of a
  controller for an image processor
- Our implementation ImageProcessorControllerImpl displays a menu of commands to the user (along
  with a welcome and farewell message). While the program is running (user does not quit by pressing
  q), the user may use any valid command to use the model and receive an output.

VIEW: The view enables the user to see the interface of the program. The Graphical User Interface
is supported in an implementation of the view interface that uses Java Swing components to create an
interactive program for the user
- Contains an IImageProcessorView interface
- Contains an ImageProcessorViewGUI class that is our implementation for HW 6
- Has a class to support image panels on the GUI

NAVIGATING THE RES FOLDER:
A lot of these images are for our own tests. The images required as examples for the assignment are
categorized and named below:

- original images of different types: help.ppm, cry.bmp, flowerboi.jpg, thinking.png

- operations done on help.ppm
  - blurredhelp.ppm
  - sharpenedhelp.ppm
  - greyhelp.ppm
  - sepiahelp.ppm

- operations done on cry.bmp
  - blurredcry.bmp
  - sharpenedcry.bmp
  - greycry.bmp
  - sepiacry.bmp

- operations done on flowerboi.jpg
  - blurredhelp.jpg
  - sharpenedhelp.jpg
  - greyhelp.jpg
  - sepiahelp.jpg

- operations done on thinking.png
  - blurredthinking.png
  - sharpenedthinking.png
  - greythinking.png
  - sepiathinking.png

- an additional image listensusan.jpg exists with a corresponding editedlistensusan.png to show that
  multiple operations can be applied to an image and it can be saved to a new type (.png). The image
  was (1) horizontally flipped (2) blurred twice (3) sepia tinted.