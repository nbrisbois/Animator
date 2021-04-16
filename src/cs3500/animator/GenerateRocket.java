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

public class GenerateRocket {

  public static void main(String[] args) throws IOException {

    Motion dropletFalling = new Motion(0, 400,
        Color.DARK_GRAY, 1, 1, 10);
    Motion ripple = new Motion(dropletFalling.getMoveX(), dropletFalling.getMoveY(),
        Color.DARK_GRAY, 0, 0, 1);

    Queue<Motion> rainMotions = new LinkedList<>();
    rainMotions.add(dropletFalling);
    rainMotions.add(ripple);
    List<IShape> shapes = new ArrayList<>();

    // Add the Floor
    Queue<Motion> floorMotion = new LinkedList<>();
    floorMotion.add(new Motion(0,0,Color.DARK_GRAY,
        1, 1, 1000));
    shapes.add(new Rectangle("floor", new Double(0, 400), 500, 400,
        Color.DARK_GRAY, 0, floorMotion, 0, 0));

    // Add the Background
    Queue<Motion> backgroundMotion = new LinkedList<>();
    backgroundMotion.add(new Motion(0,0, Color.GRAY, 1, 1, 200));
    shapes.add(new Rectangle("background", new Double(0, 0),
        500,
        400,
        Color.GRAY,
        0,
        backgroundMotion,
        0, 0));

    // Blast
    boolean directionflag = true;
    for (int ii = 0; ii < 100; ii++){
      Queue<Motion> puffMotions = new LinkedList<>();
      Random puffR = new Random();
      directionflag = !directionflag;
      Motion puffStall = new Motion(0, 0, Color.WHITE, 1, 1, 2);
      Motion generatePuff = new Motion(
          (directionflag ? puffR.nextInt(40 - 1) + 1 : (puffR.nextInt(40 - 1) + 1) * -1),
          (puffR.nextInt(40 - 1) + 1) * -1, Color.GRAY,
          puffR.nextDouble() + 1, puffR.nextDouble() + 1, 35);

      puffMotions.add(puffStall);
      puffMotions.add(generatePuff);

      shapes.add(new Oval(String.format("puff%s", ii), new Double( 260, 400 - ii*5), 5, 5,
          Color.WHITE, ii + 10, puffMotions, 0, 0));
    }

    // RocketShip
    Motion rocketStall = new Motion(0, 0, Color.RED, 1, 1, 10);
    Motion rocketAscend = new Motion(0, -500, Color.RED, 1, 1, 100);
    Queue<Motion> rocketMotion = new LinkedList<>();
    rocketMotion.add(rocketStall);
    rocketMotion.add(rocketAscend);
    shapes.add(new Rectangle("rocketBody", new Double(250, 350), 20, 40,
        Color.RED, 0, rocketMotion, 0, 0));
    shapes.add(new Rectangle("rocketBottom", new Double(245, 380), 30, 10,
        Color.RED, 0, rocketMotion, 0, 0));
    shapes.add(new Rectangle("rocketThruster1", new Double(245, 380), 5, 20,
        Color.RED, 0, rocketMotion, 0, 0));
    shapes.add(new Rectangle("rocketThruster2", new Double(257.5, 380), 5, 20,
        Color.RED, 0, rocketMotion, 0, 0));
    shapes.add(new Rectangle("rocketThruster3", new Double(270, 380), 5, 20,
        Color.RED, 0, rocketMotion, 0, 0));
    shapes.add(new Oval("rocketTop", new Double(260, 350), 10, 10, Color.RED,
        0, rocketMotion, 0, 0));

    // Add all the Rain
    for (int ii = 0; ii < 2000; ii++ ) {
      Random r = new Random();
      shapes.add(new Rectangle(String.format("rectangle%s", ii),
          new Double(r.nextInt(500 - 1) + 1, 0), 1, 8, Color.DARK_GRAY,
          r.nextInt(100 - 1) + 1, rainMotions, 0, 0));
    }

    AnimationModel model = new BasicAnimationModel(shapes,  0, 0, 500, 500, 100, 1);
    renderModel(model);
  }

  private static void renderModel(AnimationModel model) throws IOException {
    FileWriter myWriter = new FileWriter(String.format("%s/%s", System.getProperty("user.dir"), "rocketship.txt"));
    myWriter.write(String.format("canvas %s %s %s %s\n", model.getTopLeftX(), model.getTopLeftY(), model.getSceneHeight(), model.getSceneWidth()));
    for (IShape shape : model.getShapes()) {
      myWriter.write(shape.toString() + "\n \n");
    }
    myWriter.close();
  }

}
