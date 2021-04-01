package cs3500.animator;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.BasicAnimationModel;
import cs3500.animator.model.BasicAnimationModel.Builder;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Motion;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.FactoryView;
import cs3500.animator.view.IAnimationView;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Excellence Class representing the main interaction with our Model and View
 * <p>
 * The function is called via the command line.
 * <p>
 * Available Command Flags: -in "name-of-animation-file"      The file that will be used for the
 * animation -view "type-of-view"              The view type that will be used for the animation
 * -out "where-output-show-go"       The output location of the data -speed
 * "integer-ticks-per-second" The speed in which the animation should run
 * <p>
 * Example Commands: -in smalldemo.txt -view text -speed 2 -view svg -out out.svg -in buildings.txt
 * -in smalldemo.txt -view text -in smalldemo.txt -speed 50 -view visual
 */
public final class Excellence {

  /**
   * runs the program in three forms: text, visual, or SVG.
   *
   * @param args command lines that the user inputs in
   */
  public static void main(String[] args) {
    AnimationModel model = null;
    String viewType = null;
    String out = null;
    int tempo = 1;
    JFrame frame = new JFrame();
    frame.setSize(100, 100);
    for (int i = 0; i < args.length; i++) {
      // reads input file
      if (args[i].equals("-in")) {
        try {
          InputStream inputStream = new FileInputStream(args[i + 1]);
          model = AnimationReader.parseFile(new InputStreamReader(inputStream),
              new BasicAnimationModel.Builder());
        } catch (FileNotFoundException e) {
          // catch file not found error
          JOptionPane.showMessageDialog(frame,
              "File not found",
              "File warning",
              JOptionPane.WARNING_MESSAGE);
        }
      }
      // reads output file path
      if (args[i].equals("-out")) {
        try {
          if (!args[i + 1].equals("out")) {
            out = args[i + 1];
          }
        } catch (Exception e) {
          JOptionPane.showMessageDialog(frame,
              "Output source not found", "Output warning",
              JOptionPane.WARNING_MESSAGE);
        }
      }
      // reads which kind of view the user wants
      if (args[i].equals("-view")) {
        try {
          viewType = args[i + 1];
        } catch (Exception e) {
          // catch no input given
          JOptionPane.showMessageDialog(frame, "please give me a view",
              "View warning",
              JOptionPane.WARNING_MESSAGE);
        }
      }
      // reads the speed
      try {
        if (args[i].equals("-speed")) {
          tempo = Integer.valueOf(args[i + 1]);
        }
      } catch (Exception e) {
        // catch no input given
        JOptionPane.showMessageDialog(frame, "speed sir speed",
            "speed warning",
            JOptionPane.WARNING_MESSAGE);
      }
    }
    FactoryView factory = new FactoryView(model);
    // runs the program
    try {
      IAnimationView view = factory.getView(viewType);
    } catch (NullPointerException | IOException e) {
      JOptionPane.showMessageDialog(frame, "oh well I guess nothing happens",
          "Null Error", JOptionPane.ERROR_MESSAGE);
    }
  }
}
