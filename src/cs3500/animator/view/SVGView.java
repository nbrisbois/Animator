package cs3500.animator.view;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.BasicAnimationModel;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;
import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

public class SVGView implements IAnimationView {

  private final AnimationModel model;
  private final FileWriter myWriter;

  public SVGView(AnimationModel model) throws IOException {
    super();
    Objects.requireNonNull(model);
    this.model = model;
    myWriter = new FileWriter("Animator/src/cs3500/animator/view/animation.svg", false);
  }

  public static void main(String[] args) throws IOException {
    Queue<Motion> motionsR = new PriorityQueue<>();
    Queue<Motion> motionsO = new PriorityQueue<>();

    // Rectangle Motions
    motionsR.add(new Motion(20,20, Color.RED, 100, 100, 4));
    motionsR.add(new Motion(20,20, Color.RED, 50, 100, 4));
    motionsR.add(new Motion(10,10, Color.RED, 50, 100, 4));

    // Oval Motions
    motionsO.add(new Motion(0,10, Color.BLUE, 100, 100, 4));
    motionsO.add(new Motion(0,0, Color.GREEN, 100, 100, 4));

    Rectangle testRect = new Rectangle("rect", new Double(5, 5), 50, 50, Color.RED, 0, motionsR);
    Oval testOval = new Oval("oval", new Double(8, 3), 50, 50, Color.BLUE, 0, motionsO);

    List<IShape> shapes = new ArrayList<>();
    shapes.add(testRect);
    shapes.add(testOval);

    AnimationModel model = new BasicAnimationModel(shapes, 10, 10, 20, 1);
    SVGView newView = new SVGView(model);
    newView.render();
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
          model.getSceneHeight()*100,
          model.getSceneWidth()*100));

      // Write to File
      List<IShape> shapes = new ArrayList<IShape>(model.getShapes());
      writeShapes(shapes);

      // End svg
      myWriter.write("</svg>");
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  private void writeShapes(List<IShape> shapes){
    for (IShape s : shapes){
      switch (s.getName()) {
        case "rect":
          makeRectangle(s);
          break;
        case "oval":
          makeOval(s);
          break;
        case "poly":
          makePolygon(s);
          break;
      }
    }
  }

  private String getHex(Color c) {
    return String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
  }

  private void makeRectangle(IShape r){
    try {
      myWriter.write(String.format("<rect x= \"%scm\" y=\"%scm\" width=\"%s\" height=\"%s\" fill=\"%s\">",
          r.getPosition().getX(),
          r.getPosition().getY(),
          r.getSize()[0],
          r.getSize()[1],
          getHex(r.getColor())));
      myWriter.write("\n\t");
      myWriter.write(r.writeAnimation());
      myWriter.write("</rect>\n");
    } catch (Exception e){
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  private void makeOval(IShape o){
    try {
      myWriter.write(String.format("<ellipse cx=\"%scm\" cy=\"%scm\" rx=\"%s\" ry=\"%s\" fill=\"%s\">",
          o.getPosition().getX(),
          o.getPosition().getY(),
          o.getSize()[0],
          o.getSize()[1],
          getHex(o.getColor())));
      myWriter.write("\n\t");
      myWriter.write(o.writeAnimation());
      myWriter.write("</ellipse>\n");
    } catch (Exception e){
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  private void makePolygon(IShape p){
    try {
      String poly = String.format("<poly width=\"%s\" height=\"%s\" fill=\"%s\" />", p.getSize()[0], p.getSize()[1], getHex(p.getColor()));

      myWriter.write(poly);
      myWriter.write("\n");
      myWriter.write("</polygon>");
    } catch (Exception e){
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }



}
