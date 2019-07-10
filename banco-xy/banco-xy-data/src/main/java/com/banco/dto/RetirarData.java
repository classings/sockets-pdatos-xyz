package com.banco.dto;

public class RetirarData extends GenericData{

	private static final long serialVersionUID = -1493667948830588308L;
	private int idDocumento;
	private double monto;

	public RetirarData(String mensaje) {
		super(mensaje);
	}
	
	public RetirarData(int idDocumento, double monto) {
		this("");
		this.idDocumento = idDocumento;
		this.monto = monto;
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
	
	public double getMonto() {
		return monto;
	}
	
}
