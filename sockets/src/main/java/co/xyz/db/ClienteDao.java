package co.xyz.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import co.xyz.model.Cliente;

public class ClienteDao {

	private PreparedStatement preparedStatement;
	private Statement statement;
	private ResultSet resultSet;
	private Connection connection;
	private String sqlStatement;

	public ClienteDao(final Connection connection) {
		this.connection = connection;
	}

	public int crearCliente(Cliente cliente) throws SQLException {
		int candidateId = 0;

		this.sqlStatement = "INSERT INTO cliente VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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
				candidateId = resultSet.getInt(1);
		}
		return candidateId;
	}

}
