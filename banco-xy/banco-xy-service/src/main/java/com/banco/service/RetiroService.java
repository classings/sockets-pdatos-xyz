package com.banco.service;

import java.sql.SQLException;

import com.banco.dao.RetiroDao;
import com.banco.dto.RetirarData;

public final class RetiroService {
	
	private static RetiroDao retiroDao;
	
	static {
		retiroDao = new RetiroDao();
	}

	public static void retirar(final RetirarData data) throws SQLException {
		retiroDao.realizarRetiro(data);	
	}
}
