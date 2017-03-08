package utilisateurs;

public class Utilisateur {

	private static final int NOM_INDEX=1, MDP_INDEX=2, DATA_SIZE=3;
	
	private String login;
	private String mdp;
	
	public Utilisateur (String[] data){
		this(data[NOM_INDEX], data[MDP_INDEX]);
	}
	
	public Utilisateur (String name, String pwd){
		login = name;
		mdp = pwd;
	}
	
	public String toString(){
		return login + "(" + mdp + ")";
	}
	
	public boolean Authentification(String name,String pwd){
		return login.equals(name) && mdp.equals(pwd);
	}
}
