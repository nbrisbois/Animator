package cs3500.animator.view.visual;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.IShape;
import cs3500.animator.view.IAnimationView;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.Timer;

/**
 * Representing an interactive visual view that renders the animation model and allows the user to
 * control the animation.
 */
public class InteractiveView extends JFrame implements IAnimationView {

  private boolean isLoop;
  private long tick;
  private final AnimationModel model;
  private final DrawingPanel drawingPanel;
  private final JButton startStop;
  private final JButton restart;
  private final JButton loop;
  private final JButton increaseSpeed;
  private final JButton decreaseSpeed;

  private Timer timer;

  private int speed = 1;
  private boolean startStopFlag = true; // true = started, false = stopped

  /**
   * The constructor of InteractiveView using the model.
   *
   * @param model the model of the animation we want to display as an interactive view
   */
  public InteractiveView(AnimationModel model) {
    super();
    this.model = model;
    this.tick = 0;
    this.drawingPanel = new DrawingPanel();

    JScrollPane scroller = new JScrollPane(drawingPanel);
    scroller.setPreferredSize(new Dimension(50, 50));
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(model.getSceneWidth(), model.getSceneHeight());
    scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    ClickListener click = new ClickListener();

    isLoop = false;

    startStop = new JButton("Start/Stop");
    startStop.addActionListener(click);
    drawingPanel.panel.add(startStop);

    restart = new JButton("Restart");
    restart.addActionListener(click);
    drawingPanel.panel.add(restart);

    loop = new JButton("Loop Animation");
    loop.addActionListener(click);
    drawingPanel.panel.add(loop);

    increaseSpeed = new JButton("Increase Speed");
    increaseSpeed.addActionListener(click);
    drawingPanel.panel.add(increaseSpeed);

    decreaseSpeed = new JButton("Decrease Speed");
    decreaseSpeed.addActionListener(click);
    drawingPanel.panel.add(decreaseSpeed);

    add(scroller);

    timer = new Timer(100, new TimerListener());
    timer.start();
  }

  @Override
  public void render() {
    if (startStopFlag) {
    } else {
      timer.stop();
    }
    List<IShape> shapes = model.moveShapes(tick * 100);
    for (IShape s : shapes) {
      drawingPanel.addShape(s);
    }
    tick = tick + 1;
    setVisible(true);
    refresh();
  }

  @Override
  public void setSpeed(int speed) {
    this.speed = speed;
  }

  /**
   * Repaint the visual view on panel.
   */
  private void refresh() {
    this.repaint();
  }

  /**
   * Representing a listener that checks any click events on the buttons.
   */
  private class ClickListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == startStop) {
        startStopFlag = !startStopFlag;
        if (startStopFlag) {
          timer.start();
        }
      } else if (e.getSource() == restart) {
        startStopFlag = false;
        tick = 0;
        model.resetShapes();
        startStopFlag = true;
        timer.start();

      } else if (e.getSource() == loop) {
        if (isLoop) {
          isLoop = false;
        } else {
          isLoop = true;
        }
      } else if (e.getSource() == increaseSpeed) {
        if (speed < 5) {
          speed++;
          timer.stop();
          timer = new Timer((int) (100 / (speed * .6)), new TimerListener());
          timer.start();
        }
      } else if (e.getSource() == decreaseSpeed) {
        speed--;
        timer.stop();
        timer = new Timer((int) (100 / (speed / .6)), new TimerListener());
        timer.start();
        if (speed < 1) {
          speed = 1;
          timer = new Timer((int) (100), new TimerListener());
        }
      }
    }
  }

  /**
   * Representing a listener that checks the timer when the program runs.
   */
  private class TimerListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      try {
        render();
      } catch (Exception nullPoint) {
        if (isLoop) {
          startStopFlag = false;
          tick = 0;
          model.resetShapes();
          startStopFlag = true;
          timer.start();
        } else {
          timer.stop();
        }
      }
    }
  }
}
