package com.banco.application;

import com.banco.application.constants.BancoXyzConstants;
import com.banco.socket.SocketServer;

public class BancoXyzServer {

	public static void main(String[] args) {
		final SocketServer server =  new SocketServer(BancoXyzConstants.Ports.PORT_5000);
		server.escuchar();
	}
}
