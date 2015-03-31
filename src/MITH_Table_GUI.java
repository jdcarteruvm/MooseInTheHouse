import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class MITH_Table_GUI extends JFrame
{
   private JPanel table;
   final int WINDOW_WIDTH = 800;
   final int WINDOW_HEIGHT = 600;
   
   
   private JButton helpButton;
   private JButton howToPlayButton; 
   private JButton optionsButton;
   private JButton quitButton;  

   
   private JPanel imagePanel;     // To hold the label
   private JPanel buttonPanel;    // To hold a button
   private JLabel imageLabel;     // To show an image
   private JButton button;        // To get an image

     
   
   
   public MITH_Table_GUI() 
   {
      JPanel table = new JPanel();
      table.setLayout(new BoxLayout(table, BoxLayout.X_AXIS));
      
      
      JPanel menuPanel = new JPanel();
      JPanel leftPanel = new JPanel();
      
      leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
      
      
      
      JPanel playArea = new JPanel();
      JPanel handArea = new JPanel();
      
      leftPanel.add(playArea);
      leftPanel.add(handArea);
      
      table.add(leftPanel);
      table.add(menuPanel);
       
              
      imageIcon mooseLogo;      //Set the window's title
      
      setTitle("MOOSE IN THE HOUSE");
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
           
      
      getContentPane().setBackground(Color.BLUE);
      
      leftPanel.setPreferredSize(new Dimension(700,600));
      //leftPanel.setOpaque(true);
      leftPanel.setBackground(Color.BLACK);
      
      JLabel label = new JLabel("Menu Pane");
      mooseLogo = new ImageIcon("HeadOfIan.JPG");
      menuPanel.add(label); 
      menuPanel.setSize(150,400);
      helpButton = new JButton("Help");
      howToPlayButton = new JButton("How To Play");
      optionsButton = new JButton("Options");
      quitButton = new JButton("Quit");
      
      
      menuPanel.add(helpButton);
      menuPanel.add(howToPlayButton);
      menuPanel.add(quitButton);
      menuPanel.add(optionsButton);
      menuPanel.setLayout(new GridLayout(8, 3));
      
      
      
      JLabel label2 = new JLabel("Play Area");
      playArea.add(label2);
      playArea.setPreferredSize(new Dimension(700, 450));
      playArea.setBackground(Color.GREEN);
      
      JLabel label3 = new JLabel("Hand Area");
      handArea.add(label3);
      handArea.setPreferredSize(new Dimension(700, 150));
      handArea.setBackground(Color.YELLOW);
      add(table);
      setVisible(true);
      
   }
   public static void main(String[] args)
   {
      MITH_Table_GUI gameGui = new MITH_Table_GUI();
   }  
}

















