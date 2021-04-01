package cs3500.animator.view.VisualPackage;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.IShape;
import cs3500.animator.view.AnimationViewVisual;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 *  Representing the visual view that renders the animation model.
 */
public class VisualView extends JFrame implements AnimationViewVisual {

  private final DrawingPanel drawingPanel;
  private final AnimationModel model;

  /**
   * THe constructor to create a visual view.

   * @param model the model we are using for visual view
   */
  public VisualView(AnimationModel model) {
    super();
    this.model = model;
    this.drawingPanel = new DrawingPanel();
    for (IShape shape : model.getShapes()) {
      drawingPanel.addShape(shape);
    }
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
    setVisible(true);
    for (int i = 0; i <= 100; i++) {
      List<IShape> los = model.moveShapes(i);
      for (IShape s : los) {
        draw(s);
      }
    }

  }

  @Override
  public void refresh() {
    repaint();
  }

  @Override
  public void draw(IShape shape) {
    drawingPanel.addShape(shape);
  }
}
