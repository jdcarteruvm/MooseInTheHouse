import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
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

		private static final Dimension GAME_BOARD_SIZE = new Dimension(700, 500);
    JPanel backingPanel;
    JPanel player1;
    JPanel player2;
    JPanel player3;
    JPanel player4;
    
    JPanel left;
		JPanel right;
		
		MITH_Label fromCard;
		MITH_Label toCard;
		Random randomGenerator;		
		MITH_Deck deck;
		static final String imageDirPath = "..\\resources\\images\\30dpi\\";
		
		
		ArrayList<ImageIcon> images;
		
    public GameBoardTest() {
				
				deck = new MITH_Deck();
				deck.shuffle();
				// shorten the deck for testing purposes:
//				for(int i = 0; i < 48; i++)
//					deck.draw();
				
				randomGenerator = new Random();
				
//        setTitle("Complex Example");
				images = new ArrayList<ImageIcon>();
				
				File folder = new File(imageDirPath);
				File[] listOfFiles = folder.listFiles();
				
				for(int i = 0; i < listOfFiles.length; i++) {
					if(listOfFiles[i].isFile()) {
						String path = listOfFiles[i].getPath();
						if(path.substring(path.lastIndexOf('.') + 1).equals("png")) {
							images.add(new ImageIcon(path)); 
							System.out.println(path);
						}
					}
				}
				
//				System.out.println(images.isEmpty() + "   " + images.size());
				
        backingPanel  = new JPanel(new GridLayout(4,1, 7, 0));
				backingPanel.setSize(GAME_BOARD_SIZE);
//        JButton cardb = new JButton("Get Random Card");
//        cardb.setFocusable(false);
				
				player1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
				player1.setBackground(new Color(50,150, 50));
				player2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
				player2.setBackground(new Color(50,130, 50));
				player3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
				player3.setBackground(new Color(50,150, 150));
				player4 = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
				player4.setBackground(new Color(50,190, 50));
				
				backingPanel.add(player1);
				backingPanel.add(player2);
				backingPanel.add(player3);
				backingPanel.add(player4);
				
				
        left = new JPanel();
        fromCard = new MITH_Label();
        
        fromCard.setIcon(images.get(0));
        left.add(fromCard);
        left.setBackground(Color.green);
        left.setPreferredSize(new Dimension(75, 110));

//        cardb.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent event) {
//                int index = randomGenerator.nextInt(images.size());
//
//                fromCard.setIcon(images.get(index));
////                Color color = clr.showDialog(panel, "Choose Color", Color.white);
////                left.setBackground(color);
//            }
//        });

        right = new JPanel();
        toCard = new MITH_Label();
        right.setBackground(Color.blue);
        right.setPreferredSize(new Dimension(75, 110));
        right.add(toCard);
				
				setPreferredSize(GAME_BOARD_SIZE);
//        backingPanel.add(cardb);
        player1.add(left);
        player2.add(right);
        add(backingPanel, JLayeredPane.DEFAULT_LAYER);
        
        MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
        addMouseListener(myMouseAdapter);
        addMouseMotionListener(myMouseAdapter);

    }

    private class MyMouseAdapter extends MouseAdapter {
        private MITH_Label dragLabel = null;
        private int dragLabelWidthDiv2;
        private int dragLabelHeightDiv2;
        private JPanel clickedPanel = null;

        @Override
        public void mousePressed(MouseEvent me) {
            clickedPanel = (JPanel) backingPanel.getComponentAt(me.getPoint());
            
            Component[] components = clickedPanel.getComponents();
            if (components.length == 0) {
            		System.out.println("no component clicked");
                return;
            }
            else
            {
            	if(components[0] instanceof JPanel) {
            		clickedPanel = (JPanel) components[0];
            		components = clickedPanel.getComponents();
            	}
            	
//            	System.out.println("There are " + components.length + " components under there.");
//            	System.out.println(components[0].toString());
            }
            // if we click on jpanel that holds a jlabel
            if (clickedPanel != right && 
            		components[0] instanceof JLabel && 
								!deck.isEmpty()) {
								
                // remove label from panel
                dragLabel = (MITH_Label) components[0];
                
                clickedPanel.remove(dragLabel);
                clickedPanel.revalidate();
                clickedPanel.repaint();
                
                // replace the removed panel with a new one
				        if(dragLabel == fromCard) {
                	dragLabel.card = deck.draw();
                	dragLabel.setIcon(new ImageIcon(imageDirPath + dragLabel.card.getImage()));
				        	
				        	if(!deck.isEmpty()) {
				        		
				        		fromCard = new MITH_Label();
					        	fromCard.setIcon(images.get(0));
										left.add(fromCard);
									}
								}
								
                dragLabelWidthDiv2 = dragLabel.getWidth() / 2;
                dragLabelHeightDiv2 = dragLabel.getHeight() / 2;

                int x = me.getPoint().x - dragLabelWidthDiv2;
                int y = me.getPoint().y - dragLabelHeightDiv2;
                dragLabel.setLocation(x, y);
                add(dragLabel, JLayeredPane.DRAG_LAYER);
                repaint();
            }
        }

        @Override
        public void mouseDragged(MouseEvent me) {
            if (dragLabel == null) {
                return;
            }
            int x = me.getPoint().x - dragLabelWidthDiv2;
            int y = me.getPoint().y - dragLabelHeightDiv2;
            dragLabel.setLocation(x, y);
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            if (dragLabel == null) {
                return;
            }
            remove(dragLabel); // remove dragLabel for drag layer of JLayeredPane
            
            JPanel droppedPanel = (JPanel) backingPanel.getComponentAt(me.getPoint());
            Component[] components = droppedPanel.getComponents();
						if(components.length == 0) {
							return;
						} else {
	           	if(components[0] instanceof JPanel) {
            		droppedPanel = (JPanel) components[0];
            		components = droppedPanel.getComponents();
            	}
            }
            // remove the label that's already there if there is one 
            // (this will change to ignore if there's already one there)
            if (droppedPanel == right &&
            		components.length != 0 &&
            		components[0] instanceof JLabel) {
            	droppedPanel.remove(components[0]);
              droppedPanel.add(dragLabel);
              System.out.println("Card played: " + dragLabel.card.toString());
              droppedPanel.revalidate();
            }
            
            if (droppedPanel != right) {
                Component[] oldComponents = clickedPanel.getComponents();
                if(oldComponents.length != 0)
	                clickedPanel.remove(oldComponents[0]);
                clickedPanel.add(dragLabel);
                if(clickedPanel == left)
	                fromCard = dragLabel;
                clickedPanel.revalidate();
            } 
          

            repaint();
            dragLabel = null;
        }
    }

		
		private static void createAndShowUI() {
    	JFrame frame = new JFrame("Game Board Mockup");
    	frame.getContentPane().add(new GameBoardTest());
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
  		public MITH_Card card;
  	}
}


