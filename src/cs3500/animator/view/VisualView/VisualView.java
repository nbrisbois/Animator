package cs3500.animator.view.VisualView;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.BasicAnimationModel;
import cs3500.animator.model.IShape;
import cs3500.animator.view.AnimationViewVisual;
import cs3500.animator.view.IAnimationView;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.Objects;
import javax.swing.JFrame;

public class VisualView extends JFrame implements AnimationViewVisual {

  private final DrawingPanel drawingPanel;

  public VisualView(AnimationModel model) {
    super();
    Objects.requireNonNull(model);

    drawingPanel = new DrawingPanel();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(model.getSceneWidth(), model.getSceneHeight());
    setLayout( new BorderLayout());
    add(drawingPanel);
  }

  @Override
  public void render() {
    setVisible(true);
  }

  public void refresh() {
    repaint();
  }

  public void draw(IShape shape) {
    drawingPanel.addShape(shape);
  }
}
