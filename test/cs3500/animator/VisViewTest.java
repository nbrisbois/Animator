package cs3500.animator;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.BasicAnimationModel;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Oval;
import cs3500.animator.view.AnimationViewVisual;
import cs3500.animator.view.VisualPackage.VisualView;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D.Double;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import javax.swing.Timer;

/**
 * Tester class for the visual view.
 */
public class VisViewTest {

  /**
   * Renders the visual view.
   *
   * @param args StdIn
   */
  public static void main(String[] args) {
    Queue<Motion> motions = new PriorityQueue<Motion>();
    Motion o1 = new Motion(440, 70, Color.BLUE, 120, 60, 20000);
    Motion o2 = new Motion(440, 250, Color.BLUE, 120, 60, 50000);
    Motion o3 = new Motion(440, 370, new Color(0, 170, 85), 120, 60, 70000);
    Motion o4 = new Motion(440, 370, new Color(20, 114, 20, 255), 120, 60, 80000);
    Motion o5 = new Motion(440, 370, new Color(0, 250, 0), 120, 60, 100000);

    motions.add(o1);
    motions.add(o2);
    motions.add(o3);
    motions.add(o4);
    motions.add(o5);

    IShape testOval = new Oval("C", new Double(440, 70), 120, 60, Color.BLUE, 6000, motions,0,440);
    //IShape testRect = new Rectangle("R", new Double(0, 0), 600, 200, Color.BLACK, 1000, motions);

    List<IShape> shapes = new ArrayList<IShape>();
    shapes.add(testOval);
    //shapes.add(testRect);

    AnimationModel testModel = new BasicAnimationModel(shapes, 0, 0, 1000, 1000, 100000, 1);

    AnimationViewVisual view = new VisualView(testModel);

    view.render();
    ActionListener timer = new ActionListener() {
      long tick = 0;

      @Override
      public void actionPerformed(ActionEvent e) {
        List<IShape> shapes = testModel.moveShapes(tick * 100);
        for (IShape s : shapes) {
          view.draw(s);
        }
        view.refresh();
        tick++;
      }
    };
    Timer t = new Timer(100, timer);
    t.start();

  }

}