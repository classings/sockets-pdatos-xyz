package com.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.banco.db.SqlConnection;

public class GenericDao {

	protected final String CLIENTE_NO_EXISTE = "Cliente no existe, intente de nuevo.";
	protected final String CLIENTE_EXISTE = "\n\nSiguen los datos del cliente consultado:\n";
	protected final String MENSAJE_RETIRO_EXITOSO = "Retiro exitos. Tome su dinero.";
	protected final String MENSAJE_CONSIGNACION_EXITOSA = "Consignacion exitosa.";
	protected final String MENSAJE_RETIRO_INSUFICIENTE = "Fondos insuficientes.";
	protected PreparedStatement preparedStatement;
	protected Statement statement;
	protected ResultSet resultSet;
	protected static Connection connection;
	protected String sqlStatement;
	
	static {
		connection = SqlConnection.getConnection();
	}
}
