package cs3500.animator.view.SVGPackage;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.IShape;
import cs3500.animator.view.IAnimationView;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The view to display the animation as a string output in a specific format.
 */
public class SVGView implements IAnimationView {

  private final AnimationModel model;
  private final FileWriter myWriter;

  /**
   * The constructor of SVGView using the model.
   *
   * @param model the model of the animation we want to display as a SVGView
   * @throws IOException if file writer cannot output a desired writer
   */
  public SVGView(AnimationModel model) throws IOException {
    super();
    Objects.requireNonNull(model);
    this.model = model;
    myWriter = new FileWriter("Animator/src/cs3500/animator/view/SVGView/animator.svg", false);
  }

  /**
   * To display the SVG output of the model.
   */
  @Override
  public void render() {
    try {
      // Begin svg
      myWriter.write(String.format("<svg width=\"%scm\" height=\"%scm\" viewBox=\"%s %s %s %s\"\n"
              + "  xmlns=\"http://www.w3.org/2000/svg\">\n",
          model.getSceneWidth(),
          model.getSceneHeight(),
          0, 0,
          model.getSceneHeight() * 100,
          model.getSceneWidth() * 100));

      // Write to File
      List<IShape> shapes = new ArrayList<>(model.getShapes());
      for (IShape s : shapes) {
        myWriter.write(s.generateSVG());
      }

      // End svg
      myWriter.write("</svg>");
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
