package cs3500.animator;

import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
  protected List<Motion> motions;

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
  public Shape(Double pos, double x, double y, Color color, int startTick, List<Motion> motions)
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
    this.motions = new ArrayList<>(motions);
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
    this.motions.add(m);
  }

  public Shape executeMotion(int motionIndex) {
    Shape newShape = this;
    Double newPosition = new Double(position.getX() + motions.get(motionIndex).getMoveX(),
        position.getY() + motions.get(motionIndex).getMoveY());
    double[] newSize = new double[]{dimensions[0] * motions.get(motionIndex).getScaleX(),
        dimensions[1] * motions.get(motionIndex).getScaleY()};
    newShape.changePosition(newPosition);
    newShape.changeSize(newSize);
    newShape.changeColor(motions.get(motionIndex).getColor());
    newShape.changeTick(motions.get(motionIndex).getTicks());
    return newShape;
  }
}