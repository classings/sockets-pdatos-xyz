package co.xyz.db;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalTime;

import java.sql.Connection;

public class SqlConnection {

	private static Connection connection;
	private final String NOMBRE_DB = "db_banco";
	private String url = "jdbc:mysql://localhost:3306/" + NOMBRE_DB;
	private final String USERNAME = "root";
	private final String PASSWORD = "root";

	private SqlConnection() {

		try {
			connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
			System.out.println(
					LocalTime.now() + "\t server \t\tConexion a BD exitosa: " + NOMBRE_DB + " User: " + USERNAME);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			if (connection == null || connection.isClosed())
				new SqlConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static void closeConnection() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
