package cs3500.animator;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.BasicAnimationModel;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Oval;
import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class GenerateFiles {

  public static void main(String[] args) throws IOException {

    Motion dropletFalling = new Motion(50, 400, Color.CYAN, 1, 1, 15);
    Motion ripple = new Motion(dropletFalling.getMoveX(), dropletFalling.getMoveY(), Color.BLUE, 1.5, 0, 10);

    Queue<Motion> rainMotions = new LinkedList<>();
    rainMotions.add(dropletFalling);
    rainMotions.add(ripple);

    

    List shapes = new ArrayList<>();
    for (int ii = 0; ii < 1000; ii++ ) {
      Random r = new Random();
      shapes.add(new Oval(
          String.format("oval%s", ii),
          new Double(r.nextInt(500 - 1) + 1, 0),
          5,
          5,
          Color.CYAN,
          r.nextInt(100 - 1) + 1,
          rainMotions,
          0, -10));
    }
    AnimationModel model = new BasicAnimationModel(shapes,  0, 0, 500, 500, 100, 1);

    renderModel(model);
  }

  private static void renderModel(AnimationModel model) throws IOException {
    FileWriter myWriter = new FileWriter(String.format("%s/%s", System.getProperty("user.dir"), "ripple.txt"));

    myWriter.write(String.format("canvas %s %s %s %s\n", model.getTopLeftX(), model.getTopLeftY(), model.getSceneHeight(), model.getSceneWidth()));

    //TextualView view = new TextualView(model);

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
