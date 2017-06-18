package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class IShape implements  Shape , Cloneable{
	
	@Override
	public void setPosition(Point position) {
		// TODO Auto-generated method stub
	}			

	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		// TODO Auto-generated method stub
	}

	@Override
	public Map<String, Double> getProperties() {
		// TODO Auto-generated method stub
		return null ;
	}

	@Override
	public void setColor(Color color) {
		// TODO Auto-generated method stub
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null ;
	}

	@Override
	public void setFillColor(Color color) {
		// TODO Auto-generated method stub
	}

	@Override
	public Color getFillColor() {
		// TODO Auto-generated method stub
		return null ;
	}

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		 Shape cloned = (Shape)super.clone();
		 HashMap m1 = new HashMap() ;
		 m1 =  (HashMap) cloned.getProperties() ;
		 HashMap m2= (HashMap) m1.clone() ;
	     cloned.setProperties(m2);
		 Point p1 = new Point();
    	 p1 = (Point) cloned.getPosition().clone();
	     cloned.setPosition(p1); 
        return  cloned ;
	}
}
