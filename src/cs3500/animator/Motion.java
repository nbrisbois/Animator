package cs3500.animator;

import java.awt.Color;
import java.util.Objects;

/**
 * Representing a motion that a shape goes through.
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
    if (scaleX < 0 || scaleY < 0 || ticksTaken < 0) {
      throw new IllegalArgumentException("Negative argument");
    }
    Objects.requireNonNull(color);
    this.movementX = movementX;
    this.movementY = movementY;
    this.color = color;
    this.scaleX = scaleX;
    this.scaleY = scaleY;
    this.ticksTaken = ticksTaken;
  }

  /**
   * Helper method used to get the x movement of this motion.
   *
   * @return an integer
   */
  public double getMoveX() {
    return movementX;
  }

  /**
   * Helper method used to get the y movement of this motion.
   *
   * @return an integer
   */
  public double getMoveY() {
    return movementY;
  }

  /**
   * Helper method used to get the color of this motion, but as a copy.
   *
   * @return a Color object
   */
  public Color getColor() {
    return new Color(color.getRGB());
  }

  /**
   * Helper method used to get the x scale on dimension change for this motion.
   *
   * @return a double
   */
  public double getScaleX() {
    return scaleX;
  }

  /**
   * Helper method used to get the y scale on dimension change for this motion.
   *
   * @return a double
   */
  public double getScaleY() {
    return scaleY;
  }

  /**
   * Helper method used to get the duration ticks for this motion.
   *
   * @return an integer
   */
  public int getTicks() {
    return ticksTaken;
  }

  @Override
  public int compareTo(Object o) {
    return 0;
  }
}
