package cs3500.animator;

import java.awt.Color;
import java.awt.geom.Point2D.Double;

public class Oval extends Shape {

  protected double rad;

  public Oval(Double pos, double rad, Color color)
      throws NullPointerException, IllegalArgumentException {
    super(pos, color);
    this.rad = rad;
  }

  @Override
  public void render() {

  }

}
