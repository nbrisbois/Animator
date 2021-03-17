# Animator - Model

This README.md documents the following Model Design for an Easy Animator Model.

The following are components that this document will go over:

* public interface    AnimationModel
* extends AnimationModel BasicAnimationModel
* public interface    IShape
* abstract class  Shape
* public class    Motion
* extends Shape  Oval
* extends Shape  Rectangle
* extends Shape  Polygon
* public class   Main

###AnimationModel
####This interface provides the framework for the model itself.
#####This interface provides the following features:
*   addShape to add a shape to the model.
*   getShapes to get a list of all the shapes in the model.

###BasicAnimationModel
####This class implements AnimationModel and provides the bulk of the program's functionality.
#####The purpose of this class is to store shapes and run them in adjacent with the tick.
###IShape
####This interface provides the framework for the shapes that will be put into the model.
#####This interface provides the following features:
*   render which generates a log of the commands that a shape will execute (i.e motion)
*   Provide changes to the shape's sizes, locations and colors.
*   Provides getters for the shape's size, location, color, starting tick and priority in reference to other shapes.
*   addMotion adds any additional motions that the shape might need.
*   executeMotion modifies the shape in terms of the motion provided for the current tick.
*   copy will return a new cloned version of the shape that calls it.
###Shape
####This is an abstract class that implements IShape.
####This class' job is to store motions in a list and fill most of the functions implemented from IShape.

###Motion
####This class will keep track of a single motion for a shape.
####It keeps track of changes in movement, size and color.
####In addition, it will also store the tick in which it will be put in motion.
###Oval, Rectangle, Polygon
####These classes extend Shape and exists to fill out the required shape.
#####Polygon is any unique shape
###In addition to creating a unique render, it also implements the copy command.
###Main
####Main is an example of a controller utilizing the model, it is also for testing.