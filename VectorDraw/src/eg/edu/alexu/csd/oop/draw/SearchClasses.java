package eg.edu.alexu.csd.oop.draw;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import javax.swing.text.html.HTMLDocument.Iterator;
@SuppressWarnings("all")

public class SearchClasses {


    protected Set <Class <? extends Shape>> list = new HashSet(2000);

    public void processJar(String p) throws MalformedURLException {
		ClassLoader loader = getClass().getClassLoader();
		String path = (new File(p)).getPath();
		try {
			JarInputStream jis = new JarInputStream(new FileInputStream(path));
			JarEntry je = jis.getNextJarEntry();
			while (je != null) {
				if (je.getName().endsWith(".class")) {
					String x = je.getName().replaceAll("/", ".");
					x = x.split(".class")[0];
					String className = je.getName().substring(0, je.getName().length() - 6);
					className = className.replace('/', '.');
					try {
						Class<?> tC = Class.forName(x, true, loader);
						if (!tC.isInterface() && !Modifier.isAbstract(tC.getModifiers()) && tC.newInstance() instanceof Shape) {
							this.list.add((Class<? extends Shape>) tC);
						}
					}catch (Exception ex) {}
				}
				je = jis.getNextJarEntry() ;
			}
			jis.close();
		} catch (Exception ex){}
	}
    
    public Set<Class<? extends Shape>> getClasses() {

        String classpath = System.getProperty("java.class.path");
        String pathSeparator = System.getProperty("path.separator");

        String []string = classpath.split(pathSeparator)  ;

        for(int i = 0 ; i < string.length ; i++) {
        	if(string[i].endsWith(".jar")){
        		System.out.println(string[i]);
        		try {
					processJar(string[i]) ;
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
				}
        	}
        }

        return this.list;
    }

}