package cs3500.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.util.LinkedList;
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
   * @param name  The name of shape
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

    /**
     * Check to see if each motion's ticks are greater than the previous.
     * This is important since there cannot be gaps, but there will be bugs if the,
     * the wrong tick is given.
     */
    this.motions = new LinkedList<Motion>();
    for (Motion m : motions) {
      if (!this.motions.isEmpty() && m.getTicks() < motions.peek().getTicks() ) {
        throw new IllegalStateException("No tick can be less than the previous tick");
      }
      this.motions.add(new Motion(m.getMoveX(), m.getMoveY(), m.getColor(), m.getScaleX(),
          m.getScaleY(), m.getTicks()));
    }

    this.name = name;
    this.position = new Double(pos.getX(), pos.getY());
    this.dimensions = new double[]{x, y};
    this.color = new Color(color.getRGB());
    this.startTick = startTick;
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

  public void calculateMotion(long currentTick) throws IllegalStateException {
    long time = (motions.peek().getTicks() - currentTick);
    /**
     * This is to collect the next motion
     */
    if ((currentTick >= motions.peek().getTicks())) {
      motions.remove();
      /**
       * The same concept, but only if it is not at the end of the queue.
       */
      if (!motions.isEmpty()) {
        startTick = currentTick;
        time = (motions.peek().getTicks() - currentTick);
      }
    }
    if (startTick + time == motions.peek().getTicks()) {
        speedX = (((motions.peek().getMoveX() - position.getX()) / time) * 100);
        speedY = (((motions.peek().getMoveY() - position.getY()) / time) * 100);
        scaleX = (((motions.peek().getScaleX() - dimensions[0]) / time) * 100);
        scaleY = (((motions.peek().getScaleY() - dimensions[1]) / time) * 100);
    }
    position.setLocation(
        position.getX() + speedX,
        position.getY() + speedY);

    dimensions[0] = dimensions[0] + scaleX;
    dimensions[1] = dimensions[1] + scaleY;
    color = motions.peek().getColor();
  }
}