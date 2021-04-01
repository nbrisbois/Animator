package cs3500.animator.view.VisualPackage;

import cs3500.animator.model.IShape;
import cs3500.animator.view.AnimationViewVisual;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 *  Representing the visual view that renders the animation model.
 */
public class VisualView extends JFrame implements AnimationViewVisual {

  private final DrawingPanel drawingPanel;

  /**
   * THe constructor to create a visual view.

   * @param w the width of the canvas
   * @param h the height of the canvas
   */
  public VisualView(int w, int h) {
    super();
    drawingPanel = new DrawingPanel();
    JScrollPane scroller = new JScrollPane(drawingPanel);
    scroller.setPreferredSize(new Dimension(50, 50));
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(w, h);
    scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    add(scroller);
  }

  @Override
  public void render() {
    setVisible(true);
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
