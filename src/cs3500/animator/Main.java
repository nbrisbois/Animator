package cs3500.animator;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.BasicAnimationModel;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.Shape;
import cs3500.animator.view.IAnimationView;
import cs3500.animator.view.VisualView.VisuralView;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import javax.swing.Timer;

/**
 * Main Function Class.
 */
public class Main {

  /**
   * Main function to run basic model.
   * @param args StdIn
   */
  public static void main(String... args) {
    Queue<Motion> motions1 = new PriorityQueue<Motion>();
    Motion motion1 = new Motion(100, 100, Color.BLACK, 100, 100, 5000);
    Motion motion2 = new Motion(0, 5, Color.WHITE, 1, 2, 10000);
    motions1.add(motion1);
    motions1.add(motion2);

    Oval testOval = new Oval("c", new Double(0, 0), 100, 100, Color.BLACK, 1000, motions1);

    Queue<Motion> motions2 = new PriorityQueue<Motion>();
    Motion motion3 = new Motion(83, 90, Color.PINK, 50, 100, 6000);
    Motion motion4 = new Motion(0, 10, Color.YELLOW, 25, 50, 6100);
    Motion motion5 = new Motion(100, 100, Color.GRAY, 25, 50, 10000);
    motions2.add(motion3);
    motions2.add(motion4);
    motions2.add(motion5);

    Rectangle testRec = new Rectangle("r", new Double(400, 0), 25, 25, Color.BLACK, 0, motions2);

    List<IShape> shapes = new ArrayList<IShape>();
    shapes.add(testOval);
    shapes.add(testRec);

    AnimationModel model = new BasicAnimationModel(shapes, 1000, 1000, 10000, 1);
    IAnimationView vis = new VisuralView(model);
    vis.render();
    Timer t = new Timer(100, new ActionListener() {
      long tick = 0;
      @Override
      public void actionPerformed(ActionEvent e) {
        List<IShape> list = model.moveShapes(tick * 100);
        for (IShape s : list) {
          vis.draw(s);
        }
        tick++;
        vis.refresh();
      }
    });
    t.start();
  }
}
