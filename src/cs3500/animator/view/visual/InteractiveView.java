package cs3500.animator.view.visual;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.IShape;
import cs3500.animator.view.IAnimationView;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class InteractiveView extends JFrame implements NewAnimationView, ActionListener {

  private long tick;
  private final AnimationModel model;
  private final DrawingPanel drawingPanel;
  private final JButton startStop;
  private final JButton restart;
  private final JButton loop;

  public InteractiveView(AnimationModel model) {
    super();
    this.model = model;
    this.tick = 0;

    this.drawingPanel = new DrawingPanel();
    startStop = new JButton("Start/Stop");
    restart = new JButton("Restart");
    loop = new JButton("Loop Animation");

    JScrollPane scroller = new JScrollPane(drawingPanel);
    scroller.setPreferredSize(new Dimension(50, 50));
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(model.getSceneWidth(), model.getSceneHeight());
    scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    add(scroller);
    add(startStop);
    add(restart);
    add(loop);
  }

  @Override
  public void render() {
    setVisible(true);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void draw(IShape shape) throws IllegalArgumentException {
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == startStop) {

    }
    else if (e.getSource() == restart) {

    }
    else if (e.getSource() == loop) {

    }
  }
}
