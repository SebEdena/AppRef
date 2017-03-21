package serveurs;

import java.net.URL;
import java.net.URLClassLoader;

public class CustomURLClassLoader extends URLClassLoader {

	public CustomURLClassLoader(URL[] urls) {
		super(urls);
	}
	
	@Override
	public synchronized void addURL(URL url) {
		super.addURL(url);
	}
}
