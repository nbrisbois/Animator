package cs3500.animator.model;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Oval Specific Implementation of a Shape Class.
 */
public class Oval extends Shape {

  public Oval(String name, Double pos, double h, double w, Color color, long t,
      Queue<Motion> motions)
      throws NullPointerException, IllegalArgumentException {
    super(name, pos, h, w, color, t, motions);
  }

  /**
   * An Oval constructor using just a name.
   *
   * @param name The unique name of shape
   */
  public Oval(String name) throws NullPointerException, IllegalArgumentException {
    super(name, new Double(1, 1), 2, 2, Color.WHITE, 1, new PriorityQueue<Motion>());
  }

  @Override
  public String getShape() {
    return "ellipses";
  }

  @Override
  public IShape copy() {
    return new Oval(this.name, this.position, this.dimensions[0], this.dimensions[1],
        this.color, this.startTick, this.motions);
  }

  @Override
  public java.awt.Shape render() {

    Ellipse2D circle = new Ellipse2D.Double(this.position.getX(), this.position.getY(),
        this.dimensions[0], this.dimensions[1]);
    return circle;
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
    double[] newSize = new double[]{dimensions[0] * lom.get(motionIndex).getScaleX(),
        dimensions[1] * lom.get(motionIndex).getScaleY()};
    IShape newShape = new Oval(this.name, newPosition,
        dimensions[0] * lom.get(motionIndex).getScaleX(),
        dimensions[1] * lom.get(motionIndex).getScaleY(),
        lom.get(motionIndex).getColor(), this.startTick + lom.get(motionIndex).getTicks(),
        this.motions);
    return newShape;
  }

  @Override
  public String writeAnimation() {
    long ticks_passed = this.getStartTick();
    StringBuilder svg = new StringBuilder();

    double previousScaleX = this.getSize()[0];
    double previousScaleY = this.getSize()[1];
    Color previousColor = this.getColor();

    while (!this.motions.isEmpty()){
      // AnimateMotion
      svg.append(String.format("<animateMotion dur=\"%ss\" repeatCount=\"0\" "
              + "path=\"M %s, %s L %s %s\" "
              + "begin=\"%s\" "
              + "/>\n\t",
          this.motions.peek().getTicks(),
          this.getPosition().getX(),
          this.getPosition().getY(),
          this.motions.peek().getMoveX(),
          this.motions.peek().getMoveY(),
          ticks_passed
      ));
      // AnimateColor
      svg.append(String.format("<animate attributeName=\"fill\" dur=\"%ss\" repeatCount=\"0\" "
              + "from=\"#%02x%02x%02x\" to=\"#%02x%02x%02x\" "
              + "begin=\"%s\" "
              + "/>\n\t",
          this.motions.peek().getTicks(),
          previousColor.getRed(),
          previousColor.getGreen(),
          previousColor.getBlue(),
          this.motions.peek().getColor().getRed(),
          this.motions.peek().getColor().getGreen(),
          this.motions.peek().getColor().getBlue(),
          ticks_passed
      ));
      // AnimateScale
      svg.append(String.format(("<animateTransform dur=\"%ss\" repeatCount=\"0\" "
              + "attributeName=\"transform\" "
              + "type=\"scale\" "
              + "additive=\"sum\" "
              + "from=\"%s %s\" "
              + "to=\"%s %s\" "
              + "begin=\"%s\" "
              + "/>\n"),
          this.motions.peek().getTicks(),
          previousScaleX / 100,
          previousScaleY / 100,
          this.motions.peek().getScaleX() / 100,
          this.motions.peek().getScaleY() / 100,
          ticks_passed
      ));
      svg.append("\n\t");
      ticks_passed += this.motions.peek().getTicks();
      previousScaleX = this.motions.peek().getScaleX();
      previousScaleY = this.motions.peek().getScaleY();
      previousColor = this.motions.peek().getColor();
      try {
        calculateMotion(ticks_passed);
      } catch (Exception e){
        break;
      }
    }
    svg.append("\n");
    return svg.toString();
  }

}