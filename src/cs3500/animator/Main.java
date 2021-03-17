
package cs3500.animator;

import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;

/**
 * Main Function Class.
 */
public class Main {

  /**
   * Main function to run basic model.
   * @param args StdIn
   */
  public static void main(String... args) {
    List<Motion> motions = new ArrayList<Motion>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2, 10);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2, 10);
    Motion motion3 = new Motion(0, -5, Color.ORANGE, 1, 2, 20);
    Motion motion4 = new Motion(0, 0, Color.ORANGE, 1, 1, 10);
    motions.add(motion1);
    motions.add(motion2);
    motions.add(motion3);
    motions.add(motion4);

    Oval testOval = new Oval(new Double(0, 0), 10, 10, Color.BLACK, 0, motions);
    Oval testOval2 = new Oval(new Double(0, 0), 10, 10, Color.BLACK, 0, motions);
    Rectangle testRect = new Rectangle(new Double(0, 0), 10, 10, Color.BLACK, 0, motions);
    Polygon testPoly = new Polygon(new Double(0, 0), 10, 10, Color.BLACK, 0, motions, 10);

    List<IShape> shapes = new ArrayList<IShape>();
    shapes.add(testOval);
    shapes.add(testOval2);
    shapes.add(testRect);
    shapes.add(testPoly);

    AnimationModel testModel = new BasicAnimationModel(shapes, 50, 50, 50);
    String answer = testModel.toString();
    System.out.println(answer);
  }
}