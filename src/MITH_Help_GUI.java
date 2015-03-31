import javax.swing.*;
import java.awt.Color;



public class MITH_Help_GUI extends JFrame
{
   private JPanel panel;
   private JButton helpButton;
   

   final int WINDOW_WIDTH = 200;
   final int WINDOW_HEIGHT = 200;
   
   private JPanel imagePanel;     // To hold the label
   private JPanel buttonPanel;    // To hold a button
   private JLabel imageLabel;     // To show an image
   private JButton button;        // To get an image
   private JLabel helpLabel;

     
            


   // constructor
   public MITH_Help_GUI()
   {
     
           
              
            //Set the window's title
      
      setTitle("HELP");
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
      //JLabel helpLabel = new JLabel("I am useless and can not help. Sorry");
      //panel.add(helpLabel);
  
            
      buildPanel();
      add(panel);
      
      
      setVisible(true);
   }
      

private void buildPanel()
   {

      panel = new JPanel();
      
  
      
      
   }   
   
   
   
public static void main (String[]args)
   {
      
      MITH_Help_GUI tableForView = new MITH_Help_GUI();
   }
}