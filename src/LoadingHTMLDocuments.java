import java.awt.BorderLayout;
import java.io.FileReader;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.text.html.HTMLEditorKit;

public class LoadingHTMLDocuments 
{
    public static void main(String args[])throws Exception 
    {
       JFrame frame = new JFrame("Tab Attributes");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
       JEditorPane editorPane = new JEditorPane();
       editorPane.setEditorKit(new HTMLEditorKit());
       
       String filename = "...src/gameRules.html";
       
       FileReader reader = new FileReader(filename);
       editorPane.read(reader, filename);
   
       JScrollPane scrollPane = new JScrollPane(editorPane);
       frame.add(scrollPane, BorderLayout.CENTER);
   
       frame.setSize(300, 150);
       frame.setVisible(true);
  }
}