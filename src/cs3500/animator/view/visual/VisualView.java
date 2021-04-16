package cs3500.animator.view.visual;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.IShape;
import cs3500.animator.view.IAnimationView;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * Representing the visual view that renders the animation model.
 */
public class VisualView extends JFrame implements IAnimationView {

  private long tick = 0;
  private Timer timer;
  private final DrawingPanel drawingPanel;
  private final AnimationModel model;
  private int speed;

  /**
   * THe constructor to create a visual view.
   *
   * @param model the model we are using for visual view
   */
  public VisualView(AnimationModel model) {
    super();
    this.speed = 1;
    this.model = model;
    this.drawingPanel = new DrawingPanel();
    JScrollPane scroller = new JScrollPane(drawingPanel);
    scroller.setPreferredSize(new Dimension(50, 50));
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(model.getSceneWidth(), model.getSceneHeight());
    scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    add(scroller);
    timer = new Timer(100, new VisualView.TimerListener());
    timer.start();
  }

  @Override
  public void render() {
    List<IShape> shapes = model.moveShapes(tick * 100);
    for (IShape s : shapes) {
      drawingPanel.addShape(s);
    }
    setVisible(true);
    tick++;
    refresh();
  }

  @Override
  public void setSpeed(int speed) {
    this.speed = speed;
  }

  /**
   * To repaint the animation view.
   */
  private void refresh() {
    repaint();
  }

  /**
   * To draw an additional desired shape by adding it to the list of shapes in the drawing panel.
   *
   * @param shape the desired shape we want to draw
   */
  private void draw(IShape shape) {
    drawingPanel.addShape(shape);
  }

  private class TimerListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      try {
        render();
      } catch (Exception nullPoint) {
          timer.stop();
      }
    }
  }
}
