package com.banco.application;

import com.banco.application.constants.BancoXyzConstants;
import com.banco.socket.SocketCliente;

public class BancoXyzCliente {

	public static void main(String[] args) {
		final SocketCliente cliente =  new SocketCliente(BancoXyzConstants.Hosts.LOCAL_HOST, 
				BancoXyzConstants.Ports.PORT_5000);
		cliente.llamar();
	}

}
