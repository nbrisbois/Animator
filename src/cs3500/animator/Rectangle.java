package cs3500.animator;

import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.util.List;
import java.util.Objects;

/**
 * Rectangle Specific Implementation of a Shape Class.
 */
public class Rectangle extends Shape {

  /**
   * Constructor for a rectangle Shape.
   *
   * @param pos   The spawn position of the Shape
   * @param h     Height of the Rectangle
   * @param w     Width of the Rectangle
   * @param color The color of the Rectangle
   * @throws NullPointerException     A NullPointerException is thrown when a null Object argument
   *                                  is provided
   * @throws IllegalArgumentException An IllegalArgumentException is thrown when the arguments are
   *                                  invalid
   */
  public Rectangle(Double pos, double h, double w, Color color, int t, List<Motion> motions)
      throws NullPointerException, IllegalArgumentException {
    super(pos, h, w, color, t, motions);
  }

  @Override
  public String render() {
    String answer = "";
    Shape old = this;
    for (int i = 0; i < motions.size(); i++) {
      Shape newShape = this.executeMotion(i);
      String rowAnswer =
          "\nmotion R start t: " + old.getStartTik() + " x: " + old.getPosition().getX() + " y: "
              + old.getPosition().getY() + " w: " + old.getSize()[0] + " h: " + old.getSize()[1]
              + " rgb: " + String.valueOf(old.getColor().getRGB()) + "   end t: "
              + newShape.getStartTik() + " x: " + newShape.getPosition().getX() + " y: "
              + newShape.getPosition().getY() + " w: " + newShape.getSize()[0] + " h: "
              + newShape.getSize()[1] + " rgb: " + String.valueOf(newShape.getColor().getRGB());
      old = this.executeMotion(i);
    }
    return answer;
  }


}