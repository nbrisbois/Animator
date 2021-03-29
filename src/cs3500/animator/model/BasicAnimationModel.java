package cs3500.animator.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Represents a basic animation model.
 */
public class BasicAnimationModel implements AnimationModel {

  private final List<IShape> shapes;
  private final int sceneHeight;
  private final int sceneWidth;
  private final int duration;
  private final int speed;

  /**
   * Construct an animation model.
   *
   * @param shapes      the list of Shapes in this model
   * @param sceneHeight the height of the scene
   * @param sceneWidth  the width of the scene
   * @param duration    the ticks of how long this model lasts
   *
   * @throws IllegalArgumentException if an argument is illegal
   * @throws IllegalStateException if the state of a argument is illegal
   * @throws NullPointerException if an argument is null
   */
  public BasicAnimationModel(List<IShape> shapes, int sceneHeight,
      int sceneWidth, int duration, int frameSpeed)
      throws IllegalArgumentException, NullPointerException, IllegalStateException {
    if (duration < 0) {
      throw new IllegalArgumentException("Duration cannot be less than zero ticks");
    }
    if (frameSpeed < 0) {
      throw new IllegalArgumentException("Frame speed cannot go lower than 1");
    }
    Objects.requireNonNull(shapes, "List of shapes cannot be null");
    for (int i = 0; i < shapes.size(); i++) {
      for (int j = 0; j < shapes.size(); j++) {
        if (i != j && shapes.get(i).getName() == shapes.get(j).getName()) {
          throw new IllegalStateException("Cannot have shapes of the same name");
        }
      }
    }
    this.shapes = this.copyShapes(shapes);
    this.sceneHeight = sceneHeight;
    this.sceneWidth = sceneWidth;
    this.duration = duration;
    this.speed = frameSpeed;
  }

  @Override
  public void addShape(IShape shape) throws NullPointerException {
    Objects.requireNonNull(shape);
    this.shapes.add(shape.copy());
  }

  @Override
  public List<IShape> getShapes() throws IllegalArgumentException{
    if (this.shapes.isEmpty()) {
      throw new IllegalStateException("There are no shapes");
    }
    return copyShapes(this.shapes);
  }

  @Override
  public int getDuration() {
    return duration;
  }

  @Override
  public int getSceneHeight() {
    return sceneHeight;
  }

  @Override
  public int getSceneWidth() {
    return sceneWidth;
  }

  @Override
  public int getFrameSpeed() {
    return speed;
  }

  @Override
  public List<IShape> moveShapes(long time) {
    List<IShape> returnList = new ArrayList<IShape>();
    for (int i = 0; i < shapes.size(); i++) {
      if (shapes.get(i).getStartTick() <= time) {
        shapes.get(i).calculateMotion((time) * speed);
        returnList.add(shapes.get(i));
      }
    }
    //TODO sort the returnList order
    return returnList;
  }

  /**
   * Uses the copy method to clone lists of shapes
   * @param shapes inputted list of shapes
   * @return a cloned version of the list
   */
  private List<IShape> copyShapes(List<IShape> shapes) {
    List<IShape> copy = new ArrayList<IShape>();
    for (IShape shape : shapes) {
      copy.add(shape.copy());
    }
    return copy;
  }
}
