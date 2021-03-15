package cs3500.animator;

import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.util.List;

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
   * @param startTick The start tick of the Polygon. This is where the shape will be rendered on the
   *                  initially on the Screen
   * @param motions   A list of motions detailing how the shape will move as time goes on
   * @throws NullPointerException     A NullPointerException is thrown when a null Object argument
   *                                  is provided
   * @throws IllegalArgumentException An IllegalArgumentException is thrown when the arguments are
   *                                  invalid
   */
  public Rectangle(Double pos, double h, double w, Color color, int startTick, List<Motion> motions)
      throws NullPointerException, IllegalArgumentException {
    super(pos, h, w, color, startTick, motions);
  }

  /**
   * Displaying all the motions this shape would go through.
   *
   * @return a string showing what motions the shape would go through
   */
  @Override
  public String render() {
    StringBuilder answer = new StringBuilder();
    answer.append("Shape R rectangle");
    Shape old = this;
    for (int i = 0; i < motions.size(); i++) {
      Shape newShape = this.executeMotion(i);
      /*
      StringBuilder rowAnswerWithLabel = new StringBuilder();
      rowAnswerWithLabel.append(String.format("\nmotion R start t: %d ", old.getStartTik()));
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
      String rowAnswer = String.format("\nmotion R %-3d %-3.0f %-3.0f %-3.0f %-3.0f %-3d %-3d %-3d    %-3d %-3.0f %-3.0f %-3.0f %-3.0f %-3d %-3d %-3d",
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


}