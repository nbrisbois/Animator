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

/**
 * Main Class for generating a .txt file that consists information for animation that represents the
 * brick breaker game.
 */
public class GenerateBrickBreaker {

  /**
   * Create an animation model and translate the model to a .txt file representing the animation.
   *
   * @param args StdIn
   * @throws IOException when fails to render the model
   */
  public static void main(String[] args) throws IOException {
    //bricks motions
    Motion blueMotion1 = new Motion(0, 0, Color.BLUE, 1, 1, 10);
    Motion blueMotion2 = new Motion(0, 0, Color.WHITE, 1, 1, 1);
    Motion blueMotion3 = new Motion(50, 75, Color.WHITE, 1, 1, 89);

    Motion redMotion1 = new Motion(0, 0, Color.RED, 1, 1, 30);
    Motion redMotion2 = new Motion(0, 0, Color.WHITE, 1, 1, 1);
    Motion redMotion3 = new Motion(150, 75, Color.WHITE, 1, 1, 69);

    Motion greenMotion1 = new Motion(0, 0, Color.GREEN, 1, 1, 55);
    Motion greenMotion2 = new Motion(0, 0, Color.WHITE, 1, 1, 1);
    Motion greenMotion3 = new Motion(150, 25, Color.WHITE, 1, 1, 44);

    Motion yellowMotion1 = new Motion(0, 0, Color.YELLOW, 1, 1, 85);
    Motion yellowMotion2 = new Motion(50, 25, Color.WHITE, 1, 1, 1);
    Motion yellowMotion3 = new Motion(50, 25, Color.WHITE, 1, 1, 14);

    Queue<Motion> blueMotions = new LinkedList<>();
    blueMotions.add(blueMotion1);
    blueMotions.add(blueMotion2);
    blueMotions.add(blueMotion3);

    Queue<Motion> redMotions = new LinkedList<>();
    redMotions.add(redMotion1);
    redMotions.add(redMotion2);
    redMotions.add(redMotion3);

    Queue<Motion> greenMotions = new LinkedList<>();
    greenMotions.add(greenMotion1);
    greenMotions.add(greenMotion2);
    greenMotions.add(greenMotion3);

    Queue<Motion> yellowMotions = new LinkedList<>();
    yellowMotions.add(yellowMotion1);
    yellowMotions.add(yellowMotion2);
    yellowMotions.add(yellowMotion3);

    //platform motions
    Motion slabMotion1 = new Motion(70, 0, Color.BLACK, 1, 1, 20);
    Motion slabMotion2 = new Motion(150, 0, Color.BLACK, 1, 1, 20);
    Motion slabMotion3 = new Motion(100, 0, Color.BLACK, 1, 1, 30);

    Queue<Motion> platformMotions = new LinkedList<>();
    platformMotions.add(slabMotion1);
    platformMotions.add(slabMotion2);
    platformMotions.add(slabMotion3);

    //ball motions
    Motion ballMotion1 = new Motion(45, -256, Color.BLACK, 1, 1, 10);
    Motion ballMotion2 = new Motion(70, 50, Color.BLACK, 1, 1, 10);
    Motion ballMotion3 = new Motion(150, -256, Color.BLACK, 1, 1, 10);
    Motion ballMotion4 = new Motion(180, 50, Color.BLACK, 1, 1, 10);
    Motion ballMotion5 = new Motion(150, -300, Color.BLACK, 1, 1, 15);
    Motion ballMotion7 = new Motion(140, 260, Color.BLACK, 1, 1, 15);
    Motion ballMotion8 = new Motion(70, -300, Color.BLACK, 1, 1, 15);
    Motion ballMotion9 = new Motion(30, 260, Color.BLACK, 1, 1, 15);

    Queue<Motion> ballMotions = new LinkedList<>();
    ballMotions.add(ballMotion1);
    ballMotions.add(ballMotion2);
    ballMotions.add(ballMotion3);
    ballMotions.add(ballMotion4);
    ballMotions.add(ballMotion5);
    ballMotions.add(ballMotion7);
    ballMotions.add(ballMotion8);
    ballMotions.add(ballMotion9);

    //shapes
    List shapes = new ArrayList<>();
    IShape platform = new Rectangle("platform", new Double(20.0, 395.0), 80, 10, Color.BLACK, 0,
        platformMotions, 0, 0);
    IShape blue = new Rectangle("blue", new Double(50.0, 75.0), 100, 50, Color.BLUE, 0,
        blueMotions, 0, 0);
    IShape red = new Rectangle("red", new Double(150.0, 75.0), 100, 50, Color.RED, 0,
        redMotions, 0, 0);
    IShape green = new Rectangle("green", new Double(150.0, 25.0), 100, 50, Color.GREEN, 0,
        greenMotions, 0, 0);
    IShape yellow = new Rectangle("yellow", new Double(50.0, 25.0), 100, 50, Color.YELLOW, 0,
        yellowMotions, 0, 0);
    IShape ball = new Oval("ball", new Double(20.0, 390.0), 6, 6, Color.BLACK, 0, ballMotions,
        0, 0);

    shapes.add(platform);
    shapes.add(blue);
    shapes.add(yellow);
    shapes.add(green);
    shapes.add(red);
    shapes.add(ball);

    AnimationModel model = new BasicAnimationModel(shapes, 0, 0, 400, 200, 101, 1);

    renderModel(model);
  }

  /**
   * To convert the given model in to a .txt file that contains all information for an animation.
   *
   * @param model the model the file take information from
   */
  private static void renderModel(AnimationModel model) throws IOException {
    FileWriter myWriter = new FileWriter(
        String.format("%s/%s", System.getProperty("user.dir"), "brickBreaker.txt"));

    myWriter.write(String.format("canvas %s %s %s %s\n", model.getTopLeftX(), model.getTopLeftY(),
        model.getSceneHeight(), model.getSceneWidth()));

    for (IShape shape : model.getShapes()) {
      myWriter.write(shape.toString() + "\n \n");
    }

    myWriter.close();

  }

}
