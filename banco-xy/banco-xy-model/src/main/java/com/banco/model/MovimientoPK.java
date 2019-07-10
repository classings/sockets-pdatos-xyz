package com.banco.model;

import java.io.Serializable;

/**
 * The primary key class for the movimiento database table.
 * 
 */
public class MovimientoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idCuenta;

	private java.util.Date fecMov;

	private int seq;

	public MovimientoPK() {
	}
	public int getIdCuenta() {
		return this.idCuenta;
	}
	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}
	public java.util.Date getFecMov() {
		return this.fecMov;
	}
	public void setFecMov(java.util.Date fecMov) {
		this.fecMov = fecMov;
	}
	public int getSeq() {
		return this.seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MovimientoPK)) {
			return false;
		}
		MovimientoPK castOther = (MovimientoPK)other;
		return 
			(this.idCuenta == castOther.idCuenta)
			&& this.fecMov.equals(castOther.fecMov)
			&& (this.seq == castOther.seq);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idCuenta;
		hash = hash * prime + this.fecMov.hashCode();
		hash = hash * prime + this.seq;
		
		return hash;
	}
}