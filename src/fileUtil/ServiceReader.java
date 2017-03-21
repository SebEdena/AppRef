package fileUtil;

import java.io.BufferedReader;
import java.io.IOException;

public class ServiceReader implements Runnable {
	
	private BufferedReader out;
	
	public ServiceReader(BufferedReader b){
		out = b;
	}
	
	@Override
	public void run() {
		while(true){
			try {
				System.out.println(out.readLine());
			} catch (IOException e) {}
		}
	}

	public void lancer() {
		Thread t = new Thread(this);
		t.start();
	}
}
