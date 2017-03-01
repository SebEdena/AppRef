package utilisateurs;

public class Programmeur extends Utilisateur {

	private String ftp;
	
	public Programmeur(String name, String pwd, String ftp) {
		super(name, pwd);
		this.ftp = ftp;
	}

}
