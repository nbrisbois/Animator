package cs3500.animator.view;

import cs3500.animator.model.IShape;

public interface AnimationViewVisual extends IAnimationView{
  void draw(IShape shape);

  void refresh();

}
