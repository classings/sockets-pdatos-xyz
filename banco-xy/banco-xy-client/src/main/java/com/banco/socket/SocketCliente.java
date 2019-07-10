package com.banco.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalTime;
import java.util.Scanner;

import com.banco.dto.ConsignarData;
import com.banco.dto.ConsultarDatosData;
import com.banco.dto.CrearClienteData;
import com.banco.dto.GenericData;
import com.banco.dto.RetirarData;
import com.banco.enums.TipoDocumentoEnum;
import com.banco.model.Ciudad;
import com.banco.model.Cliente;
import com.banco.model.TipoDocumento;

public class SocketCliente extends CustomSocket {

	final int SALIR = 5;
	final Scanner sc = new Scanner(System.in);

	public SocketCliente(final String host, final int puerto) {
		super(host, puerto);
	}

	@Override
	public void llamar() {
		try {
			while (true) {
				this.socket = new Socket(host, puerto);

				System.out.println(LocalTime.now() + "\t cliente \t Conexion establecida con el server");

				final int opcion = seleccioneOpcionMenu();

				if (opcion == SALIR)
					break;

				synchronized (this) {
					final Object request = ejecutarOperacion(opcion);
					this.outStream = new ObjectOutputStream(socket.getOutputStream());
					this.outStream.writeObject(request);
				}

				this.inputStream = new ObjectInputStream(socket.getInputStream());

				final Object response = inputStream.readObject();

				System.out.println(LocalTime.now() + "\t cliente \t " + response);

				this.socket.close();
			}
			sc.close();
		} catch (IOException e) {
			System.err.println("Server no disponible en host: "+host+" puerto:"+puerto+"\nImposible establecer la conexion.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (socket != null && socket.isConnected()) {
				try {
					this.socket.close();
				} catch (IOException e) {
				}
			}
			System.err.println(LocalTime.now() + "\t\t cliente \t\t desconectado!");
		}
	}
	
	@Override
	protected void escuchar() {
		
	}

	private GenericData ejecutarOperacion(int opcion) {
		GenericData data = null;
		switch (opcion) {
		case 1:
			data = instanciarCliente();
			break;
		case 2:
			data = retirar();
			break;
		case 3:
			data = consignar();
			break;
		case 4:
			data = consultarDatos();
			break;
		default:
			break;
		}
		return data;
	}

	private ConsultarDatosData consultarDatos() {
		System.out.println("Consultar datos: ");
		final int documento = solicitarCampoInt("A", "Documento #");
		return new ConsultarDatosData(documento);
	}

	private ConsignarData consignar() {
		System.out.println("Consignar: ");
		final int documento = solicitarCampoInt("A", "Documento #");
		final double monto = solicitarCampoDouble("B", "Monto $");
		return new ConsignarData(documento, monto);
	}

	private RetirarData retirar() {
		System.out.println("Retirar: ");
		final int documento = solicitarCampoInt("A", "Documento #");
		final double monto = solicitarCampoDouble("B", "Monto $");
		return new RetirarData(documento, monto);
	}

	private CrearClienteData instanciarCliente() {
		System.out.println("Crear cliente: ");
		final String nombre = solicitarCampoString("A", "Nombre");
		final String apellidos = solicitarCampoString("B", "Apellidos");
		final byte tipoDocumento = solicitarTipoDocumento();
		final int documento = solicitarCampoInt("D", "Documento #");
		final String email = solicitarCampoString("E", "Email");
		final int telefono = solicitarCampoInt("F", "Telefono");
		final int ciudad = solicitarCiudad();

		final Cliente cliente = new Cliente(documento, apellidos, email, nombre, telefono, new Ciudad(ciudad),
				new TipoDocumento(tipoDocumento));

		return new CrearClienteData(cliente);
	}

	private int solicitarCampoInt(String letra, String campo) {
		System.out.print("\t" + letra + ") " + campo + ": ");
		int respuesta = sc.nextInt();
		return respuesta;
	}

	private double solicitarCampoDouble(String letra, String campo) {
		System.out.print("\t" + letra + ") " + campo + ": ");
		double respuesta = sc.nextDouble();
		return respuesta;
	}

	private String solicitarCampoString(String letra, String campo) {
		System.out.print("\t" + letra + ") " + campo + ": ");
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
		System.out.println("\t2) Retiro");
		System.out.println("\t3) Consignar");
		System.out.println("\t4) Consultar datos");
		System.out.println("\t5) Salir");
		System.out.print("Digite su opcion: ");
		opcion = sc.nextInt();
		return opcion;
	}
}
