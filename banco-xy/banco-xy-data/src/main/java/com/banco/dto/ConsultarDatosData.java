package com.banco.dto;

public class ConsultarDatosData extends GenericData{

	private static final long serialVersionUID = 7444017906206355313L;
	private ClienteData clienteData;
	private int idDocumento;

	public ConsultarDatosData(String mensaje) {
		super(mensaje);
	}
	
	public ConsultarDatosData(int idDocumento) {
		this("");
		this.idDocumento = idDocumento;
	}
	
	@Override
	public void setMensaje(String mensaje) {
		super.setMensaje(mensaje);
	}
	
	@Override
	public String getMensaje() {
		return super.getMensaje();
	}
	
	public int getIdDocumento() {
		return idDocumento;
	}
	
	public void setClienteData(ClienteData clienteData) {
		this.clienteData = clienteData;
	}

	@Override
	public String toString() {
		return super.toString() + clienteData.toString();
	}
	
	
}
