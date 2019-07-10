package com.banco.dto;

import com.banco.model.Cliente;

public class CrearClienteData extends GenericData {

	private static final long serialVersionUID = -5088081161101793621L;
	private Cliente cliente;

	public CrearClienteData(String mensaje) {
		super(mensaje);
	}
	
	public CrearClienteData(Cliente cliente) {
		this("");
		this.cliente = cliente;
	}
	
	@Override
	public void setMensaje(String mensaje) {
		super.setMensaje(mensaje);
	}
	
	@Override
	public String getMensaje() {
		return super.getMensaje();
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
}
