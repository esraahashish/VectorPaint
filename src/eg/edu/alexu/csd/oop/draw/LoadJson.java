package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LoadJson {

	@SuppressWarnings("null")
	LinkedList<Shape> load(String path) {
		Shape shape;
		Point point;
		Color color, fillcolor;
		LinkedList<Shape> ret = new LinkedList<Shape>();
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(path));
			br.readLine();
			br.readLine();
			br.readLine();
			String line = br.readLine();
			while (true) {
				line = line.replaceAll("\\s", "");
				if (line.charAt(0) == '{') {
					line = br.readLine();
					continue;
				}
				if (line.charAt(0) == ']')
					break;
				point = new Point();
				line = line.replaceAll("\"", "");
				line = line.replaceAll(",", "");
				String tem[] = line.split(":");
				String className = tem[1];
				if(!className.equals("null"))
				shape = (Shape) Class.forName(className).newInstance();
				else
				shape = null ;
				
				line = br.readLine();
				line = line.replaceAll("\\s", "");
				line = line.replaceAll("\"", "");
				line = line.replaceAll(",", "");
				tem = line.split(":");
				if (!tem[1].equals("-1"))
					point.x = Integer.parseInt(tem[1]);

				line = br.readLine();
				line = line.replaceAll("\\s", "");
				line = line.replaceAll("\"", "");
				line = line.replaceAll(",", "");
				tem = line.split(":");
				if (!tem[1].equals("-1"))
					point.y = Integer.parseInt(tem[1]);
			
				if(shape != null)
				shape.setPosition(point);
				line = br.readLine();
				line = line.replaceAll("\\s", "");
				line = line.replaceAll("\"", "");
				line = line.replaceAll(",", "");
				tem = line.split(":");
				if (!tem[1].equals("-1"))
					color = new Color(Integer.parseInt(tem[1]));
				else
					color = null;
				
				if(shape != null)
				shape.setColor(color);
				
				line = br.readLine();
				line = line.replaceAll("\\s", "");
				line = line.replaceAll("\"", "");
				line = line.replaceAll(",", "");
				tem = line.split(":");
				if (!tem[1].equals("-1"))
					fillcolor = new Color(Integer.parseInt(tem[1]));
				else
					fillcolor = null;

				if(shape != null)
				shape.setFillColor(fillcolor);
				line = br.readLine();
				line = line.replaceAll("\\s", "");
				line = line.replaceAll("\"", "");
				line = line.replaceAll(",", "");
				Map<String, Double> m = new HashMap<String, Double>();
				while (line.charAt(0) != '}') {
					tem = line.split(":");
					if (!tem[1].equals("-1"))
						m.put(tem[0], Double.parseDouble(tem[1]));
					else
						m.put(tem[0], null);

					line = br.readLine();
					line = line.replaceAll("\\s", "");
					line = line.replaceAll("\"", "");
					line = line.replaceAll(",", "");
				}
				if(shape != null)
				shape.setProperties(m);
				ret.add(shape);
				line = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
}