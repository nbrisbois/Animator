package cs3500.animator;

import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Abstract Class that all eligible shapes will be created from.
 */
public abstract class Shape implements IShape {

  protected Double position;
  protected double[] dimensions;
  protected Color color;
  protected int startTick;
  protected static int numberOfShapes = 0;
  protected final int order;
  protected Queue<Motion> motions;

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
  public Shape(Double pos, double x, double y, Color color, int startTick, Queue<Motion> motions)
      throws NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(pos);
    Objects.requireNonNull(color);
    Objects.requireNonNull(motions);

    if (x < 0 || y < 0 || startTick < 0) {
      throw new IllegalArgumentException("Primitive constructor elements must not be non negative");
    }

    this.position = new Double(pos.getX(), pos.getY());
    this.dimensions = new double[]{x, y};
    this.color = new Color(color.getRGB());
    this.startTick = startTick;
    this.motions = new PriorityQueue<Motion>(motions);
    this.order = ++numberOfShapes;
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

  public int getStartTick() {
    int t = this.startTick;
    return t;
  }

  public void changeTick(int t) {
    this.startTick += t;
  }

  public int getPriority() {
    return this.order;
  }

  public void addMotion(Motion m) {
    this.motions.add(new Motion(m.getMoveX(), m.getMoveY(), m.getColor(),
        m.getScaleX(), m.getScaleY(), m.getTicks()));
  }

  public void calculateMotion(long currentTick) {
    int time = motions.peek().getTicks();
    if (currentTick >= time) {
      motions.remove();
      time = motions.peek().getTicks();
    }
    position.setLocation(
        position.getX() + ((motions.peek().getMoveX()-position.getX())/(time-currentTick)),
        position.getY() + ((motions.peek().getMoveX()-position.getX())/(time-currentTick)));
    dimensions[0] = motions.peek().getScaleX()-dimensions[0]/(time-currentTick);
    dimensions[1] = motions.peek().getScaleY()-dimensions[0]/(time-currentTick);
    color = motions.peek().getColor();
  }
}