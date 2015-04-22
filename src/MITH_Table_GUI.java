import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;


//menuArea needs a prefred size


public class MITH_Table_GUI extends JFrame 
{
   private JPanel table;
   final int WINDOW_WIDTH = 1100;
   final int WINDOW_HEIGHT =600;
   
   final int MENU_WIDTH = 120; 
   final int MENU_HEIGHT =3000;
   
   
   
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
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Font myFont = new Font("Serif", Font.BOLD, 12);
      Font newFont = myFont.deriveFont(25F);
   
   
   
      JPanel table = new JPanel();
      table.setLayout(new BoxLayout(table, BoxLayout.X_AXIS));
      
      

      JPanel leftPanel = new JPanel();
      leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
      
      JPanel rightPanel = new JPanel();
      rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
      

      //JPanel menuPanel = new JPanel();
      //menuPanel.setLayout(new BoxLayout(table, BoxLayout.X_AXIS));
      
      
      JPanel playArea = new PlayPanel();
      JPanel handArea = new JPanel();
      JPanel menuPanel = new JPanel();
      
 
      
      leftPanel.add(playArea);
      leftPanel.add(handArea);
      rightPanel.add(menuPanel);
      
      table.add(leftPanel);
     
      table.add(rightPanel);
      //table.add(menuPanel);
       
              
      ImageIcon mooseLogo;      //Set the window's title
      
 
      
      setTitle("MOOSE IN THE HOUSE");
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
           
      
      getContentPane().setBackground(Color.RED);
      
      leftPanel.setPreferredSize(new Dimension(500,400));
      //leftPanel.setOpaque(true);
      leftPanel.setBackground(Color.BLACK);
      
      rightPanel.setPreferredSize(new Dimension(100,300));
      //leftPanel.setOpaque(true);
      rightPanel.setBackground(Color.BLACK);
      
//       JLabel mooseLabel = new JLabel();
//       mooseLogo = new ImageIcon("../resources/images/MITH_Logo_150px.png");
//       mooseLabel.setIcon(mooseLogo);
//       menuPanel.add(mooseLabel); 
      menuPanel.setSize(MENU_WIDTH, MENU_HEIGHT);
      menuPanel.setBackground(Color.BLACK);
      
   
      
      // creates button, add images
      
         JLabel buttonLabel = new JLabel();
//       woods1 = new ImageIcon("../resources/images/woods1.jpeg");
//       buttonLabel.setIcon(woods1);
         helpButton = new JButton("Help");
         helpButton.setContentAreaFilled(true);
         helpButton.setFocusPainted(true);
         helpButton.setBackground(Color.BLACK);
         helpButton.setOpaque(true);
         helpButton.setBorderPainted(false);
         try {
           helpButton.setIcon(new ImageIcon("../resources/images/woods3.jpeg"));
           helpButton.setHorizontalTextPosition(SwingConstants.CENTER);
         } catch (Exception ex) {
            System.out.println(ex.getMessage());
         } // end IO try/catch


 //        JPanel buttonPanel = new JPanel();
    //     buttonPanel.setSize(150,400);
//       
       
     
      
      howToPlayButton = new JButton("How To Play");
      //howToPlayButton.setPreferredSize(new Dimension(30, 80));
      //howToPlayButton.setBackground(new Color(25,150,50));
      howToPlayButton.setBackground(Color.BLACK);
      howToPlayButton.setOpaque(true);
      howToPlayButton.setBorderPainted(false);
      try {
           howToPlayButton.setIcon(new ImageIcon("../resources/images/woods5.jpeg"));
           howToPlayButton.setHorizontalTextPosition(SwingConstants.CENTER);
         } catch (Exception ex) {
            System.out.println(ex.getMessage());
         } // end IO try/catch

      
      //howToPlayButton.setIcon("woods2");
      
      optionsButton = new JButton("Options");
      optionsButton.setBackground(Color.BLACK);
      optionsButton.setOpaque(true);
      optionsButton.setBorderPainted(false);
         try {
//           Image img = ImageIO.read(getClass().getResource("../resources/images/woods3.jpeg"));
           optionsButton.setIcon(new ImageIcon("../resources/images/woods4.jpeg"));
           optionsButton.setHorizontalTextPosition(SwingConstants.CENTER);
         } catch (Exception ex) {
            System.out.println(ex.getMessage());
         } // end IO try/catch

      
  
      //button.setIcon("woods3");
      
      quitButton = new JButton("Quit");
      quitButton.setBackground(Color.BLACK);
      quitButton.setOpaque(true);
      quitButton.setBorderPainted(false);
         try {
              quitButton.setIcon(new ImageIcon("../resources/images/woods1.jpeg"));
              quitButton.setHorizontalTextPosition(SwingConstants.CENTER);
            } catch (Exception ex) {
               System.out.println(ex.getMessage());
            } // end IO try/catch
         
     
      
      Border loweredBevel = BorderFactory.createLoweredSoftBevelBorder();
      Border menuBorder = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10), loweredBevel);
      
      menuPanel.add(helpButton);
      menuPanel.add(howToPlayButton);
      menuPanel.add(optionsButton);
      menuPanel.add(quitButton);
      //menuPanel.setLayout(new GridLayout(3,1));
      menuPanel.setBackground(new Color(1,1,1));
      menuPanel.setBorder(menuBorder);
      menuPanel.setPreferredSize(new Dimension(120, 45));
      

      
      
      
      Border loweredBevel2 = BorderFactory.createLoweredSoftBevelBorder();
      Border playBorder = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10), loweredBevel2);
      playArea.setBorder(playBorder);
      playArea.setPreferredSize(new Dimension(1200, 450));
      playArea.setBackground(new Color(50,150, 50));
      
      
      
      
      optionsButton.addActionListener(new ButtonListener());
      optionsButton.setForeground(Color.WHITE);
      optionsButton.setFont(newFont);
      optionsButton.setVisible(true);
      
      
      helpButton.addActionListener(new ButtonListener());
      helpButton.setForeground(Color.WHITE);
      helpButton.setFont(newFont);
      helpButton.setVisible(true);
      
      
      howToPlayButton.addActionListener(new ButtonListener());     
      howToPlayButton.setFont(newFont);
      howToPlayButton.setForeground(Color.WHITE);
      howToPlayButton.setVisible(true);
      
      
   
      quitButton.addActionListener(new ButtonListener());
      quitButton.setBackground(Color.BLACK);
      quitButton.setForeground(Color.WHITE);
      quitButton.setFont(newFont);
      quitButton.setVisible(true);
      
      
      JLabel label2 = new JLabel("Play Area");
      playArea.add(label2);
      
           
      playArea.setBorder(playBorder);
      playArea.setPreferredSize(new Dimension(1200, 450));
      playArea.setBackground(new Color(50,150, 50));
      playArea.setBackground(Color.BLACK);
      label2.setForeground(Color.WHITE);
      
      
      
      //playArea.add(label3);
      
      JLabel label3 = new JLabel("Hand Area");
      handArea.add(label3);
      label3.setForeground(Color.WHITE);
      handArea.setPreferredSize(new Dimension(1200, 150));
      handArea.setBackground(new Color(50,150,50));
      handArea.setBackground(Color.BLACK);
      add(table);
      setVisible(true);  
      
   }
   
  //  /**     */
//    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
//                        
//                        
                       
   
   
   
   private class PlayPanel extends JPanel 
   {
    	
      private BufferedImage mith;
      private BufferedImage bed;
      private BufferedImage bedMoose;
   	
      public PlayPanel() 
      {
         try {
            mith = ImageIO.read(new File("../resources/images/30dpi/moose-in-the-house.png"));
            bed = ImageIO.read(new File("../resources/images/30dpi/bed.png"));
            bedMoose = ImageIO.read(new File("../resources/images/30dpi/bed-moose.png"));
         } 
         catch (IOException ex) {
            System.out.println(ex.getMessage());
         } // end IO try/catch
      }
   	
      @Override
      protected void paintComponent(Graphics g) 
      {
         super.paintComponent(g);
         g.drawImage(mith, 100, 70, null);
         g.drawImage(bed, 200, 70, null);
         g.drawImage(bedMoose, 210, 65, null);
      }
  
   
   }
 
   
   
   private class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if(e.getSource() == optionsButton)
         {
            MITH_Options_GUI optionsWindow = new MITH_Options_GUI();
         }      
         if(e.getSource() == howToPlayButton)
         {  
            MITH_HowToPlay_GUI howToPlayWindow = new MITH_HowToPlay_GUI();
            howToPlayWindow.createFrame();
         }
         if(e.getSource() == helpButton)
         {
            MITH_Help_GUI helpWindow = new MITH_Help_GUI();
         }
         if(e.getSource() == quitButton)
         {
            System.exit(0);
         }
         
      }
   }

    

   

   
public static void main(String[] args) 
{
   MITH_Table_GUI gameGui = new MITH_Table_GUI();
}  
}


 


















