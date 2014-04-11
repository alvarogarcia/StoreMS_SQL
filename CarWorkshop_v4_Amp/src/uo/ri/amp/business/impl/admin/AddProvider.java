package uo.ri.amp.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.ProvidersGateway;

import alb.util.jdbc.Jdbc;

/**
 * @author �lvaro Garc�a
 * 
 */
public class AddProvider {

	String providerName;

	/**
	 * Constructor de la clase que a�ade un proveedor
	 * 
	 * @param providerName
	 *            Nombre del proveedor
	 */
	public AddProvider(String providerName) {

		this.providerName = providerName;

	}

	/**
	 * Crea la conexi�n con la base de datos, el gateaway a partir de la
	 * factor�a y a trav�s del gateway establece la conexi�n y llama al m�todo
	 * persistente que a�ade el proveedor
	 */
	public void execute() {
		Connection c = null;

		try {
			c = Jdbc.getConnection();
			c.setAutoCommit(false);
			ProvidersGateway pG = PersistenceFactory.getProvidersGateway();

			pG.setConnection(c);
			pG.addProvider(providerName);
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}
	}

}
