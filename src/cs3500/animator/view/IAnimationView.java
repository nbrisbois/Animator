package cs3500.animator.view;

/**
 * The interface representing the view user will see as a display of the animation model.
 */
public interface IAnimationView {

  /**
   * To display the animation for the user to view.
   */
  void render();

  /**
   * sets the speed to which the animation should play
   */
  void setSpeed(int speed);
}
