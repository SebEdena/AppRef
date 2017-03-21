package serveurs;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Vector;

import fileUtil.XMLReader;
import utilisateurs.Amateur;
import utilisateurs.Programmeur;
import utilisateurs.Utilisateur;

public class DonneesClients {

	static{
		instance = new DonneesClients();
	}
	
	private static DonneesClients instance;
	private Vector<Amateur> listeAmateur;
	private Vector<Programmeur> listeProgrammeur;
	
	private DonneesClients(){
		listeAmateur = new Vector<Amateur>();
		listeProgrammeur = new Vector<Programmeur>();
		init();
	}
	
	private void init() {
		List<String> s = XMLReader.read("./data.xml");
		for(String str : s){
			String[] data = str.split(";");
			switch(data[0]){
			case "amateur" : 
				listeAmateur.add(new Amateur(data));
				break;
			case "prog" : 
				try {
					listeProgrammeur.add(new Programmeur(data));
				} catch (MalformedURLException e) {}
				break;
			default : break;
			}
		}
	}

	public static DonneesClients getInstance(){
			return instance;
	}
	
	public Utilisateur userExists(String name, String pwd){
		for(Programmeur p : listeProgrammeur)
			if(p.authentification(name, pwd))
				return p;
		for(Amateur a : listeAmateur)
			if(a.authentification(name, pwd))
				return a;
		return null;
	}
}
