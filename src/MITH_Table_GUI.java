import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

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
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
      JPanel table = new JPanel();
      table.setLayout(new BoxLayout(table, BoxLayout.X_AXIS));
      
      
      JPanel menuPanel = new JPanel();
      JPanel leftPanel = new JPanel();
      
      leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
      
      //buildImagePanel();
      //buildButtonPanel();
      
      JPanel playArea = new PlayPanel();
      JPanel handArea = new JPanel();
      
      leftPanel.add(playArea);
      leftPanel.add(handArea);
      
      table.add(leftPanel);
      table.add(menuPanel);
       
              
      ImageIcon mooseLogo;      //Set the window's title
      
      // ImageIcon woods1;
//       ImageIcon woods2;
//       ImageIcon woods3;
//       ImageIcon woods4;
//      
//       
//       woods1 = new ImageIcon("../resources/images/woods1.jpeg");
//       woods2 = new ImageIcon("../resources/images/woods2.jpeg");
//       woods3 = new ImageIcon("../resources/images/woods3.jpeg");
//       woods4 = new ImageIcon("../resources/images/woods4.jpeg");
     
      
      setTitle("MOOSE IN THE HOUSE");
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
           
      
      getContentPane().setBackground(Color.BLUE);
      
      leftPanel.setPreferredSize(new Dimension(700,600));
      //leftPanel.setOpaque(true);
      leftPanel.setBackground(Color.BLACK);
      
      JLabel mooseLabel = new JLabel();
      mooseLogo = new ImageIcon("../resources/images/MITH_Logo_150px.png");
      mooseLabel.setIcon(mooseLogo);
      menuPanel.add(mooseLabel); 
      menuPanel.setSize(150,400);
      
   
      
      // creates button, add images
      
         JLabel buttonLabel = new JLabel();
//       woods1 = new ImageIcon("../resources/images/woods1.jpeg");
//       buttonLabel.setIcon(woods1);
         helpButton = new JButton("Help");
         helpButton.setContentAreaFilled(true);
         helpButton.setFocusPainted(true);
         helpButton.setBorderPainted(true);

 //        JPanel buttonPanel = new JPanel();
//        buttonPanel.setSize(150,400);
//       
       
     
      
      howToPlayButton = new JButton("How To Play");
      //howToPlayButton.setIcon("woods2");
      
      optionsButton = new JButton("Options");
      //button.setIcon("woods3");
      
      quitButton = new JButton("Quit");
      //button.setIcon("woods");
      
      
      menuPanel.add(helpButton);
      menuPanel.add(howToPlayButton);
      menuPanel.add(optionsButton);
      menuPanel.add(quitButton);
      menuPanel.setLayout(new GridLayout(8, 3));
      menuPanel.setBackground(new Color(25,150,50));
      
      optionsButton.addActionListener(new ButtonListener());
      
      helpButton.addActionListener(new ButtonListener());
      
      howToPlayButton.addActionListener(new ButtonListener());
   
      quitButton.addActionListener(new ButtonListener());
      
      JLabel label2 = new JLabel("Play Area");
      playArea.add(label2);
      
      Border loweredBevel = BorderFactory.createLoweredSoftBevelBorder();
      Border playBorder = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10), loweredBevel);
      
      playArea.setBorder(playBorder);
      playArea.setPreferredSize(new Dimension(700, 450));
      playArea.setBackground(new Color(50,150, 50));
      
      JLabel label3 = new JLabel("Hand Area");
      handArea.add(label3);
      handArea.setPreferredSize(new Dimension(700, 150));
      handArea.setBackground(new Color(50,150,50));
      add(table);
      setVisible(true);
      
   }
   
   
   private class PlayPanel extends JPanel {
    	
      private BufferedImage mith;
      private BufferedImage bed;
      private BufferedImage bedMoose;
   	
      public PlayPanel() {
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
      protected void paintComponent(Graphics g) {
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

















