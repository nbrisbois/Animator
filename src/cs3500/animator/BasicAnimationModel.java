package cs3500.animator;

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

  /**
   * The private constructor of BasicAnimationModel using a builder.
   *
   * @param builder the builder we use to build our model
   * @throws NullPointerException if the builder is null
   */
  private BasicAnimationModel(ModelBuilder builder) throws NullPointerException {
    Objects.requireNonNull(builder);
    this.shapes = builder.shapes;
    this.sceneHeight = builder.sceneHeight;
    this.sceneWidth = builder.sceneWidth;
    this.duration = builder.duration;
  }

  /**
   * Represents a builder class for BasicAnimationModel.
   */
  public static class ModelBuilder {

    private List<IShape> shapes;
    private int sceneHeight;
    private int sceneWidth;
    private int duration;

    /**
     * The constructor of the builder.
     *
     * @param shapes the list of IShape that we will be animated
     */
    public ModelBuilder(List<IShape> shapes) {
      this.shapes = shapes;
    }

    /**
     * Sets the size of the canvas of the animation.
     *
     * @param h the height of the scene
     * @param w the width of the scene
     * @return the builder containing the size of the scene
     * @throws IllegalArgumentException Thrown when passed invalid height or width
     */
    public ModelBuilder setScene(int h, int w) throws IllegalArgumentException {
      if (h < 0 || w < 0) {
        throw new IllegalArgumentException("Negative scene size");
      }
      this.sceneHeight = h;
      this.sceneWidth = w;
      return this;
    }

    /**
     * Sets the duration of the animation.
     *
     * @param duration the ticks of how long this animation lasts
     * @return the builder containing the duration
     * @throws IllegalArgumentException Thrown when passed invalid duration
     */
    public ModelBuilder setDuration(int duration) {
      if (duration < 0) {
        throw new IllegalArgumentException("Negative duration");
      }
      this.duration = duration;
      return this;
    }

    /**
     * Build the animation model using the builder.
     *
     * @return the model being built.
     */
    public BasicAnimationModel build() {
      BasicAnimationModel model = new BasicAnimationModel(this);
      return model;
    }
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
  public List<IShape> getShapes() {
    return shapes;
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
}
