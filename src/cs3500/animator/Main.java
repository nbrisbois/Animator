package cs3500.animator;

import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String... args) {
    List<Motion> motions = new ArrayList<Motion>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2, 10);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2, 10);
    motions.add(motion1);
    motions.add(motion2);

    Oval testOval = new Oval(new Double(0, 0), 10, 10, Color.BLACK, 1, motions);
    Rectangle testRect = new Rectangle(new Double(0, 0), 10, 10, Color.BLACK, 1, motions);
    Polygon testPoly = new Polygon(new Double(0, 0), 10, 10, Color.BLACK, 1, motions, 10);

    List<IShape> shapes = new ArrayList<IShape>();
    shapes.add(testOval);
    shapes.add(testRect);
    shapes.add(testPoly);

    AnimationModel testModel = new BasicAnimationModel(shapes, 50, 50, 30);
    String answer = testModel.toString();
    System.out.println(answer);
  }
}