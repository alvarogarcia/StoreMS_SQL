package uo.ri.amp.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.ProvidersGateway;
import alb.util.jdbc.Jdbc;

public class ListAllProviders {

	/**
	 * Constructor sin parámetros
	 */
	public ListAllProviders() {
	}

	/**
	 * Crea la conexión con la base de datos y un gateway de Proveedores a
	 * través del cuál le pasa la conexión y llama al método persistente que
	 * encuentra los proveedores.
	 * 
	 * @return Lista de mapas, cada uno de ellos con un la información de un
	 *         proveedor
	 */
	public List<Map<String, Object>> execute() {
		Connection c = null;
		List<Map<String, Object>> proveedores;

		try {
			c = Jdbc.getConnection();
			c.setAutoCommit(false);
			ProvidersGateway pG = PersistenceFactory.getProvidersGateway();

			pG.setConnection(c);
			proveedores = pG.listAllProviders();
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}

		return proveedores;

	}
}
