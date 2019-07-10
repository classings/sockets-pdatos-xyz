package com.banco.dao;

import java.sql.SQLException;
import java.sql.Statement;

import com.banco.dto.ConsignarData;

public class ConsignarDao extends GenericDao{
	
	public void realizarConsignacion(final ConsignarData data) throws SQLException {
		int idCliente = data.getIdDocumento();
		double montoConsignar = data.getMonto();
		if (clienteNoExiste(idCliente)) {
			data.setMensaje(CLIENTE_NO_EXISTE);
			return;
		}
		int idMovimiento = registrarMovimiento(montoConsignar);
		registrarConsigancion(idCliente, montoConsignar, idMovimiento);
		data.setMensaje(MENSAJE_CONSIGNACION_EXITOSA); 
		return; 
	}

	private boolean clienteNoExiste(int idCliente) throws SQLException {
		this.sqlStatement = "Select c.id_cliente FROM db_banco.cliente c WHERE c.id_documento = ?";
		this.preparedStatement = connection.prepareStatement(sqlStatement);
		this.preparedStatement.setInt(1, idCliente);
		this.resultSet = preparedStatement.executeQuery();
		return !resultSet.next();
	}
	
	private int registrarMovimiento(double montoConsignar) throws SQLException {
		int movimientoId = 0;

		this.sqlStatement = "INSERT INTO db_banco.movimiento (`valor`, `id_tipo_mov`) VALUES (?, 3)";
		this.preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
		this.preparedStatement.setDouble(1,montoConsignar);
		int rowAffected = this.preparedStatement.executeUpdate();
		if (rowAffected == 1) {
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next())
				movimientoId = resultSet.getInt(1);
		}
		return movimientoId;
	}
	
	private int registrarConsigancion(int idCliente, double montoRetiro, int idMovimiento) throws SQLException {
		this.sqlStatement = "INSERT INTO db_banco.saldo (`id_saldo`, `ingreso`,`id_documento`,`id_movimiento`) VALUES (?, ?, ?, ?)";
		this.preparedStatement = connection.prepareStatement(sqlStatement);
		this.preparedStatement.setInt(1,idMovimiento);
		this.preparedStatement.setDouble(2,montoRetiro);
		this.preparedStatement.setInt(3,idCliente);
		this.preparedStatement.setInt(4,idMovimiento);
		return this.preparedStatement.executeUpdate();		
	}
}
