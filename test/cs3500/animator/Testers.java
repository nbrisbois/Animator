package cs3500.animator;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.BasicAnimationModel;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;
import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import org.junit.Test;


public class Testers {

  @Test
  public void testSingleShape() throws InterruptedException, IOException {
    Queue<Motion> motions = new PriorityQueue<Motion>();
    Motion motion1 = new Motion(5, 5, Color.PINK, 2, 2, 5);
    Motion motion2 = new Motion(0, 5, Color.BLUE, 1, 2, 10);
    motions.add(motion1);
    motions.add(motion2);

    Oval testOval = new Oval("c", new Double(0, 0), 10, 10, Color.YELLOW, 1, motions);

    List<IShape> shapes = new ArrayList<IShape>();
    shapes.add(testOval);

    AnimationModel testModel = new BasicAnimationModel(shapes, 50, 50, 10, 1);
    int tick = 0;
    while (tick != 10) {
      testModel.moveShapes(tick);
      TimeUnit.SECONDS.sleep(1);
      tick = tick + 1;
    }
  }

  @Test
  public void testManyShapes() throws InterruptedException, IOException {

    Queue<Motion> ovalMotion = new PriorityQueue<Motion>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2, 5);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2, 10);
    ovalMotion.add(motion1);
    ovalMotion.add(motion2);
    IShape testOval = new Oval("c", new Double(0, 0), 10, 10, Color.BLACK, 2, ovalMotion);

    Queue<Motion> squareMotion = new PriorityQueue<Motion>();
    Motion motion3 = new Motion(14, 6, Color.PINK, 2, 2, 3);
    Motion motion4 = new Motion(-20, -10, Color.WHITE, 1, 2, 10);
    squareMotion.add(motion3);
    squareMotion.add(motion4);
    IShape testSquare = new Rectangle("s", new Double(50, 50), 2, 6, Color.BLACK, 0, squareMotion);

    List<IShape> shapes = new ArrayList<IShape>();
    shapes.add(testOval);
    shapes.add(testSquare);

    AnimationModel testModel = new BasicAnimationModel(shapes, 50, 50, 10, 1);
    int tick = 0;
    while (tick != 10) {
      testModel.moveShapes(tick);
      TimeUnit.SECONDS.sleep(1);
      tick = tick + 1;
    }
  }
}
