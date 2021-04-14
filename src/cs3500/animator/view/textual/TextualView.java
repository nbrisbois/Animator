package cs3500.animator.view.textual;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.IShape;
import cs3500.animator.view.IAnimationView;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Representing the view displaying the animation model textually.
 */
public class TextualView implements IAnimationView {

  private final AnimationModel model;
  private final Appendable ap;
  private int speed = 1;

  /**
   * The constructor of the textual view for the animation.
   *
   * @param model the model we are trying to textualize
   * @throws NullPointerException if the model or the appendable is null
   */
  public TextualView(AnimationModel model) throws NullPointerException {
    Objects.requireNonNull(model);

    this.model = model;
    this.ap = new StringBuilder("");
  }

  /**
   * Display the animation textually and adds the textual view to an appendable.
   */
  @Override
  public void render() {
    String answer = "";
    List<IShape> shapes = model.getShapes();
    for (IShape shape : shapes) {
      answer += shape.toString() + "\n \n";
    }
    try {
      this.ap.append(answer);
    } catch (IOException e) {
      //do nothing
    }
    System.out.println(this.ap);
  }

  @Override
  public void setSpeed(int speed) {
    this.speed = speed;
  }

}