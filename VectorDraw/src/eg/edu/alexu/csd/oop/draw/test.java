package eg.edu.alexu.csd.oop.draw;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

public class test {

	@Test
	public void test() {
		int ln ; 
		DrawingEngine i = new IDrawingEngine() ;
		Shape a = new Circle() ;
		Map<String,Double> m = a.getProperties() ;
		for (Entry<String, Double> entry : m.entrySet())
		{
		    m.put(entry.getKey(),null) ;
		}
		a.setProperties(m);
		a.setColor(Color.red);
		i.addShape(a) ;
		i.addShape(null) ;
		i.addShape(a) ;
		i.addShape(a) ;
	    //i.save("C:\\Users\\User\\Desktop\\e.json");
	    i.addShape(null) ;
	    i.addShape(null) ;
	    i.removeShape(null);
	    i.removeShape(a);
	    ln = i.getShapes().length ;
	    assertEquals(ln,3);
	    }
}
