package cs3500.animator;

import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.util.Queue;

/**
 * Polygon Specific Implementation of a Shape Class.
 */
public class Polygon extends Shape {

  private final int sides;

  /**
   * Polygon Constructor for generic polygon Shape.
   *
   * @param pos       The spawn position of the Shape
   * @param h         Height of the Polygon
   * @param w         Width of the Polygon
   * @param color     The color of the Polygon
   * @param startTick The start tick of the Polygon. This is where the shape will be rendered on the
   *                  initially on the Screen
   * @param motions   A list of motions detailing how the shape will move as time goes on
   * @param sides     The number of sides the polygon should have
   * @throws NullPointerException     Thrown when a null object is passed in construction
   * @throws IllegalArgumentException Thrown when an invalid primitive is passed in construction
   */
  public Polygon(String name, Double pos, double h, double w, Color color, long startTick, Queue<Motion> motions,
      int sides)
      throws NullPointerException, IllegalArgumentException {
    super(name, pos, h, w, color, startTick, motions);
    if (sides < 3) {
      throw new IllegalArgumentException("Polygon Object must have at least 3 sides");
    }
    this.sides = sides;
  }

  /**
   * Getter used to return the number of sides the polygon has.
   *
   * @return int number of sides
   */
  public int getSides() {
    return this.sides;
  }

  @Override
  public java.awt.Shape render() {
    java.awt.Shape polygon = new java.awt.Polygon();
    return polygon;
  }

  @Override
  public IShape copy() {
    return new Polygon(this.name, this.position, this.dimensions[0], this.dimensions[1],
        this.color, this.startTick, this.motions, this.sides);
  }

  @Override
  public String toString() {
    String output = name + " Polygon";
    return output;
  }
}