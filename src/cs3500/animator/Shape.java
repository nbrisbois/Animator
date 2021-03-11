package cs3500.animator;

import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.util.Objects;

/**
 * Abstract Class that all eligible shapes will be created from.
 */
public abstract class Shape implements IShape {

  protected Double position;
  protected double[] dimensions;
  protected Color color;
  protected static int numberOfShapes = 0;
  protected final int order;

  /**
   * Abstract Shape Constructor.
   *
   * @param pos   The spawn position of the Shape
   * @param x     Dimension one of Two
   * @param y     Dimension two of Two
   * @param color The color of the Shape
   * @param order The order priority number of the Shape
   * @throws NullPointerException     A NullPointerException is thrown when a null Object argument
   *                                  is provided
   * @throws IllegalArgumentException An IllegalArgumentException is thrown when the arguments are
   *                                  invalid
   */
  public Shape(Double pos, double x, double y, Color color)
      throws NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(pos);
    Objects.requireNonNull(color);

    this.position = new Double(pos.getX(), pos.getY());
    this.dimensions = new double[]{x, y};
    this.color = new Color(color.getRGB());
    numberOfShapes += 1;
    this.order = numberOfShapes;
  }

  /**
   * constructor for oval.
   */
  public Shape(Double pos, Color color)
      throws NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(pos);
    Objects.requireNonNull(color);

    this.position = new Double(pos.getX(), pos.getY());
    this.color = new Color(color.getRGB());
    numberOfShapes += 1;
    this.order = numberOfShapes;
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

  public double[] getSize(){
    return new double[]{dimensions[0], dimensions[1]};
  }

  public void changeColor(Color c) throws NullPointerException{
    Objects.requireNonNull(c);
    this.color = new Color(c.getRGB());
  }

  public Color getColor(){
    return new Color(color.getRGB());
  }

  public int getPriority() {
    return this.order;
  }

}
