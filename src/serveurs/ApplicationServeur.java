package serveurs;

import java.io.IOException;

public class ApplicationServeur {
	
	private final static int PORT_CONNEXION = 2500;
	private final static int PORT_CLIENT = 2600;
	
	public static void main(String[] args) {
		try {
			new ServeurConnexion(PORT_CONNEXION).lancer();
			System.out.println("Serveur de connexion sur le port " + PORT_CONNEXION);
			new ServeurClient(PORT_CLIENT).lancer();
			System.out.println("Serveur d'emprunt sur le port " + PORT_CLIENT);			
		} catch (IOException e) {
				System.err.println("Pb lors de la création d'un des trois serveurs : " +  e);			
		}
	}
}
