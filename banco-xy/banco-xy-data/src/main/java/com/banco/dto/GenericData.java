package com.banco.dto;

import java.io.Serializable;

public abstract class GenericData implements Serializable {

	private static final long serialVersionUID = 1962577153404153695L;
	
	private String mensaje;

	public GenericData(String mensaje) {
		this.mensaje = mensaje;
	}
	
	protected void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String getMensaje() {
		return mensaje;
	}
	
	@Override
	public String toString() {
		return mensaje;
	}
}
