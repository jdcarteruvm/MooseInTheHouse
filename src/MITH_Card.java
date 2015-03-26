/*********************************
 * CS 205 - Software Engineering *
 * Moose in the House            *
 * MITH_Card       -     v0.1    *
 *********************************/

/** ####################################################### **
 Class object Card creates an individual card in a deck of
 58 cards for the game Moose in the House.
 
 There are 5 different types of CARDS:
    Room (EMPTY), Occupied Room (MOOSE), 
    Moose in the House, Door, Trap
    
    If type ROOM, there are 4 types of ROOM:
        Bedroom, Livingroom, Kitchen, Bathroom
 ** ###################################################### **/

public class MITH_Card{
    
    // Initial constructors for room card values.
    private int ROOMEMPTY = 0;
    private int ROOMMOOSE = 1;
    // Initial constructors for non-room card values.
    private int DOOR      = 2;
    private int TRAP      = 3;
    private int MITH      = 4;
    
    // Initialize variable for CARD TYPE, one of:
    // ROOMEMPTY, ROOMMOOSE, DOOR, TRAP, MITH
    private int cardType;
        
    // Initialize variable for ROOM TYPE, one of:
    // Bedroom, LivingRoom, Kitchen, Bathroom
    // (Only if CARD TYPE == EMPTY or MOOSE)
    private String roomType;
    
    // Initialize variable for CARD IMAGE name.
    private String cardImage;
    
    /*************************************************
     * Card() constructs a new card without being    *
     * given any parameters.                         *
     *************************************************/
    public void Card(){
        
        cardType = MITH;
        roomType = null;
        cardImage = "mith";
    
    }//End Card (default constructor)
    
    /*************************************************
     * Card() constructs a new card given parameters *
     * for card type, room type, image name.         *
     * If card type is not a room, then room = null. *
     *************************************************/
    public void Card(int type, String room, String image){
        cardType = type;
        //if (type == ROOMEMPTY || type = ROOMMOOSE){roomType = room;}
        roomType = room;
        cardImage = image;
    }//End Card
    
    /*************************************************
     * getType() returns the int value of the card's *
     * type;                                         *
     * ROOMEMPTY, ROOMMOOSE, DOOR, TRAP, MITH        *
     *************************************************/
    public int getType(){
        return cardType;
    }//End getType
    
    /*************************************************
     * getRoom() returns the string value of the     *
     * card's room type;                             *
     * Bedroom, LivingRoom, Kitchen, Bathroom        *
     *************************************************/
    public String getRoom(){
        return roomType;
    }//End getRoom
    
    /*************************************************
     * getRoom() returns the string value of the     *
     * card's image name.                            *
     *************************************************/
    public String getImage(){
        return cardImage;
    }//End getImage

}//End Class