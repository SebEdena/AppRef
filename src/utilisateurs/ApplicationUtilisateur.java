package utilisateurs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ApplicationUtilisateur {
	private static final String ADR_CONNEXION = "localhost";
	private static final int PORT_CONNEXION = 2500;

	public static void main(String[] args) {
		String login, mdp;
		Scanner clavier = new Scanner(System.in);
		Socket laSocket = null;
		
		try {
			laSocket = new Socket(ADR_CONNEXION, PORT_CONNEXION);
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(laSocket.getInputStream()));
			PrintWriter socketOut =  new PrintWriter(laSocket.getOutputStream(), true);

			/* bonjour */
			System.out.println("Bienvenue sur votre espace de connexion : ");
			
			/* saisie des données */;
			System.out.println("Veuillez renseigner votre login :");
			login = clavier.nextLine().trim().toLowerCase();
			
			System.out.println("Veuilez renseignez votre mot de passe:");
			mdp = clavier.nextLine().trim().toLowerCase();
			
			/* envoi des données au service */
			socketOut.println("");
			socketOut.println("");

			/* réception de la réponse
			 * et affichage de cette réponse */
			System.out.println(socketIn.readLine());
			
		} catch (IOException e) {System.out.println("Connexion annulée.");}
		try{
			if(laSocket != null )
				// fermeture de la connexion
				laSocket.close();
		}catch(IOException e){}
	}
}
