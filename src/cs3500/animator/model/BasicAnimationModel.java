package cs3500.animator.model;


import cs3500.animator.util.AnimationBuilder;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents an animation model that holds the information of the state of the animation.
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
   * @throws IllegalArgumentException if an argument is illegal
   * @throws IllegalStateException    if the state of a argument is illegal
   * @throws NullPointerException     if an argument is null
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
        if (i != j && shapes.get(i).getName().equals(shapes.get(j).getName())) {
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

  /**
   * A builder class to read the given files and use the information read to build an Animation
   * model.
   */
  public static final class Builder implements AnimationBuilder<AnimationModel> {

    private final List<IShape> shapes = new ArrayList<>();
    private int sceneHeight = 500;
    private int sceneWidth = 500;
    private final int duration = 1;
    private final int speed = 1;


    /**
     * Build the model using the field of the builder.
     *
     * @return a animation model that has attributes defined by the file.
     */
    @Override
    public AnimationModel build() {
      return new BasicAnimationModel(shapes, sceneHeight, sceneWidth, duration, speed);
    }

    /**
     * Specify the bounding box to be used for the animation.
     *
     * @param x      The leftmost x value
     * @param y      The topmost y value
     * @param width  The width of the bounding box
     * @param height The height of the bounding box
     * @return This {@link AnimationBuilder}
     */
    @Override
    public AnimationBuilder<AnimationModel> setBounds(int x, int y, int width, int height) {
      this.sceneHeight = height;
      this.sceneWidth = width;
      return this;
    }

    /**
     * Adds a new shape to the growing document.
     *
     * @param name The unique name of the shape to be added. No shape with this name should already
     *             exist.
     * @param type The type of shape (e.g. "ellipse", "rectangle") to be added. The set of supported
     *             shapes is unspecified, but should include "ellipse" and "rectangle" as a
     *             minimum.
     * @return convertible builder so that it can be modified again
     */
    @Override
    public AnimationBuilder<AnimationModel> declareShape(String name, String type) {
      switch (type) {
        case "rectangle":
          this.shapes.add(new Rectangle(name));
          break;
        case "ellipse":
          this.shapes.add(new Oval(name));
          break;
        default:
          break;
      }
      return this;
    }

    /**
     * Adds a transformation to the growing document.
     *
     * @param name The name of the shape (added with {@link AnimationBuilder#declareShape})
     * @param t1   The start time of this transformation
     * @param x1   The initial x-position of the shape
     * @param y1   The initial y-position of the shape
     * @param w1   The initial width of the shape
     * @param h1   The initial height of the shape
     * @param r1   The initial red color-value of the shape
     * @param g1   The initial green color-value of the shape
     * @param b1   The initial blue color-value of the shape
     * @param t2   The end time of this transformation
     * @param x2   The final x-position of the shape
     * @param y2   The final y-position of the shape
     * @param w2   The final width of the shape
     * @param h2   The final height of the shape
     * @param r2   The final red color-value of the shape
     * @param g2   The final green color-value of the shape
     * @param b2   The final blue color-value of the shape
     * @return convertible builder so that it can be modified again
     */
    @Override
    public AnimationBuilder<AnimationModel> addMotion(String name, int t1, int x1, int y1, int w1,
        int h1, int r1, int g1, int b1, int t2,
        int x2, int y2, int w2, int h2, int r2,
        int g2, int b2) {
      int duration = t2 - t1;
      double movementX = x2 - x1;
      double movementY = y2 - y1;
      Color color = new Color(r2, g2, b2);
      double scaleX = (double) w2 / w1;
      double scaleY = (double) h2 / h1;
      Motion addedMotion = new Motion(movementX, movementY, color, scaleX, scaleY, duration);
      for (IShape shape : shapes) {
        if (shape.getName().equals(name)) {
          shape.addMotion(addedMotion);
        }
      }
      return this;
    }
  }

  @Override
  public void addShape(IShape shape) throws NullPointerException {
    Objects.requireNonNull(shape);
    this.shapes.add(shape.copy());
  }

  @Override
  public List<IShape> getShapes() throws IllegalArgumentException {
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
  public List<IShape> moveShapes(long time) {
    List<IShape> returnList = new ArrayList<>();
    for (IShape shape : shapes) {
      if (shape.getStartTick() <= time) {
        shape.calculateMotion((time) * speed);
        returnList.add(shape);
      }
    }
    shapes.sort((o1, o2) -> {
      if (o1.getPriority() < o2.getPriority()) {
        return 1;
      } else if (o1.getPriority() > o2.getPriority()) {
        return -1;
      }
      return 0;
    });
    return returnList;
  }

  /**
   * Uses the copy method to clone lists of shapes.
   *
   * @param shapes inputted list of shapes
   * @return a cloned version of the list
   */
  private List<IShape> copyShapes(List<IShape> shapes) {
    List<IShape> copy = new ArrayList<>();
    for (IShape shape : shapes) {
      copy.add(shape.copy());
    }
    return copy;
  }
}

