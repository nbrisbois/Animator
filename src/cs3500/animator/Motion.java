package cs3500.animator;

import java.awt.Color;

public class Motion {
  private final int movementX;
  private final int movementY;
  private final Color color;
  private final double scaleX;
  private final double scaleY;
  private final int ticksTaken;

  /**
   * The constructor of a motion object.
   *
   * @param movementX the movement the shape is taking horizontally
   * @param movementY the movement the shape is taking vertically
   * @param color the color the shape changes to
   * @param scaleX the horizontal size change of the shape
   * @param scaleY the vertical size change of the shape
   * @param ticksTaken the time this motion takes
   */
  public Motion(int movementX, int movementY, Color color, double scaleX, double scaleY,
      int ticksTaken) {
    this.movementX = movementX;
    this.movementY = movementY;
    this.color = color;
    this.scaleX = scaleX;
    this.scaleY = scaleY;
    this.ticksTaken = ticksTaken;
  }

  public int getMoveX() {
    return movementX;
  }

  public int getMoveY() {
    return movementY;
  }

  public Color getColor() {
    return color;
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
}
