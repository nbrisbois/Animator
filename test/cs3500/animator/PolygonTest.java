package cs3500.animator;

import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for rectangle.
 */
public class PolygonTest {

  Polygon testPolygon;

  @Before
  public void setUp() {
    List<Motion> motions = new ArrayList<>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2,
        10);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2,
        10);
    motions.add(motion1);
    motions.add(motion2);
    testPolygon = new Polygon(new Double(0, 0), 10, 10, Color.BLACK, 1, motions,
        3);
  }

  /**
   * Constructor Tests - Null Arguments.
   */

  @Test(expected = NullPointerException.class)
  public void PolygonNullPositionObjectConstructorTest() {
    List<Motion> motions = new ArrayList<>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2,
        10);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2,
        10);
    motions.add(motion1);
    motions.add(motion2);
    new Polygon(null, 10, 20, Color.BLACK, 1, motions, 3);
  }

  @Test(expected = NullPointerException.class)
  public void PolygonNullColorObjectConstructorTest() {
    List<Motion> motions = new ArrayList<>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2,
        10);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2,
        10);
    motions.add(motion1);
    motions.add(motion2);
    new Polygon(new Double(0, 0), 10, 20, null, 1, motions, 3);
  }

  @Test(expected = NullPointerException.class)
  public void PolygonNullMotionsObjectConstructorTest() {
    new Polygon(new Double(0, 0), 10, 20, null, 1, null, 3);
  }

  /**
   * Constructor Tests - Illegal Arguments.
   */
  @Test(expected = IllegalArgumentException.class)
  public void PolygonInvalidHeightConstructorTest() {
    List<Motion> motions = new ArrayList<>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2,
        10);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2,
        10);
    motions.add(motion1);
    motions.add(motion2);
    new Polygon(new Double(0, 0), -1, 20, Color.BLACK, 1, motions, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void PolygonInvalidWidthConstructorTest() {
    List<Motion> motions = new ArrayList<>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2,
        10);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2,
        10);
    motions.add(motion1);
    motions.add(motion2);
    new Polygon(new Double(0, 0), 10, -1, Color.BLACK, 1, motions, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void PolygonInvalidStartTickConstructorTest() {
    List<Motion> motions = new ArrayList<>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2,
        10);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2,
        10);
    motions.add(motion1);
    motions.add(motion2);
    new Polygon(new Double(0, 0), 10, 20, Color.BLACK, -1, motions, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void PolygonInvalidSidesConstructorTest() {
    List<Motion> motions = new ArrayList<>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2,
        10);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2,
        10);
    motions.add(motion1);
    motions.add(motion2);
    new Polygon(new Double(0, 0), 10, 20, Color.BLACK, 1, motions, -1);
  }

  /**
   * Render() Tests.
   */
  @Test
  public void renderTest() {
    /*
    This test also affirms that there are no gaps in between motions.
     */
    List<Motion> motions = new ArrayList<>();
    Motion motion1 = new Motion(0, 0, Color.WHITE, 0, 0,
        5);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2,
        52);
    Motion motion3 = new Motion(20, 10, Color.BLUE, 3, 8,
        32);
    Motion motion4 = new Motion(25, 5, Color.GREEN, 10, 1,
        40);
    motions.add(motion1);
    Polygon testOval1 = new Polygon(new Double(0, 0), 10, 20, Color.BLACK, 1,
        motions, 3);
    motions.add(motion2);
    Polygon testOval2 = new Polygon(new Double(0, 0), 10, 20, Color.BLACK, 1,
        motions, 3);
    motions.add(motion3);
    Polygon testOval3 = new Polygon(new Double(0, 0), 10, 20, Color.BLACK, 1,
        motions, 3);
    motions.add(motion4);
    Polygon testOval4 = new Polygon(new Double(0, 0), 10, 20, Color.BLACK, 1,
        motions, 3);

    Assert.assertEquals("Shape P polygon\n"
            + "motion P 1   0   0   10  20  0   0   0      6   0   0   0   0   255 255 255",
        testOval1.render());
    Assert.assertEquals("Shape P polygon\n"
            + "motion P 1   0   0   10  20  0   0   0      6   0   0   0   0   255 255 255\n"
            + "motion P 6   0   0   0   0   255 255 255    58  0   5   0   0   255 255 255",
        testOval2.render());
    Assert.assertEquals("Shape P polygon\n"
            + "motion P 1   0   0   10  20  0   0   0      6   0   0   0   0   255 255 255\n"
            + "motion P 6   0   0   0   0   255 255 255    58  0   5   0   0   255 255 255\n"
            + "motion P 58  0   5   0   0   255 255 255    90  20  15  0   0   0   0   255",
        testOval3.render());
    Assert.assertEquals("Shape P polygon\n"
            + "motion P 1   0   0   10  20  0   0   0      6   0   0   0   0   255 255 255\n"
            + "motion P 6   0   0   0   0   255 255 255    58  0   5   0   0   255 255 255\n"
            + "motion P 58  0   5   0   0   255 255 255    90  20  15  0   0   0   0   255\n"
            + "motion P 90  20  15  0   0   0   0   255    130 45  20  0   0   0   255 0  ",
        testOval4.render());
  }

  /**
   * ChangePosition() AND GetPosition() Tests.
   */
  @Test
  public void positionMethodsTest() {
    Double positionOne = new Double(0, 0);
    Double positionTwo = new Double(1, 1);
    List<Motion> motions = new ArrayList<>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2,
        10);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2,
        10);
    motions.add(motion1);
    motions.add(motion2);
    testPolygon = new Polygon(new Double(0, 0), 10, 20, Color.BLACK, 1, motions,
        10);

    testPolygon.changePosition(positionTwo);
    Assert.assertEquals(testPolygon.getPosition(), positionTwo);
    Assert.assertNotEquals(testPolygon.getPosition(), positionOne);
  }

  /**
   * ChangeSize() AND GetSize() Tests.
   */
  @Test
  public void sizeMethodsTest() {
    double[] sizeOne = new double[]{1, 1};
    double[] sizeTwo = new double[]{30, 15};
    List<Motion> motions = new ArrayList<>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2,
        10);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2,
        10);
    motions.add(motion1);
    motions.add(motion2);
    testPolygon = new Polygon(new Double(0, 0), 10, 20, Color.BLACK, 1, motions,
        10);

    testPolygon.changeSize(sizeOne);
    Assert.assertEquals(testPolygon.getSize()[0], sizeOne[0], 0);
    Assert.assertEquals(testPolygon.getSize()[1], sizeOne[1], 0);
    Assert.assertNotEquals(testPolygon.getSize()[0], sizeTwo[0], 0);
    Assert.assertNotEquals(testPolygon.getSize()[1], sizeTwo[1], 0);
  }

  /**
   * ChangeColor() AND GetColor() Tests.
   */
  @Test
  public void colorMethodsTest() {
    List<Motion> motions = new ArrayList<>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2,
        10);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2,
        10);
    motions.add(motion1);
    motions.add(motion2);
    testPolygon = new Polygon(new Double(0, 0), 10, 20, Color.BLACK, 1, motions,
        10);

    Assert.assertNotEquals(testPolygon.getColor(), Color.BLUE);
    Assert.assertEquals(testPolygon.getColor(), Color.BLACK);
    testPolygon.changeColor(Color.ORANGE);
    Assert.assertNotEquals(testPolygon.getColor(), Color.BLACK);
    Assert.assertEquals(testPolygon.getColor(), Color.ORANGE);
  }

  /**
   * GetPriority Tests.
   */
  @Test
  public void getPriorityTest() {
    List<Motion> motions = new ArrayList<>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2,
        10);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2,
        10);
    motions.add(motion1);
    motions.add(motion2);
    testPolygon = new Polygon(new Double(0, 0), 10, 20, Color.BLACK, 1, motions,
        10);

    Assert.assertEquals(204, testPolygon.getPriority());
  }

  /**
   * GetSides Tests.
   */
  @Test
  public void getSidesTest() {
    List<Motion> motions = new ArrayList<>();
    Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2,
        10);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2,
        10);
    motions.add(motion1);
    motions.add(motion2);
    testPolygon = new Polygon(new Double(0, 0), 10, 20, Color.BLACK, 1, motions,
        10);

    Assert.assertEquals(10, testPolygon.getSides());
  }

  /**
<<<<<<< HEAD
   * executeMotions() test.
=======
   * ExecuteMotions() test.
>>>>>>> 8f54ecfe5b37d2a90e2814ee01d422fc60f9d56d
   */
  @Test
  public void executeMotionsTest() {
    IShape newShape = testPolygon.executeMotion(0);
    double[] sizeOne = new double[]{20, 20};

    Assert.assertEquals(new Double(5.0, 5.0), newShape.getPosition());
    Assert.assertEquals(Color.BLACK, newShape.getColor());
    Assert.assertEquals(newShape.getSize()[0], sizeOne[0], 0);
    Assert.assertEquals(newShape.getSize()[1], sizeOne[1], 0);
    Assert.assertEquals(11, newShape.getStartTick());
  }

  /**
   * changePosition() NullPointer Tests.
   */
  @Test(expected = NullPointerException.class)
  public void changePositionNullPointerTest() {
    testPolygon.changePosition(null);
  }

  /**
   * changeSize() IllegalArgument Tests.
   */
  @Test(expected = NullPointerException.class)
  public void changeSizeNullSizeTest() {
    testPolygon.changeSize(null);
  }

  /**
   * changeSize() IllegalArgument Tests.
   */
  @Test(expected = IllegalArgumentException.class)
  public void changeSizeEmptySizeTest() {
    double[] testArray = new double[]{};
    testPolygon.changeSize(testArray);
  }

  /**
   * changeSize() IllegalArgument Tests.
   */
  @Test(expected = IllegalArgumentException.class)
  public void changeSizeNot2DSizeTest() {
    double[] testArray = new double[]{1, 1, 1, 1};
    testPolygon.changeSize(testArray);
  }

  /**
   * changeColor() NullPointer Tests.
   */
  @Test(expected = NullPointerException.class)
  public void changeColorNullTest() {
    testPolygon.changeColor(null);
  }

  /**
   * changeSize() IllegalArgument Tests.
   */
  @Test(expected = IllegalArgumentException.class)
  public void executeMotionNegativeIndexTest() {
    testPolygon.executeMotion(-1);
  }
}