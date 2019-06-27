package co.xyz.model;

import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the tipo_movimiento database table.
 * 
 */
public class TipoMovimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	private byte idTipoMovimiento;

	private String descripcion;

	//bi-directional many-to-one association to Movimiento
	private List<Movimiento> movimientos;

	public TipoMovimiento() {
	}

	public byte getIdTipoMovimiento() {
		return this.idTipoMovimiento;
	}

	public void setIdTipoMovimiento(byte idTipoMovimiento) {
		this.idTipoMovimiento = idTipoMovimiento;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Movimiento> getMovimientos() {
		return this.movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public Movimiento addMovimiento(Movimiento movimiento) {
		getMovimientos().add(movimiento);
		movimiento.setTipoMovimiento(this);

		return movimiento;
	}

	public Movimiento removeMovimiento(Movimiento movimiento) {
		getMovimientos().remove(movimiento);
		movimiento.setTipoMovimiento(null);

		return movimiento;
	}

}