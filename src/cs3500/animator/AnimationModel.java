package cs3500.animator;

import java.util.List;

/**
 * The interface of animation model.
 */
public interface AnimationModel {

  /**
   * Adds a new shape to the model.
   *
   * @param shape the shape to be added
   */
  void addShape(IShape shape) throws NullPointerException;

  /**
   * To retrieve the list of shapes in the model.
   *
   * @return a list of shapes
   */
  List<IShape> getShapes() throws IllegalArgumentException;

  /**
   * To display the animation textually.
   *
   * @return a String textualize the animation
   */
  String toString();
}
