package utilisateurs;

public class Programmeur extends Utilisateur {

	private static final int FTP_NAME = 3;
	private String ftp;
	
	public Programmeur(String[] data) {
		super(data);
		this.ftp = data[FTP_NAME];
	}

}
