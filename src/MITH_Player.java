
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

    // Initialize shared variables
	private String name; // player name
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
		my_house = new MITH_House();
		
		this.name = name;
		 
		preparePicture(pictureFilename);
		
		this.soundFilestem = soundFilestem;
		
	} // end MITH_Player(String, String, String)
	
	/*********************************************************
	 setGame(game) - registers the game the player is playing
	 in with the player
	 *********************************************************/
	public void setGame(MITH_Game g)
	{
		game = g;
	} // end setGame
	
	/*********************************************************
	 getName() - returns the player's name
	 *********************************************************/
	public String getName() {
		return name;
		
	} // end getName
	
	/*********************************************************
	 numMoose() - returns the number of moose the player
	 is holding in their hand
	 *********************************************************/
	public int numMoose() {
		return my_hand.numMoose();
	} // end numMoose
	
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
	} // end preparePicture


	/*********************************************************
	 getHouse() - returns a reference to the player's house
	 *********************************************************/
	public MITH_House getHouse() {
		
		return my_house;
		
	} // end getHouse
	
	/*********************************************************
	 getHand() - returns a reference to the player's hand
	 *********************************************************/
	public MITH_Hand getHand() {
		return my_hand;
	}
	
	
	/*********************************************************
	 () - attempts to play a card from player's hand
	 *********************************************************/
//	public MITH_Move playACard() {
//		if	


	/********************************************************* 
	 playedMoose() - notifies the player that a moose was
	 recently played on their house 
	 *********************************************************/
	public void playedMoose(MITH_Move move) {
		
		// there should be some sort of ai logic added to this
		// to determine if the player wants to play a trap card
		// or not
		if(my_hand.hasCardType(MITH_Card.TRAP)) {
			MITH_Card trapCard = my_hand.removeCard(MITH_Card.TRAP, MITH_Card.NON);
			MITH_Move mymove = new MITH_Move();
			mymove.player = this;
			mymove.house = move.house;
			mymove.roomslot = move.roomslot;
			mymove.card = trapCard;
			
			if(game.playTrap(move)) {
				drawCard();
			}
			else {
				my_hand.addCard(trapCard);
			}
				
		}
	} // end playedMoose
	
	
	/********************************************************* 
	 drawCard() - attempts to draw a card from the game deck
	 and add it to this player's hand
	 *********************************************************/
	public void drawCard() {	
		if(my_hand.getSize() <= MITH_Game.MAXCARDSINHAND) // <= because a hand can temporarily have more than 5 cards (draw first, then play)
		{
			MITH_Card card = game.drawFromDeck();
			if(card != null) {
				my_hand.addCard(card);
			}	
		}
	} // end drawCard
	
	/*********************************************************
	 giveCard() - attempts to give a card to a player for 
	 their hand
	 **********((*********************************************/
	public void giveCard(MITH_Card card) {
		if(my_hand.getSize() < MITH_Game.MAXCARDSINHAND)
		{
			if(card != null) {
				my_hand.addCard(card);
			}
		}
	} // end giveCard
	
    /*********************************************************
	 addToHouse() - adds the card to player's house
	 **********((*********************************************/
    public void addToHouse(MITH_Card card){
        my_house.addCard(card);
    }
	
	/*********************************************************
	 handToString() - utility function to export toString 
	 method on this player's hand
	 *********************************************************/
	public String handToString() {
		return my_hand.toString();
	} // end handToString	
	
	
	/*********************************************************
	 main - testing and verification of the player class
	 *********************************************************/
	public static void main(String[] args) {
		
		MITH_Player player1 = new MITH_Player("Jonathan Carter", "jcarter.png", "jcarter");
		MITH_Game game = new MITH_Game();
		
		player1.setGame(game);
		for(int i = 0; i < 5; i++)
			player1.drawCard();
		
		System.out.println(player1.my_hand.toString());
		
		System.out.println(player1.getName() + " has " + player1.numMoose() + " moose in his or her hand.");
		
	} // end main
		
			
} // end MITH_Player