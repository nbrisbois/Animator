package cs3500.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * Abstract Class that all eligible shapes will be created from.
 */
public abstract class Shape implements IShape {

  protected final String name;
  protected Double position;
  protected double[] dimensions;
  protected Color color;
  protected long startTick;
  protected static int numberOfShapes = 0;
  protected final int order;
  protected Queue<Motion> motions;

  private double speedX;
  private double speedY;
  private double scaleX;
  private double scaleY;

  /**
   * Abstract Shape Constructor.
   *
   * @param name      The unique name of shape
   * @param pos       The spawn position of the Shape
   * @param x         Dimension one of Two
   * @param y         Dimension two of Two
   * @param color     The color of the Shape
   * @param startTick The start tick of the Polygon. This is where the shape will be rendered on the
   *                  initially on the Screen
   * @param motions   A list of motions detailing how the shape will move as time goes on
   * @throws NullPointerException     A NullPointerException is thrown when a null Object argument
   *                                  is provided
   * @throws IllegalArgumentException An IllegalArgumentException is thrown when the arguments are
   *                                  invalid
   */
  public Shape(String name, Double pos, double x, double y, Color color, long startTick,
      Queue<Motion> motions)
      throws NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(pos);
    Objects.requireNonNull(color);
    Objects.requireNonNull(motions);
    Objects.requireNonNull(name);
    if (x < 0 || y < 0 || startTick < 0) {
      throw new IllegalArgumentException("Primitive constructor elements must not be non negative");
    }

    /*
     * Check to see if each motion's ticks are greater than the previous.
     * This is important since there cannot be gaps, but there will be bugs if the,
     * the wrong tick is given.
     */
    this.motions = new LinkedList<>();
    for (Motion m : motions) {
      Objects.requireNonNull(motions.peek());
      if (!this.motions.isEmpty() && m.getTicks() < motions.peek().getTicks()) {
        throw new IllegalStateException("No tick can be less than the previous tick");
      }
      this.motions.add(new Motion(m.getMoveX(), m.getMoveY(), m.getColor(), m.getScaleX(),
          m.getScaleY(), m.getTicks()));
    }

    this.name = name;
    this.position = new Double(pos.getX(), pos.getY());
    this.dimensions = new double[]{x, y};
    this.color = new Color(color.getRGB());
    this.startTick = startTick;
    this.order = ++numberOfShapes;

    speedX = speedY = scaleX = scaleY = 0;

  }

  public void changePosition(Double pos) throws NullPointerException {
    Objects.requireNonNull(pos);
    this.position = new Double(pos.getX(), pos.getY());
  }

  public Double getPosition() {
    return new Double(position.getX(), position.getY());
  }

  public void changeSize(double[] size) throws NullPointerException {
    Objects.requireNonNull(size);
    this.dimensions = new double[]{size[0], size[1]};
  }

  public double[] getSize() {
    return new double[]{dimensions[0], dimensions[1]};
  }

  public void changeColor(Color c) throws NullPointerException {
    Objects.requireNonNull(c);
    this.color = new Color(c.getRGB());
  }

  public Color getColor() {
    return new Color(color.getRGB());
  }

  public long getStartTick() {
    return this.startTick;
  }

  public int getPriority() {
    return this.order;
  }

  public void addMotion(Motion m) {
    this.motions.add(new Motion(m.getMoveX(), m.getMoveY(), m.getColor(),
        m.getScaleX(), m.getScaleY(), m.getTicks()));
  }

  public Queue<Motion> getMotion() {
    return this.motions;
  }

  public void removeMotion() {
    this.motions.remove(this.motions.size() - 1);
  }

  public String getName() {
    return name;
  }

  @Override
  public void setOffset(int offsetX, int offsetY) {
    position.setLocation(position.getX() + offsetX,
        position.getY() + offsetY);
  }

  /**
   * Updates the shapes attributes based off of the current and next motion the shape is executing.
   *
   * @param currentTick an integer representing the tick we want to calculate the effects of the
   *                    motion at
   * @throws NullPointerException thrown if queue peek does not return a motion
   */
  public void calculateMotion(long currentTick) throws NullPointerException {
    // A next motion is required for calculating
    Objects.requireNonNull(motions.peek(), "No next motion for Calculation");

    // Store the next motion by peeking in the queue. Make final to make it immutable
    Motion peekedMotion = motions.peek();

    long time = (peekedMotion.getTicks() - currentTick);

    // Remove the current motion if we are at the start or greater than the next motion
    if ((currentTick >= peekedMotion.getTicks())) {
      motions.remove();
      // If the queue is still not empty, update the tick of the shape
      if (!motions.isEmpty()) {
        startTick = currentTick;
        peekedMotion = motions.peek();
        time = (peekedMotion.getTicks() - currentTick);
      }
    }
    // Update the speed
    if (startTick + time == peekedMotion.getTicks()) {
      if (time == 0) {
        time = 1;
      }
      speedX = (((peekedMotion.getMoveX() - position.getX()) / time) * 100);
      speedY = (((peekedMotion.getMoveY() - position.getY()) / time) * 100);
      scaleX = (((peekedMotion.getScaleX() - dimensions[0]) / time) * 100);
      scaleY = (((peekedMotion.getScaleY() - dimensions[1]) / time) * 100);
    }

    // Update the position
    position.setLocation(
        position.getX() + speedX,
        position.getY() + speedY);

    // Update the dimensions
    dimensions[0] = dimensions[0] + scaleX;
    dimensions[1] = dimensions[1] + scaleY;

    // Update the color
    color = peekedMotion.getColor();
  }

  private String getHex(Color c) {
    return String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
  }

  @Override
  public String generateSVG() {
    long ticks_passed = this.getStartTick();
    StringBuilder svg = new StringBuilder();

    double previousScaleX = this.getSize()[0];
    double previousScaleY = this.getSize()[1];
    Color previousColor = this.getColor();

    String[] attributes = this.getSVGAttributes();

    svg.append(
        String.format("<%s %s= \"%scm\" %s=\"%scm\" %s=\"%s\" %s=\"%s\" fill=\"%s\">",
            this.getType(),
            attributes[0],
            this.getPosition().getX(),
            attributes[1],
            this.getPosition().getY(),
            attributes[2],
            this.getSize()[0],
            attributes[3],
            this.getSize()[1],
            getHex(this.getColor())));
    svg.append("\n\t");

    while (!this.motions.isEmpty()) {
      Motion nextMotion;
      Objects.requireNonNull(this.motions.peek());
      nextMotion = this.motions.peek();
      // AnimateMotion
      svg.append(String.format("<animateMotion dur=\"%ss\" repeatCount=\"0\" "
              + "path=\"M %s, %s L %s %s\" "
              + "begin=\"%s\" "
              + "/>\n\t",
          nextMotion.getTicks(),
          this.getPosition().getX(),
          this.getPosition().getY(),
          nextMotion.getMoveX(),
          nextMotion.getMoveY(),
          ticks_passed
      ));
      // AnimateColor
      svg.append(String.format("<animate attributeName=\"fill\" dur=\"%ss\" repeatCount=\"0\" "
              + "from=\"#%02x%02x%02x\" to=\"#%02x%02x%02x\" "
              + "begin=\"%s\" "
              + "/>\n\t",
          nextMotion.getTicks(),
          previousColor.getRed(),
          previousColor.getGreen(),
          previousColor.getBlue(),
          nextMotion.getColor().getRed(),
          nextMotion.getColor().getGreen(),
          nextMotion.getColor().getBlue(),
          ticks_passed
      ));
      // AnimateScale
      svg.append(String.format(("<animateTransform dur=\"%ss\" repeatCount=\"0\" "
              + "attributeName=\"transform\" "
              + "type=\"scale\" "
              + "additive=\"sum\" "
              + "from=\"%s %s\" "
              + "to=\"%s %s\" "
              + "begin=\"%s\" "
              + "/>\n"),
          nextMotion.getTicks(),
          previousScaleX / 100,
          previousScaleY / 100,
          nextMotion.getScaleX() / 100,
          nextMotion.getScaleY() / 100,
          ticks_passed
      ));
      svg.append("\n\t");
      ticks_passed += nextMotion.getTicks();
      previousScaleX = nextMotion.getScaleX();
      previousScaleY = nextMotion.getScaleY();
      previousColor = nextMotion.getColor();
      try {
        calculateMotion(ticks_passed);
      } catch (Exception e) {
        break;
      }
    }
    svg.append(String.format("</%s>\n", this.getType()));
    return svg.toString();
  }
}