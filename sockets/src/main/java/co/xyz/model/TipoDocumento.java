package co.xyz.model;

import java.io.Serializable;


/**
 * The persistent class for the tipo_documento database table.
 * 
 */
public class TipoDocumento implements Serializable {
	private static final long serialVersionUID = 1L;

	private byte idTipoDocumento;

	private String descripción;

	public TipoDocumento() {
	}

	
	public TipoDocumento(byte idTipoDocumento, String descripción) {
		this.idTipoDocumento = idTipoDocumento;
		this.descripción = descripción;
	}

	public TipoDocumento(byte idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public byte getIdTipoDocumento() {
		return this.idTipoDocumento;
	}

	public void setIdTipoDocumento(byte idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public String getDescripción() {
		return this.descripción;
	}

	public void setDescripción(String descripción) {
		this.descripción = descripción;
	}

}