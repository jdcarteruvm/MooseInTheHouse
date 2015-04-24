/*******************************************************************
 * Moose In The House - CS205 Final Project
 * Ian Benson, Jonathan Carter, Joey Palchak, Rachel Temple Cj Zhang
 *
 * MITH_House- A class implementing each player's house cards for the
 *             table of play
 *******************************************************************/
import java.util.*;

public class MITH_Game {
	
	private MITH_Deck deck; 
	
	private ArrayList<MITH_Player> players;
	private ArrayList<MITH_House> houses;
  //private ArrayList<MITH_Card> discardPile;
	
	private int numPlayers;
	
	public static final int MAXPLAYERS = 4;
	public static final int MAXCARDSINHAND = 5;
	
	private MITH_Move lastmove; // keeps track of a move so we can be sure that player is allowed to play a trap
	
	private MITH_Game_Board GUI;
	
	
	/*********************************************************
	 MITH_Game() - default constructor, which creates a
	
	 *********************************************************/
	public MITH_Game() {
		deck = new MITH_Deck();
		deck.shuffle();
		
		players = new ArrayList<MITH_Player>();
		houses = new ArrayList<MITH_House>();
		
	} //end MITH_Game()
	
	/**********************************************************
	 setGUI() - registers the GUI that this game is going to 
	 work with
	 **********************************************************/
	public void setGUI(MITH_Game_Board board) {
		GUI = board;
	}
	
	public MITH_House getHouse(int playerID) {
		return houses.get(playerID);
	}
	
	
	/**********************************************************
	 makeMove - attempts to make the move described in move
	 if it's a valid move, the move is made and we return true
	 **********************************************************/
	public boolean makeMove(MITH_Move move){
		boolean validMove = false;
		
		switch(move.card.getType()) {
			case MITH_Card.DOOR: validMove = playDoor(move); break;
			case MITH_Card.MITH: validMove = playMITH(move); break;
			case MITH_Card.ROOMEMPTY: validMove = playRoomEmpty(move); break;
			case MITH_Card.ROOMMOOSE: validMove = playRoomMoose(move); break;
			case MITH_Card.TRAP: validMove = playTrap(move); break;
		}
		
		if(validMove) {
			GUI.update(move);
		}
		
		return validMove;
	}
		
	/**********************************************************
	 playTrap() - handles playing a trap card: makes sure that
	 it is a valid play for the current state of the game
	 FCTVAL == true => player's house just received a new moose
	 									 and no other player has started their 
	 									 move and player has passed on a trap
	 									 card
	 **********************************************************/
	public boolean playTrap(MITH_Move move) {
		System.out.println("Trap Played");
		
		if(lastmove == null) {
			return false;
		}
		
		if(move.roomslot >= 0 && 
			 move.card.getType() == MITH_Card.TRAP &&
			 lastmove.player != move.player &&
			 houses.get(move.house) == move.player.getHouse() &&
			 move.card.getType() == MITH_Card.ROOMMOOSE) {
			
			move.player.getHand().removeCard(move.card);
			houses.get(move.house).removeMoose(move.roomslot);
			
			clearLastMove();		 	
			return true;
		}
		
		return false;
	} //end playTrap
	
	
	
	/*********************************************************
	 playMITH() - attempts to play a MITH card on a player's
	 house
	 *********************************************************/
	public boolean playMITH(MITH_Move move) {
		System.out.println("MITH Played");
		
		if(move.card.getType() == MITH_Card.MITH) {
			if(houses.get(move.house).addCard(move.card)) {
				lastmove = move;
				return true;
			}
		}
		return false;
	}
	
	/*********************************************************
	 playDoor() - attempts to play a door card on a slot in 
	 a player's house
	 *********************************************************/
	public boolean playDoor(MITH_Move move) {
		System.out.println("Door played");
		
		if(move.roomslot >= 0 && 
			 move.card.getType() == MITH_Card.DOOR && 
			 houses.get(move.house).addDoor(move.card, move.roomslot)) {
				lastmove = move;
				return true;
			}
			return false;
	}
	
	
	/*********************************************************
	 playRoomEmpty - attempts to add an empty room to a
	 player's house
	 *********************************************************/
	public boolean playRoomEmpty(MITH_Move move) {
		System.out.println("Empty room played");
		
		if(move.card.getType() == MITH_Card.ROOMEMPTY &&
			 houses.get(move.house).addCard(move.card)) {
			 lastmove = move;
			 return true;
		}
		
		return false;
	}
	
	/*********************************************************
	 playRoomMoose - attempts to add an empty room to a
	 player's house
	 *********************************************************/
	public boolean playRoomMoose(MITH_Move move) {
		System.out.println("Moosed room played");
		
		if(move.roomslot >= 0 && 
			 houses.get(move.house).addMooseRoom(move.card, move.roomslot)) {
			lastmove = move;
			players.get(move.house).playedMoose(move);
			return true;
		}
		return false;
	}
	
	
	/*********************************************************
	 clearLastMove - clear the last move so trap cards can no
	 longer be played
	 *********************************************************/
	public void clearLastMove() {
		lastmove = null;
	}
	
	
	/*********************************************************
	 drawFromDeck() - removes the top card from the deck and 
	 returns it to the player
	 *********************************************************/
	public MITH_Card drawFromDeck() {
		if(deck == null)
		{
			System.out.println("We have no deck");
			System.exit(1);
		}
		
		if(!deck.isEmpty())
		{
//			System.out.println("We have a non-empty deck");  // for debugging purposes
			return deck.draw();
		}
		
		return null;
	} //end drawFromDeck
	
	/*********************************************************
	 deckEmpty - 
	 FCTVAL == true -> game's deck is empty
	 *********************************************************/
	public boolean deckEmpty() {
		return deck.isEmpty();
	}
	/*********************************************************
	 dealCards - deals out MAXCARDSINHAND cards to each of the
	 players in the game
	 PRE: players hands are empty, deck has at least 
	 players.size() * MAXCARDSINHAND cards in it
	 *********************************************************/
	public void dealCards() {
		
		for(int i = 0; i < MAXCARDSINHAND; i++)	{
			for(int j = 0; j < players.size(); j++)	{
				players.get(j).giveCard(drawFromDeck());
			}
			try {
        Thread.sleep(300);

      } catch (InterruptedException ie) {
        ie.printStackTrace();			
      }
      GUI.validateHand();

		}
	} // end dealCards
	
	
	/*********************************************************
	 addPlayer - adds a player to the game if there are 
	 currently fewer than MAXPLAYERS players total (one player should
	 be the user, but doesn't have to be) and the player 
	 isn't already in the game
	 FCTVAL == true -> player was added to the game
	 *********************************************************/
	public boolean addPlayer(MITH_Player player) {
		
		// check if there is room at the table
		if(players.size() < MAXPLAYERS && !players.contains(player)) {
			
			players.add(player); 
			houses.add(player.getHouse());
			
			player.setGame(this);
			
			return true;
		}
		 
		return false;
	} //end addPlayer

	
	/*********************************************************
	 stateOfGame - returns a textual representation of the 
	 current state of the game
	 *********************************************************/
	public String stateOfGame() {
		String playerHands = "";
		String playerHouses = "";
		for(int i = 0; i < players.size(); i++) {
			playerHands += players.get(i).getName() + "'s hand:\n";
			playerHands += players.get(i).handToString() + "\n";
			
			playerHouses += players.get(i).getName() + "'s house:\n";
			playerHouses += players.get(i).getHouse().toString() + "\n\n";
		}
		
		String deckState = "Draw pile has " + deck.numCards() + " cards remaining.\n";
        //When using "sampleHand/sampleHouse", we aren't drawing directly from the deck.
		
		return playerHands + "\n" + playerHouses + "\n" + deckState;
	} 
    
    /*********************************************************
	 numPlayers - returns the number of players in the game 
	 *********************************************************/
    public int numPlayers() {
        
        return players.size();
    }
    
    
	/*********************************************************
	 test2PlayerNR - simulates the playing of a game between two 
	 opponents to test how each of the operations is working
	 the deck is simulated through function calls to allow 
	 *********************************************************/
	public static void test2PlayerNR() {
		MITH_Game game = new MITH_Game();
		
		MITH_Player player1 = new MITH_Player("Jon", "jcarter.png", "jcarter");
		MITH_Player player2 = new MITH_Player("Comp", "generic-female.png", "random");
		MITH_Player player3 = new MITH_Player("Jon", "jcarter.png", "jcarter");
		MITH_Player player4 = new MITH_Player("Batman", "batman.png", "batman");
		MITH_Player player5 = new MITH_Player("Loser", "generic-male.png", "random");
	
//		if(game.addPlayer(player1)){
//			System.out.println(player1.getName() + " added to the game.");
//		}
//		else {
//			System.out.println(player1.getName() + " can't play.");
//		}
//		
//		if(game.addPlayer(player2)){
//			System.out.println(player2.getName() + " added to the game.");
//		}
//		else {
//			System.out.println(player2.getName() + " can't play.");
//		}
//		
//		if(game.addPlayer(player1)){
//			System.out.println(player1.getName() + " added to the game.");
//		}
//		else {
//			System.out.println(player1.getName() + " can't play.");
//		}
				
//		if(game.addPlayer(player3)){
//			System.out.println(player3.getName() + " added to the game.");
//		}
//		else {
//			System.out.println(player3.getName() + " can't play.");
//		}
//
//		if(game.addPlayer(player4)){
//			System.out.println(player4.getName() + " added to the game.");
//		}
//		else {
//			System.out.println(player4.getName() + " can't play.");
//		}
//
//		if(game.addPlayer(player5)){
//			System.out.println(player5.getName() + " added to the game.");
//		}
//		else {
//			System.out.println(player5.getName() + " can't play.");
//		}
		
//		System.out.println("\n");
//		game.dealCards();
		
		/*
		player1.getHand().sampleHand(1); //Artificially add cards to player's hand.
		player2.getHand().sampleHand(2);
		
		
		player1.getHouse().sampleHouse(1); //Artificially add cards to player's house.
		player2.getHouse().sampleHouse(2);
		**/
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.dealCards();  
        
		// verify that it worked
		System.out.println(game.stateOfGame());
		
		
	} // test2PlayerNR
	
	/**********************************************************
	 main - driver to start the various tests
	 **********************************************************/
	public static void main(String[] args)
	{
		MITH_Game.test2PlayerNR();
        
        
	}// end main
	
	
} // end MITH_Game class



		
