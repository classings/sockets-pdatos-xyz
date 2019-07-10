package com.banco.dao;

import java.sql.SQLException;
import java.sql.Statement;

import com.banco.dto.RetirarData;

public class RetiroDao extends GenericDao{
	
	public void realizarRetiro(final RetirarData data) throws SQLException {
		int idCliente = data.getIdDocumento();
		double montoRetiro = data.getMonto();
		if (clienteNoExiste(idCliente)) {
			data.setMensaje(CLIENTE_NO_EXISTE);
			return;
		}
		double saldo = consultarSaldo(idCliente);
		if (saldo >= montoRetiro) {
			int idMovimiento = registrarMovimiento(montoRetiro);
			registrarSaldo(idCliente, montoRetiro, idMovimiento);
			data.setMensaje(MENSAJE_RETIRO_EXITOSO); 
			return; 
		}
		data.setMensaje(MENSAJE_RETIRO_INSUFICIENTE);
		return;
	}

	private boolean clienteNoExiste(int idCliente) throws SQLException {
		this.sqlStatement = "Select c.id_cliente FROM db_banco.cliente c WHERE c.id_documento = ?";
		this.preparedStatement = connection.prepareStatement(sqlStatement);
		this.preparedStatement.setInt(1, idCliente);
		this.resultSet = preparedStatement.executeQuery();
		return !resultSet.next();
	}
	
	private double consultarSaldo(int idCliente) throws SQLException {
		this.sqlStatement = "SELECT (sum(s.ingreso)-sum(s.egreso))  as 'Saldo'  \n" + 
				"FROM db_banco.saldo s \n" + 
				"WHERE s.id_documento = ?";
		this.preparedStatement = connection.prepareStatement(sqlStatement);
		this.preparedStatement.setInt(1, idCliente);
		this.resultSet = preparedStatement.executeQuery();
		
		double saldo = -1;
		
		if (resultSet.next()) {
	        saldo = resultSet.getDouble(1);
	        System.out.println("saldo: "+ saldo);
		}
		
		return saldo;
	}
	
	private int registrarMovimiento(double montoRetiro) throws SQLException {
		int movimientoId = 0;

		this.sqlStatement = "INSERT INTO db_banco.movimiento (`valor`) VALUES (?)";
		this.preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
		this.preparedStatement.setDouble(1,montoRetiro);
		int rowAffected = this.preparedStatement.executeUpdate();
		if (rowAffected == 1) {
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next())
				movimientoId = resultSet.getInt(1);
		}
		return movimientoId;
	}
	
	private int registrarSaldo(int idCliente, double montoRetiro, int idMovimiento) throws SQLException {
		this.sqlStatement = "INSERT INTO db_banco.saldo (`id_saldo`, `egreso`,`id_documento`,`id_movimiento`) VALUES (?, ?, ?, ?)";
		this.preparedStatement = connection.prepareStatement(sqlStatement);
		this.preparedStatement.setInt(1,idMovimiento);
		this.preparedStatement.setDouble(2,montoRetiro);
		this.preparedStatement.setInt(3,idCliente);
		this.preparedStatement.setInt(4,idMovimiento);
		return this.preparedStatement.executeUpdate();		
	}
}
