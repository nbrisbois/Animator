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
  public int getStartTick() {
    int t = this.startTick;
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

  /**
   * Applies the changes a motion would do to the shape.
   *
   * @param motionIndex an integer representing the index of the motion we want to execute
   * @return the Shape after undergoing a motion
   * @throws IllegalArgumentException when index is negative
   */
  public IShape executeMotion(int motionIndex) throws IllegalArgumentException {
    if (motionIndex < 0) {
      throw new IllegalArgumentException("Invalid index");
    }
    IShape newShape = this.copy();
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