package cs3500.animator;

import cs3500.animator.model.Motion;
import cs3500.animator.model.Oval;
import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.util.PriorityQueue;
import java.util.Queue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for oval.
 */
public class OvalTest {

  Oval testOval;

  @Before
  public void setUp() {
    Queue<Motion> motions = new PriorityQueue<>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2,
        10);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2,
        10);
    motions.add(motion1);
    motions.add(motion2);
    testOval = new Oval("oval", new Double(0, 0), 10, 10, Color.BLACK, 1, motions,0,0);
  }

  /**
   * Constructor Tests - Null Arguments.
   */

  @Test(expected = NullPointerException.class)
  public void ovalNullPositionObjectConstructorTest() {
    Queue<Motion> motions = new PriorityQueue<>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2,
        10);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2,
        10);
    motions.add(motion1);
    motions.add(motion2);
    new Oval("oval", null, 10, 20, Color.BLACK, 1, motions,0,0);
  }

  @Test(expected = NullPointerException.class)
  public void ovalNullColorObjectConstructorTest() {
    Queue<Motion> motions = new PriorityQueue<>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2,
        10);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2,
        10);
    motions.add(motion1);
    motions.add(motion2);
    new Oval("oval", new Double(0, 0), 10, 20, null, 1, motions,0,0);
  }

  @Test(expected = NullPointerException.class)
  public void ovalNullMotionsObjectConstructorTest() {
    new Oval("oval", new Double(0, 0), 10, 20, null, 1, null,0,0);
  }

  /**
   * Constructor Tests - Illegal Arguments.
   */
  @Test(expected = IllegalArgumentException.class)
  public void ovalInvalidHeightConstructorTest() {
    Queue<Motion> motions = new PriorityQueue<>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2,
        10);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2,
        10);
    motions.add(motion1);
    motions.add(motion2);
    new Oval("oval", new Double(0, 0), -1, 20, Color.BLACK, 1, motions,0,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ovalInvalidWidthConstructorTest() {
    Queue<Motion> motions = new PriorityQueue<>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2,
        10);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2,
        10);
    motions.add(motion1);
    motions.add(motion2);
    new Oval("oval", new Double(0, 0), 10, -1, Color.BLACK, 1, motions,0,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ovalInvalidStartTickConstructorTest() {
    Queue<Motion> motions = new PriorityQueue<>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2,
        10);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2,
        10);
    motions.add(motion1);
    motions.add(motion2);
    new Oval("oval", new Double(0, 0), 10, 20, Color.BLACK, -1, motions,0,0);
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
    Oval testOval1 = new Oval("oval", new Double(0, 0), 10, 20, Color.BLACK, 1, motions,0,0);
    motions.add(motion2);
    Oval testOval2 = new Oval("oval", new Double(0, 0), 10, 20, Color.BLACK, 1, motions,0,0);
    motions.add(motion3);
    Oval testOval3 = new Oval("oval", new Double(0, 0), 10, 20, Color.BLACK, 1, motions,0,0);
    motions.add(motion4);
    Oval testOval4 = new Oval("oval", new Double(0, 0), 10, 20, Color.BLACK, 1, motions,0,0);

    Assert.assertEquals("Shape O oval\n"
            + "motion O 1   0   0   10  20  0   0   0      6   0   0   0   0   255 255 255",
        testOval1.render());
    Assert.assertEquals("Shape O oval\n"
            + "motion O 1   0   0   10  20  0   0   0      6   0   0   0   0   255 255 255\n"
            + "motion O 6   0   0   0   0   255 255 255    58  0   5   0   0   255 255 255",
        testOval2.render());
    Assert.assertEquals("Shape O oval\n"
            + "motion O 1   0   0   10  20  0   0   0      6   0   0   0   0   255 255 255\n"
            + "motion O 6   0   0   0   0   255 255 255    58  0   5   0   0   255 255 255\n"
            + "motion O 58  0   5   0   0   255 255 255    90  20  15  0   0   0   0   255",
        testOval3.render());
    Assert.assertEquals("Shape O oval\n"
            + "motion O 1   0   0   10  20  0   0   0      6   0   0   0   0   255 255 255\n"
            + "motion O 6   0   0   0   0   255 255 255    58  0   5   0   0   255 255 255\n"
            + "motion O 58  0   5   0   0   255 255 255    90  20  15  0   0   0   0   255\n"
            + "motion O 90  20  15  0   0   0   0   255    130 45  20  0   0   0   255 0  ",
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
    testOval = new Oval("oval", new Double(0, 0), 10, 20, Color.BLACK, 1, motions,0,0);

    testOval.changePosition(positionTwo);
    Assert.assertEquals(testOval.getPosition(), positionTwo);
    Assert.assertNotEquals(testOval.getPosition(), positionOne);
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
    testOval = new Oval("oval", new Double(0, 0), 10, 20, Color.BLACK, 1, motions,0,0);

    testOval.changeSize(sizeOne);
    Assert.assertEquals(testOval.getSize()[0], sizeOne[0], 0);
    Assert.assertEquals(testOval.getSize()[1], sizeOne[1], 0);
    Assert.assertNotEquals(testOval.getSize()[0], sizeTwo[0], 0);
    Assert.assertNotEquals(testOval.getSize()[1], sizeTwo[1], 0);
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
    testOval = new Oval("oval", new Double(0, 0), 10, 20, Color.BLACK, 1, motions,0,0);

    Assert.assertNotEquals(testOval.getColor(), Color.BLUE);
    Assert.assertEquals(testOval.getColor(), Color.BLACK);
    testOval.changeColor(Color.ORANGE);
    Assert.assertNotEquals(testOval.getColor(), Color.BLACK);
    Assert.assertEquals(testOval.getColor(), Color.ORANGE);
  }

  /**
   * GetPriority Tests.
   */
  @Test
  public void getPriorityTest() {
    Assert.assertEquals(155, testOval.getPriority());
  }

  /**
   * changePosition() NullPointer Tests.
   */
  @Test(expected = NullPointerException.class)
  public void changePositionNullPointerTest() {
    testOval.changePosition(null);
  }

  /**
   * changeSize() IllegalArgument Tests.
   */
  @Test(expected = NullPointerException.class)
  public void changeSizeNullSizeTest() {
    testOval.changeSize(null);
  }

  /**
   * changeSize() IllegalArgument Tests.
   */
  @Test(expected = IllegalArgumentException.class)
  public void changeSizeEmptySizeTest() {
    double[] testArray = new double[]{};
    testOval.changeSize(testArray);
  }

  /**
   * changeSize() IllegalArgument Tests.
   */
  @Test(expected = IllegalArgumentException.class)
  public void changeSizeNot2DSizeTest() {
    double[] testArray = new double[]{1, 1, 1, 1};
    testOval.changeSize(testArray);
  }

  /**
   * changeColor() NullPointer Tests.
   */
  @Test(expected = NullPointerException.class)
  public void changeColorNullTest() {
    testOval.changeColor(null);
  }

}