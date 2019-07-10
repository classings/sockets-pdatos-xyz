package com.banco.dao;

import java.sql.SQLException;
import java.sql.Statement;

import com.banco.dto.ClienteData;
import com.banco.dto.ConsultarDatosData;
import com.banco.model.Cliente;


public class ClienteDao extends GenericDao{

	public int crearCliente(final Cliente cliente) throws SQLException {
		int clienteId = 0;

		this.sqlStatement = "INSERT INTO db_banco.cliente VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		this.preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
		this.preparedStatement.setInt(1, 0);
		this.preparedStatement.setInt(2, cliente.getIdDocumento());
		this.preparedStatement.setByte(3, cliente.getTipoDocumento().getIdTipoDocumento());
		this.preparedStatement.setString(4, cliente.getNombres());
		this.preparedStatement.setString(5, cliente.getApellidos());
		this.preparedStatement.setString(6, cliente.getEmail());
		this.preparedStatement.setInt(7, cliente.getTelefono());
		this.preparedStatement.setInt(8, cliente.getCiudad().getIdCiudades());
		int rowAffected = this.preparedStatement.executeUpdate();
		if (rowAffected == 1) {
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next())
				clienteId = resultSet.getInt(1);
		}
		return clienteId;
	}

	public void consultarDatos(ConsultarDatosData data) throws SQLException {
		int idCliente = data.getIdDocumento();

		this.sqlStatement = "SELECT c.id_documento as `documento`, td.descripcion, c.nombres, c.apellidos, c.email, c.telefono, ci.nombre as `ciudad`, \n" + 
				"	(SELECT (sum(s.ingreso)-sum(s.egreso))\n" + 
				"	FROM db_banco.saldo s \n" + 
				"	WHERE s.id_documento = ? ) 'saldo'  \n" + 
				"FROM db_banco.cliente c\n" + 
				"	INNER JOIN db_banco.tipo_documento td \n" + 
				"		ON c.id_tipo_documento = td.id_tipo_documento\n" + 
				"	INNER JOIN db_banco.ciudad ci \n" + 
				"		ON ci.id_ciudades = c.id_ciudad\n" + 
				"WHERE c.id_documento = ?";
		this.preparedStatement = connection.prepareStatement(sqlStatement);
		this.preparedStatement.setInt(1, idCliente);
		this.preparedStatement.setInt(2, idCliente);
		this.resultSet = preparedStatement.executeQuery();
		
		
		if (resultSet.next()) {
			int documento = resultSet.getInt(1);
			String descripcion = resultSet.getString(2);
			String nombres = resultSet.getString(3);
			String apellidos = resultSet.getString(4);
	        String email = resultSet.getString(5);
	        int telefono = resultSet.getInt(6);
			String ciudad = resultSet.getString(7);
			double saldo = resultSet.getDouble(8);
			final ClienteData clienteData = new ClienteData(documento, descripcion, nombres, 
					apellidos, email, telefono, ciudad, saldo);
			data.setClienteData(clienteData);
			data.setMensaje(CLIENTE_EXISTE);
			return;
		} else {
			data.setMensaje(CLIENTE_NO_EXISTE);
			throw new SQLException(CLIENTE_NO_EXISTE);
		}
	}
	
}
