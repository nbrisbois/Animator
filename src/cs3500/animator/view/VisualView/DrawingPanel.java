package cs3500.animator.view.VisualView;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.IShape;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel {
  private final List<IShape> shapes;



  DrawingPanel() {
    super();
    shapes = new ArrayList<IShape>();
  }


  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (IShape s : shapes) {
      switch (s.getShape()) {
        case "rectangle": {
          g.setColor(s.getColor());
          g.fillRect((int)s.getPosition().getX(), (int)s.getPosition().getY(),
              (int)s.getSize()[0], (int)s.getSize()[1]);
        }
        case "ellipses": {
          g.setColor(s.getColor());
          g.fillOval((int)s.getPosition().getX(), (int)s.getPosition().getY(),
              (int)s.getSize()[0], (int)s.getSize()[1]);
        }
        default: {

        }
      }
    }
  }

  void addShape(IShape shape) {
    shapes.add(shape);
  }
}
