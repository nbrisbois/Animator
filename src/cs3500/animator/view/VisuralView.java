package cs3500.animator.view;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.BasicAnimationModel;
import java.util.Objects;
import javax.swing.JFrame;

public class VisuralView extends JFrame implements IAnimationView{

  private final AnimationModel model;

  public VisuralView(AnimationModel model) {
    super();

    Objects.requireNonNull(model);

    this.model = model;

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(model.getSceneWidth(), model.getSceneHeight());

  }

  @Override
  public void render() {
  }
}
