/*******************************************************************
 * Moose In The House - CS205 Final Project
 * Ian Benson, Jonathan Carter, Joey Palchak, Rachel Temple Cj Zhang
 *
 * MITH_Table_GUI - primary GUI class, which creates the game's 
 *                  window and allows the user to play the game by
 *                  interacting with its interface.
 * This class will communicate with the MITH_Game class to run the
 * actual game.        
 *******************************************************************/

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
   final int WINDOW_WIDTH = 868;
   final int WINDOW_HEIGHT= 620;
   
   final int MENU_WIDTH = 202; 
   final int MENU_HEIGHT= 300;
   
   
   
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
      Font myFont = new Font("Georgia", Font.BOLD, 8);
      Font newFont = myFont.deriveFont(15F);
   
   
   
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
      
 
      
      //leftPanel.add(playArea);
      //leftPanel.add(handArea);
       
      //ADD THE GAME
        MITH_Game game = new MITH_Game();
		MITH_Game_Board board = new MITH_Game_Board(4);
		
		
		MITH_Player player1 = new MITH_Player("Jon", "jcarter.png", "jcarter");
		MITH_Player player2 = new MITH_Player("Comp", "generic-female.png", "random");
		MITH_Player player3 = new MITH_Player("Batman", "batman.png", "batman");
		MITH_Player player4 = new MITH_Player("Loser", "generic-male.png", "random");
		
		board.setGame(game);
		game.setGUI(board);
		
		board.setPlayer(player1);
		
    game.addPlayer(player1);
    game.addPlayer(player2);
    game.addPlayer(player3);
    game.addPlayer(player4);
       
      leftPanel.add(board);
      rightPanel.add(menuPanel);
      
       
      table.add(leftPanel);
      table.add(rightPanel);
      //table.add(menuPanel);
       
              
      ImageIcon mooseLogo;      //Set the window's title
      
 
      
      setTitle("MOOSE IN THE HOUSE");
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
           
      
      //getContentPane().setBackground(Color.RED);
      
      leftPanel.setPreferredSize(new Dimension(900,600));
      //leftPanel.setOpaque(true);
      leftPanel.setBackground(Color.GREEN);
      
      rightPanel.setPreferredSize(new Dimension(170,300));
      //leftPanel.setOpaque(true);
      rightPanel.setBackground(Color.GREEN);
      
       //MOOSE LOGO
       JLabel mooseLabel = new JLabel();
       mooseLogo = new ImageIcon("../resources/images/MITH_Logo_150px.png");
       mooseLabel.setIcon(mooseLogo);
       
      menuPanel.setSize(MENU_WIDTH, MENU_HEIGHT);
      menuPanel.setBackground(Color.GREEN);      
   
      
      // creates button, add images
      
         JLabel buttonLabel = new JLabel();
//       woods1 = new ImageIcon("../resources/images/woods1.jpeg");
//       buttonLabel.setIcon(woods1);
         helpButton = new JButton("Help");
         //helpButton.setContentAreaFilled(true);
         //helpButton.setFocusPainted(true);
         //helpButton.setBackground(Color.BLACK);
         //helpButton.setOpaque(true);
         //helpButton.setBorderPainted(false);
         try {
           //helpButton.setIcon(new ImageIcon("../resources/images/woods3.jpeg"));
           helpButton.setHorizontalTextPosition(SwingConstants.CENTER);
         } catch (Exception ex) {
            System.out.println(ex.getMessage());
         } // end IO try/catch


 //        JPanel buttonPanel = new JPanel();
//     buttonPanel.setSize(150,400);
//       
       
     
      
      howToPlayButton = new JButton("Rules");
      //howToPlayButton.setPreferredSize(new Dimension(30, 80));
      //howToPlayButton.setBackground(new Color(25,150,50));
      //howToPlayButton.setBackground(Color.BLACK);
      //howToPlayButton.setOpaque(true);
      //howToPlayButton.setBorderPainted(false);
      try {
           //howToPlayButton.setIcon(new ImageIcon("../resources/images/woods5.jpeg"));
           howToPlayButton.setHorizontalTextPosition(SwingConstants.CENTER);
         } catch (Exception ex) {
            System.out.println(ex.getMessage());
         } // end IO try/catch

      
      //howToPlayButton.setIcon("woods2");
      
      optionsButton = new JButton("Statistics");
      //optionsButton.setBackground(Color.BLACK);
      //optionsButton.setOpaque(true);
      //optionsButton.setBorderPainted(false);
         try {
//           Image img = ImageIO.read(getClass().getResource("../resources/images/woods3.jpeg"));
           //optionsButton.setIcon(new ImageIcon("../resources/images/woods4.jpeg"));
           optionsButton.setHorizontalTextPosition(SwingConstants.CENTER);
         } catch (Exception ex) {
            System.out.println(ex.getMessage());
         } // end IO try/catch

      
  
      //button.setIcon("woods3");
      
      quitButton = new JButton("Quit");
      //quitButton.setBackground(Color.BLACK);
      //quitButton.setOpaque(true);
      //quitButton.setBorderPainted(false);
         try {
              //quitButton.setIcon(new ImageIcon("../resources/images/woods1.jpeg"));
              quitButton.setHorizontalTextPosition(SwingConstants.CENTER);
            } catch (Exception ex) {
               System.out.println(ex.getMessage());
            } // end IO try/catch
         
     
      
      Border loweredBevel = BorderFactory.createLoweredSoftBevelBorder();
      //Border menuBorder = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5), loweredBevel);
      Border menuBorder = BorderFactory.createEmptyBorder(10, 5, 10, 5);
      
       
      menuPanel.add(mooseLabel); 
      menuPanel.add(new JPanel()).setVisible(false);
      menuPanel.add(helpButton);
      menuPanel.add(howToPlayButton);
      menuPanel.add(optionsButton);
      menuPanel.add(quitButton);
      menuPanel.setLayout(new GridLayout(15,1)); // GRID LAYOUT
      menuPanel.setBackground(new Color(0,128,0)); // MENU BACKGROUND COLOR
      menuPanel.setBorder(menuBorder);
      //menuPanel.setPreferredSize(new Dimension(35, 300));
    
      
      optionsButton.addActionListener(new ButtonListener());
      //optionsButton.setForeground(Color.WHITE);
      optionsButton.setFont(newFont);
      optionsButton.setVisible(true);
      
      
      helpButton.addActionListener(new ButtonListener());
      //helpButton.setForeground(Color.WHITE);
      helpButton.setFont(newFont);
      helpButton.setVisible(true);
      
      
      howToPlayButton.addActionListener(new ButtonListener());     
      howToPlayButton.setFont(newFont);
      //howToPlayButton.setForeground(Color.WHITE);
      howToPlayButton.setVisible(true);
      
      
   
      quitButton.addActionListener(new ButtonListener());
      //quitButton.setBackground(Color.BLACK);
      //quitButton.setForeground(Color.WHITE);
      quitButton.setFont(newFont);
      quitButton.setVisible(true);
      
      //PLAY AREA
      //*******************************************************
      Border loweredBevel2 = BorderFactory.createLoweredSoftBevelBorder();
      Border playBorder = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10), loweredBevel2);
      playArea.setBorder(playBorder);
      playArea.setPreferredSize(new Dimension(1200, 450));
      //playArea.setBackground(new Color(50,150, 50));
      
      JLabel label2 = new JLabel("Play Area");
      playArea.add(label2);
      
           
      playArea.setBorder(playBorder);
      playArea.setPreferredSize(new Dimension(1200, 450));
      playArea.setBackground(new Color(50,150, 50));
      //playArea.setBackground(Color.BLACK);
      //label2.setForeground(Color.WHITE);
      
      
      
      //playArea.add(label3);
      
      JLabel label3 = new JLabel("Hand Area");
      handArea.add(label3);
      label3.setForeground(Color.WHITE);
      handArea.setPreferredSize(new Dimension(1200, 150));
      handArea.setBackground(new Color(50,150,50));
      //handArea.setBackground(Color.BLACK);
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
            GameStats_GUI satsWindow = new GameStats_GUI();
         }      
         if(e.getSource() == howToPlayButton)
         {  
            MITH_HowToPlay_GUI howToPlayWindow = new MITH_HowToPlay_GUI();
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