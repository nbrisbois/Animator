package cs3500.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Rectangle Specific Implementation of a Shape Class.
 */
public class Rectangle extends Shape {

  /**
   * Constructor for a rectangle Shape.
   *
   * @param pos       The spawn position of the Shape
   * @param h         Height of the Rectangle
   * @param w         Width of the Rectangle
   * @param color     The color of the Rectangle
   * @param startTick The start tick of the Polygon. This is where the shape will be rendered on the
   *                  initially on the Screen
   * @param motions   A list of motions detailing how the shape will move as time goes on
   * @throws NullPointerException     A NullPointerException is thrown when a null Object argument
   *                                  is provided
   * @throws IllegalArgumentException An IllegalArgumentException is thrown when the arguments are
   *                                  invalid
   */
  public Rectangle(String name, Double pos, double w, double h, Color color, long startTick,
      Queue<Motion> motions, int offsetY, int offsetX)
      throws NullPointerException, IllegalArgumentException {
    super(name, pos, w, h, color, startTick, motions, offsetY, offsetX);
  }

  /**
   * A rectangle constructor using just a name.
   *
   * @param name The unique name of shape
   */
  public Rectangle(String name, int offsetX, int offsetY)
      throws NullPointerException, IllegalArgumentException {
    super(name, new Double(1, 1), 2, 2, Color.WHITE, 1, new PriorityQueue<>(),
        offsetY, offsetX);
  }

  @Override
  public java.awt.Shape render() {
    return new Rectangle2D.Double(this.position.getX(), this.position.getY(),
        this.dimensions[0], this.dimensions[1]);
  }

  @Override
  public String getType() {
    return "rect";
  }

  @Override
  public String[] getSVGAttributes() {
    return new String[]{"x", "y", "width", "height"};
  }

  @Override
  public void changeTick(int t) {
    this.startTick = t;
  }

  @Override
  public IShape copy() {
    return new Rectangle(this.name, this.position, this.dimensions[0], this.dimensions[1],
        this.color, this.startTick, this.motions, this.offsetY, this.offsetX);
  }

  public int getOffsetX() {
    return offsetX;
  }

  public int getOffsetY() {
    return offsetY;
  }

  /**
   * Displaying all the motions this shape would go through.
   *
   * @return a string showing what motions the shape would go through
   */
  @Override
  public String toString() {
    StringBuilder answer = new StringBuilder();
    answer.append(String.format("shape %s rectangle", this.getName()));
    IShape old = this;
    for (int i = 0; i < motions.size(); i++) {
      IShape newShape = this.executeMotion(i);
      String rowAnswer = String.format("\nmotion %s %-3d %-3.0f %-3.0f %-3.0f %-3.0f %-3d %-3d %-3d"
              + "   %-3d %-3.0f %-3.0f %-3.0f %-3.0f %-3d %-3d %-3d",
          old.getName(),
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
    this.timeElapsed += lom.get(motionIndex).getTicks();
    return new Rectangle(this.name, newPosition,
        dimensions[0] * lom.get(motionIndex).getScaleX(),
        dimensions[1] * lom.get(motionIndex).getScaleY(),
        lom.get(motionIndex).getColor(), this.startTick + timeElapsed,
        this.motions, this.offsetY, this.offsetX);
  }

}