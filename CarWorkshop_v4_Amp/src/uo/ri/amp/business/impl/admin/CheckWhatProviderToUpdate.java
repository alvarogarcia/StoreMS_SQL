package uo.ri.amp.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.ProvidersGateway;

import alb.util.jdbc.Jdbc;

/**
 * @author Álvaro García
 * 
 */
public class CheckWhatProviderToUpdate {
	String nameProviderBeforeUpdating;

	/**
	 * Constructor
	 * 
	 * @param nameProviderBeforeUpdating
	 *            Nombre por el que buscar el proveedor
	 */
	public CheckWhatProviderToUpdate(String nameProviderBeforeUpdating) {
		this.nameProviderBeforeUpdating = nameProviderBeforeUpdating;
	}

	/**
	 * Establece la conexión y a través del gateway de persistencia que crea le
	 * pasa la conexión y llama al método persistente que comprueba cuantos
	 * proveedores tienen dicho nombre
	 * 
	 * @return Lista de mapas, cada uno de ellos con los proveedores de nombre
	 *         dado
	 */
	public List<Map<String, Object>> execute() {
		List<Map<String, Object>> lista = new ArrayList<Map<String, Object>>();
		Connection c = null;

		try {
			c = Jdbc.getConnection();
			c.setAutoCommit(false);
			ProvidersGateway pG = PersistenceFactory.getProvidersGateway();
			pG.setConnection(c);
			lista = pG.checkWhatProviderToUpdate(nameProviderBeforeUpdating);
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}

		return lista;

	}

}
