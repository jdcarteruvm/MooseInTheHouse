/*******************************************************************
 * Moose In The House - CS205 Final Project
 * Ian Benson, Jonathan Carter, Joey Palchak, Rachel Temple Cj Zhang
 *
 * MITH_Help_GUI - GUI class for creating a window, presenting
 *                 guidelines on how to use the program interface
 *******************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import javax.swing.border.Border;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.image.BufferedImage;

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

     
    public MITH_Help_GUI(){
        createFrame();
    }
    
    public static void createFrame() 
   {
   JEditorPane editorPane = new JEditorPane();
   editorPane.setEditable(false);

   java.net.URL helpURL = MITH_Help_GUI.class.getResource("HowToPlay.html");

   if (helpURL != null) {
        try {
            editorPane.setPage(helpURL);
        } catch (IOException e) {
            System.err.println("Attempted to read a bad URL: " + helpURL);
        }
    } else {
        System.err.println("Couldn't find file: HowToPlay.html");
    }

    JScrollPane editorScrollPane = new JScrollPane(editorPane);

    editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    editorScrollPane.setPreferredSize(new Dimension(600, 900));
    editorScrollPane.setMinimumSize(new Dimension(10, 10));

  
    JFrame f = new JFrame();

    f.setTitle("A Helping Hand");
    f.setSize(800, 700);
    f.setLocationRelativeTo(null);

    f.getContentPane().add(editorScrollPane, BorderLayout.NORTH);
    Border loweredBevel = BorderFactory.createLoweredSoftBevelBorder();
    //Border howToPlayBorder = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20),loweredBevel);
    Border howToPlayBorder = BorderFactory.createEmptyBorder(20,20,20,20);
    f.setBackground(Color.BLACK);
    //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //menuPanel.setBorder(menuBorder);
    editorPane.setBorder(howToPlayBorder);
  
    f.setResizable(true);
    f.setVisible(true);
}

public static void main(String[] args) 
{
     MITH_Help_GUI window = new MITH_Help_GUI();
    
    
}
}