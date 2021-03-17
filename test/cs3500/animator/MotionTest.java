package cs3500.animator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;

import java.awt.Color;
import org.junit.Assert;
import org.junit.Test;

public class MotionTest {

  /**
   * Invalid Argument Tests.
   */
  @Test
  public void motionValidScaleXArgumentTest(){
    new Motion(0, 0, Color.BLACK, 0, 1, 1);
  }

  @Test
  public void motionValidScaleYArgumentTest(){
    new Motion(0, 0, Color.BLACK, 1, 0, 1);
  }

  @Test
  public void motionValidTicksTakenArgumentTest(){
    new Motion(0, 0, Color.BLACK, 1, 1, 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void motionInvalidScaleXArgumentTest(){
    new Motion(0, 0, Color.BLACK, -1, 1, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void motionInvalidScaleYArgumentTest(){
    new Motion(0, 0, Color.BLACK, 1, -1, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void motionInvalidTicksTakenArgumentTest(){
    new Motion(0, 0, Color.BLACK, 1, 1, -1);
  }

  @Test (expected = NullPointerException.class)
  public void motionNullColorTest(){
    new Motion(0, 0, null, 1, 1, 1);
  }

  /**
   * Getter Tests.
   */
  @Test
  public void motionGetMoveXTest(){
    Motion motion = new Motion(1251, 124, Color.ORANGE, 1, 1, 1);
    assertEquals(1251, motion.getMoveX());
  }

  @Test
  public void motionGetMoveYTest(){
    Motion motion = new Motion(1251, 124, Color.ORANGE, 1, 1, 1);
    assertEquals(124, motion.getMoveY());
  }

  @Test
  public void motionGetColorTest(){
    Color c = Color.ORANGE;
    Motion motion = new Motion(1251, 124, c, 1, 1, 1);
    assertNotEquals(Color.BLACK, motion.getColor());
    assertEquals(Color.ORANGE, motion.getColor());
    assertNotSame(c, motion.getColor());
  }

  @Test
  public void motionGetScaleXTest(){
    Motion motion = new Motion(1251, 124, Color.ORANGE, 124, 125125, 1);
    Assert.assertEquals(124, motion.getScaleX(), 0);
  }

  @Test
  public void motionGetScaleYTest(){
    Motion motion = new Motion(1251, 124, Color.ORANGE, 123, 12515, 1);
    Assert.assertEquals(12515, motion.getScaleY(), 0);
  }

  @Test
  public void motionGetTickTakenTest(){
    Motion motion = new Motion(1251, 124, Color.ORANGE, 123, 12515, 9999);
    Assert.assertEquals(9999, motion.getTicks());
  }




}