
import javax.swing.JFrame;


public class Game_GUI_Tester {
	public static void main(String[] args) {
		MITH_Game game = new MITH_Game();
		MITH_Game_Board board = new MITH_Game_Board(4);
			
		MITH_Player player1 = new MITH_Player("Jon", "jcarter.png", "jcarter");
		MITH_Player player2 = new MITH_Player("Comp", "generic-female.png", "random");
		MITH_Player player4 = new MITH_Player("Batman", "batman.png", "batman");
		MITH_Player player3 = new MITH_Player("Loser", "generic-male.png", "random");
		
		board.setGame(game);
		game.setGUI(board);
		
		board.setPlayer(player1);
		
    game.addPlayer(player1);
    game.addPlayer(player2);
    game.addPlayer(player3);
    game.addPlayer(player4);
	
  	JFrame frame = new JFrame("Game Board Mockup");
  	frame.getContentPane().add(board);
  	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  	frame.pack();
  	frame.setLocationRelativeTo(null);
  	frame.setVisible(true);

    game.dealCards();  
		board.validateHand();
		
		MITH_Move move = new MITH_Move();
		move.discard = true;
		move.card = new MITH_Card();
		
		board.update(move);
			
	}
}