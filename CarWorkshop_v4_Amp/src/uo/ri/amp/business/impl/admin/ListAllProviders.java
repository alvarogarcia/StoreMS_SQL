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
	 * Constructor sin par�metros
	 */
	public ListAllProviders() {
	}

	/**
	 * Crea la conexi�n con la base de datos y un gateway de Proveedores a
	 * trav�s del cu�l le pasa la conexi�n y llama al m�todo persistente que
	 * encuentra los proveedores.
	 * 
	 * @return Lista de mapas, cada uno de ellos con un la informaci�n de un
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
