package test;

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
				u = new Programmeur(data);
				break;
			}
			System.out.println(u);
		}
	}
}
