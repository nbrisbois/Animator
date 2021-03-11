package cs3500.animator;

import java.awt.Color;
import java.awt.geom.Point2D.Double;
import org.junit.Assert;
import org.junit.Test;

public class OvalTest {

  /**
   * Constructor Tests - Null Arguments.
   */
  @Test
  public void ovalHealthyConstructorTest() {
    new Oval(new Double(0, 0), 10, 20, Color.BLACK, 0);
  }

  @Test(expected = NullPointerException.class)
  public void ovalNullPositionObjectConstructorTest() {
    new Oval(null, 10, 20, Color.BLACK, 0);
  }

  @Test(expected = NullPointerException.class)
  public void ovalNullColorObjectConstructorTest() {
    new Oval(new Double(0, 0), 10, 20, null, 0);
  }

  /**
   * Constructor Tests - Illegal Arguments.
   */
  @Test(expected = IllegalArgumentException.class)
  public void ovalInvalidHeightConstructorTest() {
    new Oval(new Double(0, 0), -1, 20, Color.BLACK, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ovalInvalidWidthConstructorTest() {
    new Oval(new Double(0, 0), 10, -1, Color.BLACK, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ovalInvalidOrderConstructorTest() {
    new Oval(new Double(0, 0), 10, 20, Color.BLACK, -1);
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
    Oval oval = new Oval(positionOne, 10, 20, Color.BLACK, 0);

    oval.changePosition(positionTwo);
    Assert.assertEquals(oval.getPosition(), positionTwo);
    Assert.assertNotEquals(oval.getPosition(), positionOne);
  }

  /**
   * ChangeSize() AND GetSize() Tests
   */
  @Test
  public void sizeMethodsTest() {
    double[] sizeOne = new double[]{1, 1};
    double[] sizeTwo = new double[]{30, 15};
    Oval oval = new Oval(new Double(0, 0), 10, 20, Color.BLACK, 0);

    oval.changeSize(sizeOne);
    Assert.assertEquals(oval.getSize()[0], sizeOne[0], 0);
    Assert.assertEquals(oval.getSize()[1], sizeOne[1], 0);
    Assert.assertNotEquals(oval.getSize()[0], sizeTwo[0], 0);
    Assert.assertNotEquals(oval.getSize()[1], sizeTwo[1], 0);
  }

  /**
   * ChangeColor() AND GetColor() Tests
   */
  @Test
  public void colorMethodsTest() {
    Oval oval = new Oval(new Double(0, 0), 10, 20, Color.BLACK, 0);

    Assert.assertNotEquals(oval.getColor(), Color.BLUE);
    Assert.assertEquals(oval.getColor(), Color.BLACK);
    oval.changeColor(Color.ORANGE);
    Assert.assertNotEquals(oval.getColor(), Color.BLACK);
    Assert.assertEquals(oval.getColor(), Color.ORANGE);
  }

  /**
   * GetPriority Tests
   */
  @Test
  public void getPriorityTest() {
    Oval oval = new Oval(new Double(0, 0), 10, 20, Color.BLACK, 0);

    Assert.assertEquals(oval.getPriority(), 0);
  }
}