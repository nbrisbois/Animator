package cs3500.animator;


import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.util.Objects;

public abstract class Shape implements IShape {

  protected final Double position;
  protected final double[] diamentions;
  protected final Color color;
  protected final int order;

  public Shape(Double pos, double h, double w, Color color, int order) throws NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(pos);
    Objects.requireNonNull(color);
    if (h < 0 || h < 0) {
      throw new IllegalArgumentException("The diamentions cannot be less than zero");
    }
    if (order < 0) {
      //ToDO figure out how to implement order
    }
    this.position = new Double(pos.getX(), pos.getY());
    diamentions = new double[]{h, w};
    this.color = new Color(color.getRGB());
    this.order = order;
  }



}
