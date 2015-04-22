/*******************************************************************
 * Moose In The House - CS205 Final Project
 * Ian Benson, Jonathan Carter, Joey Palchak, Rachel Temple Cj Zhang
 *
 * MITH_Options_GUI - GUI class for creating a window, presenting
 *                    various game options for the user
 *******************************************************************/

import java.util.*;

import javax.swing.*;
import java.awt.Color;
import java.awt.*;
import java.awt.event.*;



public class MITH_Options_GUI extends JFrame
{
   private JPanel panel;
   private JButton helpButton;
   private JButton howToPlayButton; 
   private JButton statsButton;
   private JButton quitButton;  
   final int WINDOW_WIDTH = 200;
   final int WINDOW_HEIGHT = 700;
   
   private JPanel imagePanel;     // To hold the label
   private JPanel buttonPanel;    // To hold a button
   private JLabel imageLabel;     // To show an image
   private JButton button;        // To get an image

     
            


   /*********************************************************
	 MITH_Options_GUI() - default constructor, creates a 
     window presenting various game options to the player
   *********************************************************/
   public MITH_Options_GUI()
   {
     
           
              
            //Set the window's title
      
      setTitle("MENU");
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

          
  
            
      buildPanel();
      add(panel);
      
      
      setVisible(true);
   }
      
/*********************************************************
 buildPanel() - creates and adds the various buttons & 
 content to the content panel
*********************************************************/
private void buildPanel()
   {
      helpButton = new JButton("Help");
      helpButton.addActionListener(new ButtonListener());
      howToPlayButton = new JButton("How To Play");
      howToPlayButton.addActionListener(new ButtonListener());
      statsButton = new JButton("Statistics");
      statsButton.addActionListener(new ButtonListener());
      quitButton = new JButton("Quit");
      quitButton.addActionListener(new ButtonListener());
      panel = new JPanel();
      
      panel.add(helpButton);
      panel.add(howToPlayButton);
      panel.add(quitButton);
      panel.add(statsButton);
      
      
   }   
   
private class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if(e.getSource() == statsButton)
         {
            GameStats_GUI statWindow = new GameStats_GUI();
         }
         if(e.getSource() == helpButton)
         {
            MITH_Help_GUI helpWindow = new MITH_Help_GUI();
         }
         if(e.getSource() == howToPlayButton)
         {
             MITH_HowToPlay_GUI howWindow = new MITH_HowToPlay_GUI();
         }
         if(e.getSource() == quitButton)
         {
            System.exit(0);
         }
         
      }
   }  
   
public static void main (String[]args)
   {
      
      MITH_Options_GUI tableForView = new MITH_Options_GUI();
   }
}
