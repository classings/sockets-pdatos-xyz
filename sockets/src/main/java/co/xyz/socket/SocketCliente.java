package co.xyz.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalTime;
import java.util.Scanner;

import co.xyz.enums.TipoDocumentoEnum;
import co.xyz.model.Ciudad;
import co.xyz.model.Cliente;
import co.xyz.model.TipoDocumento;

public class SocketCliente extends CustomSocket {

	final int SALIR = 2;
	final Scanner sc = new Scanner(System.in);

	
	public SocketCliente(final String host, final int puerto) {
		super(host, puerto);
	}

	public void escuchar() {
		try {
			int opcion = -1;
			while (true) {
				if (socket == null) {
					System.out.println(LocalTime.now() + "\t\t cliente \t\t Conexion establecida con el server");					
				}
				
				opcion = seleccioneOpcionMenu();
				
				if (opcion == SALIR)
					break;
				
				this.socket = new Socket(host, puerto);

				synchronized (this) {
					final Cliente cliente = instanciarCliente();
					this.outStream = new ObjectOutputStream(socket.getOutputStream());
					this.outStream.writeObject(cliente);
				}
				
				this.inputStream = new ObjectInputStream(socket.getInputStream());

				final String mensaje = (String) inputStream.readObject();

				System.out.println(LocalTime.now() + "\t\t cliente \t\t "+mensaje);
				
				this.socket.close();
			}
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (socket != null && socket.isConnected()) {
				try {
					this.socket.close();
				} catch (IOException e) {
				}				
			}
			System.out.println(LocalTime.now() + "\t\t cliente \t\t desconectado!");
		}
	}

	private Cliente instanciarCliente() {
		System.out.println("Crear cliente: ");
		final String nombre = solicitarCampoString("A","Nombre");
		final String apellidos = solicitarCampoString("B","Apellidos");
		final byte tipoDocumento = solicitarTipoDocumento();	
		final int documento = solicitarCampoInt("D","Documento #");
		final String email = solicitarCampoString("E","Email");
		final int telefono = solicitarCampoInt("F","Telefono");
		final int ciudad = solicitarCiudad();	
		
		Cliente cliente = new Cliente(documento, apellidos,
				email, nombre, telefono, new Ciudad(ciudad),
				new TipoDocumento(tipoDocumento));
		return cliente;
	}

	private int solicitarCampoInt(String letra, String campo) {
		System.out.print("\t"+letra+") "+campo+": ");
		int respuesta = sc.nextInt();
		return respuesta;
	}
	
	private String solicitarCampoString(String letra, String campo) {
		System.out.print("\t"+letra+") "+campo+": ");
		String respuesta = sc.next();
		return respuesta;
	}

	private int solicitarCiudad() {
		System.out.println("\tG) Ciudad: ");
		System.out.println("\t\t1) " + "Bogota");
		System.out.println("\t\t2) " + "Cartagena");
		System.out.println("\t\t3) " + "Medellin");
		System.out.print("Digite su opcion: ");
		int respuesta = sc.nextInt();
		return respuesta;
	}
	
	private byte solicitarTipoDocumento() {
		System.out.println("\tC) Tipo de documento: ");
		System.out.println("\t\t1) " + TipoDocumentoEnum.CEDULA.getDescripcion());
		System.out.println("\t\t2) " + TipoDocumentoEnum.PASAPORTE.getDescripcion());
		System.out.println("\t\t3) " + TipoDocumentoEnum.CEDULA_EXTRANJERIA.getDescripcion());
		System.out.print("Digite su opcion: ");
		byte respuesta = sc.nextByte();
		return respuesta;
	}

	private int seleccioneOpcionMenu() {
		int opcion = 0;
		System.out.println("Escoja una opcion: ");
		System.out.println("\t1) Crear cliente");
		System.out.println("\t2) Salir");
		System.out.print("Digite su opcion: ");
		opcion = sc.nextInt();
		return opcion;
	}

}
