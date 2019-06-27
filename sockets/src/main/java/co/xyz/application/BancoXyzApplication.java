package co.xyz.application;

import java.sql.Connection;
import java.sql.SQLException;

import co.xyz.db.ClienteDao;
import co.xyz.db.SqlConnection;
import co.xyz.enums.TipoDocumentoEnum;
import co.xyz.model.Ciudad;
import co.xyz.model.Cliente;
import co.xyz.model.TipoDocumento;

public class BancoXyzApplication {

	public static void main(String[] args) {

		Connection conn = SqlConnection.getConnection();
		try {
			TipoDocumento documento = new TipoDocumento(TipoDocumentoEnum.CEDULA.getCodigo(), 
					TipoDocumentoEnum.CEDULA.getDescripcion());
			Cliente cliente = new Cliente(1015402102,"Rios","juan@gmail.com","Juan2"
					,64156182,new Ciudad(2),documento);
			int id = new ClienteDao(conn).crearCliente(cliente);
			System.out.println("Creado !! id: " + id);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
