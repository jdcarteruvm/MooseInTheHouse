	/*********************************************************
	 MITH_move - utility class to ease management of moves
	 holds the move's player, card, house and location
	 *********************************************************/
	class MITH_Move {
		public MITH_Player player;
		public MITH_Card card;
		public int house;
		public int roomslot;  // the slot in the house where this card is to be played (-1 for entry)
		public boolean discard;
		public int handIndex; // the index of card's position in player's hand
	}	
