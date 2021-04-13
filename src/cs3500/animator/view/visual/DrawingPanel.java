package cs3500.animator.view.visual;

import cs3500.animator.model.IShape;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Represents the JPanel we will use to draw the scene for visual view.
 */
public class DrawingPanel extends JPanel implements ActionListener {

  private final List<IShape> shapes;
  private final JButton startStop;
  private final JButton restart;
  private final JButton loop;
  private final JButton speedUp;
  private final JButton slowDown;
  private boolean isRunning;
  

  DrawingPanel() {
    super();

    isRunning = true;
    shapes = new ArrayList<IShape>();

    setLayout(new BorderLayout());

    startStop = new JButton("Start/Stop");
    restart = new JButton("Restart");
    loop = new JButton("Loop Animation");
    speedUp = new JButton("Speed Up animation");
    slowDown = new JButton("Slows down animation");

    JPanel subPan = new JPanel();

    subPan.add(speedUp);
    subPan.add(restart);
    subPan.add(startStop);
    subPan.add(loop);
    subPan.add(slowDown);

    add(subPan, BorderLayout.PAGE_END);
  }

  public boolean isRunning() {
    return isRunning;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == startStop) {
      if (isRunning) {
        isRunning = false;
      }
      else {
        isRunning = true;
      }
    }
    else if (e.getSource() == restart) {

    }
    else if (e.getSource() == loop) {

    }
  }


  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (IShape s : shapes) {
      if (s.getShape().equals("rectangle")) {
        g.setColor(s.getColor());
        g.fillRect((int) s.getPosition().getX() - s.getOffsetX(),
            (int) s.getPosition().getY() - s.getOffsetY(),
            (int) s.getSize()[0], (int) s.getSize()[1]);
      } else if (s.getShape().equals("ellipses")) {
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
