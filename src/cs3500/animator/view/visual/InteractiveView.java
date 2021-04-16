package cs3500.animator.view.visual;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.IShape;
import cs3500.animator.view.IAnimationView;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.Timer;

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
    //  System.out.println("I am running");
    } else {
      System.out.println("I am not running");
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

  public void refresh() {
    this.repaint();
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  private class ClickListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      System.out.println("button pressed");
      if (e.getSource() == startStop) {
        System.out.println("startstop pressed");
        startStopFlag = !startStopFlag;
        if (startStopFlag) {
          timer.start();
        }
      } else if (e.getSource() == restart) {
        System.out.println("restart pressed");
        startStopFlag = false;
        tick = 0;
        model.resetShapes();
        startStopFlag = true;
        timer.start();

      } else if (e.getSource() == loop) {
        if (isLoop) {
          isLoop = false;
        }
        else {
          isLoop = true;
        }
        System.out.println("loop pressed");
      } else if (e.getSource() == increaseSpeed) {
        speed++;
        timer.stop();
        System.out.println((int)(100/(speed * .6)));
        timer = new Timer((int)(100/(speed * .6)), new TimerListener());
        timer.start();
        System.out.printf("speed: %s%n", speed);
      } else if (e.getSource() == decreaseSpeed) {
        speed--;
        timer.stop();
        System.out.println((int)(100/(speed / .6)));
        timer = new Timer((int)(100/(speed / .6)), new TimerListener());
        timer.start();
        System.out.printf("speed: %s%n", speed);
        if (speed < 1) {
          speed = 1;
        }
        System.out.printf("reset speed: %s%n", speed);
      }
    }
  }

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
        }
        else {
          timer.stop();
        }
      }
    }
  }
}
