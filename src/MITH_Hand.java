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
     * removeCard() removes a card of a certain      *
     * type and/or room type.                        *
     *************************************************/
    public void removeCard(int type, int room){
        for (int i=0; i < hand.size(); i++){
            card = hand.get(i);
            if (card.getType() == type && card.getRoom() == room){
                hand.remove(i);
            }
        }
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
     * print() returns, and prints to the system,    *
     * the string value of each card in the player's *
     * current hand.                                 *
     *************************************************/
    public String print(){
        String string = "";
        
        if (hand.size() == 0){
            string = "Your hand is empty.";
        }else{
            for (int i=0; i < hand.size(); i++){
                card = hand.get(i);
                string += "Type: " + card.getType() + " | Room: " + MITH_Card.roomList[card.getRoom()] + "\n";
            }
        }
        System.out.println(string);
        return string;
    }//End print
    
}//End Class
