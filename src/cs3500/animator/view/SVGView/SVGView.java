package cs3500.animator.view.SVGView;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.IShape;
import cs3500.animator.view.IAnimationView;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class SVGView implements IAnimationView {

  private final AnimationModel model;
  private final FileWriter myWriter;

  public SVGView(AnimationModel model) throws IOException {
    super();
    Objects.requireNonNull(model);
    this.model = model;
    myWriter = new FileWriter("Animator/src/cs3500/animator/view/SVGView/animator.svg", false);
  }

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
