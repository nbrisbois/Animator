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
   * @param pos       The spawn position of the Shape
   * @param x         Dimension one of Two
   * @param y         Dimension two of Two
   * @param color     The color of the Shape
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

  /**
   * Changes the position of the same to the provided position.
   *
   * @param pos the position the shape will move to
   */
  public void changePosition(Double pos) throws NullPointerException {
    Objects.requireNonNull(pos);
    this.position = new Double(pos.getX(), pos.getY());
  }

  /**
   * Getter to obtain the position of the shape.
   *
   * @return Point2D.Double representing the position
   */
  public Double getPosition() {
    return new Double(position.getX(), position.getY());
  }

  /**
   * Changes the current size of the shape.
   *
   * @param size A two element array representing the new dimensions of the shape
   */
  public void changeSize(double[] size) throws NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(size);
    if (size.length != 2) {
      throw new IllegalArgumentException("Invalid size");
    }
    this.dimensions = new double[]{size[0], size[1]};
  }

  /**
   * Getter to obtain the size dimensions of the shape.
   *
   * @return A two element array representing the dimensions of the shape
   */
  public double[] getSize() {
    return new double[]{dimensions[0], dimensions[1]};
  }

  /**
   * Changes the current color of the shape to the provided color.
   *
   * @param c New color of shape
   */
  public void changeColor(Color c) throws NullPointerException {
    Objects.requireNonNull(c);
    this.color = new Color(c.getRGB());
  }

  /**
   * Getter for the current shape color.
   *
   * @return current shape color
   */
  public Color getColor() {
    return new Color(color.getRGB());
  }

  /**
   * Getter to obtain the tick this shape appears.
   *
   * @return a int representing the starting tick of the shape
   */
  public long getStartTick() {
    long t = this.startTick;
    return t;
  }

  /**
   * Change the starting tick of the shape.
   *
   * @param t an int representing the new tick
   */
  public void changeTick(int t) {
    this.startTick += t;
  }

  /**
   * Getter for the Overlap priority of the shape.
   *
   * @return unique integer
   */
  public int getPriority() {
    return this.order;
  }

  /**
   * Adding a motion to this shape.
   *
   * @param m a motion representing the new motion being added
   */
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