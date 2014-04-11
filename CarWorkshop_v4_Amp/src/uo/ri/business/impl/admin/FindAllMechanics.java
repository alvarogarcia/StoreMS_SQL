package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MecanicosGateway;

import alb.util.jdbc.Jdbc;

public class FindAllMechanics {

	//private static String SQL_FIND_ALL_MECHANICS = "select id, nombre, apellidos from TMecanicos";

	/**
	 * Constructor de FindAllMechanichs
	 */
	public FindAllMechanics() {

	}

	/**
	 * @return Devuelve una lista con todos los mecánicos, cuyos mećanicos están metidos en un mapa
	 */
	public List<Map<String, Object>> execute() {
		Connection c = null;
		

		
		List<Map<String, Object>> mechanics;

		try {
			c = Jdbc.getConnection();
			
			MecanicosGateway mG = PersistenceFactory.getMecanicosGateway();

			mG.setConnection(c);
			mechanics = mG.findAll();
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}

		return mechanics;
	}
}
