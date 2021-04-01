package cs3500.animator.view.visual;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.IShape;
import cs3500.animator.view.IAnimationView;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 *  Representing the visual view that renders the animation model.
 */
public class VisualView extends JFrame implements IAnimationView {

  private long tick;
  private final DrawingPanel drawingPanel;
  private final AnimationModel model;

  /**
   * THe constructor to create a visual view.

<<<<<<< HEAD:src/cs3500/animator/view/VisualPackage/VisualView.java
=======
   * @param model the model we are using for visual view
>>>>>>> ab398b5c399b988aceac7094a319f509eeaa295f:src/cs3500/animator/view/visual/VisualView.java
   */
  public VisualView(AnimationModel model) {
    super();
    this.model = model;
    this.tick = tick;
    this.drawingPanel = new DrawingPanel();
    JScrollPane scroller = new JScrollPane(drawingPanel);
    scroller.setPreferredSize(new Dimension(50, 50));
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(model.getSceneWidth(), model.getSceneHeight());
    scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    add(scroller);
  }

  @Override
  public void render() {

    List<IShape> shapes = model.moveShapes(tick*100);
    for (IShape s : shapes) {
      drawingPanel.addShape(s);
    }
    setVisible(true);
    tick++;
    refresh();
  }

  private void refresh() {
    repaint();
  }


  private void draw(IShape shape) {
    drawingPanel.addShape(shape);
  }
}
