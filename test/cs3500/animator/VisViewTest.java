package cs3500.animator;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.BasicAnimationModel;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;
import cs3500.animator.view.AnimationViewVisual;
import cs3500.animator.view.IAnimationView;
import cs3500.animator.view.TexualView.TextualView;
import cs3500.animator.view.VisualView.VisualView;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D.Double;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import javax.swing.Timer;
import org.junit.Test;


public class VisViewTest {

  public static void main(String[] args) {
    Queue<Motion> motions = new PriorityQueue<Motion>();
    Motion motion1 = new Motion(50, 50, Color.BLACK, 40, 40, 5000);
    Motion motion2 = new Motion(500, 100, Color.WHITE, 150, 5, 10000);
    motions.add(motion1);
    motions.add(motion2);

    IShape testOval = new Oval("c", new Double(0, 0), 100 , 100, Color.BLACK, 1000, motions);
    IShape testRect = new Rectangle("r", new Double(0, 0), 600, 200, Color.BLACK, 1000, motions);

    List<IShape> shapes = new ArrayList<IShape>();
    shapes.add(testOval);
    shapes.add(testRect);

    AnimationModel testModel = new BasicAnimationModel(shapes, 1000, 1000, 10, 1);

    AnimationViewVisual view = new VisualView(testModel.getSceneWidth(), testModel.getSceneHeight());
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
        if (tick == testModel.getDuration()) {
          t.stop();
        }
      }
    };

    Timer t = new Timer(100, timer);
    t.start();

  }

}