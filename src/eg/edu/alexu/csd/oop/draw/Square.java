package eg.edu.alexu.csd.oop.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Square extends IShape {

	Color fillColor ;
	Color color  ;
	Point point = new Point() ;
	
	Map<String, Double> properties = new HashMap() ;

	
	public Square()
	{
		properties.put("length",null) ;	
	}
	@Override
	public void setPosition(Point position) {
		// TODO Auto-generated method stub
		this.point = position ;
	}			

	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub
		return this.point;
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		// TODO Auto-generated method stub
		this.properties = properties ;
	}

	@Override
	public Map<String, Double> getProperties() {
		// TODO Auto-generated method stub
		return this.properties ;
	}

	@Override
	public void setColor(Color color) {
		// TODO Auto-generated method stub
		this.color = color ;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return this.color;
	}

	@Override
	public void setFillColor(Color color) {
		// TODO Auto-generated method stub
		this.fillColor = color ;
	}

	@Override
	public Color getFillColor() {
		// TODO Auto-generated method stub
		return this.fillColor ;
	}

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
		Graphics2D canva = (Graphics2D) canvas;
	    canva.setStroke(new BasicStroke(5));
		canva.setColor(this.getFillColor());
		canva.fillRect(this.getPosition().x, this.getPosition().y,this.properties.get("length").intValue(),this.properties.get("length").intValue()) ;
		canva.setColor(this.getColor());
		canva.drawRect(this.getPosition().x, this.getPosition().y,this.properties.get("length").intValue(),this.properties.get("length").intValue()) ;
	
	}

}
