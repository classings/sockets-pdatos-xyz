package com.banco.model;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * The persistent class for the saldo database table.
 * 
 */
public class Saldo implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idCuenta;

	private BigDecimal egresos;

	private int idDocumento;

	private BigDecimal ingresos;

	private BigDecimal saldo;

	public Saldo() {
	}

	public int getIdCuenta() {
		return this.idCuenta;
	}

	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}

	public BigDecimal getEgresos() {
		return this.egresos;
	}

	public void setEgresos(BigDecimal egresos) {
		this.egresos = egresos;
	}

	public int getIdDocumento() {
		return this.idDocumento;
	}

	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
	}

	public BigDecimal getIngresos() {
		return this.ingresos;
	}

	public void setIngresos(BigDecimal ingresos) {
		this.ingresos = ingresos;
	}

	public BigDecimal getSaldo() {
		return this.saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

}