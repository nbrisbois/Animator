package cs3500.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;
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
  public Polygon(String name, Double pos, double h, double w, Color color, long startTick,
      Queue<Motion> motions,
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

  /**
   * Displaying all the motions this shape would go through.
   *
   * @return a string showing what motions the shape would go through
   */
  @Override
  public String toString() {
    StringBuilder answer = new StringBuilder();
    answer.append("Shape P polygon");
    IShape old = this;
    for (int i = 0; i < motions.size(); i++) {
      IShape newShape = this.executeMotion(i);
      String rowAnswer = String.format("\nmotion P %-3d %-3.0f %-3.0f %-3.0f %-3.0f %-3d %-3d %-3d"
              + "   %-3d %-3.0f %-3.0f %-3.0f %-3.0f %-3d %-3d %-3d",
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
      old = newShape.copy();
    }
    return answer.toString();
  }

  /**
   * Applies the changes a motion would do to the shape.
   *
   * @param motionIndex an integer representing the index of the motion we want to execute
   * @return the Shape after undergoing a motion
   * @throws IllegalArgumentException when index is negative
   */
  private IShape executeMotion(int motionIndex) throws IllegalArgumentException {
    List<Motion> lom = new ArrayList<>(motions);
    if (motionIndex < 0) {
      throw new IllegalArgumentException("Invalid index");
    }
    Double newPosition = new Double(position.getX() + lom.get(motionIndex).getMoveX(),
        position.getY() + lom.get(motionIndex).getMoveY());
    double[] newSize = new double[]{dimensions[0] * lom.get(motionIndex).getScaleX(),
        dimensions[1] * lom.get(motionIndex).getScaleY()};
    IShape newShape = new Polygon(this.name, newPosition,
        dimensions[0] * lom.get(motionIndex).getScaleX(),
        dimensions[1] * lom.get(motionIndex).getScaleY(),
        lom.get(motionIndex).getColor(), this.startTick + lom.get(motionIndex).getTicks(),
        this.motions, this.sides);
    return newShape;
  }
}