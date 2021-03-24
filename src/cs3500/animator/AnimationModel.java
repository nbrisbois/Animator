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
   */
  void addShape(IShape shape) throws NullPointerException;

  /**
   * To retrieve the list of shapes in the model.
   *
   * @return a list of shapes
   */
  List<IShape> getShapes() throws IllegalArgumentException;

  /**
   * To retrieve the maximum ticks of animation
   * @return the duration of the animation
   */
  int getDuration();


  /**
   * Runs through the list of shapes moving them by time.
   * @param time current time of the animation run
   * @param ap a list to append the motions of animation
   * @throws IOException thrown when the model cannot append the motion command
   */
  void moveShapes(long time, Appendable ap) throws IOException;
}
