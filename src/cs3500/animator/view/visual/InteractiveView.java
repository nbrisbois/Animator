package cs3500.animator.view.visual;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.IShape;
import cs3500.animator.view.IAnimationView;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class InteractiveView extends JFrame implements NewAnimationView {

  private long tick;
  private final AnimationModel model;
  private final DrawingPanel drawingPanel;

  public InteractiveView(AnimationModel model) {
    super();
    this.model = model;
    this.tick = 0;
    this.drawingPanel = new DrawingPanel();
    JButton startStop = new JButton("Start/Stop");
    JButton restart = new JButton("Restart");
    JButton loop = new JButton("Loop Animation");
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
}
