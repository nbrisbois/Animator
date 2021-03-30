package cs3500.animator.view.TexualView;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.IShape;
import cs3500.animator.view.IAnimationView;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class TextualView implements IAnimationView {

  private final AnimationModel model;
  private final Appendable ap;

  /**
   * The constructor of the textual view for the animation.
   *
   * @param model the model we are trying to textualize
   * @param ap    the output of tis textual view
   * @throws NullPointerException if the model or the appendable is null
   */
  public TextualView(AnimationModel model, Appendable ap) throws NullPointerException {
    Objects.requireNonNull(model);
    Objects.requireNonNull(ap);

    this.model = model;
    this.ap = ap;
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
    } catch(IOException e) {
      //do nothing
    }
    System.out.println(this.ap);
  }
}