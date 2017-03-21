package utilisateurs;

import java.net.MalformedURLException;
import java.net.URL;

public class Programmeur extends Utilisateur {

	private static final int FTP_NAME = 3;
	private URL ftp;
	
	public Programmeur(String[] data) throws MalformedURLException {
		super(data);
		this.ftp = new URL(data[FTP_NAME]);
	}

	public void setFtp(String ftp) throws MalformedURLException{
		this.ftp = new URL(ftp);
	}
}
