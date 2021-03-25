package cs3500.animator;

import java.io.IOException;
import java.util.List;

/**
 * The interface of animation model.
 */
public interface AnimationModel {

  /**
   * Adds a new shape to the model.
   *
   * @param shape the shape to be added
   * @throws NullPointerException if the given shape is null
   */
  void addShape(IShape shape) throws NullPointerException;

  /**
   * To retrieve the list of shapes in the model.
   *
   * @return a list of shapes
   */
  List<IShape> getShapes();

  /**
   * To display the animation textually.
   *
   * @return a String textualize the animation
   */
  String toString();

  int getDuration();


  void moveShapes(long time, Appendable ap) throws IOException, IllegalStateException;
}
