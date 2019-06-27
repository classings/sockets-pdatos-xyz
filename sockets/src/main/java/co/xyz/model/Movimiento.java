package co.xyz.model;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * The persistent class for the movimiento database table.
 * 
 */
public class Movimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	private MovimientoPK id;

	private String hora;

	private BigDecimal valor;

	//bi-directional many-to-one association to TipoMovimiento
	private TipoMovimiento tipoMovimiento;

	public Movimiento() {
	}

	public MovimientoPK getId() {
		return this.id;
	}

	public void setId(MovimientoPK id) {
		this.id = id;
	}

	public String getHora() {
		return this.hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoMovimiento getTipoMovimiento() {
		return this.tipoMovimiento;
	}

	public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

}