package co.xyz.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CustomSocket {

	protected ServerSocket serverSocket;
	protected Socket socket;
	protected String host;
	protected int puerto;

	protected ObjectInputStream inputStream;
	protected ObjectOutputStream outStream;

	public CustomSocket(final int puerto) {
		try {
			this.serverSocket = new ServerSocket(puerto);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public CustomSocket(final String host, final int puerto) {
		this.host = host;
		this.puerto = puerto;
	}

}
