import javax.swing.*;
import java.awt.Color;



public class MITH_HowToPlay_GUI extends JFrame
{
   private JPanel panel;
   private JButton helpButton;
   private JButton howToPlayButton; 
   private JButton optionsButton;
   private JButton quitButton;  
   final int WINDOW_WIDTH = 200;
   final int WINDOW_HEIGHT = 700;
   
   private JPanel imagePanel;     // To hold the label
   private JPanel buttonPanel;    // To hold a button
   private JLabel imageLabel;     // To show an image
   private JButton button;        // To get an image

     
            


   // constructor
   public MITH_HowToPlay_GUI()
   {
     
           
              
            //Set the window's title
      
      setTitle("How To Play");
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
          
  
            
      buildPanel();
      add(panel);
      
      
      setVisible(true);
   }
      

private void buildPanel()
   {

      quitButton = new JButton("Quit");
      panel = new JPanel();
      
      panel.add(quitButton);
 
      
   }   
   
   
   
public static void main (String[]args)
   {
      
      MITH_HowToPlay_GUI tableForView = new MITH_HowToPlay_GUI();
   }
}




 
   
