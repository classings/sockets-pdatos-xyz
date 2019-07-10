package com.banco.model;

import java.io.Serializable;


/**
 * The persistent class for the cliente database table.
 * 
 */
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idDocumento;

	private String apellidos;

	private String email;

	private String nombres;

	private int telefono;

	//bi-directional many-to-one association to Ciudad
	private Ciudad ciudad;

	//bi-directional many-to-one association to TipoDocumento
	private TipoDocumento tipoDocumento;

	public Cliente() {
	}

	
	public Cliente(int idDocumento, String apellidos, String email, String nombres, int telefono, Ciudad ciudad,
			TipoDocumento tipoDocumento) {
		this.idDocumento = idDocumento;
		this.apellidos = apellidos;
		this.email = email;
		this.nombres = nombres;
		this.telefono = telefono;
		this.ciudad = ciudad;
		this.tipoDocumento = tipoDocumento;
	}


	public int getIdDocumento() {
		return this.idDocumento;
	}

	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public int getTelefono() {
		return this.telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public Ciudad getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public TipoDocumento getTipoDocumento() {
		return this.tipoDocumento;
	}

	@Override
	public String toString() {
		return "\n\nDescipcion del Cliente:"
				+ "\nidDocumento=" + idDocumento 
				+ "\napellidos=" + apellidos 
				+ "\nemail=" + email 
				+ "\nnombres=" + nombres 
				+ "\ntelefono=" + telefono 
				+ "\nciudad=" + ciudad.getIdCiudades() 
				+ "\ntipoDocumento=" + tipoDocumento.getIdTipoDocumento();
	}


	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

}