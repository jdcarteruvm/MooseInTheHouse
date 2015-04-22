import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Image;

//import java.awt.datatransfer.DataFlavor;
//import java.awt.datatransfer.Transferable;
//import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;


public class GameBoardTest extends JLayeredPane {

		private static final Dimension GAME_BOARD_SIZE = new Dimension(700, 600);
    JPanel backingPanel;
    
    private Image backgroundImg;

    MITH_Player_Panel player1; // base panels for holding the player's panels
    MITH_Player_Panel player2;
    MITH_Player_Panel player3;
    MITH_Player_Panel player4;
    
    JPanel userPanel;
    MITH_Hand_Panel handPanel;
    
//    JPanel deckPanel;
    
    ArrayList<ArrayList<MITH_Room_Panel>> houses; // Room panels to hold the houses of each player
    ArrayList<JPanel> playerPics;

    
    
    JPanel drawPile;  // a panel to hold the draw pile of cards
		JPanel discardPile; // a panel to hold the image of the last card discarded
		
		MITH_Label fromCard; // will hold the image for the next card in the deck
		MITH_Label toCard;	// holds the image for the discard place of the deck
		Random randomGenerator;		
		MITH_Deck deck;

		static final int MAX_PLAYERS = 4;
		static final int MAX_LG_ROOMS = 5; // the most of the larger cards that will fit on one row
		static final String IM_PATH = "../resources/images/30dpi/";
		static final String SM_IM_PATH = "../resources/images/15dpi/";
		
		static final String PIC_PATH = "../resources/images/players/";
				
		static final int CARD_WIDTH = 75;
		static final int CARD_HEIGHT = 110;
		static final Dimension CARD_DIM = new Dimension(CARD_WIDTH, CARD_HEIGHT);

		static final int SM_CARD_WIDTH = 36;
		static final int SM_CARD_HEIGHT = 55;
		static final Dimension SM_CARD_DIM = new Dimension(SM_CARD_WIDTH, SM_CARD_HEIGHT);
		
		static final int PIC_WIDTH = 110;
		static final int PIC_HEIGHT = 110;
		static final Dimension PIC_DIM = new Dimension(PIC_WIDTH, PIC_HEIGHT);
		
		static final int SM_PIC_WIDTH = 110;
		static final int SM_PIC_HEIGHT = 110;
		static final Dimension SM_PIC_DIM = new Dimension(SM_PIC_WIDTH, SM_PIC_HEIGHT);

		static final int TINY_PIC_WIDTH = 50;
		static final int TINY_PIC_HEIGHT = 50;
		static final Dimension TINY_PIC_DIM = new Dimension(TINY_PIC_WIDTH, TINY_PIC_HEIGHT);
		
		ArrayList<ImageIcon> images;
		
    public GameBoardTest(int num_players) {
				
				backgroundImg = new ImageIcon("../resources/images/playareabackground.png").getImage();
//				int num_players = 4;
				deck = new MITH_Deck();
				deck.shuffle();
				// shorten the deck for testing purposes:
//				for(int i = 0; i < 48; i++)
//					deck.draw();
				
				// create the containers for the players
				houses = new ArrayList<ArrayList<MITH_Room_Panel>>();
				playerPics = new ArrayList<JPanel>();
				
				
				// set aside a slot in each house for 
				for(int i = 0; i < MAX_PLAYERS; i++)
				{
					houses.add(new ArrayList<MITH_Room_Panel>());
					MITH_Room_Panel temp = new MITH_Room_Panel();
					temp.setPreferredSize(CARD_DIM);
					temp.setOpaque(false);
					
					MITH_Label label = new MITH_Label();
					label.setIcon(new ImageIcon(IM_PATH + "entry.png"));
					temp.add(label);
				
					houses.get(i).add(temp);
				}
					
				
				randomGenerator = new Random();
				
//        setTitle("Complex Example");
				images = new ArrayList<ImageIcon>();
				
				File folder = new File(IM_PATH);
				File[] listOfFiles = folder.listFiles();
				
				for(int i = 0; i < listOfFiles.length; i++) {
					if(listOfFiles[i].isFile()) {
						String path = listOfFiles[i].getPath();
						if(path.substring(path.lastIndexOf('.') + 1).equals("png")) {
							images.add(new ImageIcon(path)); 
//							System.out.println(path);
						}
					}
				}
				
//				System.out.println(images.isEmpty() + "   " + images.size());
				
        backingPanel  = new JPanel(new GridLayout(5,1, 7, 0));
				backingPanel.setSize(GAME_BOARD_SIZE);
//        JButton cardb = new JButton("Get Random Card");
//        cardb.setFocusable(false);
				
				FlowLayout houseLayout = new FlowLayout(FlowLayout.LEFT, 5, 0);
				// create the four main panels in the GUI - one for each player.
				player1 = new MITH_Player_Panel();
				player1.id = 1;
				player1.imageStem = "jcarter";
				player1.setLayout(houseLayout);
				player1.setBackground(new Color(50,150, 50));
				player1.setOpaque(false);
				player2 = new MITH_Player_Panel();
				player2.id = 2;
				player2.imageStem = "generic-female";
				player2.setLayout(houseLayout);
				player2.setBackground(new Color(50,130, 50));
				player2.setOpaque(false);
				player3 = new MITH_Player_Panel();
				player3.id = 3;
				player3.imageStem = "generic-male";
				player3.setLayout(houseLayout);
				player3.setBackground(new Color(50,150, 150));
				player3.setOpaque(false);
				player4 = new MITH_Player_Panel();
				player4.id = 4;
				player4.imageStem = "batman";
				player4.setLayout(houseLayout);
				player4.setBackground(new Color(50,190, 50));
				player4.setOpaque(false);
				
				JPanel slot = new JPanel();
				slot.setOpaque(false);
				
				
				// add the player panels to
				if(num_players > 3) 	
					backingPanel.add(player1);
				else
					backingPanel.add(slot);
				if(num_players > 2)
					backingPanel.add(player2);
				else
					backingPanel.add(slot);
					
				backingPanel.add(player3);
				backingPanel.add(player4);
				
				
        drawPile = new JPanel();
        fromCard = new MITH_Label();
        
        fromCard.setIcon(images.get(0));
        drawPile.add(fromCard);
        drawPile.setOpaque(false);
        drawPile.setBackground(Color.green);
        drawPile.setPreferredSize(CARD_DIM);


        discardPile = new JPanel();
        toCard = new MITH_Label();
        discardPile.setBackground(Color.blue);
        discardPile.setOpaque(false);
        discardPile.setPreferredSize(CARD_DIM);
        discardPile.add(toCard);
				
				setPreferredSize(GAME_BOARD_SIZE);

//        player1.add(drawPile);
//        player2.add(discardPile);

        JPanel player1Pic = new JPanel();
        player1Pic.setOpaque(false);
        player1Pic.setPreferredSize(PIC_DIM);
        player1Pic.add(new JLabel(new ImageIcon(PIC_PATH + player1.imageStem + "-sm.png")));
        playerPics.add(player1Pic);
        player1.add(player1Pic);
        player1.playerPic = player1Pic;
        
        // add the moose in the house slot to the house
        player1.add(houses.get(0).get(0));
        
        JPanel player2Pic = new JPanel();
        player2Pic.setOpaque(false);
        player2Pic.setPreferredSize(PIC_DIM);
        player2Pic.add(new JLabel(new ImageIcon(PIC_PATH + player2.imageStem + "-sm.png")));
        playerPics.add(player2Pic);
        player2.add(player2Pic);
        player2.playerPic = player2Pic;
        
        // add the moose in the house slot to the house
        player2.add(houses.get(1).get(0));

        JPanel player3Pic = new JPanel();
        player3Pic.setOpaque(false);
        player3Pic.setPreferredSize(PIC_DIM);
        player3Pic.add(new JLabel(new ImageIcon(PIC_PATH + player3.imageStem + "-sm.png")));
        playerPics.add(player3Pic);
        player3.add(player3Pic);
        player3.playerPic = player3Pic;
        
        // add the moose in the house slot to the house
        player3.add(houses.get(2).get(0));
        
        JPanel player4Pic = new JPanel();
        player4Pic.setOpaque(false);
        player4Pic.setPreferredSize(PIC_DIM);
        player4Pic.add(new JLabel(new ImageIcon(PIC_PATH + player4.imageStem + "-sm.png")));
        player4.add(player4Pic);
        player4.playerPic = player4Pic;


        player4.add(houses.get(3).get(0));

				// set up the hand and deck panels
				userPanel = new JPanel(houseLayout);
				userPanel.setBackground(new Color(40,100, 90));
				userPanel.setOpaque(false);
				
				handPanel = new MITH_Hand_Panel();
				handPanel.setLayout(houseLayout);
				handPanel.setOpaque(false);
				handPanel.cards = new ArrayList<MITH_Label>();
				handPanel.setPreferredSize(new Dimension(525, CARD_HEIGHT));
//				handPanel.setBackground(new Color(0,0,0,0));
				
//				deckPanel = new JPanel(houseLayout);
//				deckPanel.setBackground(Color.red);
//				deckPanel.add(drawPile);
//				deckPanel.add(discardPile);
				
				userPanel.add(handPanel);
				userPanel.add(drawPile);
				userPanel.add(discardPile);
				backingPanel.add(userPanel);
        backingPanel.setOpaque(false);
//				setBackground(new Color(128, 255, 29));
				setOpaque(true);
        add(backingPanel, JLayeredPane.DEFAULT_LAYER);
        
        MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
        addMouseListener(myMouseAdapter);
        addMouseMotionListener(myMouseAdapter);
    } 
		
		public void paintComponent(Graphics g) {
	   	g.drawImage(backgroundImg, 0, 0, null);
  	}
		
    private class MyMouseAdapter extends MouseAdapter {
        private MITH_Label dragLabel = null;
        private boolean drawDragLabel = false;
        private int dragLabelWidthDiv2;
        private int dragLabelHeightDiv2;
        private JPanel clickedPanel = null;
				Component[] components;
				
        @Override
				public void mousePressed(MouseEvent me) {
      	
          clickedPanel = (JPanel) backingPanel.getComponentAt(me.getPoint());
          if(clickedPanel == userPanel) {
          	System.out.println("User panel clicked");
          	JPanel source = (JPanel) clickedPanel.getComponentAt((int)(me.getPoint().getX() - clickedPanel.getX()), (int)(me.getPoint().getY() - clickedPanel.getY()));
          	if(source == drawPile) {
          		System.out.println("Draw clicked");
          		components = source.getComponents();

          		if(!deck.isEmpty() && 
          			 components.length > 0 &&
          		   components[0] instanceof JLabel) 
          		{
							  // remove label from panel
	              dragLabel = (MITH_Label) components[0];
	              
	              source.remove(dragLabel);
	              source.revalidate();
	              source.repaint();
	              
	              // replace the removed panel with a new one
				        if(dragLabel == fromCard) {
	              	dragLabel.card = deck.draw();
	              	dragLabel.setIcon(new ImageIcon(IM_PATH + dragLabel.card.getImage()));
				        	drawDragLabel = true;
				        	if(!deck.isEmpty()) {
				        		
				        		fromCard = new MITH_Label();
					        	fromCard.setIcon(images.get(0));
										drawPile.add(fromCard);
									}
								}
								
	          	}
          		else
          		{
          			drawDragLabel = false;
          		}

          	}
          	else if(source == handPanel) {
          		System.out.println("handPanel clicked");
          		Component component = source.getComponentAt((int)(me.getPoint().getX() - userPanel.getX()), (int)(me.getPoint().getY() - userPanel.getY()));
          		
//          		System.out.println(cardLabel);
          		if(component != null && 
         			 	component instanceof MITH_Label) {
           			MITH_Label cardLabel = (MITH_Label) component;
          			System.out.println("Clicked on " + cardLabel.card);
          			dragLabel = cardLabel;
          			drawDragLabel = true;
          			removeFromHand(cardLabel);
          		}
          		else
          		{
          			drawDragLabel = false;
          		}
          		
          	}
          	else if(source == discardPile) {
          		System.out.println("discardPile clicked");
          		return;
          	}
          	else {
          		drawDragLabel = false;
          	}
          	
          	if(drawDragLabel) {
        			dragLabelWidthDiv2 = dragLabel.getWidth() / 2;
              dragLabelHeightDiv2 = dragLabel.getHeight() / 2;

              int x = me.getPoint().x - dragLabelWidthDiv2;
              int y = me.getPoint().y - dragLabelHeightDiv2;
              dragLabel.setLocation(x, y);
              add(dragLabel, JLayeredPane.DRAG_LAYER);
              repaint();
            }
          }
          
          Component[] components = clickedPanel.getComponents();
          JPanel target = (JPanel) clickedPanel.getComponentAt((int)(me.getPoint().getX() - clickedPanel.getX()), (int)(me.getPoint().getY() - clickedPanel.getY()));
//          System.out.println(target);
          if (components.length == 0) {
          		System.out.println("no component clicked");
              return;
          }
          else
          {
          	if(components[0] instanceof JPanel) {   // this should always be true...
//          		System.out.println(components[0]);
          		clickedPanel = (JPanel) components[0];
          		components = clickedPanel.getComponents();
          	}
          	else {
          		System.out.println("Clicked on something other than a JPanel in LayeredPane!");
          	}
          		
//            	System.out.println("There are " + components.length + " components under there.");
//            	System.out.println(components[0].toString());
          }

      	} // end mousePressed

        @Override
        public void mouseDragged(MouseEvent me) {
            if (dragLabel == null) {
                return;
            }
            int x = me.getPoint().x - dragLabelWidthDiv2;
            int y = me.getPoint().y - dragLabelHeightDiv2;
            dragLabel.setLocation(x, y);
            repaint();
        }// end mouseDragged

        @Override
        public void mouseReleased(MouseEvent me) {
            MITH_Player_Panel playerPanel = null;
            JPanel target = null;
            Component[] components;

            if (dragLabel == null) {
                return;
            }
            
            remove(dragLabel); // remove dragLabel from drag layer of JLayeredPane
            
            JPanel droppedPanel = (JPanel) backingPanel.getComponentAt(me.getPoint());  // this is the panel the card was released over

            if(droppedPanel instanceof MITH_Player_Panel) {
            	playerPanel = (MITH_Player_Panel) droppedPanel; //
//							System.out.println("[" + playerPanel.getX() + ", " + playerPanel.getY() + "]");
            	target = (JPanel) playerPanel.getComponentAt((int)(me.getPoint().getX() - playerPanel.getX()), (int)(me.getPoint().getY() - playerPanel.getY()));
            	
            	if(target != null)
            	{
//            		System.out.println(target);
            		if(target instanceof MITH_Room_Panel) {
            		// there's a room there, so replace the card that's there with this one
            		// need to check with game to make sure it's a valid move
            			placeCardInRoom(playerPanel, (MITH_Room_Panel) target);
            		}

            		else if(target instanceof MITH_Player_Panel)
            		// dropped on a blank area of a player's house, so add it as a new room
            		// need to check with the game to make sure this is a legal move
            		{
            			addARoom((MITH_Player_Panel) target);
	          		}
	          		

	          		else if(target == drawPile) {
	          			System.out.println("Dropped on draw pile");
           			 	components = discardPile.getComponents();
           			 	if(components.length != 0 &&
            				 components[0] instanceof JLabel) {
			            	discardPile.remove(components[0]);
			              discardPile.add(dragLabel);
			              System.out.println("Card discarded: " + dragLabel.card.toString());
			              discardPile.revalidate();
			            }
	          		}
	          		
	          		else if(target == discardPile) {
	          			
	          			discardCard();
	          		}
            	}
            	else {
            		System.out.println("Nothing under here.");
            	}
            }
            else if(droppedPanel == userPanel) {
            	System.out.println("Dropped on userPanel");
          		JPanel dest = (JPanel) droppedPanel.getComponentAt((int)(me.getPoint().getX() - droppedPanel.getX()), (int)(me.getPoint().getY() - droppedPanel.getY()));
            	if(dest == discardPile) {
            		System.out.println("Dropped on discard pile");
            		discardCard();
            	}
            	if(dest == drawPile) {
            		System.out.println("Dropped on draw pile");
            		if(handPanel.cards.size() < 6) // put that six in a constant or query the game
            		{
            			addToHand();
            		}
            		else
            		{
            			discardCard();
            		}
            	}
            	if(dest == handPanel) {
            		System.out.println("Dropped on user's hand");
            		if(handPanel.cards.size() < 6) // put that six in a constant or query the game
            		{ 
            			addToHand();
            		}
            		else // this should be avoided - made sense in testing, but in game it shouldn't allow a card from
            				 // the draw pile to be picked up if it's not a situation where the user is allowed to draw
            				 // in all of those situations there will be at most 5 cards in the hand; if we see this
            				 // it's broken somewhere
            		{
            			System.out.println("Hand full, card discarded");
            			discardCard();
            		}
            	}
            }

            
            repaint();
            dragLabel = null;
        }  // end mouseReleased
        
        /***************************************************
         removeFromHand - removes a card from the hand
         ***************************************************/
        private void removeFromHand(MITH_Label card) {
        	handPanel.remove(card);
        	handPanel.cards.remove(handPanel.cards.indexOf(card));
        	
        }
        
        /***************************************************
         addToHand() adds the released card to the user's
         hand
         ***************************************************/
        private void addToHand() {
        	handPanel.cards.add(dragLabel);
        	//redrawHand();
        	handPanel.add(dragLabel);
        	handPanel.revalidate();
        }
        
        /***************************************************
         redrawHand() - redraws the handPanel with the 
         current cards in the cards list
         ***************************************************/
        private void redrawHand() {
        	
        	// remove all the cards from the current panel
        	Component[] oldCards = handPanel.getComponents();
        	for(int i = 0; i < oldCards.length; i++) {
        		handPanel.remove(oldCards[i]);
        	}
        	
        	for(int j = 0; j < handPanel.cards.size(); j++) {
        		handPanel.add(handPanel.cards.get(j));
        	}
        	
        	handPanel.revalidate();
        	 
        }
        /***************************************************
         discardCard(target) places the released card's 
         image on the top of the discard pile and throws
         away the target object
         ***************************************************/
        private void discardCard() {
   			 	// get the list of components so we can see if there's a card
   			 	// there to remove - bad things happen when removing non-existent
   			 	// card
   			 	components = discardPile.getComponents();
   			 	
   			 	if(components.length != 0 &&		// non-zero length means there's a card there. No other object can be there
    				 components[0] instanceof JLabel) {
          	discardPile.remove(components[0]);
            discardPile.add(dragLabel);
            System.out.println("Card discarded: " + dragLabel.card.toString());
            discardPile.revalidate();
          }
				}
				
				 
        /*************************************************** 
         addARoom(playerPanel) adds the room represented by
         the card that was just released to the playerPanel
         it was released over
         ***************************************************/
        private void addARoom(MITH_Player_Panel playerPanel) {
    			MITH_Room_Panel newRoom = new MITH_Room_Panel();
    			newRoom.id = houses.get(playerPanel.id-1).size();
    			newRoom.setOpaque(false);
    			if(newRoom.id <= MAX_LG_ROOMS) {
	    			newRoom.add(dragLabel);
	    		}
	    		else {
	    			if(newRoom.id == MAX_LG_ROOMS + 1) {
	    				resizeHouse(houses.get(playerPanel.id - 1), playerPanel);
	    			}
	    			MITH_Label label = new MITH_Label();
						label.setIcon(new ImageIcon(SM_IM_PATH + dragLabel.card.getImage()));
						newRoom.add(label);
					}

    			houses.get(playerPanel.id-1).add(newRoom);
    			playerPanel.add(newRoom);
    			playerPanel.revalidate();
    			System.out.println(dragLabel.card.toString() + " dropped on player" + playerPanel.id + "'s house in slot " + newRoom.id + ".");
        	
        } // end addARoom
        
        /****************************************************
         placeCardInRoom(room) - adds the room card just
         released to the room it was released over, replacing
         that card's image
         ****************************************************/
        private void placeCardInRoom(MITH_Player_Panel playerPanel, MITH_Room_Panel roomPanel) {
    			//target = (JPanel) target;
    			Component[] cards = roomPanel.getComponents();
    			if(cards.length > 0 && cards[0] instanceof MITH_Label)
    			{
    				roomPanel.remove(cards[0]);
    				if(houses.get(playerPanel.id - 1).size() > MAX_LG_ROOMS + 1) {
		    			MITH_Label label = new MITH_Label();
							label.setIcon(new ImageIcon(SM_IM_PATH + dragLabel.card.getImage()));
							roomPanel.add(label);
    				}
    				else {
    					roomPanel.add(dragLabel);
						}
						
   					roomPanel.revalidate();
    				String oldCardString;
    				if(((MITH_Label) cards[0]).card == null) {
    					oldCardString = "in the entry of ";
    				} else {
    					oldCardString = "on a " + ((MITH_Label) cards[0]).card.toString() + " in";
    				}
    				System.out.println(dragLabel.card.toString() + " played " + oldCardString + " player" + playerPanel.id + "'s house in slot " + roomPanel.id + ".");
    			}
    		} // end placeCardInRoom     
    		
    		/*****************************************************
    		 resizeHouse() - resizes all the rooms inthe house to 
    		 smaller ones when we've run out of room
    		 *****************************************************/
    		private void resizeHouse(ArrayList<MITH_Room_Panel> house, MITH_Player_Panel player) {
    			System.out.println("Resizing house");
					
					player.playerPic.remove((player.playerPic.getComponents())[0]);
					player.playerPic.add(new JLabel (new ImageIcon(PIC_PATH + player.imageStem + "-tiny.png")));
					player.playerPic.setPreferredSize(TINY_PIC_DIM);

    			for(int i = 0; i < house.size(); i++) {
						Component[] components = house.get(i).getComponents();
						
						
						if(components[0] instanceof MITH_Label) {
							MITH_Label oldLabel = (MITH_Label) components[0];
							house.get(i).remove(oldLabel);
		    			MITH_Label label = new MITH_Label();
		    			label.card = oldLabel.card;
		    			if(label.card != null) // protect from an empty entry - which should never happen in a real game!!
								label.setIcon(new ImageIcon(SM_IM_PATH + oldLabel.card.getImage()));
							house.get(i).add(label);
							house.get(i).setPreferredSize(SM_CARD_DIM);
						}
					}
    		}   	
    } // end MyMouseAdapter

		
		private static void createAndShowUI() {
    	JFrame frame = new JFrame("Game Board Mockup");
    	frame.getContentPane().add(new GameBoardTest(4));
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.pack();
    	frame.setLocationRelativeTo(null);
    	frame.setVisible(true);
    
		}

    public static void main(String[] args) {
    	java.awt.EventQueue.invokeLater(new Runnable() {
    		public void run() {
    			createAndShowUI();
    		}
    	});
  	}
  	
  	private class MITH_Label extends JLabel {
  		private MITH_Card card;
  	}
  	
  	private class MITH_Room_Panel extends JPanel {
  		private int id;
  	}
		private class MITH_Hand_Panel extends JPanel {
			private ArrayList<MITH_Label> cards;
		}
		
  	private class MITH_Player_Panel extends JPanel {
  		private String imageStem;
  		private int id;
  		private JPanel playerPic;
  		

  	}
}// end GameBoardTest


