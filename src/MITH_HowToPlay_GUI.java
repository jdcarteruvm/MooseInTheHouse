import java.net.URL;

import javax.swing.*;
import java.awt.Color;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;



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

   private JPanel  receiptPanel;
            


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
      
      
      JEditorPane editor = new JEditorPane("../resources/src/gameRules.html","Empty Room cards" + 
      "5 each of Kitchen, Bathroom, Living Room and Bedroom 20 Moose in the Room cards" + 
      "(same as above, but with Moose) 10 There’s a Moose in the House cards; 5 Door cards;" +
      "3 Moose Trap cards"); 
      editor.setEditable(false);
      JScrollPane pane = new JScrollPane(editor);
      JFrame f = new JFrame("gameRules.html");
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.getContentPane().add(pane);
      f.setSize(800, 600);
      f.setVisible(true);
   

//       JFrame frame = new JFrame();
//       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// 
//       JEditorPane editorPane = new JEditorPane();
// 
//       editorPane.setPage(new URL("http://www.java2s.com"));
// 
//       frame.add(new JScrollPane(editorPane));
// 
//       frame.setSize(300, 200);
//       frame.setVisible(true);




//       receiptPanel = new JPanel();
//       receiptPanel.setVisible(true);
//       receiptPanel.setBackground(new Color(250,251,253)); 
//       try {
//           JEditorPane htmlPane = new JEditorPane();
//           htmlPane.setPage(new URL("../resources/src/gameRules.html"));
//           htmlPane.setEditable(false);
//           receiptPanel.add(new JScrollPane(htmlPane));
//        } catch(IOException ioe) {
//          System.err.println("Error displaying file");
//        }     
//          
//       
 
   
 
 
 
 
 
 
      
   }   
   
   
   
public static void main (String[]args)throws Exception
   {
      
      MITH_HowToPlay_GUI tableForView = new MITH_HowToPlay_GUI();
   }
}




 
   
