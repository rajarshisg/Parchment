import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Parchment implements ActionListener{
    
    //the notepad consists of mainly two parts, a frame and a text area
    private JTextArea text; //reference of JTextArea
    private JFrame frame; //reference of JFrame

    Parchment(){ //default constructor
      
      frame=new JFrame("Parchment"); //creating a frame
      text=new JTextArea(); //creating a text editor area
    
      JMenuBar mb=new JMenuBar(); //creating a menu bar for the JFrame
      
      JMenu m1=new JMenu("File");//creating a "File" menu
      
      //creating menu items for File
      JMenuItem mi1=new JMenuItem("New");
      JMenuItem mi2=new JMenuItem("Open");
      JMenuItem mi3=new JMenuItem("Save");
      //JMenuItem mi4=new JMenuItem("Save As");
      JMenuItem mi5=new JMenuItem("Print");
      JMenuItem mi6=new JMenuItem("Exit");
      
      //adding the menu items
      m1.add(mi1);
      m1.add(mi2);
      m1.add(mi3);
      //m1.add(mi4);
      m1.add(mi5);
      m1.add(mi6);
      
      //adding the action listeners to the menu items
      mi1.addActionListener(this);
      mi2.addActionListener(this);
      mi3.addActionListener(this);
      //mi4.addActionListener(this);
      mi5.addActionListener(this);
      mi6.addActionListener(this);
      
      JMenu m2=new JMenu("Edit");//creating a Edit menu
      
      //creating menu items for Edit
      JMenuItem mi7=new JMenuItem("Cut");
      JMenuItem mi8=new JMenuItem("Copy");
      JMenuItem mi9=new JMenuItem("Paste");
      
      //adding the menu items
      m2.add(mi7);
      m2.add(mi8);
      m2.add(mi9);
     
      //adding the action listeners to the menu items
      mi7.addActionListener(this);
      mi8.addActionListener(this);
      mi9.addActionListener(this);
      
      /*JMenu m3=new JMenu("Tools");//creating a Tools Menu
      
      //creating menu items for tools
      JMenuItem mi10=new JMenuItem("Themes");
      JMenuItem mi11=new JMenuItem("Font");
      JMenuItem mi12=new JMenuItem("Font Size");
      
      //adding the menu items
      m3.add(mi10);
      m3.add(mi11);
      m3.add(mi12);
      
      //adding the action listeners to the menu items
      mi10.addActionListener(this);
      mi11.addActionListener(this);
      mi12.addActionListener(this);*/
      
      //adding the menus to the menu bar
      mb.add(m1); 
      mb.add(m2);
      //mb.add(m3);
      
      try{
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //sets the theme of JFrame to the OS theme
      }catch(Exception e){
      }
      Font font=new Font("Lucida Console",Font.PLAIN,14); //creating a new font
      text.setFont(font); //changing the font of JTextArea

      frame.setJMenuBar(mb); //adding the menu bar to JFrame
      frame.add(text); //adding the JTextArea to the JFrame
      frame.setSize(500,300); //setting the default opening size of JFrame to 500 pixel x 300 pixel
      frame.setVisible(true); //making the frame visible on screen
    }
    
    private void setTheme(){
      Color c2=new Color(253,137,159);
      Font font=new Font("Lucida Console",Font.PLAIN,14); //creating a new font
      text.setFont(font); //changing the font of JTextArea
      text.setForeground(c2);
      Color c1=new Color(43,53,74);
      
      text.setBackground(c1);
      frame.setBackground(c2);
    }
    
    public void actionPerformed(ActionEvent e){
        
        String st=e.getActionCommand(); //returns the command string associated with the action
        
        if(st.equals("Cut")){
            text.cut(); //performing cut
        }
        if(st.equals("Copy")){
            text.copy(); //performing copy
        }
        if(st.equals("Paste")){
            text.paste(); //performing paste
        }
        if(st.equals("New")){
            text.setText("");
        }
        if(st.equals("Print")){
           try{ 
              text.print();
            }catch(Exception et){
                JOptionPane.showMessageDialog(frame,et.getMessage());
            }
        }
        if(st.equals("Exit")){
            frame.setVisible(false);
        }
        if(st.equals("Open")){
        
           JFileChooser j=new JFileChooser("");
        
           int r=j.showOpenDialog(null);
	           
	   if(r==JFileChooser.APPROVE_OPTION){
	       File fi=new File(j.getSelectedFile().getAbsolutePath());
	               
	       try{
	            String s1="",s2="";
	                   
	            FileReader fr=new FileReader(fi);
	                   
	            BufferedReader br=new BufferedReader(fr);
	                   
	            s1=br.readLine();
	                   
	            while((s2=br.readLine())!=null){
	                       s1+="\n"+s2;
	            }
	            text.setText(s1);
	       }
	       catch(Exception et){
	                   JOptionPane.showMessageDialog(frame,et.getMessage());
	       }
	   }
	   else
	      JOptionPane.showMessageDialog(frame,"Please choose an appropriate file");   
        }
        if(st.equals("Save")){
                JFileChooser j=new JFileChooser("");
	        
	        int r=j.showSaveDialog(null);
	        
	        if(r==JFileChooser.APPROVE_OPTION){
	            File fi = new File(j.getSelectedFile().getAbsolutePath());
	            
	            try{
	                FileWriter wr=new FileWriter(fi,false);
	                
	                BufferedWriter w=new BufferedWriter(wr);
	                
	                w.write(text.getText());
	                w.flush();
	                w.close();
	               }
	               catch(Exception et){
	                   JOptionPane.showMessageDialog(frame,et.getMessage());
	               }
	           }
	           else
	             JOptionPane.showMessageDialog(frame,"Cancelled");
	}
    }
    
    public static void main(){
        Parchment ob=new Parchment();
    }
}

