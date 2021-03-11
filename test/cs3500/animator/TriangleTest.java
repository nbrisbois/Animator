package cs3500.animator;

import java.awt.Color;
import java.awt.geom.Point2D.Double;
import org.junit.Assert;
import org.junit.Test;

public class TriangleTest {

  /**
   * Constructor Tests - Null Arguments.
   */
  @Test
  public void triangleHealthyConstructorTest(){
    new Triangle(new Double(0, 0), 10, 20, Color.BLACK, 0);
  }

  @Test (expected = NullPointerException.class)
  public void triangleNullPositionObjectConstructorTest(){
    new Triangle(null, 10, 20, Color.BLACK, 0);
  }

  @Test (expected = NullPointerException.class)
  public void triangleNullColorObjectConstructorTest(){
    new Triangle(new Double(0, 0), 10, 20, null, 0);
  }

  /**
   * Constructor Tests - Illegal Arguments.
   */
  @Test (expected = IllegalArgumentException.class)
  public void triangleInvalidHeightConstructorTest(){
    new Triangle(new Double(0, 0), -1, 20, Color.BLACK, 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void triangleInvalidWidthConstructorTest(){
    new Triangle(new Double(0, 0), 10, -1, Color.BLACK, 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void triangleInvalidOrderConstructorTest(){
    new Triangle(new Double(0, 0), 10, 20, Color.BLACK, -1);
  }

  /**
   * Render() Tests
   */
  @Test
  public void renderTest(){
    // TODO: Not sure what these tests will look like
  }

  /**
   * ChangePosition() AND GetPosition() Tests
   */
  @Test
  public void positionMethodsTest(){
    Double positionOne = new Double(0, 0);
    Double positionTwo = new Double(1, 1);
    Triangle tri = new Triangle(positionOne, 10, 20, Color.BLACK, 0);

    tri.changePosition(positionTwo);
    Assert.assertEquals(tri.getPosition(), positionTwo);
    Assert.assertNotEquals(tri.getPosition(), positionOne);
  }

  /**
   * ChangeSize() AND GetSize() Tests
   */
  @Test
  public void sizeMethodsTest(){
    double[] sizeOne = new double[]{1, 1};
    double[] sizeTwo = new double[]{30, 15};
    Triangle tri = new Triangle(new Double(0, 0), 10, 20, Color.BLACK, 0);

    tri.changeSize(sizeOne);
    Assert.assertEquals(tri.getSize()[0], sizeOne[0], 0 );
    Assert.assertEquals(tri.getSize()[1], sizeOne[1], 0 );
    Assert.assertNotEquals(tri.getSize()[0], sizeTwo[0], 0 );
    Assert.assertNotEquals(tri.getSize()[1], sizeTwo[1], 0 );
  }

  /**
   * GetPriority Tests
   */
  @Test
  public void getPriorityTest(){
    Triangle tri = new Triangle(new Double(0, 0), 10, 20, Color.BLACK, 0);
    
    Assert.assertEquals(tri.getPriority(), 0);
  }
}