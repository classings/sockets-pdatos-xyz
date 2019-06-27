package co.xyz.enums;

public enum TipoDocumentoEnum {

	CEDULA("Cedula", (byte)1),
	PASAPORTE("Pasaporte",(byte)2),
	CEDULA_EXTRANJERIA("Cedula de extrajenria", (byte)3);
	
	private String descripcion;
	private byte codigo;
	
	private TipoDocumentoEnum(String descripcion, byte codigo){
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
