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
    
    private String[] roomList = {"Bedroom", "LivingRoom", "Kitchen", "Bathroom"};
    
    // Initialize variable for CARD IMAGE name.
    private String cardImage;
    
    /*************************************************
     * MITH_Card() constructs a new card without     *
     * any parameters.                               *
     *************************************************/
    public MITH_Card(){
        cardType = MITH;
        roomType = "";
        cardImage = "mith.png";
    }//End Card (default constructor)
    
    /*************************************************
     * MITH_Card(type, room, image) constructs a new *
     * card given params for card type, room type,   *
     * image name.                                   *
     * If card type is not a room, then room = "".   *
     *************************************************/
    public MITH_Card(int type, String room, String image){
        cardType = type;
        roomType = room;
        cardImage = image;
    }//End Card (three params)
    
    /*************************************************
     * MITH_Card(type, room) constructs a new card   *
     * given params for card type and room type.     *
     * Given the type for both card and room, an if  *
     * statement will add the appropriate image name *
     * for the card.                                 * 
     * If card type is not a room, then room = "".   *
     *************************************************/
     public MITH_Card(int type, String room){
        cardType = type;
        roomType = room;
         
        // SET CARD'S IMAGE NAME (.png)
        // if type Room, either Empty or Occupied,
        // For-Loop which sets the image name for the appropriate
        // room card depending on if its EMPTY or OCCUPIED.
        if (type == ROOMEMPTY || type == ROOMMOOSE){
            for (int i = 0; i < 4; i++){
                if (room == roomList[i]){
                    if (type == ROOMEMPTY){
                        cardImage = room + ".png";
                    }else if (type == ROOMMOOSE){
                        cardImage = room + "Moose.png";
                    }
                }//end if
            }//end for
        }//end if
         
        // else, if type is anything else,
        // set the appropriate image name
        else if (type == DOOR){
            cardImage = "Door.png";
        }else if (type == TRAP){
            cardImage = "Trap.png";
        }else if (type == MITH){
            cardImage = "MitH.png";
        }//end else if
        
    }//End Card (two params)
    
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
    
    /*************************************************
     * print() returns, and prints to the system, the*
     * string value of the card.                     *
     *************************************************/
    public String print(){
        String string = "" + cardType + ", room type: " + roomType + ", image: " + cardImage;
        System.out.println(string);
        return string;
    }//End print

}//End Class