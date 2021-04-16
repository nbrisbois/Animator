package cs3500.animator.view.visual;

import cs3500.animator.model.IShape;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * Represents the JPanel we will use to draw the scene for visual view.
 */
public class DrawingPanel extends JPanel {

  private final List<IShape> shapes;
  private boolean isRunning;
  public final JPanel panel;

  DrawingPanel() {
    super();
    isRunning = true;
    shapes = new ArrayList<>();
    setLayout(new BorderLayout());
    panel = new JPanel();
    add(panel, BorderLayout.PAGE_END);
  }

  public boolean isRunning() {
    return isRunning;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (IShape s : shapes) {
      if (s.getType().equals("rect")) {
        g.setColor(s.getColor());
        g.fillRect((int) s.getPosition().getX() - s.getOffsetX(),
            (int) s.getPosition().getY() - s.getOffsetY(),
            (int) s.getSize()[0], (int) s.getSize()[1]);
      } else if (s.getType().equals("ellipse")) {
        g.setColor(s.getColor());
        g.fillOval((int) s.getPosition().getX() - s.getOffsetX(),
            (int) s.getPosition().getY() - s.getOffsetY(),
            (int) s.getSize()[0], (int) s.getSize()[1]);
      }
    }
    shapes.clear();
  }

  public void addShape(IShape shape) {
    shapes.add(shape);
  }
}
