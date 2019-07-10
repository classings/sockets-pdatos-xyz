package com.banco.service;

import java.sql.SQLException;

import com.banco.dao.ConsignarDao;
import com.banco.dto.ConsignarData;

public final class ConsignarService {
	
	private static ConsignarDao consignarDao;
	
	static {
		consignarDao = new ConsignarDao();
	}

	public static void consignar(final ConsignarData data) throws SQLException {
		consignarDao.realizarConsignacion(data);
	}
}
