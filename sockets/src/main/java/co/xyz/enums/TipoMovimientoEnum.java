package co.xyz.enums;

public enum TipoMovimientoEnum {

	RETIRO("Retiro", (byte)1),
	TRANSFERENCIA("Transferencia",(byte)2),
	CONSIGNACION("Consignacion", (byte)3);
	
	private String descripcion;
	private byte codigo;
	
	TipoMovimientoEnum(String descripcion, byte codigo){
		this.descripcion = descripcion;
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public byte getCodigo() {
		return codigo;
	}
}
