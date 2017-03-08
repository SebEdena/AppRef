package serveurs;

import java.io.IOException;

public class ApplicationServeur {
	
	private final static int PORT_CLIENT = 2500;
	
	public static void main(String[] args) {
		try {
			new ServeurClient(PORT_CLIENT).lancer();
			System.out.println("Serveur client sur le port " + PORT_CLIENT);			
		} catch (IOException e) {
				System.err.println("Pb lors de la création d'un des trois serveurs : " +  e);			
		}
	}
}
