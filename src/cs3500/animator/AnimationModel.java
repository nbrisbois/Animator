package cs3500.animator;

import java.util.List;

/**
 * The interface of animation model
 */
public interface AnimationModel {
  /**
   * Adds a new shape to the model.
   * @param shape the shape to be added
   */
  public void addShape(IShape shape);

  /**
   * To retrieve the list of shapes in the model.
   * @return a list of shapes
   */
  public List<IShape> getShapes();
}
