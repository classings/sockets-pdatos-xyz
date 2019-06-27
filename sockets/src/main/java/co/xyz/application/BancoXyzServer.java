package co.xyz.application;

import co.xyz.socket.SocketServer;

public class BancoXyzServer {

	public static void main(String[] args) {
		final SocketServer server =  new SocketServer(5000);
		server.llamar();
	}
}
