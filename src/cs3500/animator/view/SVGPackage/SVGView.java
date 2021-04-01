package cs3500.animator.view.SVGPackage;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.IShape;
import cs3500.animator.view.IAnimationView;
import java.io.FileDescriptor;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The view to display the animation as a string output in a specific format.
 */
public class SVGView implements IAnimationView {

  private final AnimationModel model;
  private final FileWriter myWriter;
  private final int speed;

  /**
   * The constructor of SVGView using the model.
   *
   * @param model the model of the animation we want to display as a SVGView
   * @throws IOException if file writer cannot output a desired writer
   */
  public SVGView(AnimationModel model, String outFileName, int speed) throws IOException {
    super();
    this.speed = speed;
    this.model = model;
    if (outFileName.equals("")) {
      myWriter = new FileWriter(FileDescriptor.out);
    } else {
      myWriter = new FileWriter(String.format("Animator/src/cs3500/animator/%s", outFileName),
          false);
    }
  }

  /**
   * To display the SVG output of the model.
   */
  @Override
  public void render() {
    try {
      // Begin svg
      myWriter.write(String.format("<svg viewBox=\"%s %s %s %s\""
              + "  xmlns=\"http://www.w3.org/2000/svg\">\n",
          model.getTopLeftX(),
          model.getTopLeftY(),
          model.getSceneHeight(),
          model.getSceneWidth()));

      // Write to File
      List<IShape> shapes = new ArrayList<>(model.getShapes());
      for (IShape s : shapes) {
        myWriter.write(s.generateSVG(this.speed));
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
