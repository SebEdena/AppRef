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
		int numAbonné, numDocument;
		Scanner clavier = new Scanner(System.in);
		Socket laSocket = null;
		
		try {
			laSocket = new Socket(ADR_CONNEXION, PORT_CONNEXION);
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(laSocket.getInputStream()));
			PrintWriter socketOut =  new PrintWriter(laSocket.getOutputStream(), true);

			/* bonjour */
			System.out.println("Bienvenue sur votre système de réservation : ");
			System.out.println("Vous pouvez ici réserver un document disponible ");
			System.out.println("et passer le chercher dans les 2 heures");
			
			/* saisie des données */;
			System.out.println("Votre numéro d'abonné, svp :");

			System.out.println("Le numéro de document que vous souhaitez réserver :");

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
