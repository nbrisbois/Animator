package cs3500.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
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
  public Rectangle(String name, Double pos, double h, double w, Color color, long startTick, Queue<Motion> motions)
      throws NullPointerException, IllegalArgumentException {
    super(name, pos, h, w, color, startTick, motions);
  }

  @Override
  public java.awt.Shape render() {
    Rectangle2D rec = new Rectangle2D.Double(this.position.getX(), this.position.getY(), this.dimensions[0], this.dimensions[1]);
    return rec;
  }

  @Override
  public IShape copy() {
    return new Rectangle(this.name, this.position, this.dimensions[0], this.dimensions[1],
        this.color, this.startTick, this.motions);
  }

  @Override
  public String toString() {
    String output = name + " Rectangle";
    return output;
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