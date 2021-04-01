package cs3500.animator.view;

import cs3500.animator.model.IShape;

/**
 * The interface representing the visual animation of the animation model.
 */
public interface AnimationViewVisual extends IAnimationView {

  /**
   * Draws out the given shape on the canvas.
   *
   * @param shape the shape we want to draw
   */
  void draw(IShape shape);

  /**
   * Repaint the Visual View.
   */
  void refresh();

}
