package com.banco.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.time.LocalTime;

import com.banco.db.SqlConnection;
import com.banco.dto.ConsignarData;
import com.banco.dto.ConsultarDatosData;
import com.banco.dto.CrearClienteData;
import com.banco.dto.GenericData;
import com.banco.dto.RetirarData;
import com.banco.service.ClienteService;
import com.banco.service.ConsignarService;
import com.banco.service.RetiroService;

public final class SocketServer extends CustomSocket {
	
	public SocketServer(final int puerto) {
		super(puerto);
	}

	@Override
	public void escuchar() {
		try {
			while (true) {
				prepareSocket();
				
				try {
					if (data instanceof CrearClienteData) {
						ClienteService.crearCliente((CrearClienteData) data);
					} else if (data instanceof RetirarData) {
						RetiroService.retirar((RetirarData) data);
					} else if (data instanceof ConsignarData) {
						ConsignarService.consignar((ConsignarData) data);
					} else if (data instanceof ConsultarDatosData) {
						ClienteService.consultarDatos((ConsultarDatosData) data);
					}
				} catch (SQLException e) {
					this.outStream.writeObject(e.getMessage());
					System.err.println(LocalTime.now() + "\t server \t\t"+e.getMessage());
					this.socket.close();
					continue;
				}
				
				closeSocket();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			SqlConnection.closeConnection();
		}
	}

	private void closeSocket() throws IOException {
		System.out.println(LocalTime.now() + "\t server \t\t" + ((GenericData)data).getMensaje());
		
		this.outStream.writeObject(data);

		this.socket.close();

		System.out.println(LocalTime.now() + "\t server \t\tConexion cerrada con el cliente.");
	}

	private void prepareSocket() throws IOException, ClassNotFoundException {
		this.socket = serverSocket.accept();

		System.out.println(LocalTime.now() + "\t server \t\tConexion establecida con el cliente.");

		this.inputStream = new ObjectInputStream(socket.getInputStream());
		this.outStream = new ObjectOutputStream(socket.getOutputStream());
		
		this.data = inputStream.readObject();

	}
	
	@Override
	protected void llamar() {
		
	}
}
