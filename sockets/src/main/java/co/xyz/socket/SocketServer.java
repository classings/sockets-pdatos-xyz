package co.xyz.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalTime;

import co.xyz.db.ClienteDao;
import co.xyz.db.SqlConnection;
import co.xyz.model.Cliente;

public class SocketServer extends CustomSocket {
	
	public SocketServer(final int puerto) {
		super(puerto);
	}

	public void llamar() {
		try {
			while (true) {
				this.socket = serverSocket.accept();

				this.inputStream = new ObjectInputStream(socket.getInputStream());
				this.outStream = new ObjectOutputStream(socket.getOutputStream());

				final Cliente cliente = (Cliente) inputStream.readObject();

				System.out.println(LocalTime.now() + "\t server \t\tConexion establecida con el cliente.");

				int idCliente = 0;
				try {
					idCliente = persistirCLiente(cliente);
				} catch (SQLException e) {
					this.outStream.writeObject(e.getMessage());
					System.err.println(LocalTime.now() + "\t server \t\t"+e.getMessage());
					this.socket.close();
					continue;
				}

				final String responseServer = "Cliente creado con id " + idCliente;
				
				System.out.println(LocalTime.now() + "\t server \t\t" + responseServer);
				System.out.println(LocalTime.now() + "\t server \t\t" + cliente);
				
				this.outStream.writeObject(responseServer);

				this.socket.close();

				System.out.println(LocalTime.now() + "\n\t server \t\tConexion cerrada con el cliente.");
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

	private int persistirCLiente(Cliente cliente) throws SQLException {

		Connection conn = SqlConnection.getConnection();
		try {
			final int id = new ClienteDao(conn).crearCliente(cliente);
			conn.close();
			return id;
		} catch (SQLException e) {
			throw e;
		}
	}

}
