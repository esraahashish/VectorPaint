package eg.edu.alexu.csd.oop.draw;

import java.awt.Point;
import java.io.File;
import java.util.Map;

import javax.management.RuntimeErrorException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class SaveXml {

	public SaveXml(Shape [] current,int length ,String path)
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance() ;
		try {
			File file = new File(path) ;
			DocumentBuilder builder = factory.newDocumentBuilder() ;
			Document document = builder.newDocument() ;
			
			Element root = document.createElement("shapes") ;
			document.appendChild(root) ;
			
			for(int i = 0;i<length ;i++)
			{
				Element shape ;
				// elements
				if(current[i] != null)
					shape = document.createElement(current[i].getClass().getName());
				else
				    shape = document.createElement("null");	
				
				Node x = document.createElement("x");
				try{
				x.appendChild(document.createTextNode(current[i].getPosition().x+""));
				}catch(Exception e)
				{
					x.appendChild(document.createTextNode("-1"));	
				}
				shape.appendChild(x) ;
				
				Node y = document.createElement("y");
				try{
				y.appendChild(document.createTextNode(current[i].getPosition().y+""));
				}catch(Exception e)
				{
					y.appendChild(document.createTextNode("-1"));	
				}
				shape.appendChild(y) ;
			
				Node color = document.createElement("color");
				try{
				color.appendChild(document.createTextNode(current[i].getColor().getRGB()+""));
				}catch(Exception e)
				{
					color.appendChild(document.createTextNode("-1"));	
				}
				shape.appendChild(color) ;
			
				Node fillcolor = document.createElement("fillcolor");
				try{
				fillcolor.appendChild(document.createTextNode(current[i].getFillColor().getRGB()+""));
				}catch(Exception e)
				{
					fillcolor.appendChild(document.createTextNode("-1"));	
				}
				shape.appendChild(fillcolor) ;
			
				try{
				Map<String,Double> m = current[i].getProperties() ;
				for(Map.Entry<String, Double> entry : m.entrySet())
				{
					Node prop = document.createElement(entry.getKey());
					if(entry.getValue() == null)
					{
						prop.appendChild(document.createTextNode("-1"));		
					}else
						prop.appendChild(document.createTextNode(entry.getValue()+""));
					
					shape.appendChild(prop) ;
				}
					}catch(Exception e){
						
					}
				
				root.appendChild(shape);
				
			}
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(file);

			transformer.transform(source, result);

			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeErrorException(null) ;
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeErrorException(null) ;
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeErrorException(null) ;
		}	
	
	}
}
