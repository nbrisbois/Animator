package cs3500.animator;

import java.io.IOException;
import java.util.ArrayList;
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
   */
  public BasicAnimationModel(List<IShape> shapes, int sceneHeight, int sceneWidth, int duration, int frameSpeed) {
    if (duration < 0) {
      throw new IllegalArgumentException("Duration cannot be less than zero ticks");
    }
    if (frameSpeed < 0) {
      throw new IllegalArgumentException("Frame speed cannot go lower than 1");
    }
    Objects.requireNonNull(shapes);
    this.shapes = this.copyShapes(shapes);
    this.sceneHeight = sceneHeight;
    this.sceneWidth = sceneWidth;
    this.duration = duration;
    this.speed = frameSpeed;
  }

  /**
   * Helper function used to add a shape to the Model
   *
   * @param shape the shape to be added
   * @throws NullPointerException thrown when shape is null
   */
  @Override
  public void addShape(IShape shape) throws NullPointerException {
    Objects.requireNonNull(shape);
    this.shapes.add(shape);
  }

  /**
   * Helper function used to get the current list of shapes from the Model
   *
   * @return List of IShapes
   */
  @Override
  public List<IShape> getShapes() throws IllegalArgumentException{
    if (this.shapes.isEmpty()) {
      throw new IllegalStateException("There are no shapes");
    }
    return copyShapes(this.shapes);
  }

  /**
   * Convert current model instance into a mutable string. This string contains all shapes and their
   * motion history
   *
   * @return string
   */
  @Override
  public String toString() {
    String answer = "";
    for (IShape shape : shapes) {
      answer += shape.render() + "\n \n";
    }
    return answer;
  }

  @Override
  public int getDuration() {
    return duration;
  }

  @Override
  public void moveShapes(long time, Appendable ap) throws IOException {
    for (int i = 0; i < shapes.size(); i++) {
      if (shapes.get(i).getStartTick() == time) {
        ap.append("shape " + shapes.get(i).toString() + "\n");
      }
      if (shapes.get(i).getStartTick() <= time) {
        shapes.get(i).calculateMotion(time * speed, ap);
      }
    }
  }


  private List<IShape> copyShapes(List<IShape> shapes) {
    List<IShape> copy = new ArrayList<IShape>();
    for (IShape shape : shapes) {
      copy.add(shape.copy());
    }
    return copy;
  }



}
