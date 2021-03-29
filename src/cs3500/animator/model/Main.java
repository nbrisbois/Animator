package cs3500.animator.model;

import cs3500.animator.view.IAnimationView;
import cs3500.animator.view.TextualView;
import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Main Function Class.
 */
public class Main {

  /**
   * Main function to run basic model.
   * @param args StdIn
   */
  public static void main(String... args) throws IOException {
    Queue<Motion> motions = new PriorityQueue<Motion>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2, 5);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2, 10);
    motions.add(motion1);
    motions.add(motion2);

    Oval testOval = new Oval("c", new Double(0, 0), 10, 10, Color.BLACK, 1, motions);

    List<IShape> shapes = new ArrayList<IShape>();
    shapes.add(testOval);

    AnimationModel testModel = new BasicAnimationModel(shapes, 50, 50, 10, 1);

    IAnimationView textView = new TextualView(testModel, new StringBuilder(""));
    textView.render();
  }
}
