package cs3500.animator;

import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Abstract Class that all eligible shapes will be created from.
 */
public abstract class Shape implements IShape {

  protected final String name;
  protected Double position;
  protected double[] dimensions;
  protected Color color;
  protected long startTick;
  protected static int numberOfShapes = 0;
  protected final int order;
  protected Queue<Motion> motions;

  private double speedX;
  private double speedY;
  private double scaleX;
  private double scaleY;

  /**
   * Abstract Shape Constructor.
   *
   * @param pos   The spawn position of the Shape
   * @param x     Dimension one of Two
   * @param y     Dimension two of Two
   * @param color The color of the Shape
   * @param startTick The start tick of the Polygon. This is where the shape will be rendered on the
   *                  initially on the Screen
   * @param motions   A list of motions detailing how the shape will move as time goes on
   * @throws NullPointerException     A NullPointerException is thrown when a null Object argument
   *                                  is provided
   * @throws IllegalArgumentException An IllegalArgumentException is thrown when the arguments are
   *                                  invalid
   */
  public Shape(String name, Double pos, double x, double y, Color color, long startTick, Queue<Motion> motions)
      throws NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(pos);
    Objects.requireNonNull(color);
    Objects.requireNonNull(motions);
    Objects.requireNonNull(name);

    if (x < 0 || y < 0 || startTick < 0) {
      throw new IllegalArgumentException("Primitive constructor elements must not be non negative");
    }

    this.name = name;
    this.position = new Double(pos.getX(), pos.getY());
    this.dimensions = new double[]{x, y};
    this.color = new Color(color.getRGB());
    this.startTick = startTick;
    this.motions = new PriorityQueue<Motion>(motions);
    this.order = ++numberOfShapes;

    speedX = speedY = scaleX = scaleY = 0;

  }


  public void changePosition(Double pos) throws NullPointerException {
    Objects.requireNonNull(pos);
    this.position = new Double(pos.getX(), pos.getY());
  }

  public Double getPosition() {
    return new Double(position.getX(), position.getY());
  }

  public void changeSize(double[] size) throws NullPointerException {
    Objects.requireNonNull(size);
    this.dimensions = new double[]{size[0], size[1]};
  }

  public double[] getSize() {
    return new double[]{dimensions[0], dimensions[1]};
  }

  public void changeColor(Color c) throws NullPointerException {
    Objects.requireNonNull(c);
    this.color = new Color(c.getRGB());
  }

  public Color getColor() {
    return new Color(color.getRGB());
  }

  public long getStartTick() {
    long t = this.startTick;
    return t;
  }

  public int getPriority() {
    return this.order;
  }

  public void addMotion(Motion m) {
    this.motions.add(new Motion(m.getMoveX(), m.getMoveY(), m.getColor(),
        m.getScaleX(), m.getScaleY(), m.getTicks()));
  }

  public String getName() {
    return name;
  }

  public void calculateMotion(long currentTick, Appendable ap) throws IOException, IllegalStateException {
    long time = (motions.peek().getTicks() - currentTick);
    if ((currentTick >= motions.peek().getTicks())) {
      motions.remove();
      if (!motions.isEmpty()) {
        startTick = currentTick;
        time = (motions.peek().getTicks() - currentTick);
      }
    }
    if (startTick + time == motions.peek().getTicks()) {
      if (time == 0) {
        time = 1;
      }

      speedX = ((motions.peek().getMoveX() - position.getX())/time);
      speedY = ((motions.peek().getMoveY()- position.getY())/time);

      scaleX = ((motions.peek().getScaleX() - dimensions[0])/time);
      scaleY = ((motions.peek().getScaleY() - dimensions[1])/time);

      ap.append("motion " + name + " " + currentTick+ " " + position.getX() + " "
          + position.getY() + " " + dimensions[0] + " " + dimensions[1] + " " + color.getRed()
          + " " + color.getGreen() + " " + color.getBlue() + " " + motions.peek().getTicks()
          + " " + motions.peek().getMoveX() + " " + motions.peek().getMoveY() + " "
          + motions.peek().getScaleX() + " " + motions.peek().getScaleY() + " "
          + motions.peek().getColor().getRed() + " " + motions.peek().getColor().getGreen() + " "
          + motions.peek().getColor().getBlue() + "\n");
    }
    position.setLocation(
        position.getX() + speedX,
        position.getY() + speedY);

    dimensions[0] = dimensions[0] + scaleX;
    dimensions[1] = dimensions[1] + scaleY;


  }
}