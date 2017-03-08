package fileUtil;

import java.io.BufferedReader;
import java.io.IOException;

public class ServiceReader implements Runnable {

	private BufferedReader input;
	
	public ServiceReader(BufferedReader in){
		input = in;
	}
	
	@Override
	public void run() {

		while(true){
			try {
				System.out.println(input.readLine());
			} catch (IOException e) {return;}
		}
	}

	public void lancer() {
		Thread t = new Thread(this);
		t.start();
	}
}
