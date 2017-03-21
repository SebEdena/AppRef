package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import fileUtil.ServiceReader;

public class ServiceClient extends Service{
	
	public ServiceClient(Socket accept) {
		super(accept);
	}
	
	@Override
	public void run() {
		System.out.println("Nouveau client : " + getClient().getInetAddress());
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			in = new BufferedReader (new InputStreamReader(getClient().getInputStream()));
			out = new PrintWriter (getClient().getOutputStream (), true);
		} catch (IOException e) {
			System.err.println(e);
		}
		System.err.println("Fin du service");
	}
}
