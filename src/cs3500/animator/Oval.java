package cs3500.animator;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D.Double;
import java.util.List;
import java.util.Queue;

/**
 * Oval Specific Implementation of a Shape Class.
 */
public class Oval extends Shape {

  public Oval(String name, Double pos, double h, double w, Color color, long t, Queue<Motion> motions)
      throws NullPointerException, IllegalArgumentException {
    super(name, pos, h, w, color, t, motions);
  }

  /**
   * Displaying all the motions this shape would go through.
   *
   * @return a string showing what motions the shape would go through
   */
  @Override
  public java.awt.Shape render() {
    Ellipse2D circle = new Ellipse2D.Double(this.position.getX(), this.position.getY(), this.dimensions[0], this.dimensions[1]);
    return circle;
  }

  /**
   * Make a copy of the oval.
   *
   * @return a new oval
   */
  @Override
  public IShape copy() {
    return new Oval(this.name, this.position, this.dimensions[0], this.dimensions[1],
        this.color, this.startTick, this.motions);
  }

  @Override
  public String toString() {
    String output = name + " ellipsis";
    return output;
  }

}