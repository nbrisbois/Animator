
This README.md documents the following Model and View Design for an Easy Animator Model.

# Model
The following are the model components that this document will go over:

* public interface AnimationModel
* implements AnimationModel BasicAnimationModel
* public interface IShape
* implements IShape abstract class  Shape
* public class Motion
* extends Shape Oval
* extends Shape Rectangle

### AnimationModel
This interface provides the framework for the model itself.
This interface provides the following features:
*   addShape to add a shape to the model.
*   getSceneHeight to get the scene height of the canvas. (newly added methods)
*   getSceneWidth to get the scene width of the canvas. (newly added methods)
*   getTopLeftX to get the top left X coordinate of the canvas. (newly added methods)
*   getTopLeftY to get the top left Y coordinate of the canvas. (newly added methods)
*   getShapes to get a list of all the shapes in the model.
*   getDuration to retrieve the maximum ticks of animation. (newly added methods)
*   moveShapes to runs through the list of shapes moving them by time. (newly added methods)
*   getMotions to observe the motions in this animation model. (newly added methods)
*   addMotion to add a motion to the desired shape in the model. (newly added methods)
*   removeShape to remove a shape from the animation model. (newly added methods)
*   removeMotion to remove the last motion of the desired shape from the animation model. (newly 
added methods)

### BasicAnimationModel
This class implements AnimationModel and provides the bulk of the program's functionality.
The purpose of this class is to store shapes and run them in adjacent with the tick.
### IShape
This interface provides the framework for the shapes that will be put into the model.
We added a few more getter methods to help other classes to retrieve needed information.
This interface provides the following features:
*   render which generates a log of the commands that a shape will execute (i.e motion)
*   Provide changes to the shape's sizes, locations and colors.
*   Provides getters for the shape's size, type, SVGAttributes location, color, offsetX, offsetY, starting tick and priority in reference to other shapes.
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

*The following shapes were chosen based off of the native java class 'Graphics2D' which handles 
Ovals, Rectangles, and Polygons. Future Shapes can be supported thanks to the abstract class. 
Examples might include Arcs, Round Rectangles, etc.* 
#### *Oval*
The <b>Oval</b> class extends from the abstract class 'Shape'. 
Ovals will be used to create curved shapes such as Circles, Ellipses, 
and Ovals. 
#### *Rectangle*
The <b>Rectangle</b> class extends from the abstract class 'Shape'.
Rectangles will be used to create block shapes such as Rectangles and Squares.

---

# View
The following are the view components that this document will go over:

* public interface IAnimationView
* implements IAnimationView public interface AnimationViewVisual
* implements IAnimationView SVGView
* implements IAnimationView TextualView
* extends JFrame implements AnimationViewVisual VisualView
* extends JPannel DrawingPanel
* public class FactoryView 

### IAnimationView
This interface provides a framework for how each individual view will serve to display the animation
*   render to display the animation for the user to view.
### AnimationViewVisual
This interface specifically defines how each visual view will display the animation
*   draw to draw out the given shape on the canvas.
*   repaint to repaint the Visual View.

### SVGView
The view to display the animation as a string output in SVG format,  an XML-based format that can be
 used to describe images and animations.

### TextualView
The view to display the animation model textually.

### VisualView
The view to show the visual demonstration of the animation.

### DrawingPannel
A class to help the visual view to draw out each needed shape/

### FactoryView
A factory class to help produce an IAnimationView based on desired View Type


### SVG View
Scalable Vector Graphics or SVG is an Extensible Markup Language-based vector image format
for two-dimensional graphics. The SVG view a part of this assignment is used to create an SVG
file or output SVG syntax from which an AnimationModel can be visualized. 

The view is currently created by taking in a Model, a file name, and a speed. 
- The model: represents the instance of AnimationModel that will be used to generate the SVG text
- The fileName: will be used for storing the svg text to a file. If empty, generated svg is sent to System.out
- The speed: the speed in which the animation will play. This is used when determining durations and start ticks for animations

(Disclaimer: Once a controller is implemented, this structure will change)

For this assignment, the SVG native function 'animate' was used to handle shape Translations in the x and y axis, shape transformations regarding width and height, and changes in color.
An Example of an SVG animate command is as follows: 

" animate attributeType="xml" attributeName="<attribute_being_changed>" dur="<animation_duration>" from="<start_of_animation>" to="<end_of_animation>" fill="color" ""

You can run an SVG file in any Browser that supports them. Most modern browsers do.

---

# Excellence
Main function that allow us to render one of SVGview, TextualView, and VisualView based on user's
input.
