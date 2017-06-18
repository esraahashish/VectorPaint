package eg.edu.alexu.csd.oop.draw;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.List;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.peer.CanvasPeer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class controller implements ActionListener{
	DrawingEngine drawing  ;
	JMenuItem item  ;
	Gui gui ;
	Shape shape ;
	DrawPanel panel ;
	Set <String> set ;
	Map<String,Double> tmp ;
    TextField arr [] ;
	java.util.List<Class<? extends Shape>> list ;
 
    
	 public controller()
	 {
		drawing = new IDrawingEngine() ;
		item = new JMenuItem() ;
		gui = new Gui() ;
		shape = new IShape();
		panel = new DrawPanel() ;
		list = new LinkedList<Class<? extends Shape>>() ;
		list = drawing.getSupportedShapes() ;
		gui.shapes = new Button[list.size()] ;
		for(int i = 0 ; i < list.size() ; i++)
		{
			//System.out.println(list.get(i).getSimpleName());
			gui.shapes[i] = new Button(list.get(i).getSimpleName()) ;
			gui.shapes[i].addActionListener(new Reflection());
			gui.panelShape.add(gui.shapes[i]) ;
		}
		 gui.frame.setVisible(true) ;
		 gui.btn2.addActionListener(new Enter());
		 gui.undo.addActionListener(new Undo());
		 gui.redo.addActionListener(new Redo());
		 gui.remove.addActionListener(new Remove());
		 gui.move.addActionListener(new Move());
		 gui.resize.addActionListener(new Resize());
		 gui.ok1.addActionListener(new SetPos());
		 gui.ok2.addActionListener(new Dimension());
		 gui.save.addActionListener(new Save());
		 gui.okSave.addActionListener(new SaveConfirm() );
		 gui.okLoad.addActionListener(new LoadConfirm());
		 gui.load.addActionListener(new Load());
		 
	 }
	 
	 // save button command
     public class Save implements ActionListener
     {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			gui.saveAndLoadTxt.setText("");
			gui.temp2.remove(gui.okLoad);
			gui.temp2.add(gui.okSave) ;
			gui.temp2.setVisible(true);
		} 
     }
     
     // save button confirmation
     public class SaveConfirm implements ActionListener
     {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			drawing.save(gui.saveAndLoadTxt.getText());
			gui.temp2.setVisible(false);
		}
    	 
     }
     
     // load button command
     public class Load implements ActionListener
     {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			gui.saveAndLoadTxt.setText("");
			gui.temp2.remove(gui.okSave);
			gui.temp2.add(gui.okLoad) ;
			gui.temp2.setVisible(true);
		}
    	 
     }
     
  // load button confirmation
     public class LoadConfirm implements ActionListener
     {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			drawing.load(gui.saveAndLoadTxt.getText());
			gui.temp2.setVisible(false);
			refresh() ;
		}
    	 
     }
 
	 // take dimensions of the shape
	 public void iterate()
	 {
		 int i = 2 ;
		 tmp = new HashMap() ;
		 for(String itr : set)
		 {
			   String t = arr[i].getText() ;
			   tmp.put(itr,Double.parseDouble(t)) ;
			   i++ ;
		 }
	 }
	 
	 
	 public class Resize implements ActionListener
	 {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			 Shape old = (Shape) gui.box.getSelectedItem();
			 arr = gui.resiz(old.getProperties()) ;
		}
	 }
	 
	// command for ok button of resizing 
	 public class Dimension implements ActionListener
	 {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Shape old = (Shape) gui.box.getSelectedItem();
			Shape updated = null ;
			gui.temp.setVisible(false);
			try {
				  updated = (Shape) old.clone() ;
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			iterate();
			updated.setProperties(tmp);
			drawing.updateShape(old,updated);
			refresh();
		}
	 }
	 
	 public void addMenu()
	 {
		Shape[] tmp = drawing.getShapes(); 
		gui.box.removeAllItems();
		for(int i = 0  ;(( i < tmp.length) && (tmp[i]!=null)) ; i++ )
		{
				   gui.box.addItem(tmp[i]) ;
     	}
	 }
	 
	 
	 public class Remove implements ActionListener
	 {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			 Shape obj = (Shape) gui.box.getSelectedItem() ;
			 drawing.removeShape(obj);
			 refresh();
	}
	 }
	 
	 // gui to update the position of the old shape 
	 public class Move implements ActionListener
	 {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			gui.setPos() ;
		}
	 }
	 
	 
	 public class SetPos implements ActionListener  // ok button of the move update 
	 {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				gui.temp.setVisible(false);
				Shape old = (Shape) gui.box.getSelectedItem();
				Shape updated = new IShape() ; 
				
				// make  a clone of the old shape
				try {
					updated = (Shape) old.clone();
				} catch (CloneNotSupportedException e1) {
					e1.printStackTrace();
				} 
				
				Double d1 = Double.parseDouble(gui.xPos.getText()) ;
				Double d2 = Double.parseDouble(gui.yPos.getText()) ;
				Point p = new Point(d1.intValue(),d2.intValue()) ;
	            updated.setPosition(p);  
	            drawing.updateShape(old,updated);
				refresh();			
	        }
	 }
	 
	 
	 public class Undo implements ActionListener
	 {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				drawing.undo();
				refresh();
			}
	  }
	 
	 public class Redo implements ActionListener
	 {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				drawing.redo() ;
				 refresh();
		}
			
	}
	 
	 public class Reflection implements ActionListener
	 {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				   String name = e.getActionCommand() ;  
				   //reflection code ;
				//	shape = (IShape) Class.forName("eg.edu.alexu.csd.oop.draw."+name).newInstance() ;
					for(int i = 0;i<list.size();i++)
					{
						if(list.get(i).getSimpleName().equals(name))
						{
							try {
								shape = (Shape) list.get(i).newInstance() ;
								break ;
							} catch (InstantiationException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IllegalAccessException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				   tmp = new HashMap();
				   arr = gui.interact(shape.getProperties());
				   tmp = shape.getProperties() ;
				   set = tmp.keySet() ;
			}
			
	}
	 
	
	public class Enter implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
					Double d1 = Double.parseDouble(arr[0].getText()) ;
					Double d2 = Double.parseDouble(arr[1].getText()) ;
					Point p = new Point(d1.intValue(),d2.intValue()) ;
				   shape.setPosition(p);
				   iterate();
				   gui.frame2.setVisible(false) ;
				   shape.setProperties(tmp);
				   shape.setColor(gui.colors[gui.comboColor1.getSelectedIndex()]);
				   shape.setFillColor(gui.colors[gui.comboColor2.getSelectedIndex()]);
				   drawing.addShape(shape) ;
				   refresh();
				   
		}
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub		
	}
	
    class DrawPanel extends Canvas{
 
		  DrawPanel(){
    	  super();
    	  super.setSize(600,600);
		  super.setBackground(Color.white);
		  super.setLocation(14, 24);
    	}
  
		public void paint(Graphics g)  // draw graphics in the panel
		{
	       drawing.refresh(g) ;  
		}
	
    }
    
    public void refresh()
    {
    	   addMenu() ;
		   panel.setBackground(Color.white);
		   gui.frame.add(panel) ;
		   gui.menuBar.updateUI(); // let the menu appears over the canvas
    }

}
