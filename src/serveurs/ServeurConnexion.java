package serveurs;

import java.io.IOException;
import services.ServiceConnexion;

public class ServeurConnexion extends Serveur {
	ServeurConnexion(int port) throws IOException {
		super(port);
	}

	@Override
	public void run() {
		try {
			while(true)
				new ServiceConnexion(getListen_socket().accept()).lancer();
		}
		catch (IOException e) { 
			try {this.getListen_socket().close();} catch (IOException e1) {}
			System.err.println("Pb sur le port d'écoute :"+e);
		}
	}
}
