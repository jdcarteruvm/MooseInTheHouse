/*******************************************************************
 * Moose In The House - CS205 Final Project
 * Ian Benson, Jonathan Carter, Joey Palchak, Rachel Temple Cj Zhang
 *
 * MITH_House- A class implementing each player's house cards for the
 *             table of play
 *******************************************************************/


import java.util.*;


public class MITH_House {

  private House_Slot entry; // a slot for the entry of the house to hold the Moose in the House card
  
  private ArrayList<House_Slot> rooms;  // to hold all the rooms that can be in the house
  
  // create an empty house
  public MITH_House() {
  
    rooms = new ArrayList<House_Slot>();
    
  }
  
  // add card attempts to add a card to the house in the first slot that is available
  public Boolean addCard(MITH_Card card){
    
    // add a Moose in the House card to the player's house
    if(card.getType() == MITH_Card.MITH)
    {
      if(entry == null)
      {
        entry = new House_Slot(card);
        return true;
      }
      else 
      {
        return false;
      }
    } // end card type MITH
  
    // add an empty room to a house
    if(card.getType() == card.ROOMEMPTY)
    {
      rooms.add(new House_Slot(card));
      return true;
    }
      
    // attempt to add a moose to a room
    if(card.getType() == card.ROOMMOOSE)
    {
      for(int i = 0; i < rooms.size(); i++)
      {
        House_Slot room = rooms.get(i);
        if(room.base_card.getRoom() == card.getRoom() && room.top_card == null)
        {
          room.top_card = card;
          return true;
        }
      }
    }
    
  
    return false;
    
  }
    
    
  /*************************************************
   * toString - makes a readable string out of the *
   *            house                              *
   *************************************************/
  public String toString() {
    String output = "";
    
    output += "{[Entry: ";
    
    if(entry == null)
      output += "empty";
    else
      output += "MITH";
      
    output += "]";
    
    
    for(int i = 0; i < rooms.size(); i++)
    {
      output += " [" + (i+1) + ": " + rooms.get(i).toString() + "]";
    }
    
    output += "}";
    
    return output;
  }
  
  public static void main(String[] args) {
    MITH_House house = new MITH_House();
    String message;
    
    System.out.println("\n---------------------------------\nTesting MITH_House class\nNB: Passed tests are only valid if previous test passed\n");
    // Test adding a MITH house card to an empty house
    message = "Add MITH card to empty house - ";
    if(house.addCard(new MITH_Card()))
      message += "PASS";
    else
      message += "FAIL";
    
    System.out.println(message);
    // PASSED => house has just a MITH card
    
    // Test adding a MITH house to a house that already has a MITH card
    message = "Add MITH card to house with MITH card already - ";
    if(house.addCard(new MITH_Card()))
      message += "FAIL";
    else
      message += "PASS";
    System.out.println(message);



    // PASSED => house has just a MITH card
    System.out.println("Current house: " + house.toString());

    // Test adding a moose room card to a house with just an MITH_card
    message = "Add moose room card to a house with just an MITH card - ";
    if(house.addCard(new MITH_Card(MITH_Card.ROOMMOOSE, MITH_Card.LIVING)))
      message += "FAIL"; // this is an illegal move
    else
      message += "PASS";
    
    System.out.println(message);
    // PASS => house has just a MITH card
  

    
    // Test adding a empty room card to a house with just an MITH_card
    message = "Add empty room card to a house with just an MITH card - ";
    if(house.addCard(new MITH_Card(MITH_Card.ROOMEMPTY, MITH_Card.LIVING)))
      message += "PASS";
    else
      message += "FAIL"; // should work bug in the code
    
    System.out.println(message);
    // PASS => house has a MITH card and an empty living room card
    System.out.println("Current house: " + house.toString());
    
    // Test adding a moose room card to a house with an MITH_card and non-matching empty room card
    message = "Add moose room card to a house with an MITH card and non-matching empty room card - ";
    if(house.addCard(new MITH_Card(MITH_Card.ROOMMOOSE, MITH_Card.BATH)))
      message += "FAIL"; // this is an illegal move
    else
      message += "PASS";
    
    System.out.println(message);
    // PASS => house has a MITH card and an empty living room card
    System.out.println("Current house: " + house.toString());

    // Test adding a moose room card to a house with an MITH_card and matching empty room card
    message = "Add moose room card to a house with an MITH card and matching empty room card - ";
    if(house.addCard(new MITH_Card(MITH_Card.ROOMMOOSE, MITH_Card.LIVING)))
      message += "PASS";
    else
      message += "FAIL";
    
    System.out.println(message);
    // PASS => house has a MITH card and an empty living room card
    System.out.println("Current house: " + house.toString());

		house = new MITH_House();
		
		System.out.println("New House: " + house.toString());
		
    // Test adding a empty room card to an empty house
    message = "Add empty room card to  - ";
    if(house.addCard(new MITH_Card(MITH_Card.ROOMEMPTY, MITH_Card.LIVING)))
      message += "PASS";
    else
      message += "FAIL"; // should work bug in the code
    
    System.out.println(message);
    // PASS => house has an empty living room card
    System.out.println("Current house: " + house.toString());
		
  
  } // end main

// end MITH_House specific functionality

  /******************************************************************
    Utility classes
   ******************************************************************/

  /**********************************************
   * House_Slot - inner class to hold cards for *
   * each "room" in a player's house            *
   **********************************************/
  private class House_Slot {
    private MITH_Card base_card;
    private MITH_Card top_card;
     
     
     // creates a house slot with the base card given
    House_Slot(MITH_Card base) {
      
      base_card = base; 
    }
    
    // creates a house slot with both base and top card given
    // might be useful for cloning - not useful in normal gameplay
    House_Slot(MITH_Card base, MITH_Card top) {
      base_card = base;
      top_card = top;
    }
    
    public String toString() {
      String output = "";
      
      output += base_card.toString();
      
      if(top_card != null)
        output += "/" + top_card.toString();
        
      return output;
    }
  }
  
}
      