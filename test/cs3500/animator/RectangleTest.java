package cs3500.animator;

import cs3500.animator.model.Motion;
import cs3500.animator.model.Rectangle;
import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.util.PriorityQueue;
import java.util.Queue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for rectangle.
 */
public class RectangleTest {

  Rectangle testRectangle;

  @Before
  public void setUp() {
    Queue<Motion> motions = new PriorityQueue<>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2,
        10);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2,
        10);
    motions.add(motion1);
    motions.add(motion2);
    testRectangle = new Rectangle("rect", new Double(0, 0), 10, 10, Color.BLACK, 1,
        motions,0,0);
  }

  /**
   * Constructor Tests - Null Arguments.
   */

  @Test(expected = NullPointerException.class)
  public void RectangleNullPositionObjectConstructorTest() {
    Queue<Motion> motions = new PriorityQueue<>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2,
        10);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2,
        10);
    motions.add(motion1);
    motions.add(motion2);
    new Rectangle("rect", null, 10, 20, Color.BLACK, 1, motions,0,0);
  }

  @Test(expected = NullPointerException.class)
  public void RectangleNullColorObjectConstructorTest() {
    Queue<Motion> motions = new PriorityQueue<>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2,
        10);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2,
        10);
    motions.add(motion1);
    motions.add(motion2);
    new Rectangle("rect", new Double(0, 0), 10, 20, null, 1, motions,0,0);
  }

  @Test(expected = NullPointerException.class)
  public void RectangleNullMotionsObjectConstructorTest() {
    new Rectangle("rect", new Double(0, 0), 10, 20, null, 1, null,0,0);
  }

  /**
   * Constructor Tests - Illegal Arguments.
   */
  @Test(expected = IllegalArgumentException.class)
  public void RectangleInvalidHeightConstructorTest() {
    Queue<Motion> motions = new PriorityQueue<>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2,
        10);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2,
        10);
    motions.add(motion1);
    motions.add(motion2);
    new Rectangle("rect", new Double(0, 0), -1, 20, Color.BLACK, 1, motions,0,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void RectangleInvalidWidthConstructorTest() {
    Queue<Motion> motions = new PriorityQueue<>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2,
        10);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2,
        10);
    motions.add(motion1);
    motions.add(motion2);
    new Rectangle("rect", new Double(0, 0), 10, -1, Color.BLACK, 1, motions,0,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void RectangleInvalidStartTickConstructorTest() {
    Queue<Motion> motions = new PriorityQueue<>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2,
        10);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2,
        10);
    motions.add(motion1);
    motions.add(motion2);
    new Rectangle("rect", new Double(0, 0), 10, 20, Color.BLACK, -1, motions,0,0);
  }

  /**
   * Render() Tests.
   */
  @Test
  public void renderTest() {
    /*
    This test also affirms that there are no gaps in between motions.
     */
    Queue<Motion> motions = new PriorityQueue<>();
    Motion motion1 = new Motion(0, 0, Color.WHITE, 0, 0,
        5);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2,
        52);
    Motion motion3 = new Motion(20, 10, Color.BLUE, 3, 8,
        32);
    Motion motion4 = new Motion(25, 5, Color.GREEN, 10, 1,
        40);
    motions.add(motion1);
    Rectangle testOval1 = new Rectangle("rect", new Double(0, 0), 10, 20, Color.BLACK,
        1, motions,0,0);
    motions.add(motion2);
    Rectangle testOval2 = new Rectangle("rect", new Double(0, 0), 10, 20, Color.BLACK,
        1, motions,0,0);
    motions.add(motion3);
    Rectangle testOval3 = new Rectangle("rect", new Double(0, 0), 10, 20, Color.BLACK,
        1, motions,0,0);
    motions.add(motion4);
    Rectangle testOval4 = new Rectangle("rect", new Double(0, 0), 10, 20, Color.BLACK,
        1, motions,0,0);

    Assert.assertEquals("Shape R rectangle\n"
            + "motion R 1   0   0   10  20  0   0   0     6   0   0   0   0   255 255 255",
        testOval1.render());
    Assert.assertEquals("Shape R rectangle\n"
            + "motion R 1   0   0   10  20  0   0   0     6   0   0   0   0   255 255 255\n"
            + "motion R 6   0   0   0   0   255 255 255   58  0   5   0   0   255 255 255",
        testOval2.render());
    Assert.assertEquals("Shape R rectangle\n"
            + "motion R 1   0   0   10  20  0   0   0     6   0   0   0   0   255 255 255\n"
            + "motion R 6   0   0   0   0   255 255 255   58  0   5   0   0   255 255 255\n"
            + "motion R 58  0   5   0   0   255 255 255   90  20  15  0   0   0   0   255",
        testOval3.render());
    Assert.assertEquals("Shape R rectangle\n"
            + "motion R 1   0   0   10  20  0   0   0     6   0   0   0   0   255 255 255\n"
            + "motion R 6   0   0   0   0   255 255 255   58  0   5   0   0   255 255 255\n"
            + "motion R 58  0   5   0   0   255 255 255   90  20  15  0   0   0   0   255\n"
            + "motion R 90  20  15  0   0   0   0   255   130 45  20  0   0   0   255 0  ",
        testOval4.render());
  }

  /**
   * ChangePosition() AND GetPosition() Tests.
   */
  @Test
  public void positionMethodsTest() {
    Double positionOne = new Double(0, 0);
    Double positionTwo = new Double(1, 1);
    Queue<Motion> motions = new PriorityQueue<>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2,
        10);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2,
        10);
    motions.add(motion1);
    motions.add(motion2);
    testRectangle = new Rectangle("rect", new Double(0, 0), 10, 20, Color.BLACK, 1,
        motions,0,0);

    testRectangle.changePosition(positionTwo);
    Assert.assertEquals(testRectangle.getPosition(), positionTwo);
    Assert.assertNotEquals(testRectangle.getPosition(), positionOne);
  }

  /**
   * ChangeSize() AND GetSize() Tests.
   */
  @Test
  public void sizeMethodsTest() {
    double[] sizeOne = new double[]{1, 1};
    double[] sizeTwo = new double[]{30, 15};
    Queue<Motion> motions = new PriorityQueue<>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2,
        10);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2,
        10);
    motions.add(motion1);
    motions.add(motion2);
    testRectangle = new Rectangle("rect", new Double(0, 0), 10, 20, Color.BLACK, 1,
        motions,0,0);

    testRectangle.changeSize(sizeOne);
    Assert.assertEquals(testRectangle.getSize()[0], sizeOne[0], 0);
    Assert.assertEquals(testRectangle.getSize()[1], sizeOne[1], 0);
    Assert.assertNotEquals(testRectangle.getSize()[0], sizeTwo[0], 0);
    Assert.assertNotEquals(testRectangle.getSize()[1], sizeTwo[1], 0);
  }

  /**
   * ChangeColor() AND GetColor() Tests.
   */
  @Test
  public void colorMethodsTest() {
    Queue<Motion> motions = new PriorityQueue<>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2,
        10);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2,
        10);
    motions.add(motion1);
    motions.add(motion2);
    testRectangle = new Rectangle("rect", new Double(0, 0), 10, 20, Color.BLACK, 1,
        motions,0,0);

    Assert.assertNotEquals(testRectangle.getColor(), Color.BLUE);
    Assert.assertEquals(testRectangle.getColor(), Color.BLACK);
    testRectangle.changeColor(Color.ORANGE);
    Assert.assertNotEquals(testRectangle.getColor(), Color.BLACK);
    Assert.assertEquals(testRectangle.getColor(), Color.ORANGE);
  }

  /**
   * GetPriority Tests.
   */
  @Test
  public void getPriorityTest() {
    Queue<Motion> motions = new PriorityQueue<>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2,
        10);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2,
        10);
    motions.add(motion1);
    motions.add(motion2);
    testRectangle = new Rectangle("rect", new Double(0, 0), 10, 20, Color.BLACK, 1,
        motions,0,0);

    Assert.assertEquals(253, testRectangle.getPriority());
  }

  /**
   * changePosition() NullPointer Tests.
   */
  @Test(expected = NullPointerException.class)
  public void changePositionNullPointerTest() {
    testRectangle.changePosition(null);
  }

  /**
   * changeSize() IllegalArgument Tests.
   */
  @Test(expected = NullPointerException.class)
  public void changeSizeNullSizeTest() {
    testRectangle.changeSize(null);
  }

  /**
   * changeSize() IllegalArgument Tests.
   */
  @Test(expected = IllegalArgumentException.class)
  public void changeSizeEmptySizeTest() {
    double[] testArray = new double[]{};
    testRectangle.changeSize(testArray);
  }

  /**
   * changeSize() IllegalArgument Tests.
   */
  @Test(expected = IllegalArgumentException.class)
  public void changeSizeNot2DSizeTest() {
    double[] testArray = new double[]{1, 1, 1, 1};
    testRectangle.changeSize(testArray);
  }

  /**
   * changeColor() NullPointer Tests.
   */
  @Test(expected = NullPointerException.class)
  public void changeColorNullTest() {
    testRectangle.changeColor(null);
  }

}