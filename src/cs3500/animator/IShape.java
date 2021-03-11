package cs3500.animator;

import java.awt.geom.Point2D.Double;

public interface IShape {

  void render();

  void changePosition(Double pos);

  Double getPosition();

  void changeSize(double[] size);

  double[] getSize();

  int getPriority();

}
