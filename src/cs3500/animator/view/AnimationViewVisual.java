package cs3500.animator.view;

import cs3500.animator.model.IShape;

/**
 * The interface representing the visual animation of the animation model.
 */
public interface AnimationViewVisual extends IAnimationView {

  void draw(IShape shape);

  void refresh();

}
