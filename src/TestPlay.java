
public class TestPlay
{
    public static void main(String[] args)
    {
        //Initial testing.
        MITH_Deck deck = new MITH_Deck();
        MITH_Hand hand = new MITH_Hand();
        deck.shuffle();
        
        MITH_Card card;
        
        for (int i=0; i < 5; i++)
        {
            card = deck.draw();
            hand.addCard(card);
        }
        
        System.out.println(hand.toString());
        System.out.println("Pull third card: " + hand.getCard(2).toString() + "\n");
        System.out.println(hand.toString());

        
        
        
        
    }//End main
    
}//End TestPlay