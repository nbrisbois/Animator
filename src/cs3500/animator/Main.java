package cs3500.animator;

import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;

public class Main {

  private static AnimationModel testModel;

  public static void main(String... args) {
    List<Motion> motions = new ArrayList<Motion>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2, 10);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2, 10);
    motions.add(motion1);
    motions.add(motion2);
    Oval testOval = new Oval(new Double(0, 0), 10, 10, Color.BLACK, 1, motions);
    Rectangle testRect = new Rectangle(new Double(0, 0), 10, 10, Color.BLACK, 1, motions);
    List<IShape> shapes = new ArrayList<IShape>();
    shapes.add(testOval);
    shapes.add(testRect);
    testModel = new BasicAnimationModel(shapes,50, 50, 30);
    testOval.changeColor(Color.RED);
    String answer = testModel.getShapes().get(0).getColor().toString();
    System.out.println(answer);
  }
}
