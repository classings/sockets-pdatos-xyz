package co.xyz.application;

import co.xyz.socket.SocketCliente;

public class BancoXyzCliente {

	public static void main(String[] args) {
		final SocketCliente cliente =  new SocketCliente("127.0.0.1", 5000);
		cliente.escuchar();
	}

}
