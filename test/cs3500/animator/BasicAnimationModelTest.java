package cs3500.animator;

import static org.junit.Assert.assertEquals;

import cs3500.animator.model.BasicAnimationModel;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;
import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the animation model.
 */
public class BasicAnimationModelTest {

  Queue<Motion> motions = new PriorityQueue<Motion>();
  Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2, 10);
  Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2, 10);

  List<IShape> testShapes = new ArrayList<>();
  Oval testOval = new Oval("oval", new Double(0, 0), 10, 10, Color.BLACK, 1, motions, 0, 0);
  Rectangle testRect = new Rectangle("rect", new Double(0, 0), 10, 10, Color.BLACK, 1, motions, 0,
      0);
  BasicAnimationModel testModel;

  @Before
  public void setup() {
    motions.add(motion1);
    motions.add(motion2);
    testShapes.add(testOval);
    testShapes.add(testRect);
    testModel = new BasicAnimationModel(testShapes,  500, 500, 100, 100);
  }

  /**
   * Invalid Constructor Testing.
   */
  @Test(expected = IllegalArgumentException.class)
  public void modelInvalidSceneHeightTest() {
    testModel = new BasicAnimationModel(testShapes, 0, 10, 10, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void modelInvalidSceneWidthTest() {
    testModel = new BasicAnimationModel(testShapes, 10, 0, 10, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void modelInvalidDurationTest() {
    testModel = new BasicAnimationModel(testShapes, 10, 10, -1, 1);
  }

  @Test(expected = NullPointerException.class)
  public void modelInvalidShapesNullTest() {
    testModel = new BasicAnimationModel(null, 10, 10, 0, 1);
  }

  /**
   * Getter Tests.
   */
  @Test
  public void modelGetShapesTest() {
    testModel = new BasicAnimationModel(testShapes, 10, 10, 0, 1);
    Assert.assertNotSame(testOval, testModel.getShapes().get(0));
    Assert.assertNotSame(testRect, testModel.getShapes().get(1));
  }

  @Test
  public void modelGetSceneHeightTest() {
    testModel = new BasicAnimationModel(testShapes, 125125, 10, 0, 1);
    assertEquals(125125, testModel.getSceneHeight());
  }

  @Test
  public void modelGetSceneWidthTest() {
    testModel = new BasicAnimationModel(testShapes, 10, 44444, 0, 1);
    assertEquals(44444, testModel.getSceneWidth());
  }

  @Test
  public void modelGetDurationTest() {
    testModel = new BasicAnimationModel(testShapes, 10, 44444, 9999125, 1);
    assertEquals(9999125, testModel.getDuration());
  }

  @Test
  public void modelGetMotionTest() {
    testModel = new BasicAnimationModel(testShapes, 10, 44444, 9999125, 1);
    List<Queue<Motion>> lolom = new ArrayList<Queue<Motion>>();
    lolom.add(motions);
    lolom.add(motions);
    assertEquals([[], []] testModel.getMotions());
  }

  /**
   * AddShape Tests.
   */
  @Test(expected = NullPointerException.class)
  public void modelAddNullShapeTest() {
    testModel = new BasicAnimationModel(testShapes, 10, 10, 10, 1);
    testModel.addShape(null);
  }

  @Test
  public void modelAddShapeTest() {
    testModel = new BasicAnimationModel(testShapes, 10, 10, 10, 1);
    Oval newOval = new Oval("oval", new Double(59, 12), 163, 205, Color.MAGENTA, 0, motions, 0, 0);
    testModel.addShape(newOval);

    Assert.assertNotSame(newOval, testModel.getShapes().get(3));

    assertEquals(newOval.getPosition().getX(),
        testModel.getShapes().get(3).getPosition().getX(), 0);
    assertEquals(newOval.getPosition().getY(),
        testModel.getShapes().get(3).getPosition().getY(), 0);
    assertEquals(newOval.getColor(), testModel.getShapes().get(3).getColor());
    assertEquals(newOval.getSize()[0], testModel.getShapes().get(3).getSize()[0], 0);
    assertEquals(newOval.getSize()[1], testModel.getShapes().get(3).getSize()[1], 0);
    assertEquals(newOval.getStartTick(), testModel.getShapes().get(3).getStartTick());
  }

  /**
   * ToString Tests.
   */
  @Test
  public void modelCopyShapesTest() {
    testModel = new BasicAnimationModel(testShapes, 10, 10, 10, 1);

    assertEquals("Shape O oval\n \nShape R rectangle\n \nShape P polygon\n \n",
        testModel.toString());

    Oval newOval = new Oval("oval", new Double(59, 12), 163, 205,
        Color.MAGENTA, 0, motions, 0, 0);
    Rectangle newRect = new Rectangle("oval", new Double(59, 12), 163, 205,
        Color.MAGENTA, 0, motions, 0, 0);

    testModel.addShape(newOval);
    testModel.addShape(newRect);

    assertEquals(
        "Shape O oval\n \nShape R rectangle\n \nShape P polygon\n \n"
            + "Shape O oval"
            + "\nmotion O 0   59  12  163 205 255 0   255    10  64  17  326 410 0   0   0  \n"
            + "motion O 10  64  17  326 410 0   0   0      20  64  22  326 820 255 255 255\n \n"
            + "Shape R rectangle"
            + "\nmotion R 0   59  12  163 205 255 0   255   10  64  17  326 410 0   0   0  \n"
            + "motion R 10  64  17  326 410 0   0   0     20  64  22  326 820 255 255 255\n \n"
            + "Shape P polygon"
            + "\nmotion P 0   59  12  163 205 255 0   255    10  64  17  326 410 0   0   0  \n"
            + "motion P 10  64  17  326 410 0   0   0      20  64  22  326 820 255 255 255\n"
            + " \n", testModel.toString());
  }


}