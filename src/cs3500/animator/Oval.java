package cs3500.animator;

import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.util.List;

/**
 * Oval Specific Implementation of a Shape Class.
 */
public class Oval extends Shape {

  public Oval(Double pos, double h, double w, Color color, int t, List<Motion> motions)
      throws NullPointerException, IllegalArgumentException {
    super(pos, h, w, color, t, motions);
  }

  /**
   * Displaying all the motions this shape would go through.
   *
   * @return a string showing what motions the shape would go through
   */
  @Override
  public String render() {
    String answer = "Shape C oval";
    Shape old = this;
    for (int i = 0; i < motions.size(); i++) {
      Shape newShape = this.executeMotion(i);
      String rowAnswer =
          "\nmotion C start t: " + old.getStartTik() + " x: " + old.getPosition().getX() + " y: "
              + old.getPosition().getY() + " w: " + old.getSize()[0] + " h: " + old.getSize()[1]
              + " rgb: " + String.valueOf(old.getColor().getRGB()) + "   end t: "
              + newShape.getStartTik() + " x: " + newShape.getPosition().getX() + " y: "
              + newShape.getPosition().getY() + " w: " + newShape.getSize()[0] + " h: "
              + newShape.getSize()[1] + " rgb: " + String.valueOf(newShape.getColor().getRGB());
      answer += rowAnswer;
      old = this.executeMotion(i);
    }
    return answer;
  }

}