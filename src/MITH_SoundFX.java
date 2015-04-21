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

public class MITH_SoundFX extends JFrame
{
   private JLabel credit;  
       
   private JButton playButton;    
   private JButton loopButton;    
   private JButton stopButton;    
   private AudioClip sound;  
   //2nd sound
   private JLabel credit2;     
   private JButton playButton2;    
   private JButton loopButton2;    
   private JButton stopButton2;    
   private AudioClip sound2;          
   
   /**
      Constructor
   */
   
   public MITH_SoundFX() throws MalformedURLException
   {
      // What to do when we close...
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      
      // Title
      setTitle("Moose Sound Effects Experiment");
      
      // Create a layout manager.
      setLayout(new FlowLayout());
      
      // Make the credit label and add it.
      credit = new JLabel("Moose Trap Closing!");
      add(credit);
       // Make the buttons and add them.
     
    
      
     
      
      // Create a file object for the step.wav file.
      File file = new File("270400__littlerobotsoundfactory__trap-01.wav");
      
      // Get a URI object for the audio file.
      URI uri = file.toURI();

      // Get a URL for the audio file.
      URL url = uri.toURL();
    
      // Get an AudioClip object for the sound
      // file using the Applet class's static
      // newAudioClip method.
      sound = Applet.newAudioClip(url);
      
// 2nd noise  
      
      
      
      credit2 = new JLabel("Moose Grunt!");
      add(credit2); 
      makeButtons();
      File file2 = new File("29597__pfujimoto__original.wav");
      URI uri2 = file2.toURI();
      URL url2 = uri2.toURL();
      sound2 = Applet.newAudioClip(url2);
     
      
      
      
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
      
      playButton2  = new JButton("Play");
      loopButton2  = new JButton("Loop");         
      stopButton2  = new JButton("Stop");
          
      // Register an action listener with each  button.
      playButton.addActionListener(new ButtonListener());
      loopButton.addActionListener(new ButtonListener());
      stopButton.addActionListener(new ButtonListener());
      
      playButton2.addActionListener(new ButtonListener());
      loopButton2.addActionListener(new ButtonListener());
      stopButton2.addActionListener(new ButtonListener());

      // Add the  buttons to the content pane.
      add(playButton);
      add(loopButton);
      add(stopButton);
      
      
      add(playButton2);
      add(loopButton2);
      add(stopButton2);
      
      
  
   }

   /**
      Private inner class that handles the action event
      that is generated when the user clicks one of the
      buttons.
   */
   
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
 
//* 2nd buttom Listener*/

   private class ButtonListener2 implements ActionListener
   {
      public void actionPerformed(ActionEvent f)
      {
  
            
         if (f.getSource() == playButton2)
            sound2.play();
         else if (f.getSource() == loopButton2)
            sound2.loop();        
         else if (f.getSource() == stopButton2)
            sound2.stop();   
      }
   }
 
  
  
  
  
  
  
   public static void main(String[] args) throws MalformedURLException
   {
      new MITH_SoundFX();
   }
}
