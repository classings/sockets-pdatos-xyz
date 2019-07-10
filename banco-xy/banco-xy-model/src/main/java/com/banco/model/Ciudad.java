package com.banco.model;

import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the ciudad database table.
 * 
 */
public class Ciudad implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idCiudades;

	private String nombre;

	//bi-directional many-to-one association to Pais
	private Pais pai;

	//bi-directional many-to-one association to Cliente
	private List<Cliente> clientes;

	public Ciudad() {
	}

	public Ciudad(int idCiudades) {
		this.idCiudades = idCiudades;
	}

	public int getIdCiudades() {
		return this.idCiudades;
	}

	public void setIdCiudades(int idCiudades) {
		this.idCiudades = idCiudades;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Pais getPai() {
		return this.pai;
	}

	public void setPai(Pais pai) {
		this.pai = pai;
	}

	public List<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente addCliente(Cliente cliente) {
		getClientes().add(cliente);
		cliente.setCiudad(this);

		return cliente;
	}

	public Cliente removeCliente(Cliente cliente) {
		getClientes().remove(cliente);
		cliente.setCiudad(null);

		return cliente;
	}

}