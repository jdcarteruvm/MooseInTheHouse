import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

		private static final Dimension GAME_BOARD_SIZE = new Dimension(640, 480);
    JPanel backingPanel;
    JPanel left;
		JPanel right;
		
		JLabel fromCard;
		JLabel toCard;
		Random randomGenerator;		
		
		
		
		ArrayList<ImageIcon> images;
		
    public GameBoardTest() {

				randomGenerator = new Random();
				
//        setTitle("Complex Example");
				images = new ArrayList<ImageIcon>();
				
				File folder = new File("..\\resources\\images\\30dpi\\");
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
				
        backingPanel  = new JPanel(new FlowLayout(FlowLayout.LEFT, 50, 15));
				backingPanel.setSize(GAME_BOARD_SIZE);
        JButton cardb = new JButton("Get Random Card");
        cardb.setFocusable(false);
				
        left = new JPanel();
        fromCard = new JLabel();
        
        fromCard.setIcon(images.get(0));
        left.add(fromCard);
        left.setBackground(Color.green);
        left.setPreferredSize(new Dimension(85, 115));

        cardb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                int index = randomGenerator.nextInt(images.size());
                fromCard.setIcon(images.get(index));
//                Color color = clr.showDialog(panel, "Choose Color", Color.white);
//                left.setBackground(color);
            }
        });

        right = new JPanel();
        toCard = new JLabel();
        right.setBackground(Color.blue);
        right.setPreferredSize(new Dimension(85, 115));
        right.add(toCard);
				
				setPreferredSize(GAME_BOARD_SIZE);
        backingPanel.add(cardb);
        backingPanel.add(left);
        backingPanel.add(right);
        add(backingPanel, JLayeredPane.DEFAULT_LAYER);
        
        MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
        addMouseListener(myMouseAdapter);
        addMouseMotionListener(myMouseAdapter);

    }

    private class MyMouseAdapter extends MouseAdapter {
        private JLabel dragLabel = null;
        private int dragLabelWidthDiv2;
        private int dragLabelHeightDiv2;
        private JPanel clickedPanel = null;

        @Override
        public void mousePressed(MouseEvent me) {
            clickedPanel = (JPanel) backingPanel.getComponentAt(me.getPoint());
            Component[] components = clickedPanel.getComponents();
            if (components.length == 0) {
                return;
            }
            // if we click on jpanel that holds a jlabel
            if (components[0] instanceof JLabel) {

                // remove label from panel
                dragLabel = (JLabel) components[0];
                clickedPanel.remove(dragLabel);
                clickedPanel.revalidate();
                clickedPanel.repaint();
                
                // replace the removed panel with a new one
				        if(dragLabel == fromCard) {
				        	fromCard = new JLabel();
				        
					        fromCard.setIcon(images.get(0));
									left.add(fromCard);
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


            // remove the label that's already there if there is one 
            // (this will change to ignore if there's already one there)
            if (droppedPanel == right &&
            		components.length != 0 &&
            		components[0] instanceof JLabel) {
            	droppedPanel.remove(components[0]);
              droppedPanel.add(dragLabel);
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
}


