package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Point;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LoadXml {


	@SuppressWarnings("null")
	LinkedList<Shape> load(String path)
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance() ;
		Shape shape ;
		LinkedList<Shape> ret = new LinkedList<Shape>() ;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder() ;
			Document document = builder.parse(path) ;
			Node shapes = document.getFirstChild() ;
			NodeList property = shapes.getChildNodes() ;
			for(int j = 0;j<property.getLength();j++)
			{
				Point p = new Point();
				Color c ,fc ;
				Element attribute = (Element) property.item(j) ;
				if(!attribute.getNodeName().equals("null"))
				shape = (Shape) Class.forName(attribute.getNodeName()).newInstance() ;
				else
					shape = null ;
				
				NodeList list = attribute.getChildNodes() ;
				if(list.item(0).getTextContent().equals("-1"))
			    p.x = Integer.parseInt(list.item(0).getTextContent()) ;
			
				if(list.item(1).getTextContent().equals("-1"))
				p.y = Integer.parseInt(list.item(1).getTextContent()) ;
				
				if(shape != null)
				shape.setPosition(p);
				if(list.item(2).getTextContent().equals("-1"))
				    c = new Color(Integer.parseInt(list.item(2).getTextContent()));
				else 
					c = (Color) null ;
				if(shape != null)
				shape.setColor(c);
				if(list.item(3).getTextContent().equals("-1"))
				    fc = new Color(Integer.parseInt(list.item(3).getTextContent()));
				else 
					fc = (Color) null ;
			
				if(shape != null)
				shape.setFillColor(fc);
				
				Map<String,Double> m = new HashMap<String, Double>() ;
				for(int i = 4;i<list.getLength();i++)
				{	
					if(list.item(i).getTextContent().equals("-1"))
					m.put(list.item(i).getNodeName(),Double.parseDouble(list.item(i).getTextContent()));
					else
					m.put(list.item(i).getNodeName(),null);
						
				}
				if(shape != null)
				shape.setProperties(m);
				
				ret.add(shape) ;
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret ;
	}
}
