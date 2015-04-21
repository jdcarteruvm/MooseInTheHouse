import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;



public class MITH_Button extends JButton
{


   MITH_Button(String text) 
   {
			super(text);
			//addActionListener(this);
         //add(table);
         
	}
   
   
   public void paintComponent(Graphics g) 
   {
			Rectangle r = getBounds();
			int x = r.x + 20;
			int y = r.y + 20;
			int width = r.width - 40;
			int height = r.height- 40;
			g.setColor(Color.BLACK);
			g.fillOval(x, y, width, height);
			x += 2;
			y += 2;
			width -= 4;
			height -= 4;
			g.setColor(new Color(50,150,50));
			g.fillOval(x, y, width, height);
			g.setColor(new Color(50,150,50));
			y += (height / 2) - 10;
			g.drawString(getText(), x, y);
         setVisible(true); 
         
	}
   
   
   
   
   
   
   
   
}