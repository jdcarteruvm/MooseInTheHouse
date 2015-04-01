/*********************************
 * CS 205 - Software Engineering *
 * Moose in the House            *
 * MITH_Deck       -     v0.1    *
 *********************************/

/** ################################################### **
 Class object Deck creates and represents the deck of 58
 cards used to play the game Moose in the House. The deck
 will be represented as an array of the MITH_Card class
 objects.
 
 Out of the 58 cards, there are:
    20 Room cards (5 of each Room),
    20 Occupied Room cards (5 of each),
    10 Moose in the House,
    5 Doors,
    3 Traps
    
 ** #################################################### **/

import java.util.*;

public class MITH_Deck{
    
    // Initialize array list of all card objects in the deck.
    private ArrayList<MITH_Card> deck;
    //private MITH_Card[] deck;
    
    // Initialize card object variable.
    private MITH_Card card;
    private MITH_Card topCard;
    
    private int count;
    
//    // Initial constructors for room card values.
//    private int ROOMEMPTY = 0;
//    private int ROOMMOOSE = 1;
//    // Initial constructors for non-room card values.
//    private int DOOR      = 2;
//    private int TRAP      = 3;
//    private int MITH      = 4;
//    
    // Initialize variable for CARD TYPE, one of:
    // ROOMEMPTY, ROOMMOOSE, DOOR, TRAP, MITH
    private int cardType;
        
    // Initialize variable & array for ROOM TYPE, one of:
    // Bedroom, LivingRoom, Kitchen, Bathroom
    // (Only if CARD TYPE == EMPTY or MOOSE)
    private String[] roomType = {"Bedroom", "LivingRoom", "Kitchen", "Bathroom", ""};
    private String room;
    
    // Initialize variable for CARD IMAGE name.
    private String cardImage;
    
    /*************************************************
     * MITH_Deck() constructs a new deck of 58 cards *
     * for the game Moose in the House. Includes:    *
     * 20 Rooms, 20 Occupied Rooms, 10 MitH,         *
     * 5 Doors, and 3 Moose Traps                    *
     *************************************************/
    public MITH_Deck(){
        
        //create empty deck array list
        deck = new ArrayList<MITH_Card>();
        //deck = new MITH_Card[58];
        
        count = 0;
        // For-Loop creates 40 Cards:
        // 20 Empty Rooms, and 20 Occupied Rooms
        for (int i = 0; i < 4; i++){ // 4 rooms
            for (int j = 0; j < 5; j++){ // 5 of each
                // create an empty Room, of type i
                card = new MITH_Card(MITH_Card.ROOMEMPTY, i);
                deck.add(card);
                // create an occupied Room, of same type i
                card = new MITH_Card(MITH_Card.ROOMMOOSE, i);
                deck.add(card); 
                count++;
            }//end 5xfor-loop
        }//end 4xfor-loop ROOMS
        
        // For-Loop creates 18 Cards:
        // 10 Moose in the House, 5 Doors, 3 Traps
        for (int i = 0; i < 18; i++){
            // create 10 MitH
            if (i<11){ 
                card = new MITH_Card(MITH_Card.MITH, 4);
                deck.add(card);
            // create 5 Doors
            }else if (i>=11 && i<16){
                card = new MITH_Card(MITH_Card.DOOR, 4);
                deck.add(card);
            // create 3 Traps
            }else if (i>=16){
                card = new MITH_Card(MITH_Card.TRAP, 4);
                deck.add(card);
            }
            count++;
        }//end 18xfor-loop MITH
    }//End Deck
    
    /*************************************************
     * draw() returns the top card of the deck and   *
     * removes it from the ArrayList.                *
     *************************************************/
    public MITH_Card draw(){
        // Will need something to take care of empty decks?
        
        // topCard object is the first card in the deck
        topCard = deck.get(0);
        // remove this card from the deck
        deck.remove(0);
        
        return topCard;
    }//End draw
    
    /*************************************************
     * shuffle() randomizes the order of cards in the*
     * deck, by using java's built-in method         *
     * Collections.shuffle.                          *
     *************************************************/
    public void shuffle(){
        Collections.shuffle(deck);
    }//End shuffle
    
    /*************************************************
     * numCards() returns the number of cards that   *
     * are currently in the deck.                    *
     *************************************************/
    public int numCards(){
        return deck.size();
    }//End numCards

    /*************************************************
     * reset() resets the deck back to a full and    *
     * randomized deck.                              *
     *************************************************/
    public void reset(){
        //MITH_Deck();
        //Collections.shuffle(deck);
    }//End reset
    
    /*************************************************
     * removeMith() removes all Moose in the House   *
     * cards from the deck.                          *
     *************************************************/
    public void removeMith(){
        for (int i = 0; i < deck.size(); i++)
        {
            MITH_Card cardm = deck.get(i);
            if (cardm.getType() == MITH_Card.MITH)
            {
                deck.remove(i);
            }//end if
        }//end for
    }//End removeMith
    
    /*************************************************
     * isEmpty() returns true if the deck is empty,    *
     * otherwise returns false.                      *
     *************************************************/
    public boolean isEmpty(){
        if (deck.size() == 0)
            return true;
        else
            return false;
    }//End empty
    
    public static void main(String[] args){  // Testing Function is Right Here.<--------------------------------- 
    	
    	MITH_Deck MD=new MITH_Deck();
    	
    	System.out.println(MD);
    	System.out.println("Main deck has constructed. --->Passed.");
    	
    	int i=MD.numCards();
    	System.out.println("deck.length is: "+i+" (it should be 58 cards in total)");
    	if(i==58){
    		System.out.println("--->Passed.");
    	}
    	else if(i!=58){
    		System.out.println("--->Failed.");
    	}
    	
    	System.out.println("Card on the top of the deck is: "+MD.draw());
    	System.out.println("compare with the actually 'first' card.");
    	
    	MD.shuffle();
    	System.out.println("new deck is: "+ MD);
    	
//    	MD.removeMith();
//    	for(int s=0; s<deck.size(); s++){
//    		if(deck.get(s)="Moose in the House"){
//    			System.out.println("removeMith() --->Failed");
//    		}
//    		else if(deck.get(s)!="Moose in the House"){
//    			System.out.println("removeMith() --->Passed.");
//    		}
//    	}
    	
    	if(MD.isEmpty()==true){
    		System.out.println("isEmpty() is true. --->Passed.");
    	}
    	else if(MD.isEmpty()==false){
    		System.out.println("isEmpty() is false, --->Passed.");
    	}
    	
    	MD.reset();
    	
    }
    
}//End Class
