package com.banco.dto;

public class ConsignarData extends GenericData{

	private static final long serialVersionUID = -1163069392607262049L;
	private int idDocumento;
	private double monto;

	public ConsignarData(String mensaje) {
		super(mensaje);
	}
	
	public ConsignarData(int idDocumento, double monto) {
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
