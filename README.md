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

### AnimationModel ###
This interface provides the framework for the model itself.
This interface provides the following features:
*   addShape to add a shape to the model.
*   getShapes to get a list of all the shapes in the model.

### BasicAnimationModel
This class implements AnimationModel and provides the bulk of the program's functionality.
The purpose of this class is to store shapes and run them in adjacent with the tick.
### IShape
This interface provides the framework for the shapes that will be put into the model.
<<<<<<< HEAD
This interface provides the following features:
=======
 This interface provides the following features:
>>>>>>> 729951418712af12c4eae4418e451caed2d3417b
*   render which generates a log of the commands that a shape will execute (i.e motion)
*   Provide changes to the shape's sizes, locations and colors.
*   Provides getters for the shape's size, location, color, starting tick and priority in reference to other shapes.
*   addMotion adds any additional motions that the shape might need.
*   executeMotion modifies the shape in terms of the motion provided for the current tick.
*   copy will return a new cloned version of the shape that calls it.
### Shape
The <b>Shape</b> class is an abstract class the implements <b>IShape</b> that will be used to hold data and behaviors common between
all shapes.

### Motion
This class will keep track of a single motion for a shape.
It keeps track of changes in movement, size and color.
In addition, it will also store the tick in which it will be put in motion.

### Shapes

---
<<<<<<< HEAD
*The following shapes were chosen based off of the native java class 'Graphics2D' which handles
Ovals, Rectangles, and Polygons. Future Shapes can be supported thanks to the abstract class.
Examples might include Arcs, Round Rectangles, etc.*
#### *Oval*
The <b>Oval</b> class extends from the abstract class 'Shape'.
Ovals will be used to create curved shapes such as Circles, Ellipses,
and Ovals.
=======

*The following shapes were chosen based off of the native java class 'Graphics2D' which handles 
Ovals, Rectangles, and Polygons. Future Shapes can be supported thanks to the abstract class. 
Examples might include Arcs, Round Rectangles, etc.* 
  #### *Oval*
The <b>Oval</b> class extends from the abstract class 'Shape'. 
Ovals will be used to create curved shapes such as Circles, Ellipses, 
and Ovals. 
>>>>>>> 729951418712af12c4eae4418e451caed2d3417b
#### *Rectangle*
The <b>Rectangle</b> class extends from the abstract class 'Shape'.
Rectangles will be used to create block shapes such as Rectangles and Squares.
####  *Polygon*
The <b>Polygon</b> class extends from the abstract class 'Shape'.
Polygons will be used to create shapes that are not rectangles or Ovals.

---

### Main
Main function can be used to demonstrate how our program works. It can also be used
for testing purposes
