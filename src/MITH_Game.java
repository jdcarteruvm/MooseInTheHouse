/*******************************************************************
 * Moose In The House - CS205 Final Project
 * Ian Benson, Jonathan Carter, Joey Palchak, Rachel Temple Cj Zhang
 *
 * MITH_House- A class implementing each player's house cards for the
 *             table of play
 *******************************************************************/

public class MITH_Game {
	
	private MITH_Deck deck; 
	
	
	public MITH_Game() {
		deck = new MITH_Deck();
		deck.shuffle();
	}
	
	public void playTrap() {
		System.out.println("Trap Played");
	}
	
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
	}
	
} // end class