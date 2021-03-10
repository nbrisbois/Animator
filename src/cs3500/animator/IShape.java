package cs3500.animator;

public interface IShape {

  void render();

  void changePos(int pos);

  void changeSize(int size);

  int getPriority();
}
