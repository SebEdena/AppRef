package serveurs;

import java.util.List;
import java.util.Vector;

import fileUtil.XMLReader;
import utilisateurs.Amateur;
import utilisateurs.Programmeur;
import utilisateurs.Utilisateur;

public class Donn�esClients {

	private static Donn�esClients instance;
	private Vector<Amateur> listeAmateur;
	private Vector<Programmeur> listeProgrammeur;
	
	private Donn�esClients(){
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
				listeProgrammeur.add(new Programmeur(data));
				break;
			default : break;
			}
		}
	}

	public static Donn�esClients getInstance(){
		synchronized (instance) {
			if(instance == null)
				instance = new Donn�esClients();
			return instance;
		}
	}
	
	public boolean UserExists(String name, String pwd){
		boolean exist = false;
		for(Programmeur p : listeProgrammeur)
			if(p.Authentification(name, pwd))
				exist = true;
		for(Amateur p : listeAmateur)
			if(p.Authentification(name, pwd))
				exist = true;
		return exist;
	}
}
