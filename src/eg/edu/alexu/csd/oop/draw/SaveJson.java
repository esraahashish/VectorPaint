package eg.edu.alexu.csd.oop.draw;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.Map.Entry;

public class SaveJson {

	public SaveJson(Shape[] current,int length, String path) {
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(path));
			br.write("{\n");
			br.write("\t\"state\" :\n");
			br.write("\t[\n");
			for (int i = 0; i < length ; i++) {
				
				java.util.Map<String, Double> m = null ;
				
				
				br.write("\t\t{\n");
				try {
					br.write("\t\t\"Class\" : \"" + current[i].getClass().getName() + "\" ,\n");
				} catch (Exception e) {
					br.write("\t\t\"Class\" : \"" + "null" + "\" ,\n");
				}

				try {
					br.write("\t\t\"x\" : \"" + current[i].getPosition().x + "\" ,\n");
				} catch (Exception e) {
					br.write("\t\t\"x\" : \"" + "-1" + "\" ,\n");
				}

				try {
					br.write("\t\t\"y\" : \"" + current[i].getPosition().y 
							+ "\" ,\n");
				} catch (Exception e) {
					br.write("\t\t\"y\" : \"" + "-1" + "\" ,\n");
				}

				try {
					br.write("\t\t\"color\" : \"" + current[i].getColor().getRGB() + "\" ,\n");
				} catch (Exception e) {
					br.write("\t\t\"color\" : \"" + "-1" + "\" ,\n");
				}

				try {
					br.write("\t\t\"fillcolor\" : \"" + current[i].getFillColor().getRGB() + "\" ,\n");
				} catch (Exception e) {
					if(current[i] != null)
					br.write("\t\t\"fillcolor\" : \"" + "-1" + "\" ,\n");
					else
						br.write("\t\t\"fillcolor\" : \"" + "-1" + "\" \n");
				}
				try{
				m = current[i].getProperties();	
				Iterator<?> entry = m.entrySet().iterator();
				while (entry.hasNext()) {
					Entry<?, ?> object = (Entry<?, ?>) entry.next();
					if (entry.hasNext()) {
						if(object.getValue() != null)
							br.write("\t\t\"" + object.getKey() + "\" : \"" + object.getValue() + "\" ,\n");
						else
							br.write("\t\t\"" + object.getKey() + "\" : \"" + "-1" + "\" ,\n");
						
					} else {
						if(object.getValue() != null)
							br.write("\t\t\"" + object.getKey() + "\" : \"" + object.getValue() + "\"\n");
						else
							br.write("\t\t\"" + object.getKey() + "\" : \"" + "-1" + "\"\n");
					}
				}
				}catch(Exception e){}
				if (i + 1 != length)
					br.write("\t\t},\n");
				else
					br.write("\t\t}\n");
			}
			br.write("\t]\n");
			br.write("}\n");
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}