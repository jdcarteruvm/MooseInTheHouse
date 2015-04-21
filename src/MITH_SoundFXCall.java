import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;  // For the File class
import java.net.*; // For URL class and MalformedURLException

/**
   This application uses the AudioClip class to play a
   sound. Sound source: NASA
*/

public class MITH_SoundFXCall extends JFrame
{
   private JLabel credit;  
       
   private JButton playButton;    
   private JButton loopButton;    
   private JButton stopButton;    
   private AudioClip sound;  

   /**
      Constructor
   */
   
   public MITH_SoundFXCall() throws MalformedURLException
   {
      // What to do when we close...
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      
      // Title
      setTitle("Moose Sound Effects Experiment");
      
      // Create a layout manager.
      setLayout(new FlowLayout());
      
      // Make the credit label and add it.
      credit = new JLabel("Moose Call (longer)!");
      add(credit);
            // Make the buttons and add them.
      makeButtons();
     
    
      
     
      
      // Create a file object for the step.wav file.
      File file = new File("mooseCall.wav");
      
      // Get a URI object for the audio file.
      URI uri = file.toURI();

      // Get a URL for the audio file.
      URL url = uri.toURL();
    
      // Get an AudioClip object for the sound
      // file using the Applet class's static
      // newAudioClip method.
      sound = Applet.newAudioClip(url);
           
      // Pack and display.
      pack();
      setVisible(true);
      
      
      
   }
   
   /**
      The makeButtons method creates the Play, Loop, and
      Stop buttons, and adds them to the content pane.
   */

   private void makeButtons()
   {
      // Create the  Play, Loop, and Stop buttons.
      playButton  = new JButton("Play");
      loopButton  = new JButton("Loop");         
      stopButton  = new JButton("Stop");
          
      // Register an action listener with each  button.
      playButton.addActionListener(new ButtonListener());
      loopButton.addActionListener(new ButtonListener());
      stopButton.addActionListener(new ButtonListener());
      
      // Add the  buttons to the content pane.
      add(playButton);
      add(loopButton);
      add(stopButton);

   }


   private class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         // Determine which button was clicked and 
         // perform the selected action.
         if (e.getSource() == playButton)
            sound.play();
         else if (e.getSource() == loopButton)
            sound.loop();        
         else if (e.getSource() == stopButton)
            sound.stop();
             
      }
   }
 

   public static void main(String[] args) throws MalformedURLException
   {
      new MITH_SoundFXCall();
   }
}
