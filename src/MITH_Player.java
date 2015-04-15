
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
	private MITH_Hand my_hand; // the player's hand
	private MITH_House my_house; // the player's house cards
	private String soundFilestem; // filename root for the player's soundfile
	
	private MITH_Game game; // a reference to the game the player is playing in
	
	/*********************************************************
	 MITH_Player(name) - takes name and sets up picture based
	 on the name of the player
	 *********************************************************/
	
	public MITH_Player(String name, String pictureFilename, String soundFilestem) {
		
		my_hand = new MITH_Hand();
		
		this.name = name;
		 
		preparePicture(pictureFilename);
		
		this.soundFilestem = soundFilestem;
		
	}
	
	/*********************************************************
	 setGame(game) - registers the game the player is playing
	 in with the player
	 *********************************************************/
	public void setGame(MITH_Game g)
	{
		game = g;
	}
	
	/*********************************************************
	 getName() - returns the player's name
	 *********************************************************/
	public String getName() {
		return name;
	}
	
	/*********************************************************
	 numMoose() - returns the number of moose the player
	 is holding in their hand
	 *********************************************************/
	public int numMoose() {
		return my_hand.numMoose();
	}
	
	/*********************************************************
	 preparePicture - opens the image specified by filename
	 and prepares the buffered image 
	 *********************************************************/
	private void preparePicture(String filename) {
		String path = "..\\resources\\images\\players\\";
		
		try {
			picture = ImageIO.read(new File(path + filename));
		} catch (IOException e) {
			System.out.println("Error opening player image: " + path + filename + ": " + e.getMessage());
		}
	}


	/*********************************************************
	 getHouse() - returns a reference to the player's house
	 *********************************************************/
	public MITH_House getHouse() {
		
		return my_house;
		
	}		
		
	/********************************************************* 
	 playedMoose() - notifies the player that a moose was
	 recently played on their house
	 FCTVAL == true - I have a mooose trap and I just played 
	 *********************************************************/
	public void playedMoose() {
		
		// there should be some sort of ai logic added to this
		// to determine if the player wants to play a trap card
		// or not
		if(my_hand.hasCardType(MITH_Card.TRAP)) {
			game.playTrap();
			my_hand.removeCard(MITH_Card.TRAP, MITH_Card.NON);
			drawCard();
		}
	}
	
	
	/********************************************************* 
	 drawCard() - attempts to draw a card from the game deck
	 and add it to this player's hand
	 *********************************************************/
	public void drawCard() {	
		if(my_hand.getSize() <= 5)
		{
			MITH_Card card = game.drawFromDeck();
			if(card != null) {
				my_hand.addCard(card);
			}	
		}
	}
	
	
			
	/*********************************************************
	 main - testing and verification of the player class
	 *********************************************************/
	public static void main(String[] args) {
		
		MITH_Player player1 = new MITH_Player("Jonathan Carter", "jcarter.png", "jcarter");
		MITH_Game game = new MITH_Game();
		
		player1.setGame(game);
		for(int i = 0; i < 5; i++)
			player1.drawCard();
		
		player1.my_hand.print();
		
		System.out.println(player1.getName() + " has " + player1.numMoose() + " moose in his or her hand.");
		
	}
		
			
}