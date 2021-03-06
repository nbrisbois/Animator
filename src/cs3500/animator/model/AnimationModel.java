package cs3500.animator.model;

import java.awt.Color;
import java.util.List;
import java.util.Queue;

/**
 * The interface of the Animator model parameterized over IShape. It represents a single animation
 * as a list of shapes, the shapes' motions, and the background.
 */
public interface AnimationModel {

  /**
   * Adds a new shape to the model.
   *
   * @param shape the shape to be added
   */
  void addShape(IShape shape) throws NullPointerException;

  /**
   * Gets the height of the scene.
   *
   * @return the height of the scene.
   */
  int getSceneHeight();

  /**
   * Gets the width of the scene.
   *
   * @return the height of the scene.
   */
  int getSceneWidth();

  /**
   * Gets the top left X coordinate of the canvas.
   *
   * @return the height of the scene.
   */
  int getTopLeftX();

  /**
   * Gets the top left Y coordinate of the canvas.
   *
   * @return the height of the scene.
   */
  int getTopLeftY();

  /**
   * To retrieve the list of shapes in the model.
   *
   * @return a list of shapes
   */
  List<IShape> getShapes() throws IllegalArgumentException;

  /**
   * To retrieve the maximum ticks of animation.
   *
   * @return the duration of the animation
   */
  int getDuration();

  /**
   * To retrieve the speed of animation.
   *
   * @return the speed of the animation
   */
  int getSpeed();

  /**
   * Runs through the list of shapes moving them by time.
   *
   * @param time current time of the animation run
   */
  List<IShape> moveShapes(long time);

  /**
   * Observe the motions in this animation model.
   *
   * @return the list of lists of motions for each shape.
   */
  List<Queue<Motion>> getMotions();

  /**
   * Add a motion to the desired shape in the model.
   *
   * @param name       the name of the desired shape
   * @param movementX  the horizontal movement
   * @param movementY  the vertical movement
   * @param color      the final color after the motion
   * @param scaleX     the horizontal scaling of the shape
   * @param scaleY     the vertical scaling of the shape
   * @param ticksTaken the duration of this motion
   */
  void addMotion(String name, double movementX, double movementY, Color color, double scaleX,
      double scaleY, int ticksTaken);

  /**
   * Remove a shape from the animation model.
   *
   * @param name the unique name of the shape we want to removed
   */
  void removeShape(String name);

  /**
   * Remove the last motion of the desired shape from the animation model.
   *
   * @param name the unique name of the shape we want to have its motion removed
   */
  void removeMotion(String name);

  /**
   * To reset any shape in the model.
   */
  void resetShapes();
}

