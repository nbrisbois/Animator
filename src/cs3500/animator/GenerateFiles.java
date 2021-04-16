package cs3500.animator;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.BasicAnimationModel;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;
import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * Main Class for generating a .txt file that consists information for animation that represents the
 * scenery of a rainy day.
 */
public class GenerateFiles {

  /**
   * Create an animation model and translate the model to a .txt file representing the animation.
   *
   * @param args StdIn
   * @throws IOException when fails to render the model
   */
  public static void main(String[] args) throws IOException {

    Motion dropletFalling = new Motion(0, 400, Color.CYAN, 1, 1, 15);
    Motion ripple = new Motion(dropletFalling.getMoveX(), dropletFalling.getMoveY(), Color.BLUE,
        1.5, 0, 10);

    Queue<Motion> rainMotions = new LinkedList<>();
    rainMotions.add(dropletFalling);
    rainMotions.add(ripple);
    List shapes = new ArrayList<>();

    Queue floorMotion = new LinkedList<>();
    floorMotion.add(new Motion(0, 0, Color.DARK_GRAY, 1, 1, 1000));
    shapes.add(new Rectangle("floor", new Double(0, 400),
        500,
        400,
        Color.DARK_GRAY,
        0,
        floorMotion,
        0, 0));

    Queue backgroundMotion = new LinkedList<>();
    backgroundMotion.add(new Motion(0, 0, Color.DARK_GRAY, 1, 1, 200));
    backgroundMotion.add(new Motion(0, 0, Color.CYAN, 1, 1, 2));
    backgroundMotion.add(new Motion(0, 0, Color.CYAN, 1, 1, 200));
    shapes.add(new Rectangle("background", new Double(0, 0),
        500,
        400,
        Color.GRAY,
        0,
        backgroundMotion,
        0, 0));

    for (int ii = 0; ii < 1000; ii++) {
      Random r = new Random();
      shapes.add(new Oval(
          String.format("oval%s", ii),
          new Double(r.nextInt(500 - 1) + 1, 0),
          5,
          5,
          Color.CYAN,
          r.nextInt(100 - 1) + 1,
          rainMotions,
          0, 0));
    }

    AnimationModel model = new BasicAnimationModel(shapes, 0, 0, 500, 500, 100, 1);

    renderModel(model);
  }

  private static void renderModel(AnimationModel model) throws IOException {
    FileWriter myWriter = new FileWriter(
        String.format("%s/%s", System.getProperty("user.dir"), "ripple.txt"));

    myWriter.write(String.format("canvas %s %s %s %s\n", model.getTopLeftX(), model.getTopLeftY(),
        model.getSceneHeight(), model.getSceneWidth()));

    for (IShape shape : model.getShapes()) {
      myWriter.write(shape.toString() + "\n \n");
    }

    myWriter.close();

  }

  private static void addRippleMotion(Queue<Motion> motion, double scale) {
    List<Motion> newMotions = new ArrayList<>(motion);
    int last = newMotions.size() - 1;
    motion.add(new Motion(
        newMotions.get(last).getMoveX(),
        newMotions.get(last).getMoveY(),
        newMotions.get(last).getColor(),
        newMotions.get(last).getScaleX() * (1 + scale),
        newMotions.get(last).getScaleY() * (1 + scale),
        newMotions.get(last).getTicks()));
    motion.add(new Motion(
        newMotions.get(last).getMoveX(),
        newMotions.get(last).getMoveY(),
        newMotions.get(last).getColor(),
        newMotions.get(last).getScaleX() * (1 - scale),
        newMotions.get(last).getScaleY() * (1 - scale),
        newMotions.get(last).getTicks()));
  }

}
