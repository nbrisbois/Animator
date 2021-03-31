package cs3500.animator;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.BasicAnimationModel.Builder;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Motion;
import cs3500.animator.view.FactoryView;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Excellence Class representing the main interaction with our Model and View
 * <p>
 * The function is called via the command line.
 * <p>
 * Available Command Flags: -in "name-of-animation-file"      The file that will be used for the
 * animation -view "type-of-view"              The view type that will be used for the animation
 * -out "where-output-show-go"       The output location of the data -speed
 * "integer-ticks-per-second" The speed in which the animation should run
 * <p>
 * Example Commands: -in smalldemo.txt -view text -speed 2 -view svg -out out.svg -in buildings.txt
 * -in smalldemo.txt -view text -in smalldemo.txt -speed 50 -view visual
 */
public final class Excellence {

  public static void main(String[] args) {
    // Set Defaults
    String inputFileName = "";
    String output = "";
    int speed = 1;
    String viewDel = "";

    // Get Inputs
    for (int ii = 0; ii < args.length; ii++) {
      try {
        switch (args[ii]) {
          case "-in":
            inputFileName = args[ii + 1];
            break;
          case "-out":
            output = args[ii + 1];
            break;
          case "-view":
            viewDel = args[ii + 1];
            break;
          case "-speed":
            speed = Integer.parseInt(args[ii + 1]);
            break;
        }
      } catch (Exception e) {
        throw new ArrayIndexOutOfBoundsException("Array index out of bounds");
      }
      ii++;
    }

    // Check Mandatory inputs
    if (inputFileName.equals("")) {
      throw new IllegalArgumentException("Input file required. Format: \'-in file_name\' ");
    }
    if (viewDel.equals("")) {
      throw new IllegalArgumentException("View type required. Format: \'-view view_type\' ");
    }

    List<IShape> shapes = new ArrayList<>();
    Point2D.Double topLeftCorner = new Double(0, 0);
    int height;
    int width;
    Map<String, List<Motion>> shapeMotionMap = new HashMap<>();

    Builder builder = new Builder();

    // Read File
    File myFile = new File(String.format("%s", inputFileName));
    try {
      Scanner myReader = new Scanner(myFile);
      while (myReader.hasNextLine()) {
        String delimiter = myReader.next();
        switch (delimiter) {
          case "canvas":
            int x = Integer.parseInt(myReader.next());
            int y = Integer.parseInt(myReader.next());
            topLeftCorner.setLocation(x, y);
            height = Integer.parseInt(myReader.next());
            width = Integer.parseInt(myReader.next());
            // Set screen bounds for model
            builder.setBounds(x, y, width, height);
            break;
          case "shape":
            String name = myReader.next();
            String shapeType = myReader.next();
            builder.declareShape(name, shapeType);
            break;
          case "motion":
            String shapeName = myReader.next();
            int timeStart1 = Integer.parseInt(myReader.next());
            int movementX1 = Integer.parseInt(myReader.next());
            int movementY1 = Integer.parseInt(myReader.next());
            int w1 = Integer.parseInt(myReader.next());
            int h1 = Integer.parseInt(myReader.next());
            int r1 = Integer.parseInt(myReader.next());
            int b1 = Integer.parseInt(myReader.next());
            int g1 = Integer.parseInt(myReader.next());

            int timeStart2 = Integer.parseInt(myReader.next());
            int movementX2 = Integer.parseInt(myReader.next());
            int movementY2 = Integer.parseInt(myReader.next());
            int w2 = Integer.parseInt(myReader.next());
            int h2 = Integer.parseInt(myReader.next());
            int r2 = Integer.parseInt(myReader.next());
            int b2 = Integer.parseInt(myReader.next());
            int g2 = Integer.parseInt(myReader.next());

            builder.addMotion(
                shapeName, timeStart1, movementX1, movementY1, w1, h1, r1, b1, g1,
                timeStart2, movementX2, movementY2, w2, h2, r2, b2, g2
            );
            break;
        }
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    // Create the model
    AnimationModel model = builder.build();
    // Create View
    FactoryView view = new FactoryView(model);

    try {
      view.getView(viewDel).render();
    } catch (Exception e) {
      System.out.println("error");
    }
  }
}
