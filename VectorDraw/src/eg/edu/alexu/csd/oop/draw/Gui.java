package eg.edu.alexu.csd.oop.draw;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Point;
import java.awt.TextField;
import java.util.Map;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Gui{
    	
	 Color[] colors = {Color.BLUE, Color.GRAY, Color.RED , Color.black , Color.CYAN , Color.darkGray , Color.gray ,
			 Color.GREEN , Color.lightGray , Color.magenta , Color.orange , Color.pink , Color.yellow , Color.white};
     String[] strings = {"blue", "gray", "red" , "black" , "cyan" , "darkGray" , "gray" , "GREEN" ,
    		 "lightGray" , "magenta" , "orange"  , "pink" , "yellow" , "white"};
    JComboBox comboColor1 = new JComboBox(strings);
    JComboBox comboColor2 = new JComboBox(strings);
    
    JFrame frame = new JFrame() ;
	JFrame temp = new JFrame() ;
	JFrame temp2 = new JFrame() ;
	JComboBox box = new JComboBox();
	JMenu menu2 = new JMenu("Action") ;
    JMenuBar menuBar = new JMenuBar();

    Button btn2 = new Button("enter") ;
	Button [] shapes  ;
	
	Button ellipse = new Button("Oval") ;
	Button ok1 = new Button("ok") ;
	Button ok2 = new Button("ok") ;
	Button okSave = new Button("ok") ; // confirm save
	Button okLoad  = new Button("ok") ; // confirm load
	
	JMenuItem undo = new JMenuItem("Undo") ;
	JMenuItem redo = new JMenuItem("Redo") ; 
	JMenu update = new JMenu("update") ;
	JMenuItem remove = new JMenuItem("remove") ; 
	JMenuItem move = new JMenuItem("move") ; 
	JMenuItem resize = new JMenuItem("resize") ; 
	JMenuItem save = new JMenuItem("save") ;
	JMenuItem load = new JMenuItem("load") ;
	
	JPanel panelShape = new JPanel();
	JPanel panel01 = new JPanel() ; 
	JFrame frame2 ;
	
	JLabel saveAndLoadLabel = new JLabel("path :") ;
	JLabel labelx ;
	JLabel labely ;	
	TextField xPos ;
	TextField yPos ;
	TextField saveAndLoadTxt = new TextField(30) ;
	public Gui()
	{
		
		// panel for set position
		panel01.setLayout(new FlowLayout());
	    labelx = new JLabel("x : ") ;
		labely = new JLabel("y : ") ;	
		xPos = new TextField(7);
		yPos = new TextField(7);
		panel01.add(labelx);
		panel01.add(xPos);
		panel01.add(labely);
		panel01.add(yPos);
		
		frame.setJMenuBar(menuBar);
		menuBar.add(menu2) ;
		menuBar.add(box) ;
		
		panelShape.setLayout(new BoxLayout(panelShape, BoxLayout.X_AXIS));
		panelShape.setSize(300, 10);
		
		btn2.setSize(50,50);
		menu2.add(undo) ;
		menu2.add(redo) ;
		menu2.add(remove) ;
		menu2.add(update) ;
		menu2.add(save) ;
		menu2.add(load) ;
		update.add(move) ;
		update.add(resize) ;

		frame.add(panelShape , BorderLayout.NORTH);		
		frame.setSize(1000,1000) ;
		
	    temp2.add(saveAndLoadLabel) ;
		temp2.add(saveAndLoadTxt) ;
		temp2.setLayout(new FlowLayout() ) ;
		temp2.setSize(300, 100);
		
	}
	
	public void setPos()
	{
		temp = new JFrame() ;        
		xPos.setText("");
		yPos.setText("");
		temp.add(panel01) ;
		temp.add(ok1);
		temp.setSize(300,100);
		temp.setLayout(new FlowLayout());
		temp.setVisible(true);
	}
	
	public void setProperiesLayout(Set<String> set , TextField [] txt , JFrame tmp)
	{
		int i = 2 ;
		for(String itr : set)
		{
			JPanel panel = new JPanel() ; 
			panel.setLayout(new FlowLayout());
			JLabel label = new JLabel(itr + ": ") ;
			TextField t = new TextField(7);
			txt[i] = t ;
			panel.add(label) ;
			panel.add(t) ;
			tmp.add(panel) ;
			i++ ;
		}
	}
	
	public TextField [] resiz(Map<String, Double> properties)
	{
		temp = new JFrame() ;
		Set<String> set = properties.keySet();
		TextField [] txt = new TextField[1000]  ;
		
		setProperiesLayout(set , txt , temp) ;
		temp.add(ok2);
		temp.setSize(350,350);
		temp.setLayout(new FlowLayout());
		temp.setVisible(true);
		return txt ;
	}
	
	public TextField [] interact(Map<String, Double> properties ) {
		// TODO Auto-generated method stub
		frame2 = new JFrame();
		JLabel label1 = new JLabel("Set Color:");
		JLabel label2 = new JLabel("Set Fill Color:");
		JPanel panel1 = new JPanel() ; 
		panel1.setLayout(new FlowLayout());
		JPanel panel2 = new JPanel() ; 
		panel2.setLayout(new FlowLayout());
		frame2.setLayout(new FlowLayout());
		panel1.add(label1);
		panel1.add(comboColor1);
		panel1.add(label2);
		panel1.add(comboColor2);
		frame2.add(btn2) ;
		frame2.setSize(350,350);
		frame2.add(panel01);
		
		xPos.setText("");
		yPos.setText("");
		
		Set<String> set = properties.keySet();
		TextField [] txt = new TextField[1000]  ;
		
		txt[0] = xPos ;
		txt[1] = yPos ;
		
		setProperiesLayout(set ,txt ,frame2) ;
		
		frame2.add(panel1);
		frame2.add(panel2);
		frame2.add(btn2) ;
		frame2.setVisible(true);
		frame2.pack();
		return txt;	
	}
}
