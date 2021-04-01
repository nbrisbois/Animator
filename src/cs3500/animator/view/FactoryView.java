package cs3500.animator.view;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.view.SVGPackage.SVGView;
import cs3500.animator.view.TexualPackage.TextualView;
import cs3500.animator.view.VisualPackage.VisualView;
import java.io.IOException;

/**
 * The factory of views that create different instances of IAnimationView based on desired view
 * type.
 */
public class FactoryView {

  private final AnimationModel model;
  private final String outFileName;
  private final int speed;

  /**
   * The constructor of the factory view.
   *
   * @param model the model that will be used to create an IAnimationView
   */
  public FactoryView(AnimationModel model, String outFileName, int speed) {
    this.model = model;
    this.outFileName = outFileName;
    this.speed = Math.max(speed, 1);
  }

  /**
   * Creates an instance of IAnimationView based on the specified view type.
   *
   * @param viewType a string representing the type of view user wants
   * @return an IAnimationView constructed using the model
   * @throws IOException if faisl to construct an SVGView
   */
  public IAnimationView getView(String viewType) throws IOException {
    if (viewType.equals("text")) {
      return new TextualView(model);
    } else if (viewType.equals("svg")) {
      return new SVGView(model, this.outFileName, speed);
    } else if (viewType.equals("visual")) {
      return new VisualView(model);
    } else {
      return null;
    }
  }
}
