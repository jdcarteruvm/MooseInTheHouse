/*******************************************************************
 * Moose In The House - CS205 Final Project
 * Ian Benson, Jonathan Carter, Joey Palchak, Rachel Temple Cj Zhang
 *
 * MITH_House- A class implementing each player's house cards for the
 *             table of play
 * Authors: Jon
 *******************************************************************/


import java.util.*;


public class MITH_House {

  private House_Slot entry; // a slot for the entry of the house to hold the Moose in the House card
  private ArrayList<House_Slot> rooms;  // to hold all the rooms that can be in the house
  
  private static final int MAX_EMPTY_ROOMS = 3;
  /********************************************************* 
   MITH_House() - is the constructor, intializing the 
   empty house arraylist and the MITH house slot
   *********************************************************/
  public MITH_House() {
  
    rooms = new ArrayList<House_Slot>();
    entry = null;
  }
  
   /********************************************************* 
   addCard() - attempts to add a card to the house in the 
   first appropriate slot that is available
   
   #### deprecated for adding moose rooms and doors ####
   *********************************************************/
  public boolean addCard(MITH_Card card){
    
    // if time allows this would probably be better replaced by switch statement
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
    if(card.getType() == card.ROOMEMPTY &&
    	numEmpty() < MAX_EMPTY_ROOMS)
    {
      rooms.add(new House_Slot(card));
      return true;
    }
      
    // attempt to add a moose to a room
    if(entry != null && card.getType() == card.ROOMMOOSE)
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
    
    // attempt to add a door to a room
    if(card.getType() == card.DOOR)
    {
      for(int i = 0; i < rooms.size(); i++)
      {
        House_Slot room = rooms.get(i);
        if(room.top_card == null)
        {
          room.top_card = card;
          return true;
        }
      }
    }
    
  
    return false;
    
  }
	/***************************************************
	 addMooseRoom 
	 add a moosed room card to the house in a certain
	 slot
	 ***************************************************/
	public boolean addMooseRoom(MITH_Card card, int slot) {
		
		if(entry != null && 
			 rooms.get(slot).top_card == null &&
		   rooms.get(slot).base_card.getRoom() == card.getRoom() && 
		   card.getType() == MITH_Card.ROOMMOOSE)
		{
			rooms.get(slot).top_card = card;
			return true;
		}
		
		return false;
	}

	/***************************************************
	 addDoor
	 attempt to add a door to a room in the house
	 ***************************************************/  
	public boolean addDoor(MITH_Card card, int slot) {
		if(rooms.get(slot).top_card == null &&
		   card.getType() == MITH_Card.DOOR) {
		  rooms.get(slot).top_card = card;
		  return true;
		}
		
		return false;
	}
		      
  public boolean removeMoose(int i) {
  	if(rooms.get(i).top_card.getType() == MITH_Card.ROOMMOOSE){
  		rooms.get(i).top_card = null;
  		return true;
  	}
  	
  	return false;
  }

   /********************************************************* 
   numMoose() - counts the number of moose cards in the hand
   *********************************************************/ 
  public int numMoose() {
  	int ct = 0;
  	
   	for(int i = 0; i < rooms.size(); i++)
   	{
   		MITH_Card card = rooms.get(i).top_card;
   		if(card.getType() == MITH_Card.ROOMMOOSE) {
   			ct++;
   		}
   	}
   	
   	return ct;
  } 
    
  /*******************************************************
   * toString - makes a human readable string out of the *
   *            house                                    *
   *******************************************************/
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
  
  /**********************************************************
   sampleHouse - fills the house with a selection of rooms  
   for testing purposes
   **********************************************************/
  public void sampleHouse(int i) {
  	
  	// make sure we start with a blank house
  	this.entry = null;
  	this.rooms = new ArrayList<House_Slot>();
  	
  	switch(i) {
  		case 1: 
  			addCard(new MITH_Card()); // add a moose in the house card
  			addCard(new MITH_Card(MITH_Card.ROOMEMPTY, MITH_Card.LIVING));
  			addCard(new MITH_Card(MITH_Card.ROOMMOOSE, MITH_Card.LIVING));
  			break;
  		
  		case 2: 
  			addCard(new MITH_Card(MITH_Card.ROOMEMPTY, MITH_Card.LIVING));
  			addCard(new MITH_Card(MITH_Card.ROOMEMPTY, MITH_Card.BATH));
  			addCard(new MITH_Card(MITH_Card.ROOMEMPTY, MITH_Card.BATH));
  			break;
  		default: 
   			addCard(new MITH_Card()); // add a moose in the house card
  			addCard(new MITH_Card(MITH_Card.ROOMEMPTY, MITH_Card.LIVING));
  			addCard(new MITH_Card(MITH_Card.ROOMEMPTY, MITH_Card.BATH));
  			addCard(new MITH_Card(MITH_Card.ROOMMOOSE, MITH_Card.LIVING));
  	}
  	
  }
  		
  		
  /*********************************************************
   entryEmpty() - checks to see if the house entry is empty
   *********************************************************/
  public boolean entryEmpty() {
  	return entry == null;
  }
  		
  /*********************************************************
   numEmpty() - returns the number of empty rooms in the
   house
   *********************************************************/
  public int numEmpty() {
  	
  	int ct = 0;
  	
  	for(int i = 0; i < rooms.size(); i++) {
  		if(rooms.get(i).top_card == null) {
  			ct++;
  		}
  	}
  	
  	return ct;
  }
  
  /********************************************************* 
   main() - TESTING METHOD
   *********************************************************/
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
    System.out.println("Current house: " + house.toString() + "\n");

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
    System.out.println("Current house: " + house.toString() + "\n");
    
    // Test adding a moose room card to a house with an MITH_card and non-matching empty room card
    message = "Add moose room card to a house with an MITH card and non-matching empty room card - ";
    if(house.addCard(new MITH_Card(MITH_Card.ROOMMOOSE, MITH_Card.BATH)))
      message += "FAIL"; // this is an illegal move
    else
      message += "PASS";
    
    System.out.println(message);
    // PASS => house has a MITH card and an empty living room card
    System.out.println("Current house: " + house.toString() + "\n");

    // Test adding a moose room card to a house with an MITH_card and matching empty room card
    message = "Add moose room card to a house with an MITH card and matching empty room card - ";
    if(house.addCard(new MITH_Card(MITH_Card.ROOMMOOSE, MITH_Card.LIVING)))
      message += "PASS";
    else
      message += "FAIL";
    
    System.out.println(message);
    // PASS => house has a MITH card and an empty living room card
    System.out.println("Current house: " + house.toString() + "\n");

		house = new MITH_House();
		
		System.out.println("New House: " + house.toString() + "\n");
		
    // Test adding a empty room card to an empty house
    message = "Add empty room card to  - ";
    if(house.addCard(new MITH_Card(MITH_Card.ROOMEMPTY, MITH_Card.LIVING)))
      message += "PASS";
    else
      message += "FAIL"; // should work bug in the code
    
    System.out.println(message);
    // PASS => house has an empty living room card
    System.out.println("Current house: " + house.toString() + "\n");
		
    // Test adding a moose room card to a house with only a matching empty room card
    message = "Add moose room card to a house with only a matching empty room card - ";
    if(house.addCard(new MITH_Card(MITH_Card.ROOMMOOSE, MITH_Card.LIVING)))
      message += "FAIL";
    else
      message += "PASS";
    
    System.out.println(message);
    // PASS => house has a moosed living room 
    System.out.println("Current house: " + house.toString() + "\n");

		System.out.println("Add two more empty rooms - already checked");
		house.addCard(new MITH_Card(MITH_Card.ROOMEMPTY, MITH_Card.BATH));
		house.addCard(new MITH_Card(MITH_Card.ROOMEMPTY, MITH_Card.KITCHEN));
		
    System.out.println("Current house: " + house.toString() + "\n");
    
    // Test adding a door to an empty room
    message = "Test adding a door to the first Empty Room - ";
    if(house.addCard(new MITH_Card(MITH_Card.DOOR, MITH_Card.NON)))
    {
    	message += "PASS";
    }
    else
    {
    	message += "FAIL";
    }
    System.out.println(message);
    // PASS => house has a MITH card and an empty living room card
    System.out.println("Current house: " + house.toString() + "\n");
    
    // Test adding a moose room card to a house with a matchin mooosed room card
    message = "Add moose room card to a house with a matching moosed room card - ";
    if(house.addCard(new MITH_Card(MITH_Card.ROOMMOOSE, MITH_Card.LIVING)))
      message += "FAIL";
    else
      message += "PASS"; // illegal move
    
    System.out.println(message);
    // PASS => house has a moosed living room, doored bathroom and empty kitchen
    System.out.println("Current house: " + house.toString() + "\n");
		
		System.out.println("Current house has " + house.numEmpty() + " empty rooms;");
		
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
     
     
   /********************************************************* 
   House_Slot - creates a house slot with the base card
   *********************************************************/
    House_Slot(MITH_Card base) {
      
      base_card = base; 
    }
    
  /********************************************************* 
   House_Slot - creates a house slot with both base and top
   card given
   might be useful for cloning; not useful for gameplay
   *********************************************************/
    House_Slot(MITH_Card base, MITH_Card top) {
      base_card = base;
      top_card = top;
    }
      
    /********************************************************* 
   toString() - translates the hand into a returnable string
   *********************************************************/
    public String toString() {
      String output = "";
      
      output += base_card.toString();
      
      if(top_card != null)
        output += "/" + top_card.toString();
        
      return output;
    }
  }
  
}
      
