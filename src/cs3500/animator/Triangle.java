package cs3500.animator;

import java.awt.Color;
import java.awt.geom.Point2D.Double;

public class Triangle extends Shape {

  public Triangle(Double pos, double h, double w, Color color,
      int order) throws NullPointerException, IllegalArgumentException {
    super(pos, h, w, color, order);
  }

  @Override
  public void render() {

  }


}
