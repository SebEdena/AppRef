package utilisateurs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import fileUtil.ServiceReader;

public class ApplicationUtilisateur {
	private static final String ADR_CONNEXION = "localhost";
	private static final int PORT_CONNEXION = 2500;

	public static void main(String[] args) {
		String data;
		Scanner clavier = new Scanner(System.in);
		Socket laSocket = null;
		
		try {
			laSocket = new Socket(ADR_CONNEXION, PORT_CONNEXION);
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(laSocket.getInputStream()));
			PrintWriter socketOut =  new PrintWriter(laSocket.getOutputStream(), true);
			
			new ServiceReader(socketIn).lancer();
			
			while(true){
				if(clavier.hasNext()){
					data = clavier.nextLine();
					socketOut.println(data);
				}
			}
			
		} catch (IOException e) {System.out.println("Connexion annulée." + e);}
		try{
			if(laSocket != null )
				// fermeture de la connexion
				laSocket.close();
		}catch(IOException e){}
	}
}
