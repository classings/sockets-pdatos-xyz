package com.banco.dto;

import java.io.Serializable;

public class ClienteData implements Serializable {

	private static final long serialVersionUID = 2167637348804380509L;
	private final String SEPARADOR = " = ";
	private final String SALTO_LINEA = "\n";

	private String idDocumento;

	private String tipoDocumento;

	private String nombres;

	private String apellidos;

	private String email;

	private String telefono;

	private String ciudad;

	private String saldo;

	public ClienteData() {
	}

	public ClienteData(int idDocumento, String tipoDocumento, String nombres, String apellidos, String email,
			int telefono, String ciudad, double saldo) {
		this.idDocumento = String.valueOf(idDocumento);
		this.tipoDocumento = tipoDocumento;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.email = email;
		this.telefono = String.valueOf(telefono);
		this.ciudad = ciudad;
		this.saldo = String.valueOf(saldo);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("");
		builder.append("Documento").append(SEPARADOR).append(idDocumento).append(SALTO_LINEA);
		builder.append("Tipo de documento").append(SEPARADOR).append(tipoDocumento).append(SALTO_LINEA);
		builder.append("Nombres").append(SEPARADOR).append(nombres).append(SALTO_LINEA);
		builder.append("Apellidos").append(SEPARADOR).append(apellidos).append(SALTO_LINEA);
		builder.append("Email").append(SEPARADOR).append(email).append(SALTO_LINEA);
		builder.append("Telefono").append(SEPARADOR).append(telefono).append(SALTO_LINEA);
		builder.append("Ciudad").append(SEPARADOR).append(ciudad).append(SALTO_LINEA);
		builder.append("Saldo").append(SEPARADOR+"$ ").append(saldo).append(SALTO_LINEA);
		return builder.toString();
	}

}