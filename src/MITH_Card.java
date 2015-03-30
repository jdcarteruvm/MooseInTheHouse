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
    
    // Initial constructors for room card types.
    public static final int ROOMEMPTY = 0;
    public static final int ROOMMOOSE = 1;
    // Initial constructors for non-room card types.
    public static final int DOOR      = 2;
    public static final int TRAP      = 3;
    public static final int MITH      = 4;
    
    // Card values and room types
    public static final int BED       = 0;
    public static final int LIVING    = 1;
    public static final int KITCHEN   = 2;
    public static final int BATH      = 3;
    // Non-room type cards have value of 4
    public static final int NON       = 4;
    
    // Array of card types, indexed according to integer values above.
    public static final String[] cardList = {"Empty", "Moosed", "Door", "Trap", "Moose in the House"};
    
    // Initialize variable for CARD TYPE, one of:
    // ROOMEMPTY, ROOMMOOSE, DOOR, TRAP, MITH
    private int cardType;
        
    // Initialize variable for ROOM TYPE, one of:
    // Bedroom, LivingRoom, Kitchen, Bathroom
    // (Only if CARD TYPE == EMPTY or MOOSE)
    private int roomType;
    
    // Array of room types, with fourth index containing empty string for non-room types.
    public static final String[] roomList = {"Bedroom", "Living Room", "Kitchen", "Bathroom", ""};
    
    // Initialize variable for CARD IMAGE name.
    private String cardImage;
    
    /*************************************************
     * MITH_Card() constructs a new card without     *
     * any parameters.                               *
     *************************************************/
    public MITH_Card(){
        cardType = MITH;
        roomType = 4;
        cardImage = "mith.png";
    }//End Card (default constructor)
    
    /*************************************************
     * MITH_Card(type, room, image) constructs a new *
     * card given params for card type, room type,   *
     * image name.                                   *
     * If card type is not a room, then room = 4.   *
     *************************************************/
    public MITH_Card(int type, int room, String image){
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
     * If card type is not a room, then room = 4.   *
     *************************************************/
     public MITH_Card(int type, int room){
        cardType = type;
        roomType = room;
         
        // SET CARD'S IMAGE NAME (.png)
        // if type Room, either Empty or Occupied,
        if (type == ROOMEMPTY){
            cardImage = roomList[roomType] + ".png";
        }else if (type == ROOMMOOSE){
            cardImage = roomList[roomType] + "Moose.png";
        }
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
    public int getRoom(){
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
        String string = "" + cardList[cardType] + ", room type: " + roomType + ", image: " + cardImage;
        System.out.println(string);
        return string;
    }//End print

    /*************************************************
     * toString() overload the default toString so   *
     *            prints make sense                  *
     *************************************************/
     public String toString(){
       String string = "" + cardList[cardType] + " " + roomList[roomType];

       return string;
     }      
    
    /*************************************************
     * main() is used for testing MITH_Card          *
     *************************************************/
    public static void main(String[] args) {
        
        String message = "";
        
        System.out.println("\n---------------------------------");
        System.out.println("    Testing MITH_Card class.");
        System.out.println("---------------------------------\n");
        
        message = "Testing default constructor: ";
        
        // CONSTRUCT AND TEST DEFAULT CARD
        MITH_Card card = new MITH_Card();
        if(card.toString() != ""){
            message += "PASS. \n---> Default card: ";
            message += card.toString();
        }else
            message += "FAIL.";
        
        System.out.println(message);
        
        message = "\nTesting <GETTER> methods on default card. ";
        
        message += "\nTesting method getType(): ";
        if (card.getType() >= 0){
            message += "PASS. \n ---> Card type: ";
            message += "(int) " + card.getType() + ", ";
            message += "(string) " + MITH_Card.cardList[card.getType()];
        }else
            message += "FAIL.";
        System.out.println(message);
        
        message = "Testing method getRoom(): ";
        
        if (card.getRoom() >= 0){
            message += "PASS. \n ---> Room type: ";
            message += "(int) " + card.getRoom() + ", ";
            message += "(string) " + "'" + MITH_Card.roomList[card.getRoom()] + "'";
        }else
            message += "FAIL.";
        System.out.println(message);
        
        message = "Testing method getImage(): ";
        
        if (card.getImage() != ""){
            message += "PASS. \n ---> Image name: ";
            message += card.getImage();
        }else
            message += "FAIL.";
        System.out.println(message + "\n");
        
        message = "\nTesting MITH_Card(type, room) constructor.";
        
        
        
    }//End MAIN test method
}//End Class