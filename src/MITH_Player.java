
/*******************************************************************
 * Moose In The House - CS205 Final Project
 * Ian Benson, Jonathan Carter, Joey Palchak, Rachel Temple Cj Zhang
 *
 * MITH_Player - A base player class for Moose In The House
 *******************************************************************/

import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

class MITH_Player {

	private String name; // i am adding a comment
	
	private BufferedImage picture; // filename for a image for this player to be utilized by a GUI
	
	private MITH_Hand hand; // the player's hand
	
	private MITH_House my_house; // the player's house cards
	
	private String soundFilestem; // filename root for the player's soundfile
	
	
	/*********************************************************
	 MITH_Player(name) - takes name and sets up picture based
	 on the name of the player
	 *********************************************************/
	
	public MITH_Player(String name, String pictureFilename, String soundFilestem) {
		
		hand = new MITH_Hand();
		
		this.name = name;
		 
		preparePicture(pictureFilename);
		
		this.soundFilestem = soundFilestem;
			
	}
	

	
	/*********************************************************
	 preparePicture - opens the image specified by filename
	 and prepares the buffered image 
	 *********************************************************/
	private void preparePicture(String filename) {
		String path = "..\\..\\resources\\images\\";
		
		try {
			picture = ImageIO.read(new File(path + filename));
		} catch (IOException e) {
			System.out.println("Error opening player image: " + path + filename + ": " + e.getMessage());
		}
	}
		
		
		
	/*********************************************************
	 main - testing and verification of the player class
	 *********************************************************/
	public static void main(String[] args) {
		
		MITH_Player player1 = new MITH_Player("Jonathan Carter", "jcarter.png", "jcarter");
		
	}
		
			
}