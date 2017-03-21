package test;

import java.net.MalformedURLException;
import java.util.List;

import fileUtil.XMLReader;
import utilisateurs.Amateur;
import utilisateurs.Programmeur;
import utilisateurs.Utilisateur;

public class Test {
 
	public static void main(String[] args) {
		List<String> s = XMLReader.read("./data.xml");
		System.out.println(s);
		Utilisateur u = null;
		for(String str : s){
			String[] data = str.split(";");
			switch(data[0]){
			case "amateur" : 
				u = new Amateur(data);
				break;
			case "prog" : 
				try {
					u = new Programmeur(data);
				} catch (MalformedURLException e) {}
				break;
			}
			System.out.println(u);
		}
	}
}
