package co.xyz.model;

import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the pais database table.
 * 
 */
public class Pais implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idPais;

	private String nombre;

	//bi-directional many-to-one association to Ciudad
	private List<Ciudad> ciudads;

	public Pais() {
	}

	public int getIdPais() {
		return this.idPais;
	}

	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Ciudad> getCiudads() {
		return this.ciudads;
	}

	public void setCiudads(List<Ciudad> ciudads) {
		this.ciudads = ciudads;
	}

	public Ciudad addCiudad(Ciudad ciudad) {
		getCiudads().add(ciudad);
		ciudad.setPai(this);

		return ciudad;
	}

	public Ciudad removeCiudad(Ciudad ciudad) {
		getCiudads().remove(ciudad);
		ciudad.setPai(null);

		return ciudad;
	}

}