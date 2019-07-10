package com.banco.service;

import java.sql.SQLException;

import com.banco.dao.ClienteDao;
import com.banco.dto.ConsultarDatosData;
import com.banco.dto.CrearClienteData;

public final class ClienteService {

	private static ClienteDao clienteDao;

	static {
		clienteDao = new ClienteDao();
	}

	public static void crearCliente(final CrearClienteData data) throws SQLException {
		final int idCliente = clienteDao.crearCliente(data.getCliente());
		final String responseServer = "Cliente creado con id " + idCliente;
		data.setMensaje(responseServer);
	}

	public static void consultarDatos(final ConsultarDatosData data) throws SQLException {
		clienteDao.consultarDatos(data);
	}

}
