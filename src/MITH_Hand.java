/*********************************
 * CS 205 - Software Engineering *
 * Moose in the House            *
 * MITH_Hand       -     v0.1    *
 *********************************/

/** ####################################################### **
 Class object Hand will keep track of the cards in each 
 individual player’s hand, represented as an array of class 
 Card objects. 
 
 This class takes advantage of class objects 
 MITH_Card and MITH_Deck.
 
 Each player should have up to 5 cards in their hands.
 
 ** ###################################################### **/

import java.util.*;

public class MITH_Hand{
    
    // Initialize array list of all card objects
    // currently in the player's hand.
    private ArrayList<MITH_Card> hand;
    
    // Initialize card object variable.
    MITH_Card card;

    /*************************************************
     * MITH_Hand() creates the initial empty hand,   *
     * by creating an empty array list of Cards.     *
     *************************************************/
    public MITH_Hand(){
        // Create the empty hand. 
        hand = new ArrayList<MITH_Card>();
    }//End Hand
    
    /*************************************************
     * addCard() adds a MITH_Card object c to the    *
     * player's hand.                                *
     *************************************************/
    public void addCard(MITH_Card c){
        hand.add(c);
    }//End addCard
    
    /*************************************************
     * getCard() gets the card specified by the list *
     * index, and REMOVES it from the hand.          *
     *************************************************/
    public MITH_Card getCard(int i){
        card = hand.get(i);
        hand.remove(i);
        return card;
    }
    
    /*************************************************
     * get() merely get's the card specified         *
     * by the list index.                            *
     *************************************************/
    public MITH_Card check(int i){
        if (i < hand.size())
            return hand.get(i);
        else
            return null;
    }
    
    /*************************************************
     * removeCard() removes a card of a certain      *
     * type and/or room type.                        *
     *************************************************/
    public MITH_Card removeCard(int type, int room){
        for (int i=0; i < hand.size(); i++){
            card = hand.get(i);
            if (card.getType() == type && card.getRoom() == room){
                hand.remove(i);
                return card;
            }
        }
        
        return null;
    }//End removeCard (two params)
    
    /*************************************************
     * removeCard() removes the specified card from  *
     * the player's hand.                            *
     *************************************************/
    public void removeCard(MITH_Card c){
        for (int i=0; i < hand.size(); i++){
            card = hand.get(i);
            if (c.getType() == card.getType() && c.getRoom() == card.getRoom()){
                hand.remove(i);
                return;
            }
        }
    }//End removeCard (one param)
    
    /*************************************************
    * isEmpty() checks to see if the players hand is *
    * empty.Returns true if it is and false otherwise*
    *************************************************/
    public boolean isEmpty(){
        if (hand.size() == 0)
            return true;
        else 
            return false;
    }//End empty

    /*************************************************
    * getSize() returns the number of cards in the   *
    *           hand
    *************************************************/
    public int getSize(){
        return hand.size();
    }//End getSize

    /*************************************************
     * hasCard(cardType) - checks to see if the hand *
     * has a certain type of card                    *
     * FCTVAL == true -> there is at least one       *
     * instance of cardType in the hand              *
     *************************************************/
    public boolean hasCardType(int cardType) {
      for (int i=0; i < hand.size(); i++){
      	card = hand.get(i);
      	if (card.getType() == cardType) {
      		return true;
      	}
      }
      return false;
    }
    
    /*************************************************
     * toString() returns, and prints to the system, *
     * the string value of each card in the player's *
     * current hand.                                 *
     *************************************************/
    public String toString(){
        String string = "";
        
        if (hand.size() == 0){
            string = "Empty.";
        }else{
            for (int i=0; i < hand.size(); i++){
                card = hand.get(i);
                string += card.toString() + "\n";
            }
        }
       // System.out.println(string);
        return string;
    }//End toString
    
  /**********************************************************
   sampleHand - fills the hand with a selection of cards  
   for testing purposes
   **********************************************************/
  public void sampleHand(int i) {
  	
  	// make sure we start with a blank house
  	hand = new ArrayList<MITH_Card>();
  	
  	switch(i) {
  		case 1: 
  			addCard(new MITH_Card()); // add a moose in the house card
  			addCard(new MITH_Card(MITH_Card.ROOMEMPTY, MITH_Card.LIVING));
  			addCard(new MITH_Card(MITH_Card.ROOMMOOSE, MITH_Card.BATH));
  			addCard(new MITH_Card(MITH_Card.DOOR, MITH_Card.NON));
  			addCard(new MITH_Card(MITH_Card.TRAP, MITH_Card.NON));
  			break;
  		
  		case 2: 
  			addCard(new MITH_Card(MITH_Card.ROOMEMPTY, MITH_Card.LIVING));
  			addCard(new MITH_Card(MITH_Card.ROOMEMPTY, MITH_Card.BED));
  			addCard(new MITH_Card(MITH_Card.ROOMMOOSE, MITH_Card.BATH));
   			addCard(new MITH_Card(MITH_Card.ROOMEMPTY, MITH_Card.LIVING));
  			addCard(new MITH_Card(MITH_Card.ROOMEMPTY, MITH_Card.KITCHEN));
 			break;
  		default: 
   			addCard(new MITH_Card()); // add a moose in the house card
  			addCard(new MITH_Card(MITH_Card.ROOMEMPTY, MITH_Card.LIVING));
  			addCard(new MITH_Card(MITH_Card.ROOMEMPTY, MITH_Card.LIVING));
  			addCard(new MITH_Card(MITH_Card.ROOMEMPTY, MITH_Card.BATH));
  			addCard(new MITH_Card(MITH_Card.ROOMMOOSE, MITH_Card.LIVING));
  	}
  	
  }
  
  /********************************************************* 
   numMoose() - counts the number of moose cards in the hand
   *********************************************************/ 
  public int numMoose() {
  	int ct = 0;
  	
   	for(int i = 0; i < hand.size(); i++)
   	{
   		card = hand.get(i);
   		if(card.getType() == MITH_Card.ROOMMOOSE) {
   			ct++;
   		}
   	}
   	
   	return ct;
  }
   
   
   /*******************************************
    main() test driver for the hand class
    *******************************************/
    public static void main(String [] args){
       
        String message = ""; 
        System.out.println("\n -------------------------");
        System.out.println("   Testing MITH_Hand class.");
        System.out.println(" ----------------------------\n");

        //TEST 
        MITH_Hand test_hand = new MITH_Hand();
        MITH_Card test_card = new MITH_Card();
        
        //Testing addCard()
        if (test_hand.getSize() == 0){
         test_hand.addCard(test_card);
         System.out.println(test_hand.getSize());

         message += "Passed.";
        }    
        else{
         message += "Failed";
         System.out.println(message);
        } 

        if (test_hand.isEmpty()== true){
         message += "Passed";
        } else{
            message+= "Failed";
        }
    } //End Test      
}//End Class
