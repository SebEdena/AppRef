package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import serveurs.DonneesClients;
import utilisateurs.Programmeur;
import utilisateurs.Utilisateur;

public class ServiceConnexion extends Service{
	
	public ServiceConnexion(Socket accept) {
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
			
			while(in.ready()){in.readLine();};
			
			/* bonjour */
			out.println("Bienvenue sur votre espace de connexion : ");
			/* saisie des données */
			out.println("Veuillez renseigner votre login :");
			String login = in.readLine().trim().toLowerCase();
			
			while(in.ready()){in.readLine();};
			out.println("Veuilez renseignez votre mot de passe:");
			String mdp = in.readLine().trim().toLowerCase();
			
			Utilisateur u = DonneesClients.getInstance().userExists(login, mdp);
			
			if(u != null){
				out.println("Access Granted");
			}else{
				out.println("Access Denied");
			}
			
			if(u instanceof Programmeur){
				new ServiceProgrammeur(getClient(), u).lancer();
			}else{
				out.println("NOPE");
			}
			
		} catch (IOException e) {
			System.err.println(e);
		}
		System.err.println("Fin du service");
	}
}