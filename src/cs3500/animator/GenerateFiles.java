package cs3500.animator;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.BasicAnimationModel;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Oval;
import cs3500.animator.view.FactoryView;
import cs3500.animator.view.textual.TextualView;
import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.io.FileDescriptor;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GenerateFiles {

  public static void main(String[] args) throws IOException {

    Motion centerMotion1 = new Motion(0, 200, Color.BLACK, 1, 1, 10);
    Motion centerMotion2 = new Motion(-150, 100, Color.BLUE, 1, 1, 10);
    Motion centerMotion3 = new Motion(-125, -150, Color.ORANGE, 1, 1, 10);
    Motion centerMotion4 = new Motion(125, -150, Color.YELLOW, 1, 1, 10);
    Motion centerMotion5 = new Motion(150, 100, Color.RED, 1, 1, 10);

    Motion scaleMotion1 = new Motion(0, 200, Color.BLACK, 1, 1, 10);
    Motion scaleMotion2 = new Motion(-150, 100, Color.BLUE, 1.5, 1.5, 10);
    Motion scaleMotion3 = new Motion(-125, -150, Color.ORANGE, 2, 2, 10);
    Motion scaleMotion4 = new Motion(125, -150, Color.YELLOW, 2.5, 2.5, 10);
    Motion scaleMotion5 = new Motion(150, 100, Color.RED, 3, 3, 10);

    Motion scaleMotion11 = new Motion(0, 200, Color.BLACK, 1, 1, 10);
    Motion scaleMotion21 = new Motion(-150, 100, Color.BLUE, 1.5, 1.5, 10);
    Motion scaleMotion31 = new Motion(-125, -150, Color.ORANGE, 2, 2, 10);
    Motion scaleMotion41 = new Motion(125, -150, Color.YELLOW, 2.5, 2.5, 10);
    Motion scaleMotion51 = new Motion(150, 100, Color.RED, 3, 3, 10);

    Motion scaleMotion12 = new Motion(0, 200, Color.BLACK, 1, 1, 10);
    Motion scaleMotion22 = new Motion(-150, 100, Color.BLUE, 1.5, 1.5, 10);
    Motion scaleMotion32 = new Motion(-125, -150, Color.ORANGE, 2, 2, 10);
    Motion scaleMotion42 = new Motion(125, -150, Color.YELLOW, 2.5, 2.5, 10);
    Motion scaleMotion52 = new Motion(150, 100, Color.RED, 3, 3, 10);

    Queue<Motion> oval1Motions = new LinkedList<>();
    oval1Motions.add(centerMotion1);
    oval1Motions.add(scaleMotion1);

    Queue<Motion> oval2Motions = new LinkedList<>();
    oval2Motions.add(centerMotion2);
    oval2Motions.add(scaleMotion2);

    Queue<Motion> oval3Motions = new LinkedList<>();
    oval3Motions.add(centerMotion3);
    oval3Motions.add(scaleMotion3);

    Queue<Motion> oval4Motions = new LinkedList<>();
    oval4Motions.add(centerMotion4);
    oval4Motions.add(scaleMotion4);

    Queue<Motion> oval5Motions = new LinkedList<>();
    oval5Motions.add(centerMotion5);
    oval5Motions.add(scaleMotion5);

    List shapes = new ArrayList<>();
    IShape oval1 = new Oval("oval1", new Double(250.0, 50.0), 25, 25, Color.BLACK, 0, oval1Motions, 0, 0);
    IShape oval2 = new Oval("oval2", new Double(400, 150.0), 25, 25, Color.BLUE, 0, oval2Motions, 0, 0);
    IShape oval3 = new Oval("oval3", new Double(375.0, 400.0), 25, 25, Color.ORANGE, 0, oval3Motions, 0, 0);
    IShape oval4 = new Oval("oval4", new Double(125.0, 400.0), 25, 25, Color.YELLOW, 0, oval4Motions, 0, 0);
    IShape oval5 = new Oval("oval5", new Double(100.0, 150.0), 25, 25, Color.RED, 0, oval5Motions, 0, 0);
    IShape oval6 = new Oval("oval5", new Double(100.0, 150.0), 25, 25, Color.RED, 10, oval5Motions, 0, 0);
    shapes.add(oval5);
    shapes.add(oval4);
    shapes.add(oval3);
    shapes.add(oval2);
    shapes.add(oval1);
    shapes.add(oval6);

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

}
