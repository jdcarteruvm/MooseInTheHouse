/*********************************
 * CS 205 - Software Engineering *
 * Moose in the House            *
 * GameStats_GUI                 *
 * Authors: Joey                 *
 *********************************/

/** ####################################################### **
 GUI class for creating and updating a statistics window.
 
 For now, a method creates multiple labels, incorporating 
 content from an ArrayList using a for-loop. 
    > Could have the method use an arraylist as a param,
      which can be the list of statistics (read from a 
      text file). 
    > At the moment, it's all done within createStats()
 ** ###################################################### **/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

 public class GameStats_GUI extends JFrame{
     
     //public static JFrame frame;
     private JLabel textLabel;
     private JPanel content;
     private SpringLayout layout;
     private JButton button;
     
     
     private String text;
     
     /*************************************************
     * GameStats_GUI is the constructor, creating the *
     * window frame and content pane in which the     *
     * statistics will be displayed.                  *
     **************************************************/
     public GameStats_GUI(){
         
         //Initial window attributes
         setTitle("Statistics");
         //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
         //Content Pane
         content = new JPanel();
         layout = new SpringLayout();
         content.setLayout(layout);
         //content.setLayout(new FlowLayout());
         
         //CREATE THE CONTENT AND ADD IT TO
         //THE CONTENT PANE
         createStats();
         
         //Set the window's attributes.
         setContentPane(content);
         setLocationRelativeTo(null);
         setSize(300, 250); 
         setVisible(true);
         
     }//End GameStats
     
     /*************************************************
     * createStats() makes use of two for-loops,      *
     * creating the labels for stat titles and the    *
     * statistics themselves, which are pulled from   *
     * the stats.txt file with use of Scanner.        *
     **************************************************/
     public void createStats(){
        
         //List of specific statistic titles.
         String[] stats = {"# Wins: ","Games Played:","Avg Points: ","# Players: "};
         
         //Create JLabel array list for the stat titles (left side)
         ArrayList<JLabel> titleLabels = new ArrayList<JLabel>();
         //Create JLabel array list for the stats themselves (right side)
         ArrayList<JLabel> statLabels = new ArrayList<JLabel>();
         //Create String array list to pull the stats from the txt file
         ArrayList<String> pulledStats = new ArrayList<String>();
         
         //USE SCANNER TO READ STAT.TXT AND PULL EACH LINE OF STATS;
         //ADD EACH LINE OF STATS TO THE ARRAY LIST
         try{
            Scanner inputFile = new Scanner(new File("stats.txt"));
            while(inputFile.hasNext()){ //while there's stuff to read
                pulledStats.add(inputFile.next()); //pull numbers and add to arraylist
            }//while
         }catch(FileNotFoundException fnfe){ 
             System.out.println(fnfe.getMessage());
         }//catch
         
         //CREATE LABELS & LAYOUT FOR THE STAT TITLES (left side)
         for (int i=0; i < 4; i++){
             JLabel label = new JLabel(stats[i]);
             titleLabels.add(label);
             content.add(titleLabels.get(i));
             //Layout in content pane
             layout.putConstraint(SpringLayout.EAST, titleLabels.get(i), 140, SpringLayout.WEST, content);
             layout.putConstraint(SpringLayout.NORTH, titleLabels.get(i), 20 + (i*50), SpringLayout.NORTH, content);
         }//for
         
         //CREATE LABELS & LAYOUT FOR THE ACTUAL STATISTICS (right side)
         for (int i=0; i < 4; i++){
             JLabel label = new JLabel(pulledStats.get(i));
             statLabels.add(label);
             content.add(statLabels.get(i));
             //Layout in content pane
             layout.putConstraint(SpringLayout.WEST, statLabels.get(i), 150, SpringLayout.WEST, content);
             layout.putConstraint(SpringLayout.NORTH, statLabels.get(i), 20 + (i*50), SpringLayout.NORTH, content);
         }//for
         
     }//End createStats
     
     public static void main(String[] args){
         
         GameStats_GUI window = new GameStats_GUI();
         
     }//End main
 
 }//End CLASS