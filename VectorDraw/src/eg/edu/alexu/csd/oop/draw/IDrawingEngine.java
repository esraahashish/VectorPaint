package eg.edu.alexu.csd.oop.draw;

import java.awt.Graphics;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import javax.management.RuntimeErrorException;

public class IDrawingEngine implements DrawingEngine{

	private Stack<LinkedList> prev = new Stack<LinkedList>() ;
	private Stack<LinkedList> next = new Stack<LinkedList>() ;
	String[] extension = new String[3] ;
	private Shape [] current = new Shape[1000] ; 
	private int counter ;
	
	IDrawingEngine()
	{
		counter = 0 ; 
		prev.push(new LinkedList<Shape>()) ;
		return ;
	}
	@Override
	public void refresh(Graphics canvas) {
		// TODO Auto-generated method stub
		LinkedList<?> l = (LinkedList<?>) prev.peek();
		for(int i = 0;i<l.size();i++)
		{
			((Shape)l.get(i)).draw(canvas);
		}	
	}

	@Override
	public void addShape(Shape shape) {
		// TODO Auto-generated method stub
		counter++ ;
		LinkedList<Shape> tmp = new LinkedList<Shape>() ;
		tmp = (LinkedList<Shape>) prev.peek().clone() ;
		tmp.add(shape) ;
		prev.push(tmp) ;
		current = (Shape[]) tmp.toArray(current) ;
		if(next.size()>0)next.clear(); 
		if(prev.size()>21)prev.remove(0) ;
		
	}

	@Override
	public void removeShape(Shape shape) {
		// TODO Auto-generated method stub
		
		LinkedList<Shape> tmp = (LinkedList<Shape>) prev.peek().clone();
		boolean t = tmp.remove(shape);
		if(!t)throw new RuntimeErrorException(null) ;
		prev.push(tmp) ;
		current = (Shape[]) tmp.toArray(current) ;
		counter=tmp.size() ;
		if(next.size()>0)next.clear(); 
		if(prev.size()>21)prev.remove(0) ;
	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		// TODO Auto-generated method stub
		Shape updated = new IShape();
		try {
			updated  = (Shape) oldShape.clone() ;
			updated = newShape ;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LinkedList<Shape> tmp = (LinkedList<Shape>) prev.peek().clone();
		boolean t = tmp.removeLastOccurrence(oldShape);
		if(!t)throw new RuntimeErrorException(null) ;
		tmp.add(updated) ;
		prev.push(tmp) ;
		current = (Shape[]) tmp.toArray(current) ;
		if(next.size()>0)next.clear(); 
		if(prev.size()>21)prev.remove(0) ;

	}

	@Override
	public Shape[] getShapes() {
		// TODO Auto-generated method stub
		Shape [] s = new Shape[counter] ; 
		s = (Shape[]) prev.peek().toArray(s) ;
		return s;
	}

	@Override
	public List<Class<? extends Shape>> getSupportedShapes() {
		// TODO Auto-generated method stub
		List<Class<? extends Shape>> list = new LinkedList<Class<? extends Shape>>();
		list.add(Circle.class) ;
		list.add(Line.class) ;
		list.add(Oval.class) ;
		list.add(Square.class) ;
		list.add(Rectangle.class) ;
		list.add(Triangle.class) ;
		SearchClasses a = new SearchClasses();
		Iterator<Class<? extends Shape>> e = a.getClasses().iterator();
		while (e.hasNext()) {
			list.add((Class<? extends Shape>) e.next());
		}
		return list ;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		if(prev.size()>1)
		{
			next.push(prev.pop()) ;
			LinkedList<?> tmp = (LinkedList<?>) prev.peek() ;
			counter = tmp.size() ;
			current = (Shape[]) tmp.toArray(current) ;
		}else
		{
			throw new RuntimeErrorException(null) ;
		}
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		if(next.size()>0)
		{
			prev.push(next.pop());
			LinkedList<?> tmp = (LinkedList<?>) prev.peek() ;
			counter = tmp.size() ;
			current = (Shape[]) tmp.toArray(current) ;
		}else
		{
			throw new RuntimeErrorException(null) ;
		}
	}

	@Override
	public void save(String path) {
		// TODO Auto-generated method stub
		SaveXml xml ;
		SaveJson json ;
		String tpath = path.toLowerCase() ;
		if(tpath.endsWith(".xml"))
		{
		try{
			xml = new SaveXml(current,counter,path) ;
		}catch(Exception e){
			throw new RuntimeException("Some Useful Information",  e);
		}
		}else if(tpath.endsWith("json"))
		{
		try{
			json = new SaveJson(current,counter,path) ;
		}catch(Exception e){
			throw new RuntimeException("Some Useful Information",  e);
		}
		}else
			throw new RuntimeErrorException(null) ; 
	}
	@Override
	public void load(String path)
	{
		LoadXml xml ;
		LoadJson json ;
		String tpath = path.toLowerCase() ;
		LinkedList<Shape> list = new LinkedList<Shape>(); 
		if(tpath.endsWith("xml"))
		{	
			try{
			xml = new LoadXml() ;
			list = xml.load(path) ;
			}catch(Exception e){
				throw new RuntimeException("Some Useful Information",  e);			}
		}else if(tpath.endsWith("json"))
		{	
		try{	
			json = new LoadJson() ;
			list = json.load(path) ;
			}catch(Exception e){
				throw new RuntimeErrorException(null) ;
			}
		}else
			throw new RuntimeErrorException(null) ; 		
		prev.clear();
		next.clear(); 
		prev.push(list) ;
		counter = list.size() ;
		current = list.toArray(current) ;
	}
}