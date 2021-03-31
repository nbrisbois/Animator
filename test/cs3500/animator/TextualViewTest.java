package cs3500.animator;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.BasicAnimationModel;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;
import cs3500.animator.view.IAnimationView;
import cs3500.animator.view.TextualView;
import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Textual View.
 */
public class TextualViewTest {
  IAnimationView textView;
  AnimationModel testModel;

  @Before
  public void setUp() {
    Queue<Motion> motions = new PriorityQueue<Motion>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2, 5);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2, 10);
    motions.add(motion1);
    motions.add(motion2);

    IShape testOval = new Oval("c", new Double(0, 0), 10, 10, Color.BLACK, 1, motions);
    IShape testRect = new Rectangle("r", new Double(0, 0), 10, 10, Color.BLACK, 1, motions);

    List<IShape> shapes = new ArrayList<IShape>();
    shapes.add(testOval);
    shapes.add(testRect);

    testModel = new BasicAnimationModel(shapes, 50, 50, 10, 1);

    textView = new TextualView(testModel, new StringBuilder(""));
  }

  //Constructor Tests
  @Test(expected = NullPointerException.class)
  public void nullModelViewTest() {
    textView = new TextualView(null, new StringBuilder(""));
  }

  @Test(expected = NullPointerException.class)
  public void nullAppendableViewTest() {
    textView = new TextualView(testModel, null);
  }

  //Render Tests
  @Test
  public void renderTest() {
    textView.render();
  }
}