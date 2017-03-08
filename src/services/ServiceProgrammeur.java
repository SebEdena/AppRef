package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServiceProgrammeur extends Service{
	
	public ServiceProgrammeur(Socket accept) {
		super(accept);
	}
	
	@Override
	public void run() {
		System.out.println("Nouveau client : " + getClient().getInetAddress());
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			in = new BufferedReader (new InputStreamReader(getClient().getInputStream()));
			out = new PrintWriter (getClient().getOutputStream (), true);
		} catch (IOException e) {
			System.err.println(e);
		}
		
		
		System.err.println("Fin du service");
	}
	
	public void displayMessage(PrintWriter out){
		out.println("Bienvenue sur le service programmeur. Vous pouvez : ");
		out.println("1 - Déclarer un nouveau service");
		out.println("2 - Mettre à jour un service");
		out.println("3 - Changer l'adresse du serveur ftp");
		out.println("Sélectionnez votre choix.");
	}
}