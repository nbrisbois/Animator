package cs3500.animator.model;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Oval Specific Implementation of a Shape Class.
 */
public class Oval extends Shape {

  /**
   * Public Constructor for Oval Shape. Extends abstract class Shape.
   *
   * @param name    The name of the shape as a String
   * @param pos     Starting position of shape as a Double object
   * @param h       Starting height of the shape
   * @param w       Starting Width of the shape
   * @param color   Starting Color of the shape
   * @param t       Spawn time of the shape
   * @param motions A Queue of motions that the shape will utilize
   */
  public Oval(String name, Double pos, double w, double h, Color color, long t,
      Queue<Motion> motions, int offsetY, int offsetX) {
    super(name, pos, w, h, color, t, motions, offsetY, offsetX);
  }

  /**
   * An Oval constructor using just a name.
   *
   * @param name The unique name of shape
   */
  public Oval(String name, int offsetX, int offsetY) throws NullPointerException, IllegalArgumentException {
    super(name, offsetX, offsetY);
  }

  @Override
  public String getShape() {
    return "ellipses";
  }

  @Override
  public IShape copy() {
    return new Oval(this.name, this.position, this.dimensions[0], this.dimensions[1],
        this.color, this.startTick, this.motions, this.offsetY, this.offsetX);
  }

  @Override
  public java.awt.Shape render() {

    return new Ellipse2D.Double(this.position.getX(), this.position.getY(),
        this.dimensions[0], this.dimensions[1]);
  }

  @Override
  public String getType() {
    return "ellipse";
  }

  @Override
  public String[] getSVGAttributes() {
    return new String[]{"cx", "cy", "rx", "ry"};
  }

  @Override
  public void changeTick(int t) {
    this.startTick = t;
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
    answer.append("Shape C oval");
    IShape old = this;
    for (int i = 0; i < motions.size(); i++) {
      IShape newShape = this.executeMotion(i);
      String rowAnswer = String.format("\nmotion C %-3d %-3.0f %-3.0f %-3.0f %-3.0f %-3d %-3d %-3d"
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
    this.timeElapsed += lom.get(motionIndex).getTicks();
    return new Oval(this.name, newPosition,
        dimensions[0] * lom.get(motionIndex).getScaleX(),
        dimensions[1] * lom.get(motionIndex).getScaleY(),
        lom.get(motionIndex).getColor(), this.startTick + timeElapsed,
        this.motions, this.offsetY, this.offsetX);

  }

}