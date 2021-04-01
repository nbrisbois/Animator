package cs3500.animator;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.BasicAnimationModel.Builder;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.FactoryView;
import cs3500.animator.view.IAnimationView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * Excellence Class representing the main interaction with our Model and View
 *
 * <p>The function is called via the command line.
 *
 * <p>Available Command Flags: -in "name-of-animation-file"      The file that will be used for
 * the
 * animation -view "type-of-view"              The view type that will be used for the animation
 * -out "where-output-show-go"       The output location of the data -speed
 * "integer-ticks-per-second" The speed in which the animation should run
 *
 * <p>Example Commands: -in smalldemo.txt -view text -speed 2 -view svg -out out.svg -in
 * buildings.txt
 * -in smalldemo.txt -view text -in smalldemo.txt -speed 50 -view visual
 */
public final class Excellence {

  /**
   * runs the program in three forms: text, visual, or SVG.
   *
   * @param args command lines that the user inputs in
   */
  public static void main(String[] args) {
    // Set Defaults
    Readable inputFileName = new StringReader("");
    String output = "";
    String viewDel = "";
    Builder builder = new Builder();
    AnimationModel model = null;

    int speed = 1;
    // Get Inputs
    for (int ii = 0; ii < args.length; ii++) {
      switch (args[ii]) {
        case "-in":
          try {
            model = AnimationReader.parseFile(new FileReader(args[ii + 1]), builder);
          } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(new JFrame(),
                "File not Found",
                "File warning",
                JOptionPane.WARNING_MESSAGE);
          }
          break;
        case "-out":
          try {
            output = args[ii + 1];
          } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(),
                "Output not found",
                "Output Warning",
                JOptionPane.WARNING_MESSAGE);
          }
          break;
        case "-view":
          try {
            viewDel = args[ii + 1];
          } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(),
                "No view provided",
                "View Warning",
                JOptionPane.WARNING_MESSAGE);
          }
          break;
        case "-speed":
          speed = Integer.parseInt(args[ii + 1]);
          break;
        default:
          continue;
      }
      ii++;
    }

    // Check Mandatory inputs
    if (inputFileName.equals("")) {
      throw new IllegalArgumentException("Input file required. Format: '-in file_name' ");
    }
    if (viewDel.equals("")) {
      throw new IllegalArgumentException("View type required. Format: '-view view_type' ");
    }

    // Create View
    FactoryView view = new FactoryView(model, output, speed);


    try {
      IAnimationView v = view.getView(viewDel);

      if (viewDel.equals("visual")) {
        Timer t = new Timer(100, null);
        t.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              v.render();
            } catch (Exception nullPoint) {
              t.stop();
            }
          }
        });
        t.start();
      }
      else {
        v.render();
      }
    } catch (Exception e) {
      System.out.printf("%s%n", e);
    }
  }
}
