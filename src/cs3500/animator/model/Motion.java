package cs3500.animator.model;

import java.awt.Color;
import java.util.Objects;

/**
 * Representing a motion that a shape goes through. A motion can be one of the following: 1. A
 * Horizontal AND/OR Vertical movement along the screen. (Both in one motion creates a diagonal
 * movement) 2.
 */
public class Motion implements Comparable {

  private final double movementX;
  private final double movementY;
  private final Color color;
  private final double scaleX;
  private final double scaleY;
  private final int ticksTaken;

  /**
   * The constructor of a motion object.
   *
   * @param movementX  the movement the shape is taking horizontally
   * @param movementY  the movement the shape is taking vertically
   * @param color      the color the shape changes to
   * @param scaleX     the horizontal size change of the shape
   * @param scaleY     the vertical size change of the shape
   * @param ticksTaken the time this motion takes
   */
  public Motion(double movementX, double movementY, Color color, double scaleX, double scaleY,
      int ticksTaken) throws IllegalArgumentException {
    // Check for Valid Inputs
    if (scaleX < 0) {
      throw new IllegalArgumentException("ScaleX cannot be Negative");
    }
    if (scaleY < 0) {
      throw new IllegalArgumentException("ScaleY cannot be Negative");
    }
    if (ticksTaken < 0) {
      throw new IllegalArgumentException("TicksTaken cannot be Negative");
    }
    // Check for Null Inputs
    Objects.requireNonNull(color, "Color cannot be null");

    this.movementX = movementX;
    this.movementY = movementY;
    this.color = color;
    this.scaleX = scaleX;
    this.scaleY = scaleY;
    this.ticksTaken = ticksTaken;
  }

  public double getMoveX() {
    return movementX;
  }

  public double getMoveY() {
    return movementY;
  }

  public double getScaleX() {
    return scaleX;
  }

  public double getScaleY() {
    return scaleY;
  }

  public int getTicks() {
    return ticksTaken;
  }

  public Color getColor() {
    return new Color(color.getRGB());
  }

  @Override
  public int compareTo(Object o) {
    return 0;
  }
}

