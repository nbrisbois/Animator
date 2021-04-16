package cs3500.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

/**
 * Abstract Class that all eligible shapes will be created from.
 */
public abstract class Shape implements IShape {

  protected static int numberOfShapes = 0;
  protected final String name;
  protected final int order;
  protected Double position;
  protected double[] dimensions;
  protected Color color;
  protected long startTick;
  protected long originalStartTick;
  protected Queue<Motion> motions;
  protected final List<Motion> originalMotions;
  protected final int offsetX;
  protected final int offsetY;
  protected long timeElapsed = 0;
  private double speedX;
  private double speedY;
  private double scaleX;
  private double scaleY;
  private boolean visual;
  private final double orignalSizeX;
  private final double orignalSizeY;
  private final Double originalPos;

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
   * @param offsetX   The offset in the x direction
   * @param offsetY   The offset in the y direction
   * @throws NullPointerException     A NullPointerException is thrown when a null Object argument
   *                                  is provided
   * @throws IllegalArgumentException An IllegalArgumentException is thrown when the arguments are
   *                                  invalid
   */
  public Shape(String name, Double pos, double x, double y, Color color, long startTick,
      Queue<Motion> motions, int offsetY, int offsetX)
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
      this.motions.add(new Motion(m.getMoveX(), m.getMoveY(), m.getColor(), m.getScaleX(),
          m.getScaleY(), m.getTicks()));
    }

    this.visual = false;
    this.offsetX = offsetX;
    this.offsetY = offsetY;
    this.name = name;
    this.position = new Double(pos.getX(), pos.getY());
    this.dimensions = new double[]{x, y};
    this.color = new Color(color.getRGB());
    this.startTick = startTick;
    this.order = ++numberOfShapes;

    this.originalMotions = new ArrayList(motions);
    this.orignalSizeX = x;
    this.orignalSizeY = y;
    this.originalPos = new Double(pos.getX(), pos.getY());
    this.originalStartTick = startTick;
    speedX = speedY = scaleX = scaleY = 0;

  }

  /**
   * Construct a Shape object using its unique name.
   *
   * @param name the unique name of this shape
   * @throws NullPointerException     A NullPointerException is thrown when a null Object argument
   *                                  is provided
   * @throws IllegalArgumentException An IllegalArgumentException is thrown when the arguments are
   *                                  invalid
   */
  public Shape(String name, int offsetX, int offsetY)
      throws NullPointerException, IllegalArgumentException {
    this.name = name;
    this.offsetX = offsetX;
    this.offsetY = offsetY;
    this.order = 0;
    this.orignalSizeX = 0;
    this.orignalSizeY = 0;
    this.motions = new LinkedList<>();
    this.originalMotions = new ArrayList<>();
    this.originalPos = new Double(0,0);
    this.originalStartTick = startTick;

  }

  @Override
  public void isVisual() {
    if (!visual) {
      startTick = startTick * 1000;
    }
    visual = true;
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

  public void reset() {
    this.motions = new LinkedList<>(originalMotions);
    this.position.x = this.originalPos.getX();
    this.position.y = this.originalPos.getY();;
    this.dimensions[0] = this.orignalSizeX;
    this.dimensions[1] = this.orignalSizeY;
    this.startTick = originalStartTick * 1000;
    this.timeElapsed = 0;
    speedX = speedY = scaleX = scaleY = 0;
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

    long time = ((peekedMotion.getTicks() * 1000));

    // Remove the current motion if we are at the start or greater than the next motion
    if (currentTick >= ((peekedMotion.getTicks() * 1000) + (startTick))) {
      motions.remove();
      // If the queue is still not empty, update the tick of the shape
      if (!motions.isEmpty()) {
        startTick = currentTick;
      }
      peekedMotion = motions.peek();
      time = ((peekedMotion.getTicks() * 1000));
    }

    // Update the speed
    if (currentTick == startTick) {
      if (time == 0) {
        time = peekedMotion.getTicks() * 1000;
      }
      speedX = ((peekedMotion.getMoveX() / time) * 100);
      speedY = ((peekedMotion.getMoveY() / time) * 100);

      scaleX = (((orignalSizeX * peekedMotion.getScaleX() - dimensions[0]) / time) * 100);
      scaleY = (((orignalSizeY * peekedMotion.getScaleY() - dimensions[1]) / time) * 100);
    }

    // Update the position
    position.setLocation(
          position.getX() + speedX,
          position.getY() + speedY);
    // Update the dimensions
    dimensions[0] = dimensions[0] + scaleX;
    dimensions[1] = dimensions[1] + scaleY;

    if (position.getX() < 0) {
      position.setLocation(
          0,
          position.getY() + speedY);
    }
    else if (position.getY() < 0) {
      position.setLocation(
          position.getX() + speedX,
          0);
    }

    // Update the color
    color = peekedMotion.getColor();
  }

  private String getHex(Color c) {
    return String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
  }

  @Override
  public String generateSVG(int speed) {
    long ticks_passed = this.getStartTick() * 100 / speed;
    StringBuilder svg = new StringBuilder();

    // Add shape attributes
    String[] attributes = this.getSVGAttributes();
    svg.append(
        String.format("\t<%s "    // Shape Type
                + "id=\"%s\" "      // Shape Name
                + "%s=\"%s\" "  // Attribute 1 Position X
                + "%s=\"%s\" "  // Attribute 2 Position Y
                + "%s=\"%s\" "  // Attribute 3 Size Width/Radius X
                + "%s=\"%s\" "  // Attribute 4 Size Height/Radius Y
                + "fill=\"%s\" "// Shape Color
                + "visibility=\"hidden\" >",
            this.getType(),
            this.getName(),
            attributes[0],
            this.getPosition().getX(),
            attributes[1],
            this.getPosition().getY(),
            attributes[2],
            this.getSize()[0],
            attributes[3],
            this.getSize()[1],
            getHex(this.getColor())));
    svg.append("\n");

    svg.append(String.format("\t\t<set "
            + "attributeType=\"xml\" "
            + "attributeName=\"visibility\" "
            + "to=\"visible\" "
            + "begin=\"%sms\" "
            + "fill=\"freeze\"/>\n",
        ticks_passed));

    // Begin adding animations
    while (!this.motions.isEmpty()) {
      Objects.requireNonNull(this.motions.peek());
      Motion nextMotion = this.motions.peek();
      long duration = nextMotion.getTicks() * 100L / speed;

      // Move Position X
      svg.append(String.format("\t\t<animate "
              + "attributeType=\"xml\" "
              + "attributeName=\"%s\" "
              + "dur=\"%sms\" "
              + "repeatCount=\"0\" "
              + "from=\"%s\" to=\"%s\" "
              + "begin=\"%sms\" "
              + "fill=\"freeze\" "
              + "/>\n",
          attributes[0],
          duration,
          this.getPosition().getX(),
          this.getPosition().getX() + this.motions.peek().getMoveX(),
          ticks_passed
      ));
      // Move Position Y
      svg.append(String.format("\t\t<animate "
              + "attributeType=\"xml\" "
              + "attributeName=\"%s\" "
              + "dur=\"%sms\" "
              + "repeatCount=\"0\" "
              + "from=\"%s\" to=\"%s\" "
              + "begin=\"%sms\" "
              + "fill=\"freeze\" "
              + "/>\n",
          attributes[1],
          duration,
          this.getPosition().getY(),
          this.getPosition().getY() + this.motions.peek().getMoveY(),
          ticks_passed
      ));
      // Change Color
      svg.append(String.format("\t\t<animate "
              + "attributeType=\"xml\" "
              + "attributeName=\"fill\" "
              + "dur=\"%sms\" "
              + "repeatCount=\"0\" "
              + "from=\"#%02x%02x%02x\" "
              + "to=\"#%02x%02x%02x\" "
              + "begin=\"%sms\" "
              + "fill=\"freeze\" "
              + "/>\n",
          duration,
          this.getColor().getRed(),
          this.getColor().getGreen(),
          this.getColor().getBlue(),
          nextMotion.getColor().getRed(),
          nextMotion.getColor().getGreen(),
          nextMotion.getColor().getBlue(),
          ticks_passed
      ));
      // Change X Scale
      svg.append(String.format(("\t\t<animate "
              + "dur=\"%sms\" "
              + "repeatCount=\"0\" "
              + "attributeType=\"xml\" "
              + "attributeName=\"%s\" "
              + "from=\"%s\" "
              + "to=\"%s\" "
              + "begin=\"%sms\" "
              + "fill=\"freeze\" "
              + "/>\n"),
          duration,
          attributes[2],
          this.getSize()[0],
          this.getSize()[0] * nextMotion.getScaleX(),
          ticks_passed
      ));
      // Change Y Scale
      svg.append(String.format(("\t\t<animate "
              + "dur=\"%sms\" "
              + "repeatCount=\"0\" "
              + "attributeType=\"xml\" "
              + "attributeName=\"%s\" "
              + "from=\"%s\" "
              + "to=\"%s\" "
              + "begin=\"%sms\" "
              + "fill=\"freeze\" "
              + "/>\n\n"),
          duration,
          attributes[3],
          this.getSize()[1],
          this.getSize()[1] * nextMotion.getScaleY(),
          ticks_passed
      ));
      ticks_passed += duration;
      this.updateShape(motions.remove());
    }
    svg.append(String.format("\t</%s>\n\n", this.getType()));
    return svg.toString();
  }

  private void updateShape(Motion m) {
    this.position.setLocation(
        this.getPosition().getX() + m.getMoveX(),
        this.getPosition().getY() + m.getMoveY()
    );
    this.color = m.getColor();
    this.dimensions = new double[]{
        this.dimensions[0] * m.getScaleX(),
        this.dimensions[1] * m.getScaleY()};
  }
}