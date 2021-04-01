package cs3500.animator.view.VisualPackage;

import cs3500.animator.model.IShape;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * Represents the JPanel we will use to draw the scene for visual view.
 */
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
        case "rectangle":
          g.setColor(s.getColor());
          g.fillRect((int) s.getPosition().getX() - s.getOffsetX(),
              (int) s.getPosition().getY() - s.getOffsetY(),
              (int) s.getSize()[0], (int) s.getSize()[1]);
        case "ellipses":
          g.setColor(s.getColor());
          g.fillOval((int) s.getPosition().getX() - s.getOffsetX(),
              (int) s.getPosition().getY() - s.getOffsetY(),
              (int) s.getSize()[0], (int) s.getSize()[1]);
          //default
//          if (s.getShape().equals("rectangle")) {
//            g.setColor(s.getColor());
//            g.fillRect((int) s.getPosition().getX(), (int) s.getPosition().getY(),
//                (int) s.getSize()[0], (int) s.getSize()[1]);
//          } else {
//            g.setColor(s.getColor());
//            g.fillOval((int) s.getPosition().getX(), (int) s.getPosition().getY(),
//                (int) s.getSize()[0], (int) s.getSize()[1]);
//          }
      }
    }
  }

  public void addShape(IShape shape) {
    shapes.add(shape);
  }
}
