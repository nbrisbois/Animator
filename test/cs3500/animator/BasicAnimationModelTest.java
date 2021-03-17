package cs3500.animator;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.css.Rect;

public class BasicAnimationModelTest {

  List<Motion> motions = new ArrayList<Motion>();
  Motion motion1 = new Motion(5, 5, Color.BLACK, 2, 2, 10);
  Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2, 10);
  Motion motion3 = new Motion(0, -5, Color.ORANGE, 1, 2, 20);
  Motion motion4 = new Motion(0, 0, Color.ORANGE, 1, 1, 10);

  List<IShape> testShapes = new ArrayList<>();
  Oval testOval = new Oval(new Double(0, 0), 10, 10, Color.BLACK, 0, motions);
  Rectangle testRect = new Rectangle(new Double(0, 0), 10, 10, Color.BLACK, 0, motions);
  Polygon testPoly = new Polygon(new Double(0, 0), 10, 10, Color.BLACK, 0, motions, 10);

  @Before
  public void setup() {
    motions.add(motion1);
    motions.add(motion2);
    testShapes.add(testOval);
    testShapes.add(testRect);
    testShapes.add(testPoly);
  }

  BasicAnimationModel testModel;

  /**
   * Invalid Argument Testing.
   */
  @Test(expected = IllegalArgumentException.class)
  public void modelInvalidSceneHeightTest() {
    testModel = new BasicAnimationModel(testShapes, 0, 10, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void modelInvalidSceneWidthTest() {
    testModel = new BasicAnimationModel(testShapes, 10, 0, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void modelInvalidDurationTest() {
    testModel = new BasicAnimationModel(testShapes, 10, 10, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void modelInvalidShapesEmptyTest() {
    testModel = new BasicAnimationModel(new ArrayList<IShape>(), 10, 10, 0);
  }

  @Test(expected = NullPointerException.class)
  public void modelInvalidShapesNullTest() {
    testModel = new BasicAnimationModel(null, 10, 10, 0);
  }

  /**
   * Getter Tests.
   */
  @Test
  public void modelGetShapesTest() {
    testModel = new BasicAnimationModel(testShapes, 10, 10, 0);
    Assert.assertNotSame(testOval, testModel.getShapes().get(0));
    Assert.assertNotSame(testRect, testModel.getShapes().get(1));
    Assert.assertNotSame(testPoly, testModel.getShapes().get(2));
  }

  @Test
  public void modelGetSceneHeightTest() {
    testModel = new BasicAnimationModel(testShapes, 125125, 10, 0);
    Assert.assertEquals(125125, testModel.getSceneHeight());
  }

  @Test
  public void modelGetSceneWidthTest() {
    testModel = new BasicAnimationModel(testShapes, 10, 44444, 0);
    Assert.assertEquals(44444, testModel.getSceneWidth());
  }

  @Test
  public void modelGetDurationTest() {
    testModel = new BasicAnimationModel(testShapes, 10, 44444, 9999125);
    Assert.assertEquals(9999125, testModel.getDuration());
  }

  /**
   * AddShape Tests.
   */
  @Test(expected = NullPointerException.class)
  public void modelAddNullShapeTest() {
    testModel = new BasicAnimationModel(testShapes, 10, 10, 10);
    testModel.addShape(null);
  }

  @Test
  public void modelAddShapeTest() {
    testModel = new BasicAnimationModel(testShapes, 10, 10, 10);
    Oval newOval = new Oval(new Double(59, 12), 163, 205, Color.MAGENTA, 0, motions);
    testModel.addShape(newOval);

    Assert.assertNotSame(newOval, testModel.getShapes().get(3));

    Assert.assertEquals(newOval.getPosition().getX(),
        testModel.getShapes().get(3).getPosition().getX(), 0);
    Assert.assertEquals(newOval.getPosition().getY(),
        testModel.getShapes().get(3).getPosition().getY(), 0);
    Assert.assertEquals(newOval.getColor(), testModel.getShapes().get(3).getColor());
    Assert.assertEquals(newOval.getSize()[0], testModel.getShapes().get(3).getSize()[0], 0);
    Assert.assertEquals(newOval.getSize()[1], testModel.getShapes().get(3).getSize()[1], 0);
    Assert.assertEquals(newOval.getStartTick(), testModel.getShapes().get(3).getStartTick());
  }

  /**
   * ToString Tests.
   */
  @Test
  public void modelCopyShapesTest(){
    testModel = new BasicAnimationModel(testShapes, 10, 10, 10);

    assertEquals("Shape O oval\n \nShape R rectangle\n \nShape P polygon\n \n",
        testModel.toString());

    Oval newOval = new Oval(new Double(59, 12), 163, 205, Color.MAGENTA, 0, motions);
    Rectangle newRect = new Rectangle(new Double(59, 12), 163, 205, Color.MAGENTA, 0, motions);
    Polygon newPoly = new Polygon(new Double(59, 12), 163, 205, Color.MAGENTA, 0, motions, 10);
    testModel.addShape(newOval);
    testModel.addShape(newRect);
    testModel.addShape(newPoly);

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