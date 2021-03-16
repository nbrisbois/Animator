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
    StringBuilder answer = new StringBuilder();
    answer.append("Shape C oval");
    IShape old = this;
    for (int i = 0; i < motions.size(); i++) {
      IShape newShape = this.executeMotion(i);
      /*
      StringBuilder rowAnswerWithLabel = new StringBuilder();
      rowAnswerWithLabel.append(String.format("\nmotion C start t: %d ", old.getStartTik()));
      rowAnswerWithLabel.append(String.format("x: %.0f ", old.getPosition().getX()));
      rowAnswerWithLabel.append(String.format("y: %.0f ", old.getPosition().getY()));
      rowAnswerWithLabel.append(String.format("w: %.0f ", old.getSize()[0]));
      rowAnswerWithLabel.append(String.format("h: %.0f ", old.getSize()[1]));
      rowAnswerWithLabel.append(String.format("rgb: %d ", old.getColor().getRed()));
      rowAnswerWithLabel.append(String.format("%d ", old.getColor().getGreen()));
      rowAnswerWithLabel.append(String.format("%d   ", old.getColor().getBlue()));
      rowAnswerWithLabel.append(String.format("end t: %d ",  newShape.getStartTik()));
      rowAnswerWithLabel.append(String.format("x: %.0f ", newShape.getPosition().getX()));
      rowAnswerWithLabel.append(String.format("y: %.0f ", newShape.getPosition().getY()));
      rowAnswerWithLabel.append(String.format("w: %.0f ", newShape.getSize()[0]));
      rowAnswerWithLabel.append(String.format("h: %.0f ", newShape.getSize()[1]));
      rowAnswerWithLabel.append(String.format("rgb: %d ", newShape.getColor().getRed()));
      rowAnswerWithLabel.append(String.format("%d ", newShape.getColor().getGreen()));
      rowAnswerWithLabel.append(String.format("%d   ", newShape.getColor().getBlue()));
      */
      String rowAnswer = String.format(
          "\nmotion C %-3d %-3.0f %-3.0f %-3.0f %-3.0f %-3d %-3d %-3d    "
              + "%-3d %-3.0f %-3.0f %-3.0f %-3.0f %-3d %-3d %-3d",
          old.getStartTick(),
          old.getPosition().getX(),
          old.getPosition().getY(),
          old.getSize()[0],
          old.getSize()[1],
          old.getColor().getRed(),
          old.getColor().getGreen(),
          old.getColor().getBlue(),
          newShape.getStartTick(),
          newShape.getPosition().getX(),
          newShape.getPosition().getY(),
          newShape.getSize()[0],
          newShape.getSize()[1],
          newShape.getColor().getRed(),
          newShape.getColor().getGreen(),
          newShape.getColor().getBlue()
      );

      answer.append(rowAnswer);
      old = this.executeMotion(i);
    }
    return answer.toString();
  }

  @Override
  public IShape copy() {
    return new Oval(this.position, this.dimensions[0], this.dimensions[1],
        this.color, this.startTick, this.motions);
  }
}