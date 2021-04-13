package cs3500.animator.view.visual;

import cs3500.animator.model.IShape;
import cs3500.animator.view.IAnimationView;

public interface NewAnimationView extends IAnimationView {

  void refresh();
  void draw(IShape shape) throws IllegalArgumentException;
}
