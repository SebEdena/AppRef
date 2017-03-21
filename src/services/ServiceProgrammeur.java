package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import serveurs.ServiceRegistry;
import utilisateurs.Programmeur;
import utilisateurs.Utilisateur;

public class ServiceProgrammeur extends Service{
	
	private static final int MAX_CHOIX = 4;
	Programmeur p;
	
	public ServiceProgrammeur(Socket accept, Utilisateur u) {
		super(accept);
		p = (Programmeur)u;
	}
	
	@Override
	public void run() {
		System.out.println("Nouveau client : " + getClient().getInetAddress());
		String res;
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			in = new BufferedReader (new InputStreamReader(getClient().getInputStream()));
			out = new PrintWriter (getClient().getOutputStream (), true);
			
			while(true){
				while(in.ready()){in.readLine();};
				displayMessage(out);
				
				res = in.readLine();
				
				if(res.equals("exit"))
					return;
				
				int i = 0;
				
				try{
					i = Integer.parseInt(res);
					if(i < 1 || i > MAX_CHOIX)
						throw new Exception();
				}catch(Exception e){
					out.println("Choix invalide");
					continue;
				}
				
				switch(i){
				case 1 : newService(in, out); break;
				case 2 : majService(in, out); break;
				case 3 : changeFtp(in, out); break;
				case 4 : new ServiceConnexion(getClient()).lancer(); return;
				default : break;
				}
			}
			
		} catch (IOException e) {
			System.err.println(e);
		}
		
		System.err.println("Fin du service");
	}
	
	private void displayMessage(PrintWriter out){
		out.println("Bienvenue sur le service programmeur. Vous pouvez : ");
		out.println("1 - Déclarer un nouveau service");
		out.println("2 - Mettre à jour un service");
		out.println("3 - Changer l'adresse du serveur ftp");
		out.println("4 - Se déconnecter et revenir à l'écran de connexion");
		out.println("Sélectionnez votre choix.");
	}
	
	private void newService(BufferedReader in, PrintWriter out) throws IOException{
		while(in.ready()){in.readLine();};
		out.println("Saisissez votre nom de service à ajouter.");
		String res = in.readLine();
		try {
			ServiceRegistry.addService(res, p.getLogin());
			out.println("Service ajouté !");
		} catch (Exception e) {
			out.println("Erreur : " + e);
		}
	}
	
	private void majService(BufferedReader in, PrintWriter out) throws IOException{
		while(in.ready()){in.readLine();};
		out.println("Saisissez votre nom de service à mettre à jour");
		String res = in.readLine();
		try {
			ServiceRegistry.majService(res, p.getLogin());
			out.println("Service mis à jour !");
		} catch (Exception e) {
			out.println("Erreur : " + e);
		}
	}
	
	private void changeFtp(BufferedReader in, PrintWriter out) throws IOException{
		while(in.ready()){in.readLine();};
		out.println("Saisissez votre nouvelle adresse ftp.");
		String res = in.readLine();
		try {
			p.setFtp(res);
		} catch (Exception e) {
			out.println("Votre URL n'est pas valide");
			return;
		}
		try {
			ServiceRegistry.refreshServices(p.getLogin());
		} catch (Exception e) {
			out.println("Erreur" + e);
			return;
		}
		out.println("Changement d' @ftp réussi.");
	}
}