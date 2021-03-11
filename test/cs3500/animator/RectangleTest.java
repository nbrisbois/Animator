package cs3500.animator;

import java.awt.Color;
import java.awt.geom.Point2D.Double;
import org.junit.Assert;
import org.junit.Test;

public class RectangleTest {

  /**
   * Constructor Tests - Null Arguments.
   */
  @Test
  public void rectangleHealthyConstructorTest() {
    new Rectangle(new Double(0, 0), 10, 20, Color.BLACK, 0);
  }

  @Test(expected = NullPointerException.class)
  public void rectangleNullPositionObjectConstructorTest() {
    new Rectangle(null, 10, 20, Color.BLACK, 0);
  }

  @Test(expected = NullPointerException.class)
  public void rectangleNullColorObjectConstructorTest() {
    new Rectangle(new Double(0, 0), 10, 20, null, 0);
  }

  /**
   * Constructor Tests - Illegal Arguments.
   */
  @Test(expected = IllegalArgumentException.class)
  public void rectangleInvalidHeightConstructorTest() {
    new Rectangle(new Double(0, 0), -1, 20, Color.BLACK, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void rectangleInvalidWidthConstructorTest() {
    new Rectangle(new Double(0, 0), 10, -1, Color.BLACK, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void rectangleInvalidOrderConstructorTest() {
    new Rectangle(new Double(0, 0), 10, 20, Color.BLACK, -1);
  }

  /**
   * Render() Tests
   */
  @Test
  public void renderTest() {
    // TODO: Not sure what these tests will look like
  }

  /**
   * ChangePosition() AND GetPosition() Tests
   */
  @Test
  public void positionMethodsTest() {
    Double positionOne = new Double(0, 0);
    Double positionTwo = new Double(1, 1);
    Rectangle rect = new Rectangle(positionOne, 10, 20, Color.BLACK, 0);

    rect.changePosition(positionTwo);
    Assert.assertEquals(rect.getPosition(), positionTwo);
    Assert.assertNotEquals(rect.getPosition(), positionOne);
  }

  /**
   * ChangeSize() AND GetSize() Tests
   */
  @Test
  public void sizeMethodsTest() {
    double[] sizeOne = new double[]{1, 1};
    double[] sizeTwo = new double[]{30, 15};
    Rectangle rect = new Rectangle(new Double(0, 0), 10, 20, Color.BLACK, 0);

    rect.changeSize(sizeOne);
    Assert.assertEquals(rect.getSize()[0], sizeOne[0], 0);
    Assert.assertEquals(rect.getSize()[1], sizeOne[1], 0);
    Assert.assertNotEquals(rect.getSize()[0], sizeTwo[0], 0);
    Assert.assertNotEquals(rect.getSize()[1], sizeTwo[1], 0);
  }

  /**
   * ChangeColor() AND GetColor() Tests
   */
  @Test
  public void colorMethodsTest() {
    Rectangle rect = new Rectangle(new Double(0, 0), 10, 20, Color.BLACK, 0);

    Assert.assertNotEquals(rect.getColor(), Color.BLUE);
    Assert.assertEquals(rect.getColor(), Color.BLACK);
    rect.changeColor(Color.ORANGE);
    Assert.assertNotEquals(rect.getColor(), Color.BLACK);
    Assert.assertEquals(rect.getColor(), Color.ORANGE);
  }

  /**
   * GetPriority Tests
   */
  @Test
  public void getPriorityTest() {
    Rectangle rect = new Rectangle(new Double(0, 0), 10, 20, Color.BLACK, 0);

    Assert.assertEquals(rect.getPriority(), 0);
  }
}