/*******************************************************************
 * Moose In The House - CS205 Final Project
 * Ian Benson, Jonathan Carter, Joey Palchak, Rachel Temple Cj Zhang
 *
 * MITH_HowToPlay_GUI - GUI class for creating a window, presenting
 *                      the game's rules & guidelines for playing
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

public class MITH_HowToPlay_GUI extends JFrame 
{


   public static void createFrame() 
   {
   JEditorPane editorPane = new JEditorPane();
   editorPane.setEditable(false);

   java.net.URL helpURL = MITH_HowToPlay_GUI.class.getResource("GameRulesV1.1.html");

   if (helpURL != null) {
        try {
            editorPane.setPage(helpURL);
        } catch (IOException e) {
            System.err.println("Attempted to read a bad URL: " + helpURL);
        }
    } else {
        System.err.println("Couldn't find file: GameRulesV1.1.html");
    }

    JScrollPane editorScrollPane = new JScrollPane(editorPane);

    editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    editorScrollPane.setPreferredSize(new Dimension(600, 900));
    editorScrollPane.setMinimumSize(new Dimension(10, 10));

  
    JFrame f = new JFrame();

    f.setTitle("How To Play");
    f.setSize(910, 850);
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
     createFrame();
    
    
}
}